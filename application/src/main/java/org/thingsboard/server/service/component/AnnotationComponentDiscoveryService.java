/**
 * Copyright © 2016-2017 The Thingsboard Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thingsboard.server.service.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thingsboard.server.common.data.plugin.ComponentDescriptor;
import org.thingsboard.server.common.data.plugin.ComponentScope;
import org.thingsboard.server.common.data.plugin.ComponentType;
import org.thingsboard.server.common.msg.computation.ComputationActionCompiled;
import org.thingsboard.server.dao.component.ComponentDescriptorService;
import org.thingsboard.server.extensions.api.component.*;
import org.thingsboard.server.service.computation.ComputationDiscoveryService;

import javax.annotation.PostConstruct;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
public class AnnotationComponentDiscoveryService implements ComponentDiscoveryService {

    @Value("${plugins.scan_packages}")
    private String[] scanPackages;

    @Autowired
    private Environment environment;

    @Autowired
    private ComponentDescriptorService componentDescriptorService;

    @Autowired
    private ComputationDiscoveryService computationDiscoveryService;

    private Map<String, ComponentDescriptor> components = new HashMap<>();

    private Map<ComponentType, List<ComponentDescriptor>> componentsMap = new HashMap<>();

    private ObjectMapper mapper = new ObjectMapper();

    private boolean isInstall() {
        return environment.acceptsProfiles("install");
    }

    @PostConstruct
    public void init() {
        if (!isInstall()) {
            discoverComponents();
        }
    }

    private void registerComponents(ComponentType type, Class<? extends Annotation> annotation) {
        List<ComponentDescriptor> components = persist(getBeanDefinitions(annotation), type);
        componentsMap.put(type, components);
        registerComponents(components);
    }

    private void registerComponents(Collection<ComponentDescriptor> comps) {
        comps.forEach(c -> components.put(c.getClazz(), c));
    }

    private List<ComponentDescriptor> persist(Set<BeanDefinition> filterDefs, ComponentType type) {
        List<ComponentDescriptor> result = new ArrayList<>();
        for (BeanDefinition def : filterDefs) {
            ComponentDescriptor scannedComponent = new ComponentDescriptor();
            String clazzName = def.getBeanClassName();
            try {
                scannedComponent.setType(type);
                Class<?> clazz = Class.forName(clazzName);
                String descriptorResourceName;
                switch (type) {
                    case FILTER:
                        Filter filterAnnotation = clazz.getAnnotation(Filter.class);
                        scannedComponent.setName(filterAnnotation.name());
                        scannedComponent.setScope(filterAnnotation.scope());
                        descriptorResourceName = filterAnnotation.descriptor();
                        break;
                    case PROCESSOR:
                        Processor processorAnnotation = clazz.getAnnotation(Processor.class);
                        scannedComponent.setName(processorAnnotation.name());
                        scannedComponent.setScope(processorAnnotation.scope());
                        descriptorResourceName = processorAnnotation.descriptor();
                        break;
                    case ACTION:
                        Action actionAnnotation = clazz.getAnnotation(Action.class);
                        scannedComponent.setName(actionAnnotation.name());
                        scannedComponent.setScope(actionAnnotation.scope());
                        descriptorResourceName = actionAnnotation.descriptor();
                        break;
                    case PLUGIN:
                        Plugin pluginAnnotation = clazz.getAnnotation(Plugin.class);
                        scannedComponent.setName(pluginAnnotation.name());
                        scannedComponent.setScope(pluginAnnotation.scope());
                        descriptorResourceName = pluginAnnotation.descriptor();
                        for (Class<?> actionClazz : pluginAnnotation.actions()) {
                            ComponentDescriptor actionComponent = getComponent(actionClazz.getName())
                                    .orElseThrow(() -> {
                                        log.error("Can't initialize plugin {}, due to missing action {}!", def.getBeanClassName(), actionClazz.getName());
                                        return new ClassNotFoundException("Action: " + actionClazz.getName() + "is missing!");
                                    });
                            if (actionComponent.getType() != ComponentType.ACTION) {
                                log.error("Plugin {} action {} has wrong component type!", def.getBeanClassName(), actionClazz.getName(), actionComponent.getType());
                                throw new RuntimeException("Plugin " + def.getBeanClassName() + "action " + actionClazz.getName() + " has wrong component type!");
                            }
                        }
                        scannedComponent.setActions(Arrays.stream(pluginAnnotation.actions()).map(action -> action.getName()).collect(Collectors.joining(",")));
                        break;
                    default:
                        throw new RuntimeException(type + " is not supported yet!");
                }
                scannedComponent.setConfigurationDescriptor(mapper.readTree(
                        Resources.toString(Resources.getResource(descriptorResourceName), Charsets.UTF_8)));
                scannedComponent.setClazz(clazzName);
                log.info("Processing scanned component: {}", scannedComponent);
            } catch (Exception e) {
                log.error("Can't initialize component {}, due to {}", def.getBeanClassName(), e.getMessage(), e);
                throw new RuntimeException(e);
            }
            ComponentDescriptor persistedComponent = componentDescriptorService.findByClazz(clazzName);
            if (persistedComponent == null) {
                log.info("Persisting new component: {}", scannedComponent);
                scannedComponent = componentDescriptorService.saveComponent(scannedComponent);
            } else if (scannedComponent.equals(persistedComponent)) {
                log.info("Component is already persisted: {}", persistedComponent);
                scannedComponent = persistedComponent;
            } else {
                log.info("Component {} will be updated to {}", persistedComponent, scannedComponent);
                componentDescriptorService.deleteByClazz(persistedComponent.getClazz());
                scannedComponent.setId(persistedComponent.getId());
                scannedComponent = componentDescriptorService.saveComponent(scannedComponent);
            }
            result.add(scannedComponent);
        }
        return result;
    }

    private Set<BeanDefinition> getBeanDefinitions(Class<? extends Annotation> componentType) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new AnnotationTypeFilter(componentType));
        Set<BeanDefinition> defs = new HashSet<>();
        for (String scanPackage : scanPackages) {
            defs.addAll(scanner.findCandidateComponents(scanPackage));
        }
        return defs;
    }

    @Override
    public void discoverComponents() {
        registerComponents(ComponentType.FILTER, Filter.class);

        registerComponents(ComponentType.PROCESSOR, Processor.class);

        registerComponents(ComponentType.ACTION, Action.class);

        registerComponents(ComponentType.PLUGIN, Plugin.class);

        log.info("Found following definitions: {}", components.values());
    }

    @Override
    public List<ComponentDescriptor> getComponents(ComponentType type) {
        return Collections.unmodifiableList(componentsMap.get(type));
    }

    @Override
    public Optional<ComponentDescriptor> getComponent(String clazz) {
        return Optional.ofNullable(components.get(clazz));
    }

    @Override
    public List<ComponentDescriptor> getPluginActions(String pluginClazz) {
        Optional<ComponentDescriptor> pluginOpt = getComponent(pluginClazz);
        log.warn("Components are {} and components map {}", new PrettyPrintingMap<>(components), new PrettyPrintingMap<>(componentsMap));
        if (pluginOpt.isPresent()) {
            ComponentDescriptor plugin = pluginOpt.get();
            if (ComponentType.PLUGIN != plugin.getType()) {
                throw new IllegalArgumentException(pluginClazz + " is not a plugin!");
            }
            List<ComponentDescriptor> result = new ArrayList<>();
            for (String action : plugin.getActions().split(",")) {
                getComponent(action).ifPresent(v -> result.add(v));
            }
            return result;
        } else {
            throw new IllegalArgumentException(pluginClazz + " is not a component!");
        }
    }

    @Override
    public void updateActionsForPlugin(List<ComputationActionCompiled> actions, String pluginClazz) {
        List<ComponentDescriptor> actionDescriptors = new ArrayList<>();
        for(ComputationActionCompiled action: actions){
            if(!getComponent(action.getActionClazz()).isPresent()) {
                log.warn("Component is not present {}", action.getActionClazz());
                ComponentDescriptor descriptor = new ComponentDescriptor();
                descriptor.setType(ComponentType.ACTION);
                descriptor.setClazz(action.getActionClazz());
                descriptor.setScope(ComponentScope.TENANT);
                descriptor.setName(action.getName());
                descriptor.setConfigurationDescriptor(action.getConfigurationDescriptor());
                ComponentDescriptor persistedComponent = componentDescriptorService.findByClazz(action.getActionClazz());
                if (persistedComponent == null) {
                    log.warn("Persisted component {}", action.getActionClazz());
                    descriptor = componentDescriptorService.saveComponent(descriptor);
                } else {
                    log.warn("Already persisted component found {}", persistedComponent);
                    descriptor = persistedComponent;
                }
                components.putIfAbsent(action.getActionClazz(), descriptor);
                actionDescriptors.add(descriptor);
            }
        }
        updateCachedComponentsMap(actionDescriptors);
        associateDescriptorWithPlugin(actionDescriptors, pluginClazz);
    }

    private void updateCachedComponentsMap(List<ComponentDescriptor> descriptors){
        if(!descriptors.isEmpty()) {
            List<ComponentDescriptor> componentDescriptors = componentsMap.get(ComponentType.ACTION);
            componentDescriptors.addAll(descriptors);
            componentsMap.put(ComponentType.ACTION, componentDescriptors);
        }
    }

    private void associateDescriptorWithPlugin(List<ComponentDescriptor> descriptors, String pluginClazz){
        if(!descriptors.isEmpty()) {
            Optional<ComponentDescriptor> plugin = getComponent(pluginClazz);
            if (plugin.isPresent()) {
                ComponentDescriptor pluginDescriptor = plugin.get();
                List<String> pluginActions = Arrays.asList(pluginDescriptor.getActions().split(","));
                String actionsToAdd =
                        descriptors.stream()
                        .filter(d -> !pluginActions.contains(d.getClazz()))
                        .map(ComponentDescriptor::getClazz).collect(Collectors.joining(","));
                if(!StringUtils.isEmpty(actionsToAdd)) {
                    pluginDescriptor.setActions(pluginDescriptor.getActions() + "," + actionsToAdd);
                    components.put(pluginClazz, pluginDescriptor);
                    List<ComponentDescriptor> plugins = componentsMap.get(ComponentType.PLUGIN).stream().map(o -> {
                        if (o.getClazz().equals(pluginDescriptor.getClazz())) {
                            return pluginDescriptor;
                        } else {
                            return o;
                        }
                    }).collect(toList());

                    componentsMap.put(ComponentType.PLUGIN, plugins);
                }
            }
        }
    }

    private class PrettyPrintingMap<K, V> {
        private Map<K, V> map;

        public PrettyPrintingMap(Map<K, V> map) {
            this.map = map;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            Iterator<Map.Entry<K, V>> iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<K, V> entry = iter.next();
                sb.append(entry.getKey());
                sb.append('=').append('"');
                sb.append(entry.getValue());
                sb.append('"');
                if (iter.hasNext()) {
                    sb.append(',').append(' ');
                }
            }
            return sb.toString();

        }
    }

}
