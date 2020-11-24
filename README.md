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

## Tutorial :four: - Basic Consumer
Consumer that read data from topics created in the previous tutorials.

**Requirements**
- Run Consumer from the class:
```
tk.andreafalco.kafka.tutorial04.Consumer
```
- Produce data with a producer from the previous tutorials:
```
tk.andreafalco.kafka.tutorial01.Producer
tk.andreafalco.kafka.tutorial02.ProducerWithCallback
tk.andreafalco.kafka.tutorial03.ProducerWithKey
```