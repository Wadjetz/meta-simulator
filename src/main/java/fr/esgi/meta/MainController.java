package fr.esgi.meta;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.RandomValueGenerator;
import fr.esgi.meta.view.TileType;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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

    @FXML
    private void initialize() {}

    @FXML
    private void nextTurn() {
        simulatorThread.interrupt();
    }

    /**
     * Update the view of the controller. Note that if the view have already been drawn, a cached version is used for
     * the background
     */
    private void update() {
        Board board = simulator.getBoard();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Draw the canvas
        if(canvasCache == null) {
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
                    gc.fillText((unit.getType().charAt(0) + "").toUpperCase(), x * board.getTileSet().getTileWidth(), y * board.getTileSet().getTileHeight());
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

        // Register as an observer
        simulator.addObserver(() -> Platform.runLater(this::update));

        // Launch the simulator
        simulatorThread = new Thread(() -> {
            this.simulator.run();
        });
        simulatorThread.start();
    }
}
