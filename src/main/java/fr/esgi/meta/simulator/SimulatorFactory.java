package fr.esgi.meta.simulator;

import fr.esgi.meta.pattern.Factory;

public class SimulatorFactory extends Factory<Simulator, String> {
    @Override
    public Simulator getInstance(String type) {
        switch (type) {
            case "zombies-land":
                return new ZombiesLandSimulator();
            default:
                throw new RuntimeException("Unknown Simulation");
        }
    }
}
