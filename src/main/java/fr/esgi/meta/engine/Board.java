package fr.esgi.meta.engine;

import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.RandomValueGenerator;

import java.util.List;
import java.util.Random;

/**
 * Default board where units interact
 *
 * Created by vuzi on 07/01/2016.
 */
public abstract class Board {

    public  Board(){}

    Zone[][] zones;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    int width = 0;
    int height = 0;

    public Board(int width, int height) {
        zones = new Zone[width][height];
        System.out.println("Board width=" + width + " height=" + height);
        this.width = width;
        this.height = height;
        zones = new Zone[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                zones[i][j] = new Zone();
            }
        }
    }

    public  void randomDispatch(List<Unit> units) {
        for (Unit unit : units) {
            if(unit.getFaction().getLeader().get().getType().equals("porte-avions"))
                randomBattleShip(unit);
            else
                randomZombie(unit);
        }
    }

    private void randomZombie(Unit unit){
        int x = RandomValueGenerator.get(1, width);
        int y = RandomValueGenerator.get(1, height);

        System.out.println("randomDispatch x=" + x + " y=" + y);

        zones[y][x].addUnit(unit);
    }

    private void randomBattleShip(Unit unit)
    {
        int x =0;
        int y =0;
        Random rand = new Random();

        if(unit.getFaction().getLeader().get().getFaction().getName().equals("Allie")){
            x = rand.nextInt(width - 1 + 1) + 1;
            y = rand.nextInt(height - 1 + 1);
        }
        else{
            x = rand.nextInt(width - (width/2) + 1) + (width/2);
            y = rand.nextInt(height - 1 + 1);
        }

        System.out.println(unit.getFaction().getLeader().get().getFaction().getName()+"  randomDispatch x=" + x + " y=" + y);

        zones[y][x].addUnit(unit);
    }

    public Zone[][] getZones() {
        return zones;
    }
}
