package fr.esgi.meta.controller;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.RandomValueGenerator;
import fr.esgi.meta.utils.SimulatorParser;
import fr.esgi.meta.utils.logger.CallbackLogger;
import fr.esgi.meta.utils.logger.LogLevel;
import fr.esgi.meta.view.TileType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Controller of the main panel
 */
public class MainController {
    @FXML
    private TextArea eventTextArea;
    @FXML
    private Canvas canvas;

    private Simulator simulator;
    private Thread simulatorThread;

    private Image canvasCache;
    private Stage stage;

    @FXML
    private void initialize() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Logger.addLogger(new CallbackLogger(LogLevel.INFO, s -> {
            Platform.runLater(() -> {
                eventTextArea.appendText("\n[" + format.format(new Date()) + "] -> " + s);
            });
        }));
    }

    @FXML
    private void nextTurn() {
        if(simulator != null)
            simulatorThread.interrupt();
    }

    /**
     * Update the view of the controller. Note that if the view have already been drawn, a cached version is used for
     * the background
     */
    private void update() {
        update(false);
    }

    private void update(boolean force) {
        Board board = simulator.getBoard();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw the canvas
        if(canvasCache == null || force) {
            // No cached canvas, draw everything
            canvas.setWidth(board.getWidth() * board.getTileSet().getTileWidth());
            canvas.setHeight(board.getHeight() * board.getTileSet().getTileHeight());

            for (int x = 0; x < board.getWidth(); x++) {
                for (int y = 0; y < board.getHeight(); y++) {
                    int tileTypeValue = board.getZones()[x][y].getTileType();

                    for (TileType tileType : board.getTileTypeList()) {
                        if (tileType.getType() == tileTypeValue) {
                            int randomTilePos = RandomValueGenerator.get(0, tileType.getTileSetValues().length);
                            int tilePos = tileType.getTileSetValues()[randomTilePos];

                            int tilePosY = tilePos / board.getTileSet().getTileNbHeight();
                            int tilePosX = tilePos % board.getTileSet().getTileNbWidth();

                            gc.drawImage(board.getTileSet().getTileSetImage(),
                                    (double) tilePosX * board.getTileSet().getTileWidth(), (double) tilePosY * board.getTileSet().getTileHeight(),
                                    board.getTileSet().getTileWidth(), board.getTileSet().getTileHeight(),
                                    (double) x * board.getTileSet().getTileWidth(), (double) y * board.getTileSet().getTileHeight(),
                                    board.getTileSet().getTileWidth(), board.getTileSet().getTileHeight());

                            break;
                        }
                    }
                }
            }

            SnapshotParameters params = new SnapshotParameters();
            params.setFill(Color.TRANSPARENT);
            canvasCache = canvas.snapshot(params, null);
        } else {
            // Canvas cached, only draw the cache
            gc.drawImage(canvasCache, 0, 0);
        }

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                if (board.getZones()[x][y].getUnit().isPresent()) {
                    Unit unit = board.getZones()[x][y].getUnit().get();
                    gc.setFont(Font.font("Verdana", 60));
                    gc.fillText((unit.getType().charAt(0) + "").toUpperCase(), x * board.getTileSet().getTileWidth(), (y + 1) * board.getTileSet().getTileHeight() - 10);

                    gc.setFont(Font.font("Verdana", 10));
                    gc.fillText(unit.getFaction().getName(), x * board.getTileSet().getTileWidth(), (y + 1) * board.getTileSet().getTileHeight());
                }
            }
        }
    }

    /**
     * Set the simulator to the controller. The controller will start and handle the simulation
     *
     * @param simulator The simulation to use
     */
    public void setSimulator(Simulator simulator) {
        this.simulator = simulator;
        
        update(true);
        stage.sizeToScene();

        // Register as an observer
        simulator.addObserver((event) -> {
            if(event.equals(Simulator.SimulatorEvent.TURN))
                Platform.runLater(this::update);
            else if(event.equals(Simulator.SimulatorEvent.GAME_OVER))
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Simulation is over");
                    alert.setHeaderText("The simulation is over !");
                    alert.setContentText("Every unit on the board is either dead, ally or neutrals between each others");

                    alert.showAndWait();
                });
        });

        // Launch the simulator
        simulatorThread = new Thread(() -> {
            this.simulator.run();
        });
        simulatorThread.start();
    }

    @FXML
    private void loadZombieSimulation() {
        loadSimulation("zombies.xml");
    }

    @FXML
    private void loadMicroorganismeSimulation() {
        loadSimulation("microorganism.xml");
    }

    @FXML
    private void loadBattleshipSimulation() {
        loadSimulation("battleship.xml");
    }

    private void loadSimulation(String filename) {
        Simulator simulator = null;

        try {
            simulator = new SimulatorParser().parse(filename);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
            Logger.log(LogLevel.ERROR, e.getMessage());
        }

        // Set the simulator to the view
        setSimulator(simulator);
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
