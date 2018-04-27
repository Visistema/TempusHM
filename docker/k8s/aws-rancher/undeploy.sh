#!/bin/bash

# delete tempus pods
kubectl delete pod cassandra-setup

# delete tempus services
kubectl delete service  cassandra-headless \
                        nifi \
                        tb \
                        zk-cs \
                        zk-hs

# delete tempus stateful sets
kubectl delete statefulset  cassandra \
                            nifi \
                            tb \
                            zk

# delete all tempus storage classes
kubectl delete storageclass tempus

# delete tempus pvcs
kubectl delete pvc  cassandra-commitlog-cassandra-0 \
                    cassandra-data-cassandra-0 \
                    nifi-content-repo-dir-nifi-0 \
                    nifi-db-repo-dir-nifi-0 \
                    nifi-flowfile-repo-dir-nifi-0 \
                    nifi-log-dir-nifi-0 \
                    nifi-provenance-repo-dir-nifi-0 \
                    zk-datadir-zk-0 \
                    zk-datadir-zk-1 \
                    zk-datadir-zk-2

# delete all tempus configmaps
kubectl delete configmap tb-config

# delete all tempus pod disruption budgets
kubectl delete poddisruptionbudget  tb-budget \
                                    zk-pdb
