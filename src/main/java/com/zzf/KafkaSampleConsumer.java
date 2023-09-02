package com.zzf;

import org.apache.kafka.clients.consumer.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static com.zzf.KafkaSample.loadConfig;


public class KafkaSampleConsumer {

    public static void main(String[] args) throws IOException {
        final Properties props = loadConfig("C:\\Users\\zfzhou\\IdeaProjects\\kafka-java-getting-started\\src\\main\\resources\\client.properties");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "kafka-java-getting-started");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("orders"));
//        consumer.subscribe(Arrays.asList("users"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> record : records) {
                System.out.printf("key = %s, value = %s%n", record.key(), record.value());
            }
        }
    }
}
