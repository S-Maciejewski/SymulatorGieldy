package Infrastructure;

import Model.*;

import java.io.*;
import java.util.ArrayList;

/**
 * Klasa odpowiedzialna za serializację i deserializację
 */
public class Zapis {
    private ObjectOutputStream out;

    /**
     * Metoda, która realizuje serializację danych z klasy Ekonomia do pliku zapis.txt
     */
    public void zapisz(){
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\Public\\Documents\\zapis.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie mozna stworzyc pliku zapisu");
        }

        try {
            out.writeObject(Ekonomia.getGieldy());
            out.writeObject(Ekonomia.getInwestorzy());
            out.writeObject(Ekonomia.getAktywa());
            out.writeObject(Ekonomia.getNrSesji());

            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Wystapil blad podczas zapisywania obiektu");
        }
    }

    /**
     * Metoda, która realizuje deserializację danych z pliku zapis.txt i aktualizację pól klasy Ekonomia
     */
    public void wczytaj(){
        try {
            ObjectInputStream in = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("C:\\Users\\Public\\Documents\\zapis.txt")));

            Main.ekonomia.setGieldy((ArrayList<Gielda>) in.readObject());
            Main.ekonomia.setInwestorzy((ArrayList<PodmiotInwestujacy>) in.readObject());
            Main.ekonomia.setAktywa((Aktywa)in.readObject());
            Main.ekonomia.setNrSesji((Integer) in.readObject());

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono pliku zapisu zapis.txt\n" +
                    "Sprawdz C:\\Users\\Public\\Documents");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nie udalo sie odczytac obiektu z pliku zapis.txt");
        }
    }
}
