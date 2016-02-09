package fr.esgi.meta.zombiland.item;

import fr.esgi.meta.simulator.Item;
import fr.esgi.meta.simulator.RandomValueGenerator;

public class Armor extends Item {
    private enum ArmorPlacement {
        HEAD, CHEST, LEGS, OTHER
    }

    /** Resistance provided by the armor */
    private RandomValueGenerator resistance = new RandomValueGenerator(0D, 0D);

    /** Placement of the armor on the caracter */
    private ArmorPlacement placement = ArmorPlacement.OTHER;

    public ArmorPlacement getPlacement() {
        return placement;
    }

    public void setPlacement(ArmorPlacement placement) {
        this.placement = placement;
    }

    public double computeDamage(double dmg) {
        return dmg - dmg * resistance.getValue();
    }
}
