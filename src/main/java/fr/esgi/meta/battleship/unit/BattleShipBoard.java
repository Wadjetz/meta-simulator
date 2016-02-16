package fr.esgi.meta.battleship.unit;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.units.Unit;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class BattleShipBoard extends Board {

    @Override
    public void randomDispatch(List<Unit> units) {
        for (Unit unit : units) {
            int x =0;
            int y =0;
            Random rand = new Random();

            if(unit.getFaction().getLeader().get().getFaction().getName().equals("Allie")){
                x = rand.nextInt(getWidth()/2 - 1) + 1;
                y = rand.nextInt(getHeight() - 1 + 1);
            }
            else{
                x = rand.nextInt(getWidth() - (getWidth()/2) ) + (getWidth()/2);
                y = rand.nextInt(getHeight() - 1 + 1);
            }

            System.out.println(unit.getFaction().getLeader().get().getFaction().getName()+"  randomDispatch x=" + x + " y=" + y);

            getZones()[x][y].setUnit(Optional.of(unit));
        }

    }

}
