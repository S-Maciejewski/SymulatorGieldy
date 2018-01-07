package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Klasa, która reprezentuje surowiec i jest jednocześnie jednostką surowca, którą inwestor może posiadać w
 * swoim portfelu
 */
public class Surowiec implements Serializable{
    private String nazwa;
    private String jednostkaHandlowa;

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

    /**
     * Aktualizuje kurs minimalny i maksymalny surowca
     */
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

    public double getWartosc() {
        return wartosc;
    }

    public double getWartoscMax() {
        return wartoscMax;
    }

    public double getWartoscMin() {
        return wartoscMin;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public ArrayList<Double> getHistoriaKursu() {
        return historiaKursu;
    }
}
