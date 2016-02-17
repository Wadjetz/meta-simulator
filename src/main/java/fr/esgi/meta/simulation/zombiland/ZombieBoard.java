package fr.esgi.meta.simulation.zombiland;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.RandomValueGenerator;
import fr.esgi.meta.utils.logger.LogLevel;

import java.util.List;
import java.util.Optional;

public class ZombieBoard extends Board {
    @Override
    public void randomDispatch(List<Unit> units) {
        Logger.log(LogLevel.VERBOSE, "ZombieBoard.randomDispatch");
        for (Unit unit : units) {
            do {
                int x = RandomValueGenerator.get(1, getWidth());
                int y = RandomValueGenerator.get(1, getHeight());

                if(getZones()[x][y].isEmpty()) {
                    getZones()[x][y].setUnit(Optional.of(unit));
                    Logger.log(LogLevel.VERBOSE, "randomDispatch x=" + x + " y=" + y);
                    break;
                }
            } while(true);
        }
    }
}
