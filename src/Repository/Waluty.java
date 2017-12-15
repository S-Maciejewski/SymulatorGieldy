package Repository;

import Model.Waluta;

import java.util.ArrayList;

public class Waluty {
    private ArrayList<Waluta> waluty = new ArrayList<Waluta>();

    public Waluty() {
        ArrayList<String> nazwyKrajow = new ArrayList<>();

        Waluta waluta = new Waluta("PLN", 1);   //TODO Konstruktory surowców, walut i spółek
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

    }

    public ArrayList<Waluta> getWaluty() {
        return waluty;
    }
}
