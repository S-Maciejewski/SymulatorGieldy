package Repository;

import Infrastructure.Ekonomia;
import Model.Spolka;

import java.util.ArrayList;
import java.util.Random;


public class Nazwy {
    private ArrayList<String> nazwySpolek = new ArrayList<String>();
    private ArrayList<String> imiona = new ArrayList<String>();
    private ArrayList<String> nazwiska = new ArrayList<String>();
    private ArrayList<String> indeksy = new ArrayList<>();
    private Random random;

    public Nazwy() {
        random = new Random();

        nazwySpolek.add("Ga$ & Die$el");
        nazwySpolek.add("Steel Co.");
        nazwySpolek.add("Strawberry Computers");
        nazwySpolek.add("Spicy Burritos Co.");
        nazwySpolek.add("Milky Dairies Ltd.");
        nazwySpolek.add("People's Cars Corp.");
        nazwySpolek.add("Mining Co.");
        nazwySpolek.add("Macrosoft");
        nazwySpolek.add("Cruel Mortgage Bank");
        nazwySpolek.add("High Voltage Energy");
        nazwySpolek.add("High tensile beams Ltd.");
        nazwySpolek.add("Compressible flow water supply");

        imiona.add("Adam");
        imiona.add("Beatrice");
        imiona.add("Clementine");
        imiona.add("Dory");
        imiona.add("Emily");
        imiona.add("Francis");
        imiona.add("Glenn");
        imiona.add("Hans");
        imiona.add("Leonardo");
        imiona.add("Rafael");

        nazwiska.add("Klein");
        nazwiska.add("Lasagne");
        nazwiska.add("Mercredi");
        nazwiska.add("Doppelkupplungsgetriebe");
        nazwiska.add("O'Clock");
        nazwiska.add("Petitbeurre");
        nazwiska.add("Richtig");
        nazwiska.add("Salami");
        nazwiska.add("Toffifee");
        nazwiska.add("Rafaello");

        indeksy.add("Top IG");
        indeksy.add("Broke IG");
        indeksy.add("Indeks upadlych");
        indeksy.add("Biggest and richest");

    }

    public String getNazwaSpolki() {
        boolean unikalne;
        String nazwa;
        do {
            unikalne = true;
            nazwa = nazwySpolek.get(random.nextInt(nazwySpolek.size()));
            for (Spolka spolka : Ekonomia.getAktywa().getSpolki()) {
                if (nazwa.equals(spolka.getNazwa())) {
                    unikalne = false;
                    break;
                }
            }
        } while (!unikalne);
        return nazwa;
    }

    public String getImie() {
        return imiona.get(random.nextInt(imiona.size()));
    }

    public String getNazwisko() {
        return nazwiska.get(random.nextInt(nazwiska.size()));
    }

    public String getNazwaIndeksu() {
        return indeksy.get(random.nextInt(indeksy.size()));
    }
}
