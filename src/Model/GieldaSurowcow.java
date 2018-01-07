package Model;

import java.util.ArrayList;

/**
 * Klasa, która reprezentuje giełdę surowców
 */
public class GieldaSurowcow extends Gielda {

    private ArrayList<Surowiec> surowce;

    public GieldaSurowcow(String nazwa, String kraj, String miasto, String adresSiedziby, double marza) {
        super(nazwa, kraj, miasto, adresSiedziby, marza);
        surowce = new ArrayList<Surowiec>();
    }

    public ArrayList<Surowiec> getSurowce() {
        return surowce;
    }

    public void dodajSurowiec(Surowiec surowiec) {
        surowce.add(surowiec);
    }
}
