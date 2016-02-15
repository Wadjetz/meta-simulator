package fr.esgi.meta.battleship.unit;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.units.Unit;

import java.util.List;
import java.util.Random;

public class BattleShipBoard extends Board {
    private void randomBattleShip(Unit unit) {
        int x = 0;
        int y = 0;
        Random rand = new Random();

        if (unit.getFaction().getLeader().get().getFaction().getName().equals("Allie")) {
            x = rand.nextInt(getWidth() - 1 + 1) + 1;
            y = rand.nextInt(getHeight() - 1 + 1);
        } else {
            x = rand.nextInt(getWidth() - (getWidth() / 2) + 1) + (getWidth() / 2);
            y = rand.nextInt(getHeight() - 1 + 1);
        }

        System.out.println(unit.getFaction().getLeader().get().getFaction().getName()+"  randomDispatch x=" + x + " y=" + y);

        getZones()[y][x].addUnit(unit);
    }

    @Override
    public void randomDispatch(List<Unit> units) {
        System.out.println("BattleShipBoard.randomDispatch");
        units.forEach(this::randomBattleShip);
    }
}
