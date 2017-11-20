package Model;

import java.util.ArrayList;

public class GieldaSurowcow extends Gielda {

    private ArrayList<Surowiec> surowce = new ArrayList<Surowiec>();

    public GieldaSurowcow(String nazwa, String kraj, String miasto, String adresSiedziby, Waluta waluta) {
        super(nazwa, kraj, miasto, adresSiedziby, waluta);
    }

    public ArrayList<Surowiec> getSurowce() {
        return surowce;
    }

    public void setSurowce(ArrayList<Surowiec> surowce) {
        this.surowce = surowce;
    }

    public void dodajSurowiec(Surowiec surowiec) {
        surowce.add(surowiec);
    }
}
