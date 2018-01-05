package Infrastructure;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));

        primaryStage.setTitle("Symulator Gieldy");
        Scene scene = new Scene(root, 1000, 600);
        primaryStage.setScene(scene);

        Ekonomia ekonomia = new Ekonomia();
        ekonomia.inicjalizujSymulacje();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
