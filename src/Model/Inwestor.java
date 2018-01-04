package Model;

import Infrastructure.Ekonomia;

import java.util.Random;

public class Inwestor extends PodmiotInwestujacy {
    private String pesel;

    //private double budzet;

    public Inwestor(String imieInwestora, String nazwiskoInwestora, double budzet) {
        setImie(imieInwestora);
        setNazwisko(nazwiskoInwestora);
        setPortfel(new Portfel());
        setBudzet(budzet);

        //Pesel generowany automatycznie
        this.pesel = "";
        for (int i = 0; i < 11; i++) {
            this.pesel += rand.nextInt(10);
        }
        System.out.println("Pesel: " + pesel);

        setAgresja(rand.nextInt(49) + 1);
    }
}
