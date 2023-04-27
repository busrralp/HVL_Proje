package org.havelsan;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Kafka Consumer sınıfından bir nesne oluşturuyoruz, topic olarak "havelsan" kullanıyoruz
        Consumer consumer = new Consumer("havelsan");

        // Kafka'dan 2 adet mesaj çekiyoruz
        List<String> messages = consumer.getMessages(2);

        // İki sensörden gelen mesajları ayırıyoruz
        String sensor_1_message = messages.get(0);
        String sensor_2_message = messages.get(1);

        // Ayrıştırılmış mesajları virgüle göre ayırıyoruz
        String[] splitted_sensor_1_message = sensor_1_message.split(",");
        String[] splitted_sensor_2_message = sensor_2_message.split(",");

        // Sensor 1'den gelen mesajdan gerekli bilgileri alıyoruz
        int sensor_1_x = Integer.parseInt(splitted_sensor_1_message[0]);
        int sensor_1_y = Integer.parseInt(splitted_sensor_1_message[1]);
        double sensor_1_hedef_kerteriz = Double.parseDouble(splitted_sensor_1_message[2]);

        // Sensor 2'den gelen mesajdan gerekli bilgileri alıyoruz
        int sensor_2_x = Integer.parseInt(splitted_sensor_2_message[0]);
        int sensor_2_y = Integer.parseInt(splitted_sensor_2_message[1]);
        double sensor_2_hedef_kerteriz = Double.parseDouble(splitted_sensor_2_message[2]);

        // Hedefin koordinatlarını bulmak için Hedefin_Koordinatlarini_Bul fonksiyonunu çağırıyoruz
        double[] hedef_koordinat_arr = Hedefin_Koordinatlarini_Bul(sensor_1_x, sensor_1_y, sensor_1_hedef_kerteriz, sensor_2_x, sensor_2_y, sensor_2_hedef_kerteriz);

        // Hedefin koordinatlarını ekrana yazdırıyoruz
        System.out.println("Hedef koordinat x: " + hedef_koordinat_arr[0] + "\nHedef koordinat y: " + hedef_koordinat_arr[1] );
    }

    // İki sensörden gelen mesajlardan hedefin koordinatlarını hesaplar
    public static double[] Hedefin_Koordinatlarini_Bul(int sensor_1_x, int sensor_1_y, double hedef_kerteriz_degeri_1,
                                                       int sensor_2_x, int sensor_2_y, double hedef_kerteriz_degeri_2)
    {
        double sensor_1_gore_hedef_kerteriz_tan_degeri = Math.tan(Math.toRadians(hedef_kerteriz_degeri_1));
        double sensor_2_gore_hedef_kerteriz_tan_degeri = Math.tan(Math.toRadians(hedef_kerteriz_degeri_2));

        double hedef_koordinat_x = ((sensor_1_gore_hedef_kerteriz_tan_degeri * sensor_1_x) - (sensor_2_gore_hedef_kerteriz_tan_degeri * sensor_2_x) + sensor_2_y - sensor_1_y) / (sensor_1_gore_hedef_kerteriz_tan_degeri - sensor_2_gore_hedef_kerteriz_tan_degeri);

        double hedef_koordinat_y = sensor_1_gore_hedef_kerteriz_tan_degeri * (hedef_koordinat_x - sensor_1_x) + sensor_1_y;

        return new double[]{hedef_koordinat_x, hedef_koordinat_y};
    }
}
