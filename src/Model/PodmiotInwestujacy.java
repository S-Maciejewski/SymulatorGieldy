package Model;

public abstract class PodmiotInwestujacy {
    private String imie;
    private String nazwisko;
    private Portfel portfel;
    private int agresja;


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

    public int getAgresja() {
        return agresja;
    }

    public void setAgresja(int agresja) {
        this.agresja = agresja;
    }
}
