package Model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Klasa, która jest jednocześnie funduszem inwestycyjnym i jednostką funduszu inwestycyjnego, którą może
 * kupić inwestor indywidualny. Implementuje po PodmiotInwestujący interfejs Runnable i przy tworzeniu obiektu
 * tej klasy tworzony jest nowy wątek
 */
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
