package Model;

public class Fundusz extends PodmiotInwestujacy {

    private double wartoscJednostki;
    private int iloscJendostek;
    private int iloscJednostekNaSprzedaz;

    public Fundusz(String imieSzefa, String nazwiskoSzefa, Waluta walutaRozliczeniowa) {
        setImie(imieSzefa);
        setNazwisko(nazwiskoSzefa);
        setPortfel(new Portfel(walutaRozliczeniowa));
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
}
