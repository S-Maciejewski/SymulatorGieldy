package Infrastructure;

import Model.*;
import Repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

import java.io.IOException;

public class Controller {

    @FXML
    private Button przeliczSesje, dodajInwestora, dodajSpolke, dodajWalute, dodajSurowiec, dodajGielde, dodajIndeks,
            pokazInwestorow, pokazSpolki, pokazWaluty, pokazSurowce, pokazGieldy, pokazIndeksy, zamknij;
    @FXML
    private Label nrSesji;
    @FXML
    private TextField imie, nazwisko, budzet, nazwa, kurs, akcje, freeFloat, kapitalZakladowy,
            nazwaWaluty, wartoscWaluty, nazwaSurowca, jednostkaSurowca, wartoscSurowca,
            nazwaGieldy, kraj, miasto, adres, marza;
    @FXML
    private TextArea console;
    @FXML
    private CheckBox czyFundusz;
    @FXML
    private ChoiceBox<String> typ;
    @FXML
    private TableView topTabela, tabela;

    private Random rand = new Random();

    public void executeMenuAction(ActionEvent event) {

        if (event.getSource().equals(przeliczSesje)) {
            Ekonomia.setNrSesji(Ekonomia.getNrSesji() + 1);

            nrSesji.setText(Ekonomia.getNrSesji() + "");

            Ekonomia.przeliczSesje();
            zaktualizujTopInwestorow();
        }

        if (event.getSource().equals(zamknij))
            zamknijOkno((Stage) zamknij.getScene().getWindow());

        try {
            if (event.getSource().equals(dodajInwestora)) {     //TODO dodawanie indeksów
                dodawanieInwestora();
            } else if (event.getSource().equals(dodajSpolke)) {
                dodawanieSpolki();
            } else if (event.getSource().equals(dodajWalute)) {
                dodawanieWaluty();
            } else if (event.getSource().equals(dodajSurowiec)) {
                dodawanieSurowca();
            } else if (event.getSource().equals(dodajGielde)) {
                dodawanieGieldy();  //TODO powtarzające się nazwy
            } else if (event.getSource().equals(pokazInwestorow)) {     //TODO wyswietlanie
                wyswietlanieInwestorow();
            } else if (event.getSource().equals(pokazSpolki)) {
                wyswietlanieSpolek();
            } else if (event.getSource().equals(pokazWaluty)) {
                wyswietlanieWalut();
            } else if (event.getSource().equals(pokazSurowce)) {
                wyswietlanieSurowcow();
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
        kapitalZakladowy.setText(Ekonomia.getPodstawowyBudzet() * 100 * rand.nextDouble() + "");
        akcje.setText(rand.nextInt(1000000) + 100000 + "");
        freeFloat.setText(rand.nextDouble() + "");


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
        }
        if (!znaleziono)
            Ekonomia.getAktywa().dodajSurowiec(new Surowiec(nazwaSurowca.getText(), jednostkaSurowca.getText(),
                    Double.parseDouble(wartoscSurowca.getText())));
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
        TextField miasto = (TextField) root.lookup("#miasto");
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
        typ.getItems().addAll("Giełda papierów wartościowych", "Giełda walut", "Giełda surowców");

        if (gielda.getClass().equals(GieldaPW.class))
            typ.setValue("Giełda papierów wartościowych");
        else if (gielda.getClass().equals(GieldaWalut.class))
            typ.setValue("Giełda walut");
        else
            typ.setValue("Giełda surowców");

        stage.showAndWait();
    }

    public void potwierdzenieGieldy() {
        System.out.println("Pojawila sie nowa gielda");
        System.out.println("Nazwa " + nazwaGieldy.getText());
        System.out.println("Kraj " + kraj.getText());
        System.out.println("Miasto " + miasto.getText());
        System.out.println("Adres siedziby  " + adres.getText());
        System.out.println("Marza " + marza.getText());

        boolean znaleziono = false;
        Gieldy gieldy = new Gieldy();
        for (Gielda gielda : gieldy.getGieldy()) {
            if (gielda.getNazwa().equals(nazwaGieldy.getText())) {
                znaleziono = true;
                Ekonomia.getGieldy().add(gielda);
                break;
            }
        }
        if (!znaleziono) {
            if (typ.getValue().equals("Giełda papierów wartościowych"))
                Ekonomia.dodajGielde(new GieldaPW(nazwaGieldy.getText(), kraj.getText(), miasto.getText(),
                        adres.getText(), Double.parseDouble(marza.getText())));
            else if (typ.getValue().equals("Giełda walut"))
                Ekonomia.dodajGielde(new GieldaWalut(nazwaGieldy.getText(), kraj.getText(), miasto.getText(),
                        adres.getText(), Double.parseDouble(marza.getText())));
            else
                Ekonomia.dodajGielde(new GieldaSurowcow(nazwaGieldy.getText(), kraj.getText(), miasto.getText(),
                        adres.getText(), Double.parseDouble(marza.getText())));
        }

        zamknijOkno((Stage) zamknij.getScene().getWindow());
    }

    //Wyswietlanie

    public void zaktualizujTopInwestorow() {
        ObservableList<PodmiotInwestujacy> podmioty = FXCollections.observableArrayList(Ekonomia.getInwestorzyIndywidualni());

        TableColumn<PodmiotInwestujacy, String> imie = new TableColumn<>("Imię");
        TableColumn<PodmiotInwestujacy, String> nazwisko = new TableColumn<>("Nazwisko");
        TableColumn<PodmiotInwestujacy, Double> budzet = new TableColumn<>("Budżet");

        imie.setMinWidth(80);
        imie.setMaxWidth(80);
        imie.setCellValueFactory(new PropertyValueFactory<>("imie"));

        nazwisko.setMinWidth(160);
        nazwisko.setMaxWidth(160);
        nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));

