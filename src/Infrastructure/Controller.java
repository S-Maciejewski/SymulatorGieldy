package Infrastructure;

import Model.*;
import Repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

import java.io.IOException;

public class Controller {

    @FXML
    private Button przeliczSesje, dodajInwestora, dodajSpolke, dodajWalute, dodajSurowiec, dodajGielde,
            pokazInwestorow, pokazSpolki, pokazWaluty, pokazSurowce, pokazGieldy, zamknij;
    @FXML   //Czy potrzebne?
    private Button potwierdzInwestora, potwierdzSpolke, potwierdzWalute, potwierdzSurowiec, potwierdzGielde;
    @FXML
    private Label nrSesji;
    @FXML
    private TextField imie, nazwisko, budzet, nazwa, kurs, akcje, freeFloat, kapitalZakladowy,
            nazwaWaluty, wartoscWaluty, nazwaSurowca, jednostkaSurowca, wartoscSurowca,
            nazwaGieldy, kraj, miasto, adres, marza;
    @FXML
    private CheckBox czyFundusz;
    @FXML
    private ChoiceBox<String> typ;

    private Random rand = new Random();

    //TODO INDEKSY!!!
    public void executeMenuAction(ActionEvent event) {

        if (event.getSource().equals(przeliczSesje)) {
            Ekonomia.setNrSesji(Ekonomia.getNrSesji() + 1);

            nrSesji.setText(Ekonomia.getNrSesji() + "");
            //TODO wywołanie przeliczenia sesji
            //TODO aktualizacja top inwestorów
        }

        if (event.getSource().equals(zamknij))
            zamknijOkno((Stage) zamknij.getScene().getWindow());

        try {
            if (event.getSource().equals(dodajInwestora)) {     //TODO dodawanie
                dodawanieInwestora();
            } else if (event.getSource().equals(dodajSpolke)) {
                dodawanieSpolki();
            } else if (event.getSource().equals(dodajWalute)) {
                dodawanieWaluty();
            } else if (event.getSource().equals(dodajSurowiec)) {
                dodawanieSurowca();
            } else if (event.getSource().equals(dodajGielde)) {
                dodawanieGieldy();
            } else if (event.getSource().equals(pokazInwestorow)) {     //TODO wyswietlanie

            } else if (event.getSource().equals(pokazSpolki)) {

            } else if (event.getSource().equals(pokazWaluty)) {

            } else if (event.getSource().equals(pokazSurowce)) {

            } else if (event.getSource().equals(pokazGieldy)) {

            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono dopowiedniego pliku .fxml - prosze sprawdzic zawartosc folderu Infrastructure");
        }
    }

    public void zamknijOkno(Stage stage) {
        stage.close();
    }

    public void dodawanieInwestora() throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieInwestora.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajInwestora.getScene().getWindow());
        stage.setTitle("Dodaj inwestora");

        TextField imie = (TextField) root.lookup("#imie");
        TextField nazwisko = (TextField) root.lookup("#nazwisko");
        TextField budzet = (TextField) root.lookup("#budzet");

        Nazwy nazwy = new Nazwy();

        imie.setText(nazwy.getImie());
        nazwisko.setText(nazwy.getNazwisko());
        budzet.setText(rand.nextInt((int) Ekonomia.getPodstawowyBudzet()) + "");

