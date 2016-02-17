package fr.esgi.meta.simulation.battleship.unit;

import fr.esgi.meta.engine.units.UnitState;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourDisplacement;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;

public class Boat extends Unit {

    //Boat parameters
    private int life;
    private int damages;


    protected Boat(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense, BehaviourDisplacement behaviourDisplacement, UnitState unitState) {
        super(type, behaviourFight, behaviourDefense, behaviourDisplacement, unitState);

        switch (type) {
            case "aircraft-carrier":
                this.life = 500;
                this.damages = 0;
                break;

            case "cruiser":
                this.life = 300;
                this.damages = 50;
                break;

            case "submarine":
                this.life = 450;
                this.damages = 40;
                break;

            case "frigate":
                this.life = 200;
                this.damages = 70;
                break;
        }
    }

    /*
    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }*/

    public int getDamages() {
        return damages;
    }

    public void setDamages(int damages) {
        this.damages = damages;
    }

}
