package Repository;

import Infrastructure.Ekonomia;
import Model.Waluta;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa zawierająca przykładowe waluty
 */
public class WalutyPrzykladowe {
    private ArrayList<Waluta> waluty = new ArrayList<Waluta>();

    public WalutyPrzykladowe() {
        ArrayList<String> nazwyKrajow = new ArrayList<>();

        Waluta waluta = new Waluta("PLN", 1);
        nazwyKrajow.add("Polska");
        waluta.setListaKrajow(nazwyKrajow);
        waluty.add(waluta);
        nazwyKrajow.clear();

        waluta = new Waluta("USD", 3.75);
        nazwyKrajow.add("Stany Zjednoczone");
        waluty.add(waluta);
        nazwyKrajow.clear();

        waluta = new Waluta("EUR", 4.25);
        nazwyKrajow.add("Francja");
        nazwyKrajow.add("Niemcy");
        nazwyKrajow.add("Hiszpania");
        waluta.setListaKrajow(nazwyKrajow);
        waluty.add(waluta);
        nazwyKrajow.clear();

        waluta = new Waluta("CHF", 3.625);
        nazwyKrajow.add("Szwajcaria");
        nazwyKrajow.add("Liechtenstein");
        waluta.setListaKrajow(nazwyKrajow);
        waluty.add(waluta);
        nazwyKrajow.clear();

        waluta = new Waluta("GBP", 5.205);
        nazwyKrajow.add("Wielka Brytania");
        waluta.setListaKrajow(nazwyKrajow);
        waluty.add(waluta);
        nazwyKrajow.clear();

        waluta = new Waluta("RUB", 0.061);
        nazwyKrajow.add("Rosja");
        nazwyKrajow.add("Abchazja");
        waluta.setListaKrajow(nazwyKrajow);
        waluty.add(waluta);
        nazwyKrajow.clear();

    }

    public ArrayList<Waluta> getWaluty() {
        return waluty;
    }

    public Waluta getWaluta() {  //Podaje losową walutę z bazy
        Random rand = new Random();

//        //TODO Unikalność walut
//        Waluta waluta;
//        do {
//            waluta = waluty.get(rand.nextInt(waluty.size()));
//        } while (Ekonomia.getAktywa().getWaluty().contains(waluta));
//
//        return waluta;

        boolean unikalne;
        Waluta walutaLosowa;
        do {
            unikalne = true;
            walutaLosowa = waluty.get(rand.nextInt(waluty.size()));
            for (Waluta waluta : Ekonomia.getAktywa().getWaluty()) {
                if (walutaLosowa.getNazwa().equals(waluta.getNazwa())) {
                    unikalne = false;
                    break;
                }
            }
        } while (!unikalne);
        return walutaLosowa;
    }
}
