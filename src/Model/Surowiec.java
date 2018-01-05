package Model;

import java.util.ArrayList;

public class Surowiec {
    private String nazwa;
    private String jednostkaHandlowa;

//    private Waluta waluta;

    private double wartosc;
    private double wartoscMin;
    private double wartoscMax;

    private ArrayList<Double> historiaKursu = new ArrayList<>();

    public Surowiec(String nazwa, String jednostkaHandlowa, double wartosc) {
        this.nazwa = nazwa;
        this.jednostkaHandlowa = jednostkaHandlowa;
        this.wartosc = wartosc;
        wartoscMax = wartosc;
        wartoscMin = wartosc;
    }

    public void przeliczWartosciMinMax() {
        if (wartosc <= wartoscMin)
            wartoscMin = wartosc;
        if (wartosc >= wartoscMax)
            wartoscMax = wartosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getJednostkaHandlowa() {
        return jednostkaHandlowa;
    }


//    public Waluta getWaluta() {
//        return waluta;
//    }
//
//    public void setWaluta(Waluta waluta) {
//        this.waluta = waluta;
//    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public ArrayList<Double> getHistoriaKursu() {
        return historiaKursu;
    }
}
