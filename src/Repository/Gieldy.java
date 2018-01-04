package Repository;

import Model.*;
import Infrastructure.*;

import java.util.ArrayList;
import java.util.Random;

public class Gieldy {   //TODO jedna lista by wszystkie złączyć
    private ArrayList<GieldaPW> gieldyPW;
    private ArrayList<GieldaWalut> gieldyWalut;
    private ArrayList<GieldaSurowcow> gieldySurowcow;

    public Gieldy() {
        gieldyPW = new ArrayList<>();

        GieldaPW gieldaPW = new GieldaPW("GPW", "Polska", "Warszawa", "Ksiazeca 4",
                0.0037);
        gieldyPW.add(gieldaPW);

        gieldaPW = new GieldaPW("NewConnect", "Polska", "Warszawa", "Ksiazeca 4",
                0.0025);
        gieldyPW.add(gieldaPW);

        gieldaPW = new GieldaPW("NASDAQ", "USA", "Nowy Jork", "Broadway 165", 0.0064);
        gieldyPW.add(gieldaPW);


        gieldyWalut = new ArrayList<>();

        GieldaWalut gieldaWalut = new GieldaWalut("Deutche Borse", "Niemcy", "Berlin",
                "Unter den Linden 36", 0.0052);
        gieldyWalut.add(gieldaWalut);

        gieldaWalut = new GieldaWalut("STOXX", "Szwajcaria", "Zurych",
                "Manessestrasse 85", 0.0021);
        gieldyWalut.add(gieldaWalut);


        gieldySurowcow = new ArrayList<>();

        GieldaSurowcow gieldaSurowcow = new GieldaSurowcow("NYSE", "USA", "Nowy Jork",
                "11 Wall Street", 0.0040);
        gieldySurowcow.add(gieldaSurowcow);

        gieldaSurowcow = new GieldaSurowcow("Euronext", "Holandia", "Amsterdam",
                "Postbus 19163", 0.0083);
        gieldySurowcow.add(gieldaSurowcow);

    }

    public Gielda getGielda() {
        Random rand = new Random();
        int randNum = rand.nextInt(3);
        Gielda gielda;

        switch (randNum) {
            case 0:
                gielda = gieldyPW.get(rand.nextInt(gieldyPW.size()));
                break;
            case 1:
                gielda = gieldySurowcow.get(rand.nextInt(gieldySurowcow.size()));
                break;
            case 2:
                gielda = gieldyWalut.get(rand.nextInt(gieldyWalut.size()));
                break;
            default:
                gielda = gieldyPW.get(rand.nextInt(gieldyPW.size()));
        }

        return gielda;
    }

}
