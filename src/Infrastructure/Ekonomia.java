package Infrastructure;

import Model.*;

import java.util.ArrayList;
import java.util.Random;

public class Ekonomia {
    private ArrayList<Gielda> gieldy = new ArrayList<>();
    private Aktywa aktywa = new Aktywa();
    private int nrSesji = 0;

    public void zainicjujSwiat(){

    }

    public void przeliczSesje(){


        

        losoweZmianyCen();
        aktualizacjaParametrowAktywow();
    }

    private void losoweZmianyCen(){
        for(Surowiec surowiec : aktywa.getSurowce()){
            Random rand = new Random();
            double stabilnosc = 7;
            double mnoznik = rand.nextDouble()/stabilnosc;

            surowiec.setWartosc(surowiec.getWartosc()*mnoznik + surowiec.getWartosc());
        }

        for(Waluta waluta : aktywa.getWaluty()){
            Random rand = new Random();
            double stablinosc = 5;
            double mnoznik = rand.nextDouble()/stablinosc;

            waluta.setWartosc(waluta.getWartosc()*mnoznik + waluta.getWartosc());
        }

        for(Spolka spolka : aktywa.getSpolki()){
            Random rand = new Random();
            double mnoznik = rand.nextDouble()/spolka.getStabilnoscKursu();

            spolka.setKursAktualny(spolka.getKursAktualny()*mnoznik + spolka.getKursAktualny());
        }
    }

    private void aktualizacjaParametrowAktywow(){
        for(Surowiec surowiec : aktywa.getSurowce())
            surowiec.przeliczWartosciMinMax();

        for(Spolka spolka : aktywa.getSpolki()){
            spolka.przeliczWartosciMinMax();
            spolka.ustawKursOtwarcia();
        }

        for(Fundusz fundusz : aktywa.getFunduszeInwestycyjne()){
            fundusz.wyliczWartoscJednostki();   //TODO Odpowiednia zależność od agresywności funduszu
        }

    }
}

