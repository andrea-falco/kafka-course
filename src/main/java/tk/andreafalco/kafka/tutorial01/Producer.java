package tk.andreafalco.kafka.tutorial01;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import tk.andreafalco.kafka.Constants;

import java.util.Properties;

public class Producer {

    private static final String TOPIC = "test-t01";

    public static void main(String[] args) {

        /*
            # We are sending string data to "test-t01" topic

            1) Create the topic:
            >> kafka-topics --bootstrap-server localhost:9092 --topic test-t01 --partitions 3 --create

            2) Read with a consumer
            >> kafka-console-consumer --bootstrap-server localhost:9092 --topic test-t01
        */

        // Create producer properties
        // (https://kafka.apache.org/documentation/#producerconfigs)
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.LOCAL_KAFKA_HOST);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // Create a producer record
        String valueToSend = "Data";
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, valueToSend);

        // Send data
        producer.send(record);

        // Flush and close producer
        producer.flush();
        producer.close();
    }

}
