package Model;

import java.util.ArrayList;

public class Indeks {
    private String nazwa;
    private ArrayList<Spolka> spolki = new ArrayList<Spolka>();

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {    //konstruktor?
        this.nazwa = nazwa;
    }

    public ArrayList<Spolka> getSpolki() {
        return spolki;
    }

    public void setSpolki(ArrayList<Spolka> spolki) {
        this.spolki = spolki;
    }

    public void dodajSpolke(Spolka spolka){
        spolki.add(spolka);
    }
}
