package fr.esgi.meta.battleship.unit;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.RandomValueGenerator;

import java.util.List;

/**
 * Created by 626 on 15/02/2016.
 */
public class BattleShipBorad extends Board {
    public BattleShipBorad(int width, int height) {
        super(width, height);
    }

    @Override
    public void randomDispatch(List<Unit> units) {
        for (Unit unit : units) {
            int x = RandomValueGenerator.get(1, getWidth());
            int y = RandomValueGenerator.get(1, getHeight());

            System.out.println("randomDispatch x=" + x + " y=" + y);

            getZones()[x][y].addUnit(unit);
        }
    }
}
