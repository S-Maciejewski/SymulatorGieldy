package Model;

import java.util.ArrayList;
import java.util.Random;

public abstract class PodmiotInwestujacy {
    private String imie;
    private String nazwisko;
    private Portfel portfel;
    private int agresja;    //zakres od 0 do 100
    private double budzet;


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


    //TODO działania inwstorów

    public void zakupAkcje(ArrayList<Spolka> akcje){
        Random rand = new Random();
        Spolka spolka = akcje.get(rand.nextInt(akcje.size()));

        //TODO wyliczyć zakup akcji
        //portfel.dodajAkcje(spolka, ilosc);
    }

    public void zakupSurowce(){

    }

    public void zakupWaluty(){

    }

    public void zakupJednostkiFunduszu(){

    }

    public void podejmijDzialanie(Aktywa stanAktywow){
        Random rand = new Random();
        int randNum;

        randNum = rand.nextInt(100);
        if(randNum+agresja>=100){
            zakupAkcje(stanAktywow.getSpolki());
        }

        randNum = rand.nextInt(100);
        if(randNum+agresja>=100){
            zakupSurowce();
        }

        randNum = rand.nextInt(100);
        if(randNum+agresja>=100){
            zakupWaluty();
        }

        randNum = rand.nextInt(100);
        if(randNum+agresja>=100){
            zakupJednostkiFunduszu();
        }

    }
}
