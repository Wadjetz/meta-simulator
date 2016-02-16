package fr.esgi.meta.engine.factories;

import fr.esgi.meta.engine.simulations.BattleShipSimulator;
import fr.esgi.meta.engine.simulations.MicroorganismSimulator;
import fr.esgi.meta.pattern.factory.Factory;
import fr.esgi.meta.engine.simulations.Simulator;
import fr.esgi.meta.engine.simulations.ZombiesLandSimulator;

public class SimulatorFactory extends Factory<Simulator, String> {
    @Override
    public Simulator getInstance(String type) {
        switch (type) {
            case "zombies-land":
                return new ZombiesLandSimulator();
            case "BattleShip":
                return new BattleShipSimulator();
            case "microorganism":
                return new MicroorganismSimulator();
            default:
                throw new RuntimeException("Unknown Simulation");
        }
    }
}
