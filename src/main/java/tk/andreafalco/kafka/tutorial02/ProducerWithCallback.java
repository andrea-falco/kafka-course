package tk.andreafalco.kafka.tutorial02;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.andreafalco.kafka.Constants;

import java.util.Properties;

public class ProducerWithCallback {

    private static final Logger log = LoggerFactory.getLogger(ProducerWithCallback.class);

    public static void main(String[] args) {

        /*
            # We are sending string data to "test-t01" topic, with a callback after

            1) Create the topic:
            >> kafka-topics --bootstrap-server localhost:9092 --topic test-t02 --partitions 3 --create

            2) Read with a consumer
            >> kafka-console-consumer --bootstrap-server localhost:9092 --topic test-t02
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
        ProducerRecord<String, String> record = new ProducerRecord<>(Constants.TOPIC_T02, valueToSend);

        // Send data and set callback
        producer.send(record, (recordMetadata, e) -> {
            // Execute every time a record is successfully sent or ane exception is thrown
            if (e == null) {
                // Record was successfully sent
                log.info(String.format(
                        "Data sent to:\nTopic:\t\t%s\nPartition:\t%s\nOffset:\t\t%s\nTimestamp:\t%s",
                        recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset(), recordMetadata.timestamp()
                ));
            } else {
                // Exception
                log.error("Error while producing!", e);
            }
        });

        // Flush and close producer
        producer.flush();
        producer.close();
    }

}
