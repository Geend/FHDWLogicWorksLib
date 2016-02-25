package net.torbenvoltmer.fhdw.es.mcgenerator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by torben on 22.02.16.
 */
public class MCGenerator extends javafx.application.Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Microcode PROM Generator");
        stage.setWidth(1100);
        stage.setHeight(600);

        Parent root = FXMLLoader.load(getClass().getResource("/mainwindow.fxml"));

        Scene scene = new Scene(root, 1200, 800);

        stage.setScene(scene);
        stage.show();

        new MainWindowController();
    }
}
