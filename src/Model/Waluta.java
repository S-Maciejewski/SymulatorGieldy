package Model;

import java.util.ArrayList;

public class Waluta {
    private String nazwa;
    private ArrayList<String> listaKrajow = new ArrayList<String>();

    public Waluta(String nazwa){
        this.nazwa = nazwa;
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
}
