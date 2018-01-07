package Model;

import java.util.ArrayList;

/**
 * Klasa, która reprezentuje giełdę walut
 */
public class GieldaWalut extends Gielda {

    private ArrayList<Waluta> waluty;

    public GieldaWalut(String nazwa, String kraj, String miasto, String adresSiedziby, double marza) {
        super(nazwa, kraj, miasto, adresSiedziby, marza);
        waluty = new ArrayList<>();
    }

    public ArrayList<Waluta> getWaluty() {
        return waluty;
    }

    public void dodajWalute(Waluta waluta) {
        waluty.add(waluta);
    }

}
