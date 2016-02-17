package fr.esgi.meta.engine.factories;

import fr.esgi.meta.simulation.battleship.unit.BattleShipBoard;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.simulation.microorganism.MicroorganismBoard;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.simulation.zombiland.ZombieBoard;

public class BoardFactory extends Factory<Board, String> {

    @Override
    public Board getInstance(String type) {
        switch (type) {
            case "zombies-land":
                return new ZombieBoard();
            case "BattleShip":
                return new BattleShipBoard();
            case "microorganism":
                return new MicroorganismBoard();
            default:
                throw new RuntimeException("Unknown Simulation");
        }
    }
}
