package fr.esgi.meta.zombiland;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.RandomValueGenerator;

import java.util.List;
import java.util.Optional;

public class ZombieBoard extends Board {
    @Override
    public void randomDispatch(List<Unit> units) {
        System.out.println("ZombieBoard.randomDispatch");
        for (Unit unit : units) {
            int x = RandomValueGenerator.get(1, getWidth());
            int y = RandomValueGenerator.get(1, getHeight());

            System.out.println("randomDispatch x=" + x + " y=" + y);

            getZones()[x][y].setUnit(Optional.of(unit));
        }
    }

}
