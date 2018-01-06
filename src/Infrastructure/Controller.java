package Infrastructure;
/*
    TODO comment
 */

import Model.*;
import Repository.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.Chart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
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
            pokazInwestorow, pokazSpolki, pokazWaluty, pokazSurowce, pokazGieldy, pokazIndeksy, zamknij, usun;
    @FXML
    private Label nrSesji;
    @FXML
    private TextField imie, nazwisko, budzet, nazwa, kurs, akcje, freeFloat, kapitalZakladowy,
            nazwaWaluty, wartoscWaluty, nazwaSurowca, jednostkaSurowca, wartoscSurowca,
            nazwaGieldy, kraj, miasto, adres, marza, nazwaIndeksu,
            pesel, agresja, wartoscPortfela, ilosc, wartosc,
            data, kursOtwarcia, kursMax, kursMin, wolumen, kapital, obroty, przychody, zysk;
    @FXML
    private TextArea console;
    @FXML
    private CheckBox czyFundusz;
    @FXML
    private ChoiceBox<String> typ, wyborSpolki, wyborGieldy;
    @FXML
    private TableView topTabela, tabela;
    @FXML
    private LineChart<String, Number> wykres;
    @FXML
    private ToggleButton procent;

    private Random rand = new Random();
    private ArrayList<String> nazwySpolek, nazwyGieldPW;

    //TODO pozmieniac public na private


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
            } else if (event.getSource().equals(dodajIndeks)) {
                dodawanieIndeksu();
            } else if (event.getSource().equals(pokazInwestorow)) {     //TODO wyswietlanie okien z wykresami
                wyswietlanieInwestorow();
            } else if (event.getSource().equals(pokazSpolki)) {
                wyswietlanieSpolek();
            } else if (event.getSource().equals(pokazWaluty)) {
                wyswietlanieWalut();
            } else if (event.getSource().equals(pokazSurowce)) {
                wyswietlanieSurowcow();
            } else if (event.getSource().equals(pokazGieldy)) {
                wyswietlanieGield();
            } else if (event.getSource().equals(pokazIndeksy)) {
                wyswietlanieIndeksow();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono dopowiedniego pliku .fxml - prosze sprawdzic zawartosc folderu Infrastructure");
        }
    }

    public void zamknijOkno(Stage stage) {
        stage.close();
    }

    //Dodawanie

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

    public void dodawanieIndeksu() throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("dodawanieIndeksu.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(dodajInwestora.getScene().getWindow());
        stage.setTitle("Dodaj indeks");

        TextField nazwaIndeksu = (TextField) root.lookup("#nazwaIndeksu");
        nazwySpolek = new ArrayList<>();
        nazwyGieldPW = new ArrayList<>();

        Nazwy nazwy = new Nazwy();
        nazwaIndeksu.setText(nazwy.getNazwaIndeksu());

        ChoiceBox<String> wyborGieldy = (ChoiceBox<String>) root.lookup("#wyborGieldy");
//        for (Gielda gielda : Ekonomia.getGieldy()) {
//            if (gielda.getClass().equals(GieldaPW.class))
//                nazwyGieldPW.add(gielda.getNazwa());
//        }
        Ekonomia.getGieldyPW();
        for (GieldaPW gieldaPW : Ekonomia.getGieldyPW()) {
            nazwyGieldPW.add(gieldaPW.getNazwa());
        }

        wyborGieldy.getItems().addAll(nazwyGieldPW);
        if (nazwyGieldPW.size() > 0)
            wyborGieldy.setValue(nazwyGieldPW.get(rand.nextInt(nazwyGieldPW.size())));
        else
            wyborGieldy.setValue("Brak gield papierow wartosciowych");

        ChoiceBox<String> typ = (ChoiceBox<String>) root.lookup("#wyborSpolki");
        typ.getItems().addAll(Ekonomia.getNazwySpolek());
        typ.setValue(Ekonomia.getNazwySpolek().get(rand.nextInt(Ekonomia.getNazwySpolek().size())));

        stage.showAndWait();
    }   //TODO do poprawy

    public void dodawanieDoIndeksu() {
        nazwySpolek.add(wyborSpolki.getValue());
    }

    public void potwierdzenieIndeksu() {
        System.out.println("Pojawil sie nowy indeks");
        System.out.println("Nazwa " + nazwaIndeksu.getText());

        Indeks indeks = new Indeks();
        indeks.setNazwa(nazwaIndeksu.getText());

        ArrayList<Spolka> spolki = new ArrayList<>();
        for (Spolka spolka : Ekonomia.getAktywa().getSpolki()) {
            for (String nazwa : nazwySpolek) {
                if (nazwa.equals(spolka.getNazwa())) {
                    spolki.add(spolka);
                    break;
                }
            }
        }
        indeks.setSpolki(spolki);

        for (GieldaPW gieldaPW : Ekonomia.getGieldyPW()) {
            if (gieldaPW.getNazwa().equals(wyborGieldy.getValue())) {
                gieldaPW.dodajIndeks(indeks);
                break;
            }
        }
    }   //TODO do poprawy

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

    public void wyswietlanieSurowcow() {    //TODO Błąd z wartoscMax i wartoscMin
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

    public void wyswietlanieGield() {
        tabela.getColumns().clear();
        ObservableList<Gielda> gieldy = FXCollections.observableArrayList(Ekonomia.getGieldy());
        TableColumn<Gielda, String> nazwa = new TableColumn<>("Nazwa");
        TableColumn<Gielda, String> kraj = new TableColumn<>("Kraj");
        TableColumn<Gielda, String> miasto = new TableColumn<>("Miasto");
        TableColumn<Gielda, String> adres = new TableColumn<>("Adres");
        TableColumn<Gielda, Double> marza = new TableColumn<>("Marza");
        nazwa.setMinWidth(200);
        nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        kraj.setMinWidth(60);
        kraj.setCellValueFactory(new PropertyValueFactory<>("kraj"));
        miasto.setMinWidth(60);
        miasto.setCellValueFactory(new PropertyValueFactory<>("miasto"));
        adres.setMinWidth(80);
        adres.setCellValueFactory(new PropertyValueFactory<>("adresSiedziby"));
        marza.setMinWidth(60);
        marza.setCellValueFactory(new PropertyValueFactory<>("marza"));

        tabela.setItems(gieldy);
        tabela.getColumns().addAll(nazwa, kraj, miasto, adres, marza);
        console.setText("Na rynku funkcjonuje " + gieldy.size() + " gield" + "\nAby dowiedziec sie wiecej kliknij " +
                "wybrana gielde");
    }

    public void wyswietlanieIndeksow() {
        tabela.getColumns().clear();
        ObservableList<Indeks> indeksy = FXCollections.observableArrayList(Ekonomia.getIndeksy());
        for (Indeks indeks : indeksy)
            indeks.wyliczWartosc();

        TableColumn<Indeks, String> nazwa = new TableColumn<>("Nazwa");
        TableColumn<Indeks, Double> wartosc = new TableColumn<>("Wartość");

        nazwa.setMinWidth(100);
        nazwa.setCellValueFactory(new PropertyValueFactory<>("nazwa"));
        wartosc.setMinWidth(100);
        wartosc.setCellValueFactory(new PropertyValueFactory<>("wartosc"));
    }

    //Szczegóły

    public void pokazSzczegoly() {
        System.out.println(tabela.getSelectionModel().getSelectedItem().getClass());

        try {
            if (tabela.getSelectionModel().getSelectedItem().getClass().equals(Inwestor.class)) {
                szczegolyInwestora((Inwestor) tabela.getSelectionModel().getSelectedItem());
            } else if (tabela.getSelectionModel().getSelectedItem().getClass().equals(Fundusz.class)) {
                szczegolyFunduszu((Fundusz) tabela.getSelectionModel().getSelectedItem());
            } else if (tabela.getSelectionModel().getSelectedItem().getClass().equals(Spolka.class)) {
                szczegolySpolki((Spolka) tabela.getSelectionModel().getSelectedItem());
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nie znaleziono dopowiedniego pliku .fxml - prosze sprawdzic zawartosc folderu Infrastructure");
        }

    }

    private LineChart<String, Number> stworzWykres(Parent root, ArrayList<Double> historiaWartosci) {
        LineChart<String, Number> wykres = (LineChart<String, Number>) root.lookup("#wykres");
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        if (procent.isSelected()) {
            series.getData().add(new XYChart.Data<>(1 + "", 100));
            for (int i = 1; i < historiaWartosci.size(); i++) {
                series.getData().add(new XYChart.Data<>((i + 1) + "",
                        historiaWartosci.get(i) / historiaWartosci.get(0) * 100));
            }
        } else
            for (int i = 0; i < historiaWartosci.size(); i++)
                series.getData().add(new XYChart.Data<>((i + 1) + "", historiaWartosci.get(i)));

        wykres.getData().add(series);

        return wykres;
    }

    public void szczegolyInwestora(Inwestor inwestor) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("szczegolyInwestora.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(tabela.getScene().getWindow());
        stage.setTitle("Szczegoly inwestora");

        TextField imie = (TextField) root.lookup("#imie");
        TextField nazwisko = (TextField) root.lookup("#nazwisko");
        TextField budzet = (TextField) root.lookup("#budzet");
        TextField pesel = (TextField) root.lookup("#pesel");
        TextField agresja = (TextField) root.lookup("#agresja");
        TextField wartoscPortfela = (TextField) root.lookup("#wartoscPortfela");

        imie.setText(inwestor.getImie());
        nazwisko.setText(inwestor.getNazwisko());
        budzet.setText(inwestor.getBudzet() + "");
        pesel.setText(inwestor.getPesel());
        agresja.setText(inwestor.getAgresja() + "");
        wartoscPortfela.setText(inwestor.getPortfel().przeliczPortfel() + "");

        LineChart<String, Number> wykres = stworzWykres(root, inwestor.getHistoriaWartosciMajatku());
        stage.showAndWait();
    }

    public void usunInwestora() {
        for (Inwestor inwestor : Ekonomia.getInwestorzyIndywidualni()) {
            if (inwestor.getImie().equals(imie.getText()) && inwestor.getNazwisko().equals(nazwisko.getText())) {
                Ekonomia.removeInwestor(inwestor);
                break;
            }
        }
        zamknijOkno((Stage) zamknij.getScene().getWindow());
    }

    public void szczegolyFunduszu(Fundusz fundusz) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("szczegolyFunduszu.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(tabela.getScene().getWindow());
        stage.setTitle("Szczegoly funduszu inwestycyjnego");

        TextField imie = (TextField) root.lookup("#imie");
        TextField nazwisko = (TextField) root.lookup("#nazwisko");
        TextField budzet = (TextField) root.lookup("#budzet");
        TextField agresja = (TextField) root.lookup("#agresja");
        TextField wartosc = (TextField) root.lookup("#wartosc");
        TextField ilosc = (TextField) root.lookup("#ilosc");
        TextField wartoscPortfela = (TextField) root.lookup("#wartoscPortfela");

        imie.setText(fundusz.getImie());
        nazwisko.setText(fundusz.getNazwisko());
        budzet.setText(fundusz.getBudzet() + "");
        agresja.setText(fundusz.getAgresja() + "");
        wartosc.setText(fundusz.getWartoscJednostki() + "");    //TODO błąd?
        ilosc.setText(fundusz.getIloscJendostek() + "");
        wartoscPortfela.setText(fundusz.getPortfel().przeliczPortfel() + "");

        LineChart<String, Number> wykres = stworzWykres(root, fundusz.getHistoriaWartosciMajatku());
        stage.showAndWait();
    }

    public void usunFundusz() {
        for (PodmiotInwestujacy fundusz : Ekonomia.getInwestorzy()) {
            if (fundusz.getImie().equals(imie.getText()) && fundusz.getNazwisko().equals(nazwisko.getText()) &&
                    fundusz.getClass().equals(Fundusz.class)) {
                Ekonomia.removeFundusz((Fundusz) fundusz);
                break;
            }
        }
        zamknijOkno((Stage) zamknij.getScene().getWindow());
    }

    public void szczegolySpolki(Spolka spolka) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("szczegolySpolki.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(tabela.getScene().getWindow());
        stage.setTitle("Szczegoly spolki");

        TextField nazwa = (TextField) root.lookup("#nazwa");
        TextField data = (TextField) root.lookup("#data");
        TextField kurs = (TextField) root.lookup("#kurs");
        TextField kursOtwarcia = (TextField) root.lookup("#kursOtwarcia");
        TextField kursMax = (TextField) root.lookup("#kursMax");
        TextField kursMin = (TextField) root.lookup("#kursMin");
        TextField wolumen = (TextField) root.lookup("#wolumen");
        TextField akcje = (TextField) root.lookup("#akcje");
        TextField freeFloat = (TextField) root.lookup("#freeFloat");
        TextField kapital = (TextField) root.lookup("#kapital");
        TextField kapitalZakladowy = (TextField) root.lookup("#kapitalZakladowy");
        TextField obroty = (TextField) root.lookup("#obroty");
        TextField przychody = (TextField) root.lookup("#przychody");
        TextField zysk = (TextField) root.lookup("#zysk");

        nazwa.setText(spolka.getNazwa());
        data.setText(spolka.getDataPierwszejWyceny());
        kurs.setText(spolka.getKursAktualny() + "");
        kursOtwarcia.setText(spolka.getKursOtwarcia() + "");
        kursMax.setText(spolka.getKursMaksymalny() + "");
        kursMin.setText(spolka.getKursMinimalny() + "");
        wolumen.setText(spolka.getWolumen() + "");
        akcje.setText(spolka.getLiczbaAkcji() + "");
        freeFloat.setText(spolka.getLiczbaAkcjiNaSprzedaz() + "");
        kapital.setText(spolka.getKapitalWlasny() + "");
        kapitalZakladowy.setText(spolka.getKapitalZakladowy() + "");
        obroty.setText(spolka.getObroty() + "");
        przychody.setText(spolka.getPrzychod() + "");
        zysk.setText(spolka.getZysk() + "");

        LineChart<String, Number> wykres = stworzWykres(root, spolka.getHistoriaKursu());
        stage.showAndWait();
    }

    public void usunSpolke() {
        for (Spolka spolka : Ekonomia.getAktywa().getSpolki()) {
            if (spolka.getNazwa().equals(nazwa.getText()) && spolka.getDataPierwszejWyceny().equals(data.getText())) {
                Ekonomia.getAktywa().removeSpolka(spolka);
                break;
            }
        }
        zamknijOkno((Stage) zamknij.getScene().getWindow());
    }

    public void szczegolyWaluty(Waluta waluta) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("szczegolyWaluty.fxml"));
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(tabela.getScene().getWindow());
        stage.setTitle("Szczegoly waluty");

    }
}
