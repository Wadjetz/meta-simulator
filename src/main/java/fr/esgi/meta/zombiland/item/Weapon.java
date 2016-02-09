package fr.esgi.meta.zombiland.item;

import fr.esgi.meta.simulator.Item;

public class Weapon extends Item {
    private int ammunition = 0;

    public int getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(int ammunition) {
        this.ammunition = ammunition;
    }

    @Override
    public String toString() {
        return "Weapon(" + getName() + ", " + ammunition + ")";
    }
}
