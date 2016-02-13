package fr.esgi.meta.battleship.unit;

import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;

public class Boats extends Unit {

    //Boat parameters
    private int life;
    private int damages;


    protected Boats(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense) {
        super(type, behaviourFight, behaviourDefense);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDamages() {
        return damages;
    }

    public void setDamages(int damages) {
        this.damages = damages;
    }
}
