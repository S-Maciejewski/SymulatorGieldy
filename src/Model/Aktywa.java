package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Aktywa implements Serializable{
    private ArrayList<Spolka> spolki = new ArrayList<>();
    private ArrayList<Surowiec> surowce = new ArrayList<>();
    private ArrayList<Waluta> waluty = new ArrayList<>();
    private ArrayList<Fundusz> funduszeInwestycyjne = new ArrayList<>();

    public ArrayList<Waluta> getWaluty() {
        return waluty;
    }

    public void dodajWalute(Waluta waluta) {
        waluty.add(waluta);
    }

    public ArrayList<Surowiec> getSurowce() {
        return surowce;
    }

    public void dodajSurowiec(Surowiec surowiec) {
        surowce.add(surowiec);
    }

    public ArrayList<Spolka> getSpolki() {
        return spolki;
    }

    public void dodajSpolke(Spolka spolka) {
        spolki.add(spolka);
    }

    public ArrayList<Fundusz> getFunduszeInwestycyjne() {
        return funduszeInwestycyjne;
    }

    public void dodajFundusz(Fundusz fundusz) {
        funduszeInwestycyjne.add(fundusz);
    }

    public void removeSpolka(Spolka spolka) {
        spolki.remove(spolka);
    }

    public void removeWaluta(Waluta waluta) {
        waluty.remove(waluta);
    }

    public void removeSurowiec(Surowiec surowiec){
        surowce.remove(surowiec);
    }
}
