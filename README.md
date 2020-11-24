# Apache Kafka Course
Some tutorials written while taking a [course](https://www.udemy.com/course/apache-kafka/) on Apache Kafka

## Tutorial :one: - Basic Producer
Simple producer that send data to a topic.

**Requirements**
- Create the topic:
```
kafka-topics --bootstrap-server localhost:9092 --topic test-t01 --partitions 3 --create
```
- Read data sent to the topic with a console consumer:
```
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-t01
```

## Tutorial :two: - Producer with a callback
Producer that send data to a topic, and execute a callback after.

**Requirements**
- Create the topic:
```
kafka-topics --bootstrap-server localhost:9092 --topic test-t02 --partitions 3 --create
```
- Read data sent to the topic with a console consumer:
```
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-t02
```

## Tutorial :three: - Producer with key
Producer that send data with a key to a topic.

**Requirements**
- Create the topic:
```
kafka-topics --bootstrap-server localhost:9092 --topic test-t03 --partitions 3 --create
```
- Read data sent to the topic with a console consumer:
```
kafka-console-consumer --bootstrap-server localhost:9092 --topic test-t03 --property print.key=true
```