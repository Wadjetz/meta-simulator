package fr.esgi.meta.engine.factories;

import fr.esgi.meta.battleship.unit.BattleShipBorad;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.zombiland.ZombieBoard;

import java.util.List;

/**
 * Created by 626 on 15/02/2016.
 */
public class BoardFactory extends Board {

    public BoardFactory(int width, int height) {
        super(width, height);
    }

    public BoardFactory(String name,int width, int height) {
        super(width,height);
        switch (name) {
            case "zombies-land":
                new ZombieBoard(width,height);
            case "BattleShip":
                new BattleShipBorad(width,height);
            default:
                 new RuntimeException("Unknown Simulation");
        }
    }
}
