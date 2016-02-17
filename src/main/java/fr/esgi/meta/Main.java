package fr.esgi.meta;

import fr.esgi.meta.controller.MainController;
import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.utils.SimulatorParser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        // Load the engine view
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("/view/main.fxml").openStream());
        MainController controller = fxmlLoader.getController();
        controller.setStage(primaryStage);

        primaryStage.setTitle("Simulator 1.0.0");
        primaryStage.setScene(new Scene(root));
        primaryStage.sizeToScene();
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
