package fr.esgi.meta.zombiland;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.RandomValueGenerator;

import java.util.List;

public class ZombieBoard extends Board {
    public ZombieBoard(int width, int height) {
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
