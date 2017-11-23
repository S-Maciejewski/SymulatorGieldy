package Model;

import java.util.ArrayList;

public class Waluta {
    private String nazwa;
    private ArrayList<String> listaKrajow = new ArrayList<String>();
    private double wartosc; //Wartość w głównej walucie giełdy

    public Waluta(String nazwa, double wartosc){
        this.nazwa = nazwa;
        this.wartosc = wartosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public ArrayList<String> getListaKrajow() {
        return listaKrajow;
    }

    public void setListaKrajow(ArrayList<String> listaKrajow) {
        this.listaKrajow = listaKrajow;
    }

    public void dodajKraj(String nazwaKraju) {
        this.listaKrajow.add(nazwaKraju);
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }
}

