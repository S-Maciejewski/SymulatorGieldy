package Model;

import Infrastructure.Ekonomia;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public abstract class PodmiotInwestujacy {
    private String imie;
    private String nazwisko;
    private Portfel portfel;
    private int agresja;    //zakres od 1 do 50
    private double budzet;

    protected Random rand = new Random();

    private ArrayList<Double> historiaWartosciPortfela = new ArrayList<>();

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

    public void setAgresja(int agresja) {
        this.agresja = agresja;
    }

    public void setBudzet(double Budzet) {
        this.budzet = budzet;
    }

    public double getBudzet() {
        return budzet;
    }

    //TODO działania inwstorów wielowątkowych
    private void sprzedajAkcje() {
        Random rand = new Random();
        int i = 0, randNum = rand.nextInt(portfel.getAkcje().size()), ilosc;

        for (Entry<Spolka, Integer> entry : portfel.getAkcje().entrySet()) {
            if (i == randNum) {
                ilosc = (int) Math.floor(entry.getValue() * agresja / 100);

                entry.setValue(entry.getValue() - ilosc);
                entry.getKey().setLiczbaAkcjiNaSprzedaz(entry.getKey().getLiczbaAkcjiNaSprzedaz() + ilosc);
                entry.getKey().setWolumen(entry.getKey().getWolumen() + ilosc);
                budzet += entry.getKey().getKursAktualny() * ilosc;
                break;
            }
            i++;
        }
    }

    private void sprzedajSurowce() {
        Random rand = new Random();
        int i = 0, randNum = rand.nextInt(portfel.getSurowce().size()), ilosc = 0;

        for (Entry<Surowiec, Integer> entry : portfel.getSurowce().entrySet()) {
            if (i == randNum) {
                ilosc = (int) Math.floor(entry.getValue() * agresja / 100);

                entry.setValue(entry.getValue() - ilosc);
                budzet += entry.getKey().getWartosc() * ilosc;
                break;
            }
            i++;
        }
    }

    private void sprzedajWaluty() {
        Random rand = new Random();
        int i = 0, randNum = rand.nextInt(portfel.getSurowce().size()), ilosc = 0;

        for (Entry<Waluta, Integer> entry : portfel.getWaluty().entrySet()) {
            if (i == randNum) {
                ilosc = (int) Math.floor(entry.getValue() * agresja / 100);

                entry.setValue(entry.getValue() - ilosc);
                budzet += entry.getKey().getWartosc() * ilosc;
                break;
            }
            i++;
        }
    }

    private void sprzedajJednostkiFunduszu() {
        Random rand = new Random();
        int i = 0, randNum = rand.nextInt(portfel.getSurowce().size()), ilosc = 0;

        for (Entry<Fundusz, Integer> entry : portfel.getJednostkiFunduszy().entrySet()) {
            if (i == randNum) {
                ilosc = (int) Math.floor(entry.getValue() * agresja / 100);

                entry.setValue(entry.getValue() - ilosc);
                entry.getKey().setIloscJednostekNaSprzedaz(entry.getKey().getIloscJednostekNaSprzedaz() + ilosc);
                budzet += entry.getKey().getWartoscJednostki() * ilosc;
                break;
            }
            i++;
        }
    }

    private void zakupAkcje(ArrayList<Spolka> akcje) {
        Random rand = new Random();
        Spolka spolka = akcje.get(rand.nextInt(akcje.size()));
        int iloscMax = (int) Math.floor(budzet / spolka.getKursAktualny());
        int ilosc = rand.nextInt(iloscMax * agresja / 100);

        if (iloscMax > spolka.getLiczbaAkcjiNaSprzedaz()) {
            ilosc = rand.nextInt(spolka.getLiczbaAkcjiNaSprzedaz() * agresja / 100);
        }

        portfel.dodajAkcje(spolka, ilosc);
        budzet -= spolka.getKursAktualny() * ilosc;
        spolka.setLiczbaAkcjiNaSprzedaz(spolka.getLiczbaAkcjiNaSprzedaz() - ilosc);
        spolka.setWolumen(spolka.getWolumen() + ilosc);
    }

    private void zakupSurowce(ArrayList<Surowiec> surowce) {
        Random rand = new Random();
        Surowiec surowiec = surowce.get(rand.nextInt(surowce.size()));
        int iloscMax = (int) Math.floor(budzet / surowiec.getWartosc());
        int ilosc = rand.nextInt(iloscMax * agresja / 100);

        portfel.dodajSurowiec(surowiec, ilosc);
    }

    private void zakupWaluty(ArrayList<Waluta> waluty) {
        Random rand = new Random();
        Waluta waluta = waluty.get(rand.nextInt(waluty.size()));
        int iloscMax = (int) Math.floor(budzet / waluta.getWartosc());
        int ilosc = rand.nextInt(iloscMax * agresja / 100);

        portfel.dodajWalute(waluta, ilosc);
    }

    private void zakupJednostkiFunduszu(ArrayList<Fundusz> fundusze) {
        Random rand = new Random();
        Fundusz fundusz = fundusze.get(rand.nextInt(fundusze.size()));
        int iloscMax = (int) Math.floor(budzet / fundusz.getWartoscJednostki());
        int ilosc = rand.nextInt(iloscMax * agresja / 100);

        if (iloscMax > fundusz.getIloscJendostek()) {
            ilosc = rand.nextInt(fundusz.getIloscJendostek() * agresja / 100);
        }

        portfel.dodajJednostkeFunduszu(fundusz, ilosc);
    }

    public void podejmijDzialanie(Aktywa stanAktywow) {
        Random rand = new Random();
        int randNum;

        randNum = rand.nextInt(100);
        if (randNum + agresja >= 100) {
            sprzedajAkcje();
        }

        randNum = rand.nextInt(100);
        if (randNum + agresja >= 100) {
            sprzedajSurowce();
        }

        randNum = rand.nextInt(100);
        if (randNum + agresja >= 100) {
            sprzedajWaluty();
        }

        randNum = rand.nextInt(100);
        if (randNum + agresja >= 100) {
            sprzedajJednostkiFunduszu();
        }

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
            zakupJednostkiFunduszu(stanAktywow.getFunduszeInwestycyjne());
        }

    }

    public void zwiekszBudzetLosowo() {
        if (rand.nextDouble() >= 0.25) {
            setBudzet(getBudzet() + Ekonomia.getPodstawowyBudzet() * rand.nextDouble());
        }
    }

}
