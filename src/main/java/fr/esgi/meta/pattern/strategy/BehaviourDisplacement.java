package fr.esgi.meta.pattern.strategy;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.graph.Edge;
import fr.esgi.meta.utils.graph.Vertex;
import fr.esgi.meta.utils.logger.LogLevel;

import java.util.List;
import java.util.Optional;

public interface BehaviourDisplacement {

    int getDistancePerTurn();

    default void displace(Unit me, Board board, Zone currentZone) {
        System.out.println(me.toString());

        // Get the nearest enemy
        List<Vertex> nearestEnemyPath = board.findNearest(currentZone, vertex -> {
            for(Edge e : vertex.getAdjacencies()) {
                Vertex neighbor = e.getOtherSide(vertex);

                if(((Zone) neighbor).getUnit().map(me::isEnemyWith).orElseGet(() ->  false))
                    return true; // Case near an enemy
            }
            return false;
        });

        if(nearestEnemyPath == null) // No enemy
            return;

        Zone toGo = currentZone;
        int i = getDistancePerTurn(); // Number of iteration
        while(i > 0 && nearestEnemyPath.size() > 0) {
            toGo = (Zone) nearestEnemyPath.remove(0);
            i--;
        }

        if(!toGo.equals(currentZone)) {
            Logger.log(LogLevel.INFO, me.toString() + " has moved from " + currentZone + " to " + toGo);
            currentZone.setUnit(Optional.empty());
            toGo.setUnit(Optional.of(me));
            me.setZone(toGo);
        } else
            Logger.log(LogLevel.INFO, me.toString() + " has not moved at " + currentZone);
    }
}
