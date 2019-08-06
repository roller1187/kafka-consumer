![OpenShift](https://developers.redhat.com/blog/wp-content/uploads/2018/10/Untitled-drawing-4.png)
# Acrostic Kafka Consumer

This purpose of this service is to:
  1. Listen to a Kafka topic and consume messages
  2. Produce an acrostic* map based on the message from the topic
  3. Send a request to a separate producer containing the acrostic output (The output will be posted into a separate UI topic)

---

## Instructions for deploying on OpenShift:
  1. Login to OpenShift:
```sh
oc login <openshift_cluster>
```
  2. Create a new project:
```sh
oc new-project kafka-$(oc whoami)
```
  3. Deploy the service using s2i (Source-2-Image). Don't forget to provide a Kafka topic:
```sh
oc new-app redhat-openjdk18-openshift:1.4~https://github.com/roller1187/kafka-consumer.git \
    --env KAFKA_BACKEND_TOPIC=my-topic-$(oc whoami) \
    --env KAFKA_UI_TOPIC=ui-topic-$(oc whoami) \
    --env SPRING_KAFKA_BOOTSTRAP_SERVERS=my-cluster-kafka-external-bootstrap.kafka-demo.svc.cluster.local:9094  
```

*Acrostic example:

![Acrostic](https://www.researchgate.net/profile/Andrew_Finch/publication/260593143/figure/fig3/AS:392472879484941@1470584234596/Acrostic-poem-Teaching-points-Spelling-Vocabulary-Dictionary-Holmes-Moulton-2001.png)

