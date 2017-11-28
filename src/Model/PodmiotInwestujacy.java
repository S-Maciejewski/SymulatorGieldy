package Model;

import java.util.ArrayList;
import java.util.Random;

public abstract class PodmiotInwestujacy {
    private String imie;
    private String nazwisko;
    private Portfel portfel;
    private int agresja;    //zakres od 1 do 50
    private double budzet;


    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public Portfel getPortfel() {
        return portfel;
    }

    public void setPortfel(Portfel portfel) {
        this.portfel = portfel;
    }


    //TODO działania inwstorów wielowątkowych

    public void zakupAkcje(ArrayList<Spolka> akcje) {
        Random rand = new Random();
        Spolka spolka = akcje.get(rand.nextInt(akcje.size()));
        int ilosc = rand.nextInt(spolka.getliczbaAkcjiWObrocie());

        //Zapobieganie wiecznej pętli w przypadku niskiego budżetu / wysokich cen
        for (int i = 0; i < 5 && ilosc * spolka.getKursAktualny() > budzet; i++) {
            spolka = akcje.get(rand.nextInt(akcje.size()));

            for (int j = 0; j < 5 && ilosc * spolka.getKursAktualny() > budzet; j++) {
                ilosc = rand.nextInt(spolka.getliczbaAkcjiWObrocie());
            }
        }

        portfel.dodajAkcje(spolka, ilosc);
        budzet -= spolka.getKursAktualny() * ilosc;
        spolka.setliczbaAkcjiWObrocie(spolka.getliczbaAkcjiWObrocie() - ilosc);
        spolka.setWolumen(spolka.getWolumen() + ilosc);
    }

    public void zakupSurowce(ArrayList<Surowiec> surowce) {
        Random rand = new Random();
        Surowiec surowiec = surowce.get(rand.nextInt(surowce.size()));
        int iloscMax = (int) Math.floor(budzet / surowiec.getWartosc());
        int ilosc = rand.nextInt(iloscMax * agresja / 100);

        portfel.dodajSurowiec(surowiec, ilosc);
    }

    public void zakupWaluty(ArrayList<Waluta> waluty) {
        Random rand = new Random();
        Waluta waluta = waluty.get(rand.nextInt(waluty.size()));
        int iloscMax = (int) Math.floor(budzet / waluta.getWartosc());
        int ilosc = rand.nextInt(iloscMax * agresja / 100);

        portfel.dodajWalute(waluta, ilosc);
    }

    public void zakupJednostkiFunduszu() {

    }

    public void podejmijDzialanie(Aktywa stanAktywow) {
        Random rand = new Random();
        int randNum;

        randNum = rand.nextInt(100);
        if (randNum + agresja >= 100) {
            zakupAkcje(stanAktywow.getSpolki());
        }

        randNum = rand.nextInt(100);
        if (randNum + agresja >= 100) {
            zakupSurowce(stanAktywow.getSurowce());
        }

        randNum = rand.nextInt(100);
        if (randNum + agresja >= 100) {
            zakupWaluty(stanAktywow.getWaluty());
        }

        randNum = rand.nextInt(100);
        if (randNum + agresja >= 100) {
            zakupJednostkiFunduszu();
        }

    }
}
