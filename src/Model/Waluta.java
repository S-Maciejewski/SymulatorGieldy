package Model;

import java.util.ArrayList;

public class Waluta {
    private String nazwa;
    private ArrayList<String> listaKrajow = new ArrayList<String>();
    private double cena; //cena wzgledem waluty glownej

    public Waluta(String nazwa, double cena){
        this.nazwa = nazwa;
        this.cena = cena;
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

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}
