package fr.esgi.meta.engine;

/**
 * Default board where units interact
 *
 * Created by vuzi on 07/01/2016.
 */
public abstract class Board {

    Zone[][] zones;

    public Board(int width, int height) {

        zones = new Zone[width][height];
    }

}
