![Strimzi](https://developers.redhat.com/blog/wp-content/uploads/2018/05/strimzilogo_stacked_default_450px.png)

# Kafka Demo App

**Note:** the following instructions deploy AMQ Streams leveraging the Strimzi operator. Additionally, it deploys a Java application that leverages Kafka with a built-in consumer and producer. It is capable of sending messages to AMQ Streams using REST endpoints

## To install AMQ Streams:

1) git clone https://github.com/roller1187/amq-examples.git

2) cd ./amq-examples

3) oc login <cluster URL>

4) oc new-project kafka_demo

5) oc project kafka_demo

6) Run:

* oc adm policy add-cluster-role-to-user strimzi-cluster-operator-namespaced --serviceaccount strimzi-cluster-operator -n kafka_demo
* oc adm policy add-cluster-role-to-user strimzi-entity-operator --serviceaccount strimzi-cluster-operator -n kafka_demo
* oc adm policy add-cluster-role-to-user strimzi-topic-operator --serviceaccount strimzi-cluster-operator -n kafka_demo

7) oc apply -f kafka_demo/install/cluster-operator/

## To configure Kafka:

1) oc apply -f kafka_demo/setup/my-cluster.yaml

2) **Wait until all replicas of Kafka and Zookeeper are online**

3) oc apply -f kafka_demo/setup/my-topic.yaml

4) Create new app via Red Hat OpenJDK 8 S2i Template

* **Git URL**: https://github.com/roller1187/amq-examples.git

## Enjoy!
