package Repository;


import Infrastructure.Ekonomia;
import Model.Surowiec;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa zawierająca przykładowe surowce
 */
public class Surowce {

    private ArrayList<Surowiec> surowce = new ArrayList<>();

    public Surowce() {

        Surowiec surowiec = new Surowiec("Zloto", "uncja", 4412.5);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);

        surowiec = new Surowiec("Srebro", "uncja", 59.64);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);

        surowiec = new Surowiec("Platyna", "uncja", 3517.5);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);


        surowiec = new Surowiec("Ropa", "barylka", 193.32);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);

        surowiec = new Surowiec("Miedź", "tona", 2272.9);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);
    }

    public ArrayList<Surowiec> getSurowce() {
        return surowce;
    }

    public Surowiec getSurowiec() {
        Random rand = new Random();

        boolean unikalne;
        Surowiec surowiecLosowy;
        do {
            unikalne = true;
            surowiecLosowy = surowce.get(rand.nextInt(surowce.size()));
            for (Surowiec surowiec : Ekonomia.getAktywa().getSurowce()) {
                if (surowiecLosowy.getNazwa().equals(surowiec.getNazwa())) {
                    unikalne = false;
                    break;
                }
            }
        } while (!unikalne);
        return surowiecLosowy;
    }
}


