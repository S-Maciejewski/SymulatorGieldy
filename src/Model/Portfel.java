package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Portfel {
    private Map<Spolka, Integer> akcje = new HashMap<Spolka, Integer>();
    private Map<Surowiec, Integer> surowce = new HashMap<Surowiec, Integer>();
    private Map<Waluta, Integer> waluty = new HashMap<Waluta, Integer>();
    private Map<Fundusz, Integer> jednostkiFunduszy = new HashMap<Fundusz, Integer>();

    private double wartoscPortfela;
    private Waluta walutaRozliczeniowa;

    public Portfel(Waluta walutaPortfela){
        this.walutaRozliczeniowa = walutaPortfela;
    }

    public double przeliczPortfel(){    //waluta hardcoded, w razie potrzeby można dodać wybór waluty rozliczeniowej
        for(Entry<Spolka, Integer> entry : akcje.entrySet()){
            wartoscPortfela+=entry.getKey().getKursAktualny()*entry.getValue();
        }
        for(Entry<Surowiec, Integer> entry : surowce.entrySet()){
            wartoscPortfela+=entry.getKey().getWartosc()*entry.getValue();
        }
        for(Entry<Fundusz, Integer> entry : jednostkiFunduszy.entrySet()){
            wartoscPortfela+=entry.getKey().getWartoscJednostki()*entry.getValue();
        }

        for(Entry<Waluta, Integer> entry : waluty.entrySet()){
            wartoscPortfela+=entry.getKey().getWartosc()*entry.getValue();
        }
        return wartoscPortfela;
    }

    public void dodajAkcje(Spolka akcja, int ilosc){
        akcje.put(akcja, ilosc);
    }

    public void dodajSurowiec(Surowiec surowiec, int ilosc){
        surowce.put(surowiec, ilosc);
    }

    public void dodajWalute(Waluta waluta, int ilosc){
        waluty.put(waluta, ilosc);
    }

    public void dodajJednostkeFunduszu(Fundusz fundusz, int ilosc){
        jednostkiFunduszy.put(fundusz, ilosc);
    }
}
