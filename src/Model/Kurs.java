package Model;

public class Kurs {
    private Waluta waluta1;
    private Waluta waluta2;

    private double cenaKupna12;
    private double cenaSprzedazy12;
    private double cenaKupna21;
    private double cenaSprzedazy21;


    public void setCenaSprzedazy12andCenaKupna21(double cenaSprzedazy) {
        this.cenaSprzedazy12 = cenaSprzedazy;
        this.cenaKupna21 = 1/cenaSprzedazy;
    }

    public void setCenaKupna12andCenaSprzedazy21(double cenaKupna) {
        this.cenaKupna12 = cenaKupna;
        this.cenaSprzedazy21 = 1 / cenaKupna;
    }

    public double getCenaKupna12() {
        return cenaKupna12;
    }

    public double getCenaSprzedazy12() {
        return cenaSprzedazy12;
    }

    public double getCenaKupna21() {
        return cenaKupna21;
    }

    public double getCenaSprzedazy21() {
        return cenaSprzedazy21;
    }

    public Waluta getWaluta1() {
        return waluta1;
    }

    public void setWaluta1(Waluta waluta1) {
        this.waluta1 = waluta1;
    }

    public Waluta getWaluta2() {
        return waluta2;
    }

    public void setWaluta2(Waluta waluta2) {
        this.waluta2 = waluta2;
    }
}
