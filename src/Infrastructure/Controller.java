package Infrastructure;

import Model.*;
import Repository.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    int numerSesji = 0;

    @FXML
    private Button przeliczSesje, dodajInwestora, dodajSpolke, dodajWalute, dodajSurowiec, dodajGielde,
            pokazInwestorow, pokazSpolki, pokazWaluty, pokazSurowce, pokazGieldy, zamknij;

    @FXML
    private Label nrSesji;

    @FXML
    private TextField imie, nazwisko, budzet;

    public void executeMenuAction(ActionEvent event) {
        Stage stage;
        Parent root;
        if (event.getSource().equals(przeliczSesje)) {
            numerSesji++;
            nrSesji.setText(numerSesji + "");
            //TODO wywołanie przeliczenia sesji
        }

        if (event.getSource().equals(zamknij))
            zamknijOkno((Stage) zamknij.getScene().getWindow());

        try {
            if (event.getSource().equals(dodajInwestora)) {     //TODO dodawanie
                dodawanieInwestora();


            } else if (event.getSource().equals(dodajSpolke)) {

            } else if (event.getSource().equals(dodajWalute)) {

            } else if (event.getSource().equals(dodajSurowiec)) {

            } else if (event.getSource().equals(dodajGielde)) {

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
        imie.setText(Nazwy.getImie());
        nazwisko.setText(Nazwy.getNazwisko());
        //TODO dodawanie budżetu
        stage.showAndWait();


    }

    public void potwierdzenieInwestora() {

    }

}
