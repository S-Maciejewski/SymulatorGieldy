package Infrastructure;

import Model.*;
import Repository.*;

import java.util.ArrayList;
import java.util.Random;

public class Ekonomia {
    private static ArrayList<Gielda> gieldy = new ArrayList<>();
    private static ArrayList<PodmiotInwestujacy> inwestorzy = new ArrayList<>();

    private static Aktywa aktywa = new Aktywa();

    private static int nrSesji = 1;
    private static double podstawowyBudzet = 10000;
    private static Random rand = new Random();


    public static void przeliczSesje() {
        for (PodmiotInwestujacy inwestor : inwestorzy) {  //TODO Wielowątkowość inwestorów
            inwestor.podejmijDzialanie(aktywa);
        }

        losoweZmianyCen();
        aktualizacjaParametrowAktywow();
        losoweZmianyBudzetu();
    }

    public void inicjalizujSymulacje() {
        Nazwy nazwy = new Nazwy();
        for (int i = 0; i < 3; i++)
            inwestorzy.add(new Inwestor(nazwy.getImie(), nazwy.getNazwisko(), rand.nextInt((int) podstawowyBudzet)));
        for (int i = 0; i < 2; i++) {
            Fundusz fundusz = new Fundusz(nazwy.getImie(), nazwy.getNazwisko(), rand.nextInt((int) podstawowyBudzet));
            inwestorzy.add(fundusz);
            aktywa.dodajFundusz(fundusz);
        }
        for (int i = 0; i < 4; i++)
            aktywa.dodajSpolke(new Spolka(nazwy.getNazwaSpolki(), podstawowyBudzet * rand.nextDouble() / 100,
                    podstawowyBudzet * 100 * rand.nextDouble(), rand.nextInt(1000000 + 100000), rand.nextDouble()));
        Surowce surowce = new Surowce();
        for (int i = 0; i < 2; i++)
            aktywa.dodajSurowiec(surowce.getSurowiec());
        WalutyPrzykladowe walutyPrzykladowe = new WalutyPrzykladowe();
        aktywa.dodajWalute(walutyPrzykladowe.getWaluty().get(0));   //Dodawanie PLN
        for (int i = 0; i < 2; i++)
            aktywa.dodajWalute(walutyPrzykladowe.getWaluta());
        Gieldy gieldyPrzykladowe = new Gieldy();
        gieldy.add(gieldyPrzykladowe.getGielda());

    }

    //TODO NTH Stabilność zależna od giełdy
    private static void losoweZmianyCen() {
        for (Surowiec surowiec : aktywa.getSurowce()) {

            double stabilnosc = 7; //Parametr symulacji
            double mnoznik = rand.nextDouble() / stabilnosc - rand.nextDouble() / stabilnosc;

            surowiec.getHistoriaKursu().add(surowiec.getWartosc());
            surowiec.setWartosc(surowiec.getWartosc() * mnoznik + surowiec.getWartosc());
            if (surowiec.getWartosc() <= 0)
                surowiec.setWartosc(1);
        }

        for (Waluta waluta : aktywa.getWaluty()) {
            if (!waluta.getNazwa().equals("PLN")) {
                Random rand = new Random();
                double stabilnosc = 5;
                double mnoznik = rand.nextDouble() / stabilnosc - rand.nextDouble() / stabilnosc;

                waluta.getHistoriaKursu().add(waluta.getWartosc());
                waluta.setWartosc(waluta.getWartosc() * mnoznik + waluta.getWartosc());
                if (waluta.getWartosc() <= 0)
                    waluta.setWartosc(1);
            }
        }

        for (Spolka spolka : aktywa.getSpolki()) {
            Random rand = new Random();
            double mnoznik = rand.nextDouble() / spolka.getStabilnoscKursu() - rand.nextDouble() / spolka.getStabilnoscKursu();

            spolka.setKursAktualny(spolka.getKursAktualny() * mnoznik + spolka.getKursAktualny());
            if (spolka.getKursAktualny() <= 0)
                spolka.setKursAktualny(1);
        }
    }

    private static void aktualizacjaParametrowAktywow() {
        for (Surowiec surowiec : aktywa.getSurowce())
            surowiec.przeliczWartosciMinMax();

        for (Spolka spolka : aktywa.getSpolki()) {
            spolka.przeliczWartosciMinMax();
            spolka.ustawKursOtwarcia();
            spolka.ustawInneParametry();
            spolka.setWolumen(0);   //Zerowanie wolumenu na koniec każdej sesji
        }

        for (Fundusz fundusz : aktywa.getFunduszeInwestycyjne()) {
            fundusz.getHistoriaKursu().add(fundusz.getWartoscJednostki());
            fundusz.wyliczWartoscJednostki();
        }

    }

    private static void losoweZmianyBudzetu() {
        for (PodmiotInwestujacy inwestor : inwestorzy)
            inwestor.zwiekszBudzetLosowo();
    }

    public static ArrayList<PodmiotInwestujacy> getInwestorzy() {
        return inwestorzy;
    }

    public static ArrayList<Inwestor> getInwestorzyIndywidualni() {
        ArrayList<Inwestor> inwestorzyIndywidualni = new ArrayList<>();
        for (PodmiotInwestujacy inwestor : inwestorzy) {
            if (inwestor.getClass().equals(Inwestor.class))
                inwestorzyIndywidualni.add((Inwestor) inwestor);
        }
        return inwestorzyIndywidualni;
    }

    public static Aktywa getAktywa() {
        return aktywa;
    }

    public static int getNrSesji() {
        return nrSesji;
    }

    public static void setNrSesji(int numerSesji) {
        nrSesji = numerSesji;
    }

    public static double getPodstawowyBudzet() {
        return podstawowyBudzet;
    }

    public static ArrayList<Gielda> getGieldy() {
        return gieldy;
    }

    public static void dodajGielde(Gielda gielda) {
        gieldy.add(gielda);
    }

    public static ArrayList<String> getNazwySpolek() {
        ArrayList<String> nazwy = new ArrayList<>();
        for (Spolka spolka : aktywa.getSpolki()) {
            nazwy.add(spolka.getNazwa());
        }
        return nazwy;
    }

    public static ArrayList<GieldaPW> getGieldyPW() {
        ArrayList<GieldaPW> gieldyPW = new ArrayList<>();

        for (Gielda gielda : gieldy) {
            if (gielda.getClass().equals(GieldaPW.class)) {
                //System.out.println(gielda.getNazwa() + " jest PW");
                gieldyPW.add((GieldaPW) gielda);                     //Czy aby na pewno?
            }
        }
        return gieldyPW;
    }

    public static ArrayList<Indeks> getIndeksy() {
        ArrayList<Indeks> indeksy = new ArrayList<>();
        for (GieldaPW gieldaPW : getGieldyPW())
            for (Indeks indeks : gieldaPW.getIndeksy())
                indeksy.add(indeks);
        return indeksy;
    }

    public static void removeInwestor(Inwestor inwestor){
        inwestorzy.remove(inwestor);
    }

    public static void removeFundusz(Fundusz fundusz){
        inwestorzy.remove(fundusz);
    }
}

