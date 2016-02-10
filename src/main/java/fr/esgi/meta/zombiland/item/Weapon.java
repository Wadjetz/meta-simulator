package fr.esgi.meta.zombiland.item;

import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.utils.RandomValueGenerator;

/**
 * Weapon. A weapon is defined by its damages, its range
 *
 * Created by vuzi on 07/01/2016.
 */
public class Weapon extends Item {

    private int ammunition = 0;

    /** Damages provided by the weapon */
    private RandomValueGenerator damage = new RandomValueGenerator(0D, 10D);

    /** Range of the weapon (0 = melee) */
    private double range = 0D;

    public double getDamageValue() {
        return damage.getValue();
    }

    public RandomValueGenerator getDamage() {
        return damage;
    }

    public void setDamage(RandomValueGenerator damage) {
        this.damage = damage;
    }

    public double getRange() {
        return range;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    @Override
    public String toString() {
        return "Weapon(" + getType() + ", " + ammunition + ")";
    }
}

