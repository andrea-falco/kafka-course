package tk.andreafalco.kafka.tutorial04;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.andreafalco.kafka.Constants;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    private static final String GROUP_ID = "tutorial-04";

    public static void main(String[] args) {

        /*
            # We are consuming string data from "test-t01", "test-t02", "test-t03" topics
        */

        // Create consumer properties
        // (https://kafka.apache.org/documentation/#consumerconfigs)
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, Constants.LOCAL_KAFKA_HOST);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");  // earliest -> From the beginning (or consumer offset, if present)
                                                                                    // latest   -> From now on

        // Create consumer
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        // Subscribe consumer to topic(s)
        consumer.subscribe(Arrays.asList(Constants.TOPIC_T01, Constants.TOPIC_T02, Constants.TOPIC_T03));

        // Poll for new data
        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, String> r : records) {
                log.info(String.format(
                        "Data received:\nTopic:\t\t%s\nKey:\t\t%s\nValue:\t\t%s\nPartition:\t%s\nOffset:\t\t%s",
                        r.topic(), r.key(), r.value(), r.partition(), r.offset()
                ));
            }
        }
    }

}
