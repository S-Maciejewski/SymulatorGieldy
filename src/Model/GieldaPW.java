package Model;

import java.util.ArrayList;

public class GieldaPW extends Gielda {

    private ArrayList<Indeks> indeksy = new ArrayList<Indeks>();

    public GieldaPW(String nazwa, String kraj, String miasto, String adresSiedziby, Waluta waluta) {
        super(nazwa, kraj, miasto, adresSiedziby, waluta);
    }

    public ArrayList<Indeks> getIndeksy() {
        return indeksy;
    }

    public void setIndeksy(ArrayList<Indeks> indeksy) {
        this.indeksy = indeksy;
    }

    public void dodajIndeks(Indeks indeks) {
        indeksy.add(indeks);
    }
}
