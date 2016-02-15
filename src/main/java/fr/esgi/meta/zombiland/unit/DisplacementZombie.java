package fr.esgi.meta.zombiland.unit;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;

public class DisplacementZombie implements BehaviourDisplacement {
    @Override
    public void displace(Unit me, Board board, Zone currentZone, Zone nearestEnemy) {
        // Zombie will move two by two, in the direction of the nearest enemy
        int newX, newY;

        double x = (double)currentZone.getX() - (double)nearestEnemy.getX();
        double y = (double)currentZone.getY() - (double)nearestEnemy.getY();

        newX = currentZone.getX() + (x > 0 ? 2 : -2);
        newY = currentZone.getY() + (y > 0 ? 2 : -2);

        // Check for out of bound values
        if(newX < 0 || newX > board.getZones().length)
            newX = currentZone.getX();
        if(newY < 0 || newY > board.getZones()[0].length)
            newY = currentZone.getY();

        // Move the unit
        currentZone.removeUnit(me);
        board.getZones()[newX][newY].addUnit(me);
    }
}
