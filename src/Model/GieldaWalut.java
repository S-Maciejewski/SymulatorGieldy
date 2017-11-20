package Model;

import java.util.ArrayList;

public class GieldaWalut extends Gielda {

    private ArrayList<Waluta> waluty = new ArrayList<Waluta>();
    private ArrayList<Kurs> kursy = new ArrayList<Kurs>();

    public GieldaWalut(String nazwa, String kraj, String miasto, String adresSiedziby, Waluta waluta) {
        super(nazwa, kraj, miasto, adresSiedziby, waluta);
    }


    public ArrayList<Waluta> getWaluty() {
        return waluty;
    }

    public void setWaluty(ArrayList<Waluta> waluty) {
        this.waluty = waluty;
    }

    public ArrayList<Kurs> getKursy() {
        return kursy;
    }

    public void setKursy(ArrayList<Kurs> kursy) {
        this.kursy = kursy;
    }

    public void dodajWalute(Waluta waluta) {
        waluty.add(waluta);
    }

    public void dodajKurs(Kurs kurs) {
        kursy.add(kurs);
    }
}
