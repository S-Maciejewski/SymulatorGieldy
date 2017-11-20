package Model;

import java.util.ArrayList;

public class Aktywa {
    private ArrayList<Spolka> spolki = new ArrayList<>();
    private ArrayList<Surowiec> surowce = new ArrayList<>();
    private ArrayList<Waluta> waluty = new ArrayList<>();


    public ArrayList<Waluta> getWaluty() {
        return waluty;
    }

    public void dodajWalute(Waluta waluta) {
        this.waluty.add(waluta);
    }

    public ArrayList<Surowiec> getSurowce() {
        return surowce;
    }

    public void dodajSurowiec(Surowiec surowiec) {
        this.surowce.add(surowiec);
    }

    public ArrayList<Spolka> getSpolki() {
        return spolki;
    }

    public void dodajSpolke(Spolka spolka) {
        this.spolki.add(spolka);
    }
}