        budzet.setMinWidth(50);
        budzet.setCellValueFactory(new PropertyValueFactory<>("budzet"));
        budzet.setSortType(TableColumn.SortType.DESCENDING);

        topTabela.getColumns().clear();

        topTabela.setItems(podmioty);
        topTabela.getColumns().addAll(imie, nazwisko, budzet);
        topTabela.getSortOrder().add(budzet);
    }

    public void wyswietlanieInwestorow() {
        tabela.getColumns().clear();
        ObservableList<PodmiotInwestujacy> podmioty = FXCollections.observableArrayList(Ekonomia.getInwestorzy());
        TableColumn<PodmiotInwestujacy, String> imie = new TableColumn<>("Imię");
        TableColumn<PodmiotInwestujacy, String> nazwisko = new TableColumn<>("Nazwisko");
        TableColumn<PodmiotInwestujacy, Double> budzet = new TableColumn<>("Budżet");
        imie.setMinWidth(80);
        imie.setMaxWidth(80);
        imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        nazwisko.setMinWidth(160);
        nazwisko.setMaxWidth(160);
        nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        budzet.setMinWidth(50);
        budzet.setCellValueFactory(new PropertyValueFactory<>("budzet"));

        tabela.setItems(podmioty);
        tabela.getColumns().addAll(imie, nazwisko, budzet);
        console.setText("Na gieldzie funkcjonuje " + Ekonomia.getInwestorzyIndywidualni().size() + " inwestorow indywidualnych i " +
                (Ekonomia.getInwestorzy().size() - Ekonomia.getInwestorzyIndywidualni().size() + " funduszy inwestycyjnych\n" +
                        "Aby dowiedziec sie wiecej kliknij wybranego inwestora"));

    }

    public void wyswietlanieSpolek() {
        tabela.getColumns().clear();
        ObservableList<Spolka> spolki = FXCollections.observableArrayList(Ekonomia.getAktywa().getSpolki());
        TableColumn<Spolka, String> nazwa = new TableColumn<>("Nazwa");
        TableColumn<Spolka, Double> kurs = new TableColumn<>("Kurs");
        TableColumn<Spolka, Double> kursMax = new TableColumn<>("Kurs maksyalny");
        TableColumn<Spolka, Double> kursMin = new TableColumn<>("Kurs minimalny");
        nazwa.setMinWidth(200);
        nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        kurs.setMinWidth(60);
        kurs.setCellValueFactory(new PropertyValueFactory<>("kursAktualny"));
        kursMax.setMinWidth(60);
        kursMax.setCellValueFactory(new PropertyValueFactory<>("kursMaksymalny"));
        kursMin.setMinWidth(60);
        kursMin.setCellValueFactory(new PropertyValueFactory<>("kursMinimalny"));

        double lacznaWartosc = 0;
        for (Spolka spolka : spolki) {
            lacznaWartosc += spolka.getKursAktualny() * spolka.getLiczbaAkcji();
        }

        tabela.setItems(spolki);
        tabela.getColumns().addAll(nazwa, kurs, kursMax, kursMin);
        console.setText("Na gieldzie funkcjonuje " + spolki.size() + " spolek o lacznej wartosci " + lacznaWartosc + " PLN\n" +
                "Aby dowiedziec sie wiecej kliknij wybrana spolke");
    }

    public void wyswietlanieWalut() {
        tabela.getColumns().clear();
        ObservableList<Waluta> waluty = FXCollections.observableArrayList(Ekonomia.getAktywa().getWaluty());
        TableColumn<Spolka, String> nazwa = new TableColumn<>("Nazwa");
        TableColumn<Spolka, Double> kurs = new TableColumn<>("Kurs");
        nazwa.setMinWidth(200);
        nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        kurs.setMinWidth(60);
        kurs.setCellValueFactory(new PropertyValueFactory<>("wartosc"));

        tabela.setItems(waluty);
        tabela.getColumns().addAll(nazwa, kurs);
        console.setText("Na rynku funkcjonuje " + waluty.size() + " walut, waluta glowna to PLN " +
                "\nAby dowiedziec sie wiecej kliknij wybrana walute");
    }

    public void wyswietlanieSurowcow() {
        tabela.getColumns().clear();
        ObservableList<Surowiec> surowce = FXCollections.observableArrayList(Ekonomia.getAktywa().getSurowce());
        TableColumn<Surowiec, String> nazwa = new TableColumn<>("Nazwa");
        TableColumn<Surowiec, String> jednostka = new TableColumn<>("Jednostka");
        TableColumn<Surowiec, Double> kurs = new TableColumn<>("Wartość");
        TableColumn<Surowiec, Double> kursMax = new TableColumn<>("Wartość maksymalna");
        TableColumn<Surowiec, Double> kursMin = new TableColumn<>("Wartość minimalna");
        nazwa.setMinWidth(80);
        nazwa.setMaxWidth(80);
        nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        jednostka.setMinWidth(60);
        jednostka.setCellValueFactory(new PropertyValueFactory<>("jednostkaHandlowa"));
        kurs.setMinWidth(60);
        kurs.setCellValueFactory(new PropertyValueFactory<>("wartosc"));
        kursMax.setMinWidth(60);
        kursMax.setCellValueFactory(new PropertyValueFactory<>("wartoscMax"));
        kursMin.setMinWidth(60);
        kursMin.setCellValueFactory(new PropertyValueFactory<>("wartoscMin"));

        tabela.setItems(surowce);
        tabela.getColumns().addAll(nazwa, jednostka, kurs, kursMax, kursMin);
        console.setText("Na rynku funkcjonuje " + surowce.size() + " surowcow" + "\nAby dowiedziec sie wiecej kliknij " +
                "wybrana walute");
    }
}
