package Repository;


import Model.Surowiec;

import java.util.ArrayList;
import java.util.Random;

public class Surowce {

    private ArrayList<Surowiec> surowce = new ArrayList<>();

    public Surowce(){
        Surowiec surowiec = new Surowiec();

        surowiec.setNazwa("Zloto");
        surowiec.setJednostkaHandlowa("uncja");
        surowiec.setWartosc(4412.5);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);

        surowiec.setNazwa("Srebro");
        surowiec.setJednostkaHandlowa("uncja");
        surowiec.setWartosc(59.64);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);

        surowiec.setNazwa("Platyna");
        surowiec.setJednostkaHandlowa("uncja");
        surowiec.setWartosc(3517.5);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);

        surowiec.setNazwa("Ropa");
        surowiec.setJednostkaHandlowa("barylka");
        surowiec.setWartosc(193.32);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);

        surowiec.setNazwa("Miedź");
        surowiec.setJednostkaHandlowa("tona");
        surowiec.setWartosc(2272.9);
        surowiec.przeliczWartosciMinMax();
        surowce.add(surowiec);


    }

    public ArrayList<Surowiec> getSurowce() {
        return surowce;
    }

}
