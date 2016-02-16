package fr.esgi.meta;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.simulations.Simulator;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;

public class MainController {
    @FXML
    private TextArea eventTextArea;
    @FXML
    private Canvas canvas;

    int[][] tiles = new int[][]{
        { 16,16,16,16,16,16,16,16,16,16,16 },
        { 16,56,56,56,56,56,56,56,56,56,16 },
        { 16,56,56,56,56,56,56,56,56,56,16 },
        { 16,56,56,56,56,56,56,56,56,56,16 },
        { 16,56,56,56,16,16,56,56,56,16,16 },
        { 16,56,56,56,56,56,56,56,16,56,16 },
        { 16,56,56,56,16,16,16,16,16,56,16 },
        { 16,56,56,56,16,56,56,56,56,56,16 },
        { 16,56,56,56,16,56,56,56,56,56,16 },
        { 16,56,56,56,56,56,56,56,16,56,16 },
        { 16,56,56,56,56,56,56,56,56,56,16 },
        { 16,16,16,16,16,16,16,16,16,16,16 }
    };

    final String IMAGE_PATH = "http://server.vuzi.fr/tests/algo/floor.png";
    final int TILE_WIDTH = 8;
    final int TILE_HEIGHT = 8;
    private double tileWidth;
    private double tileHeight;
    private Image tilesImg;

    @FXML
    private void initialize() {
        // Load the tile image
        tilesImg = new Image(IMAGE_PATH);
        tilesImg.errorProperty().addListener((observable, oldValue, newValue) -> {
            System.out.println("ERROR");
            System.out.println(newValue);
        });

        // Guess the tile width and height
        tileWidth = tilesImg.getWidth() / TILE_WIDTH;
        tileHeight = tilesImg.getHeight() / TILE_HEIGHT;
    }

    public void update(Simulator simulator) {
        Board board = simulator.getBoard();

        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Update canvas size
        canvas.setWidth(board.getWidth() * tileWidth);
        canvas.setHeight(board.getHeight() * tileHeight);

        for(int x = 0; x < board.getWidth(); x++) {
            for(int y = 0; y < board.getHeight(); y++) {
                int tileValue = board.getZones()[x][y].getTileType();
                int tileValueY = tileValue / TILE_WIDTH;
                int tileValueX = tileValue % TILE_WIDTH;

                gc.drawImage(tilesImg, (double)tileValueX * tileHeight, (double)tileValueY * tileWidth, tileWidth, tileHeight, (double)x * tileWidth, (double)y * tileHeight, tileWidth, tileHeight);
            }
        }

        //drawShapes(gc);

    }
}
