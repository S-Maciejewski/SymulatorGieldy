package Model;

import Infrastructure.Ekonomia;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Klasa, która jest jednocześnie reprezentacją spółki i jednostką uczestnictwa w akcjonariacie spółki (akcja).
 * Dzięki temu możliwe jest prostsze odwoływanie się do danych spółki np. z portfela, podczas obliczania jego wartosci
 */
public class Spolka implements Serializable{
    private String nazwa;
    private String dataPierwszejWyceny;

    private double kursOtwarcia;
    private double kursAktualny;
    private double kursMinimalny;
    private double kursMaksymalny;
    private double zysk;
    private double przychod;
    private double obroty;
    private double kapitalWlasny;
    private double kapitalZakladowy;
    private double stabilnoscKursu; //TODO

    private int wolumen;
    private int liczbaAkcji;
    private int liczbaAkcjiNaSprzedaz;

    private Random rand = new Random();

    private ArrayList<Double> historiaKursu = new ArrayList<>();

    public Spolka(String nazwa, double kurs, double kapitalZakladowy, int akcje, double freeFloat) {
        this.nazwa = nazwa;
        this.kursAktualny = kurs;
        this.kapitalZakladowy = kapitalZakladowy;
        this.liczbaAkcji = akcje;
        this.liczbaAkcjiNaSprzedaz = (int) (akcje * freeFloat);
        kursMaksymalny = kurs;
        kursMinimalny = kurs;

        this.stabilnoscKursu = rand.nextInt(10) + 1;
        this.kapitalWlasny = Ekonomia.getPodstawowyBudzet() * rand.nextInt(1000);
        this.dataPierwszejWyceny = rand.nextInt(31) + "/" + rand.nextInt(12) + "/" + (rand.nextInt(118) + 1900);
    }

    /**
     * Oblicza maksymalny i minimalny kurs akcji
     */
    public void przeliczWartosciMinMax() {
        if (kursMinimalny > kursAktualny)
            kursMinimalny = kursAktualny;
        if (kursMaksymalny < kursAktualny)
            kursMaksymalny = kursAktualny;
    }

    public void ustawKursOtwarcia() {
        kursOtwarcia = kursAktualny;
    }

    /**
     * Aktualizuje co sesję parametry spółki takie jak obrót, przychód itp.
     */
    public void ustawInneParametry() {
        historiaKursu.add(kursAktualny);
        obroty = kursAktualny * liczbaAkcji / 100 * rand.nextDouble();
        zysk = Ekonomia.getPodstawowyBudzet() * rand.nextInt(10) * rand.nextDouble() * obroty;
        przychod = zysk * rand.nextInt(50) * rand.nextDouble();

        stabilnoscKursu += rand.nextDouble() - rand.nextDouble();
        kapitalWlasny += kapitalWlasny * (rand.nextDouble() - rand.nextDouble()) / 10;

    }

    public String getNazwa() {
        return nazwa;
    }

    public String getDataPierwszejWyceny() {
        return dataPierwszejWyceny;
    }

    public double getKursOtwarcia() {
        return kursOtwarcia;
    }

    public double getKursAktualny() {
        return kursAktualny;
    }

    public void setKursAktualny(double kursAktualny) {
        this.kursAktualny = kursAktualny;
    }

    public double getKursMinimalny() {
        return kursMinimalny;
    }

    public double getKursMaksymalny() {
        return kursMaksymalny;
    }

    public double getZysk() {
        return zysk;
    }

    public double getPrzychod() {
        return przychod;
    }

    public double getKapitalWlasny() {
        return kapitalWlasny;
    }

    public double getKapitalZakladowy() {
        return kapitalZakladowy;
    }

    public double getObroty() {
        return obroty;
    }

    public int getWolumen() {
        return wolumen;
    }

    public void setWolumen(int wolumen) {
        this.wolumen = wolumen;
    }

    public int getLiczbaAkcji() {
        return liczbaAkcji;
    }

    public int getLiczbaAkcjiNaSprzedaz() {
        return liczbaAkcjiNaSprzedaz;
    }

    public void setLiczbaAkcjiNaSprzedaz(int liczbaAkcjiWObrocie) {
        this.liczbaAkcjiNaSprzedaz = liczbaAkcjiWObrocie;
    }

    public double getStabilnoscKursu() {
        return stabilnoscKursu;
    }

    public ArrayList<Double> getHistoriaKursu() {
        return historiaKursu;
    }
}
