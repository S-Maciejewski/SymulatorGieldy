package Model;

public class Inwestor extends PodmiotInwestujacy {
    private String pesel;

    private double budzet;

    public Inwestor(String imieInwestora, String nazwiskoInwestora, String peselInwestora, Waluta walutaRozliczeniowa) {
        setImie(imieInwestora);
        setNazwisko(nazwiskoInwestora);
        this.pesel = peselInwestora;
        setPortfel(new Portfel(walutaRozliczeniowa));
        this.budzet = 0;
    }

    public double getBudzet() {
        return budzet;
    }

    public void setBudzet(double budzet) {
        this.budzet = budzet;
    }
}
