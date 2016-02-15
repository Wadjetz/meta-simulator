package fr.esgi.meta.pattern.strategy;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.utils.graph.Edge;
import fr.esgi.meta.utils.graph.Vertex;

import java.util.List;

public interface BehaviourDisplacement {

    int getDistancePerTurn();

    default void displace(Unit me, Board board, Zone currentZone) {
        // Get the nearest enemy
        List<Vertex> nearestEnemyPath = board.findNearest(currentZone, vertex -> {
            for(Edge e : vertex.getAdjacencies()) {
                if(((Zone) vertex).hasUnit() && me.isEnemyWith(((Zone) vertex).getUnit()))
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
            System.out.println(me.getName() + " has moved from " + currentZone + " to " + toGo);
            currentZone.setUnit(null);
            toGo.setUnit(me);
            me.setZone(toGo);
        } else
            System.out.println(me.getName() + " has not moved");
    }
}
