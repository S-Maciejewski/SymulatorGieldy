package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Waluta implements Serializable{

    private String nazwa;
    private ArrayList<String> listaKrajow;
    private double wartosc; //Wartość w głównej walucie
    private ArrayList<Double> historiaKursu = new ArrayList<>();

    public Waluta(String nazwa, double wartosc){
        this.nazwa = nazwa;
        this.wartosc = wartosc;
        listaKrajow = new ArrayList<>();
    }

    public String getNazwa() {
        return nazwa;
    }

    public ArrayList<String> getListaKrajow() {
        return listaKrajow;
    }

    public void setListaKrajow(ArrayList<String> listaKrajow) {
        this.listaKrajow = listaKrajow;
//        System.out.println(this.getListaKrajow());
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

    public ArrayList<Double> getHistoriaKursu() {
        return historiaKursu;
    }
}

