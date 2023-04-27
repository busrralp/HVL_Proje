package org.havelsan;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Random rand = new Random();

        // 1000x1000 boyutunda bir alanda random olarak hedef koordinatları belirlenir.
        int hedef_koordinat_x = rand.nextInt(1000);
        int hedef_koordinat_y = rand.nextInt(1000);

        // 1000x1000 boyutunda bir alanda random olarak sensor 1 koordinatları belirlenir.
        int sensor_1_koordinat_x = rand.nextInt(1000);
        int sensor_1_koordinat_y = rand.nextInt(1000);

        // 1000x1000 boyutunda bir alanda random olarak sensor 2 koordinatları belirlenir.
        int sensor_2_koordinat_x = rand.nextInt(1000);
        int sensor_2_koordinat_y = rand.nextInt(1000);

        Producer sensor1 = new Producer(sensor_1_koordinat_x, sensor_1_koordinat_y, hedef_koordinat_x, hedef_koordinat_y);
        Producer sensor2 = new Producer(sensor_2_koordinat_x, sensor_2_koordinat_y, hedef_koordinat_x, hedef_koordinat_y);

        sensor1.Kafkaya_Koordinat_Gonder();
        sensor2.Kafkaya_Koordinat_Gonder();

        System.out.println("Hedef x koordinati: " + hedef_koordinat_x + "\nHedef y koordinati: " + hedef_koordinat_y + "\n\n" +
                           "Sensör 1 x koordinati: " + sensor_1_koordinat_x + "\nSensör 1 y koordinati: " + sensor_1_koordinat_y + "\n\n" +
                           "Sensör 2 x koordinati: " + sensor_2_koordinat_x + "\nSensör 2 y koordinati: " + sensor_2_koordinat_y + "\n\n");
    }
}