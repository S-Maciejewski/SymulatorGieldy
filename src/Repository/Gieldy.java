package Repository;

import Model.*;
import Infrastructure.*;

import java.util.ArrayList;
import java.util.Random;

public class Gieldy {
    private ArrayList<Gielda> gieldy;

    public Gieldy() {
        gieldy = new ArrayList<>();

        Gielda gielda = new GieldaPW("GPW", "Polska", "Warszawa", "Ksiazeca 4",
                0.0037);
        gieldy.add(gielda);

        gielda = new GieldaPW("NewConnect", "Polska", "Warszawa", "Ksiazeca 4",
                0.0025);
        gieldy.add(gielda);

        gielda = new GieldaPW("NASDAQ", "USA", "Nowy Jork", "Broadway 165", 0.0064);
        gieldy.add(gielda);


        gielda = new GieldaWalut("Deutche Borse", "Niemcy", "Berlin",
                "Unter den Linden 36", 0.0052);
        gieldy.add(gielda);

        gielda = new GieldaWalut("STOXX", "Szwajcaria", "Zurych",
                "Manessestrasse 85", 0.0021);
        gieldy.add(gielda);


        gielda = new GieldaSurowcow("NYSE", "USA", "Nowy Jork",
                "11 Wall Street", 0.0040);
        gieldy.add(gielda);

        gielda = new GieldaSurowcow("Euronext", "Holandia", "Amsterdam",
                "Postbus 19163", 0.0083);
        gieldy.add(gielda);

    }

    public ArrayList<Gielda> getGieldy() {
        return gieldy;
    }

    public Gielda getGielda() {
        Random rand = new Random();
        Gielda gieldaLosowa;
        boolean zajeta;

        do{
            zajeta = false;
            gieldaLosowa = gieldy.get(rand.nextInt(gieldy.size()));
            for(Gielda gielda : gieldy){
                if(gielda.getNazwa().equals(gieldaLosowa.getNazwa()))
                    zajeta = true;
            }

        } while(!zajeta);

        return gieldaLosowa;
    }

}
