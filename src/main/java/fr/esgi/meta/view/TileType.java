package fr.esgi.meta.view;

/**
 * Created by Vuzi on 16/02/2016.
 */
public class TileType {

    int type;
    int[] tileSetValues;
    boolean isWall;

    public TileType(int type, int[] tileSetValues, boolean isWall) {
        this.type = type;
        this.tileSetValues = tileSetValues;
        this.isWall = isWall;
    }

    public int getType() {
        return type;
    }

    public int[] getTileSetValues() {
        return tileSetValues;
    }

    public boolean isWall() {
        return isWall;
    }
}
