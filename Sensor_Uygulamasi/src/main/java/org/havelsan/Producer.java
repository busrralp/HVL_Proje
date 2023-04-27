package org.havelsan;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;
import java.util.Random;

public class Producer {
    private KafkaProducer<String, String> producer;
    // create instance of Random class
    Random rand = new Random();
    private int koordinat_x;
    private int koordinat_y;
    private double hedef_kerteriz;

    // Hedef koordinati hardcoded olarak verilmiştir.
    private int hedef_x_koordinat ;
    private int hedef_y_koordinat ;

    public Producer(int x, int y, int hedef_x, int hedef_y)
    {
        hedef_x_koordinat = hedef_x;
        hedef_y_koordinat = hedef_y;
        // Koordinatları ve Kafka sunucusu adresini ayarlar.
        this.koordinat_x = x;
        this.koordinat_y = y;
        String bootstrapServers = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // KafkaProducer nesnesini oluşturur.
        producer = new KafkaProducer<>(properties);
    }

    public void Kafkaya_Koordinat_Gonder()
    {
        hedef_kerteriz = Hedef_Kerterizi_Hesapla();
        // producerRecord oluşturulur.
        ProducerRecord<String, String> producerRecord =
                new ProducerRecord<>("havelsan", Integer.toString(koordinat_x) + "," + Integer.toString(koordinat_y) + "," + Double.toString(hedef_kerteriz));

        producer.send(producerRecord);
        producer.flush();
        producer.close();
    }

    public double Hedef_Kerterizi_Hesapla()
    {
        double y_eksen_farki = (hedef_y_koordinat - koordinat_y);
        double x_eksen_farki = (hedef_x_koordinat - koordinat_x);
        double kerteriz_degeri = Math.atan(y_eksen_farki / x_eksen_farki);

        return  Math.toDegrees(kerteriz_degeri);
    }

}

