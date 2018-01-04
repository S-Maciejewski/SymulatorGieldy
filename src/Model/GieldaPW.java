package Model;

import java.util.ArrayList;

public class GieldaPW extends Gielda {

    private ArrayList<Indeks> indeksy;

    public GieldaPW(String nazwa, String kraj, String miasto, String adresSiedziby, double marza) {
        super(nazwa, kraj, miasto, adresSiedziby, marza);
        indeksy = new ArrayList<Indeks>();
    }

    public ArrayList<Indeks> getIndeksy() {
        return indeksy;
    }

    public void dodajIndeks(Indeks indeks) {
        indeksy.add(indeks);
    }
}
