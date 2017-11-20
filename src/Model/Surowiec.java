package Model;

public class Surowiec {
    private String nazwa;
    private String jednostkaHandlowa;

    private Waluta waluta;

    private double wartosc;
    private double wartoscMin;
    private double wartoscMax;

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getJednostkaHandlowa() {
        return jednostkaHandlowa;
    }

    public void setJednostkaHandlowa(String jednostkaHandlowa) {
        this.jednostkaHandlowa = jednostkaHandlowa;
    }

    public Waluta getWaluta() {
        return waluta;
    }

    public void setWaluta(Waluta waluta) {
        this.waluta = waluta;
    }

    public double getWartosc() {
        return wartosc;
    }

    public void setWartosc(double wartosc) {
        this.wartosc = wartosc;
    }

    public double getWartoscMin() {
        return wartoscMin;
    }

    public void setWartoscMin(double wartoscMin) {
        this.wartoscMin = wartoscMin;
    }

    public double getWartoscMax() {
        return wartoscMax;
    }

    public void setWartoscMax(double wartoscMax) {
        this.wartoscMax = wartoscMax;
    }
}
