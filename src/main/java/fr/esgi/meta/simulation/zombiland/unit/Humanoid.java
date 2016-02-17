package fr.esgi.meta.simulation.zombiland.unit;

import fr.esgi.meta.engine.units.UnitState;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.engine.units.HasInventory;
import fr.esgi.meta.simulation.zombiland.item.Armor;
import fr.esgi.meta.engine.units.Item;
import fr.esgi.meta.simulation.zombiland.item.Weapon;

import java.util.ArrayList;
import java.util.List;

public abstract class Humanoid extends Unit implements HasInventory {

    private List<Item> inventory;

    public Humanoid(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense, BehaviourDisplacement behaviourDisplacement, UnitState unitState) {
        super(type, behaviourFight, behaviourDefense, behaviourDisplacement, unitState);
    }

    @Override
    public List<Item> getInventory() {
        if(inventory == null)
            inventory = new ArrayList<>();

        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public List<Weapon> getWeapons() {
        return getItems(Weapon.class);
    }

    public void setWeapons(List<Item> weapons) {
        addItems(weapons);
    }

    public List<Armor> getArmors() {
        return getItems(Armor.class);
    }

    public void setArmors(List<Item> armors) {
        addItems(armors);
    }
}
