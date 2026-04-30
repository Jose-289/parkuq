package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        // La ruta debe empezar con / y seguir el camino de las carpetas desde src
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/resources/Login.view.fxml"));

        if (loader.getLocation() == null) {
            throw new RuntimeException("¡No se encontró el archivo FXML! Revisa la ruta.");
        }

        Scene scene = new Scene(loader.load());
        stage.setTitle("ParkuQ - Sistema de Parqueadero");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}