        stage.showAndWait();
    }

    public void potwierdzenieInwestora() {
        System.out.println("Pojawil sie nowy inwestor");
        System.out.println("Imie " + imie.getText());
        System.out.println("Nazwisko " + nazwisko.getText());
        System.out.println("Budżet " + Double.parseDouble(budzet.getText()));

        if (czyFundusz.isSelected()) {
            Fundusz fundusz = new Fundusz(imie.getText(), nazwisko.getText(), Double.parseDouble(budzet.getText()));
            Ekonomia.getAktywa().dodajFundusz(fundusz);
            Ekonomia.getInwestorzy().add(fundusz);
        } else {
            Inwestor inwestor = new Inwestor(imie.getText(), nazwisko.getText(), Double.parseDouble(budzet.getText()));
            Ekonomia.getInwestorzy().add(inwestor);
        }
        zamknijOkno((Stage) zamknij.getScene().getWindow());
    }

    public void dodawanieSpolki() throws IOException {

        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieSpolki.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajInwestora.getScene().getWindow());
        stage.setTitle("Dodaj spolke");

        TextField nazwa = (TextField) root.lookup("#nazwa");
        TextField kurs = (TextField) root.lookup("#kurs");
        TextField akcje = (TextField) root.lookup("#akcje");
        TextField freeFloat = (TextField) root.lookup("#freeFloat");
        TextField kapitalZakladowy = (TextField) root.lookup("#kapitalZakladowy");

        Nazwy nazwy = new Nazwy();

        nazwa.setText(nazwy.getNazwaSpolki());
        kurs.setText(Ekonomia.getPodstawowyBudzet() * rand.nextDouble() / 100 + "");
        akcje.setText(rand.nextInt(1000000) + 100000 + "");
        freeFloat.setText(rand.nextDouble() + "");
        kapitalZakladowy.setText(Ekonomia.getPodstawowyBudzet() * 100 * rand.nextDouble() + "");

        stage.showAndWait();
    }

    public void potwierdzenieSpolki() {
        System.out.println("Pojawila sie nowa spolka");
        System.out.println("Nazwa " + nazwa.getText());
        System.out.println("Kurs " + kurs.getText());
        System.out.println("Akcje " + Integer.parseInt(akcje.getText()));
        System.out.println("FreeFloat " + Double.parseDouble(freeFloat.getText()));
        System.out.println("Kapital zakladowy " + Double.parseDouble(kapitalZakladowy.getText()));

        Spolka spolka = new Spolka(nazwa.getText(), Double.parseDouble(kurs.getText()),
                Double.parseDouble(kapitalZakladowy.getText()), Integer.parseInt(akcje.getText()),
                Double.parseDouble(freeFloat.getText()));

        Ekonomia.getAktywa().dodajSpolke(spolka);

        zamknijOkno((Stage) zamknij.getScene().getWindow());
    }

    public void dodawanieWaluty() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieWaluty.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajInwestora.getScene().getWindow());
        stage.setTitle("Dodaj walute");

        TextField nazwaWaluty = (TextField) root.lookup("#nazwaWaluty");
        TextField wartoscWaluty = (TextField) root.lookup("#wartoscWaluty");

        WalutyPrzykladowe walutyPrzykladowe = new WalutyPrzykladowe();
        Waluta walutaLosowa = walutyPrzykladowe.getWaluta();

        nazwaWaluty.setText(walutaLosowa.getNazwa());
        wartoscWaluty.setText(walutaLosowa.getWartosc() + "");

        stage.showAndWait();
    }

    public void potwierdzenieWaluty() {
        System.out.println("Pojawila sie nowa waluta");
        System.out.println("Nazwa " + nazwaWaluty.getText());
        System.out.println("Wartosc " + wartoscWaluty.getText());

        boolean znaleziono = false;
        WalutyPrzykladowe walutyPrzykladowe = new WalutyPrzykladowe();
        for (Waluta waluta : walutyPrzykladowe.getWaluty()) {
            if (waluta.getNazwa().equals(nazwaWaluty.getText())) {
                znaleziono = true;
                Ekonomia.getAktywa().dodajWalute(waluta);
                break;
            }
        }
        if (!znaleziono)
            Ekonomia.getAktywa().dodajWalute(new Waluta(nazwaWaluty.getText(), Double.parseDouble(wartoscWaluty.getText())));

        zamknijOkno((Stage) zamknij.getScene().getWindow());
    }

    public void dodawanieSurowca() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieSurowca.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajInwestora.getScene().getWindow());
        stage.setTitle("Dodaj surowiec");

        TextField nazwaSurowca = (TextField) root.lookup("#nazwaSurowca");
        TextField jednostkaSurowca = (TextField) root.lookup("#jednostkaSurowca");
        TextField wartoscSurowca = (TextField) root.lookup("#wartoscSurowca");

        Surowce surowce = new Surowce();
        Surowiec surowiecLosowy = surowce.getSurowiec();

        nazwaSurowca.setText(surowiecLosowy.getNazwa());
        jednostkaSurowca.setText(surowiecLosowy.getJednostkaHandlowa());
        wartoscSurowca.setText(surowiecLosowy.getWartosc() + "");

        stage.showAndWait();
    }

    public void potwierdzenieSurowca() {
        System.out.println("Pojawil sie nowy surowiec");
        System.out.println("Nazwa " + nazwaSurowca.getText());
        System.out.println("Jednostka handlowa " + jednostkaSurowca.getText());
        System.out.println("Wartosc " + wartoscSurowca.getText());

        boolean znaleziono = false;
        Surowce surowce = new Surowce();
        for (Surowiec surowiec : surowce.getSurowce()) {
            if (surowiec.getNazwa().equals(nazwaSurowca.getText())) {
                znaleziono = true;
                Ekonomia.getAktywa().dodajSurowiec(surowiec);
                break;
            }

            if (!znaleziono)
                Ekonomia.getAktywa().dodajSurowiec(new Surowiec(nazwaSurowca.getText(), jednostkaSurowca.getText(), Double.parseDouble(wartoscSurowca.getText())));
        }
        zamknijOkno((Stage) zamknij.getScene().getWindow());
    }

    public void dodawanieGieldy() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieGieldy.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajInwestora.getScene().getWindow());
        stage.setTitle("Dodaj gielde");

        TextField nazwaGieldy = (TextField) root.lookup("#nazwaGieldy");
        TextField kraj = (TextField) root.lookup("#kraj");
        TextField miasto = (TextField) root.lookup("miasto");
        TextField adres = (TextField) root.lookup("#adres");
        TextField marza = (TextField) root.lookup("#marza");

        Gieldy gieldy = new Gieldy();

        Gielda gielda = gieldy.getGielda();

        nazwaGieldy.setText(gielda.getNazwa());
        kraj.setText(gielda.getKraj());
        miasto.setText(gielda.getMiasto());
        adres.setText(gielda.getAdresSiedziby());
        marza.setText(gielda.getMarza() + "");

        ChoiceBox<String> typ = (ChoiceBox<String>) root.lookup("#typ");

        typ.setValue();
        typ.getItems().addAll("Giełda papierów wartościowych", "Giełda walut", "Giełda surowców");
        stage.showAndWait();
    }

    public void potwierdzenieGieldy() {

    }

}
