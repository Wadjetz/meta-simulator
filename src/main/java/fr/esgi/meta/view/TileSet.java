package fr.esgi.meta.view;

import javafx.scene.image.Image;

/**
 * Tile set, image + info, used in the UI
 *
 * Created by Vuzi on 16/02/2016.
 */
public class TileSet {

    private final Image tileSetImage;
    private final int tileNbWidth;
    private final int tileNbHeight;
    private double tileWidth;
    private double tileHeight;

    public TileSet(Image tileSetImage, int tileNbWidth, int tileNbHeight) {
        this.tileSetImage = tileSetImage;
        this.tileNbWidth = tileNbWidth;
        this.tileNbHeight = tileNbHeight;

        tileWidth = tileSetImage.getWidth() / tileNbWidth;
        tileHeight = tileSetImage.getHeight() / tileNbHeight;
    }

    public Image getTileSetImage() {
        return tileSetImage;
    }

    public int getTileNbWidth() {
        return tileNbWidth;
    }

    public int getTileNbHeight() {
        return tileNbHeight;
    }

    public double getTileWidth() {
        return tileWidth;
    }

    public double getTileHeight() {
        return tileHeight;
    }
}
