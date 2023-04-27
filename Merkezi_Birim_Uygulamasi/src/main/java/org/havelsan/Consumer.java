package org.havelsan;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

public class Consumer {
    private final KafkaConsumer<String, String> kafkaConsumer;
    private final String topicName;

    public Consumer(String topicName) {
        this.topicName = topicName;
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka broker adresi
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group"); // Consumer grubunun ID'si
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // Key deserializer sınıfı
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName()); // Value deserializer sınıfı
        this.kafkaConsumer = new KafkaConsumer<>(properties); // Kafka consumer oluşturuluyor
        kafkaConsumer.subscribe(Collections.singletonList(topicName)); // Consumer, belirtilen topic'i dinleyecek şekilde abone oluyor
    }

    public List<String> getMessages(int n) {
        List<String> messageList = new ArrayList<>(); // Mesajların tutulacağı liste

        while (messageList.size() < n) { // Belirtilen sayıda mesaj alana kadar döngü çalışacak
            ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(100)); // Kafka broker'dan mesajları çekiyoruz (100 ms bekleme süresi)
            for (ConsumerRecord<String, String> record : records) { // Çekilen mesajlar üzerinde dönüyoruz
                messageList.add(record.value()); // Mesajın değerini (value) listeye ekliyoruz
            }
        }
        return messageList; // Listeyi döndürüyoruz
    }

    public void close() {
        kafkaConsumer.close(); // Consumer kapatılıyor
    }
}