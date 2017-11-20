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

    private ArrayList<Kurs> kursyWalut = new ArrayList<Kurs>();

    public Portfel(Waluta walutaPortfela){
        this.walutaRozliczeniowa = walutaPortfela;
    }

    public ArrayList<Kurs> getKursyWalut() {
        return kursyWalut;
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
        //TODO konwersja wartości walut jako osobna metoda
        for(Entry<Waluta, Integer> entry : waluty.entrySet()){
            for(Kurs kurs : kursyWalut){
                if((kurs.getWaluta1().equals(entry.getKey()) && kurs.getWaluta2().equals(walutaRozliczeniowa)) ||
                        (kurs.getWaluta2().equals(entry.getKey()) && kurs.getWaluta1().equals(walutaRozliczeniowa))){
                    if(kurs.getWaluta1().equals(walutaRozliczeniowa))
                        wartoscPortfela+=kurs.getCenaSprzedazy21() * entry.getValue();
                    else
                        wartoscPortfela+=kurs.getCenaKupna12() * entry.getValue();
                }
            }
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
