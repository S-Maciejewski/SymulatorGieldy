package Model;

import Infrastructure.Ekonomia;

/**
 * Klasa, która rozszerza PodmiotInwestujący, reprezentująca inwestora indywidualnego. Po PodmiotInwestujący
 * implementuje interfejs Runnable i każdy obiekt tej klasy jest uruchamiany w systemie jako osobny wątek
 */
public class Inwestor extends PodmiotInwestujacy {
    private String pesel;

    public Inwestor(String imieInwestora, String nazwiskoInwestora, double budzet) {
        setImie(imieInwestora);
        setNazwisko(nazwiskoInwestora);
        setPortfel(new Portfel());
        setBudzet(budzet);

        //Pesel generowany automatycznie
        this.pesel = "";
        for (int i = 0; i < 11; i++) {
            this.pesel += rand.nextInt(10);
        }
        setAgresja(rand.nextInt(49) + 1);
    }

    public String getPesel() {
        return pesel;
    }
}
