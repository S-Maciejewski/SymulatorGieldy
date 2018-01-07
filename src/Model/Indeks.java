package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa, która impelemntuje indeks giełdowy. Jej obiekty mogą należeć jedynie do obiektu klasy GieldaPW jako
 * elementy tablicy indeksy
 */
public class Indeks implements Serializable{
    private String nazwa;
    private ArrayList<Spolka> spolki = new ArrayList<Spolka>();
    private double wartosc;

    private ArrayList<Double> historiaWartosci = new ArrayList<>();

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

    public void wyliczWartosc() {
        wartosc = 0;
        for (Spolka spolka : spolki) {
            wartosc += spolka.getKursAktualny() * spolka.getLiczbaAkcji();
        }
    }

    public double getWartosc() {
        return wartosc;
    }

    public ArrayList<Double> getHistoriaWartosci() {
        return historiaWartosci;
    }
}
