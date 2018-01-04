package Model;

import java.util.ArrayList;
import java.util.Random;

public class Fundusz extends PodmiotInwestujacy {

    private double wartoscJednostki;
    private int iloscJendostek;
    private int iloscJednostekNaSprzedaz;

    private ArrayList<Double> historiaKursu = new ArrayList<>();

    public Fundusz(String imieSzefa, String nazwiskoSzefa, double wartoscJednostki) {
        setImie(imieSzefa);
        setNazwisko(nazwiskoSzefa);
        setPortfel(new Portfel());
        this.wartoscJednostki = wartoscJednostki;

        iloscJendostek = rand.nextInt(10000);
        iloscJednostekNaSprzedaz = (int) (iloscJendostek * rand.nextDouble());

        //TODO problem z wartością portfela i przeliczniem wartości jednostki
        setBudzet(iloscJendostek * wartoscJednostki);
        setAgresja(rand.nextInt(49) + 1);
    }

    public int getIloscJendostek() {
        return iloscJendostek;
    }

    public void setIloscJendostek(int iloscJendostek) {
        this.iloscJendostek = iloscJendostek;
    }

    public double getWartoscJednostki() {
        return wartoscJednostki;
    }

    public void wyliczWartoscJednostki() {
        this.wartoscJednostki = getPortfel().przeliczPortfel() / iloscJendostek;
    }

    public int getIloscJednostekNaSprzedaz() {
        return iloscJednostekNaSprzedaz;
    }

    public void setIloscJednostekNaSprzedaz(int iloscJednostekNaSprzedaz) {
        this.iloscJednostekNaSprzedaz = iloscJednostekNaSprzedaz;
    }

    public ArrayList<Double> getHistoriaKursu() {
        return historiaKursu;
    }
}
