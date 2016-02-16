package fr.esgi.meta.battleship.unit;

import fr.esgi.meta.pattern.observer.Observable;
import fr.esgi.meta.pattern.observer.Observer;
import fr.esgi.meta.pattern.strategy.BehaviourDefense;
import fr.esgi.meta.pattern.strategy.BehaviourFight;
import fr.esgi.meta.engine.units.Unit;

public class Boats extends Unit implements Observable {

    //Boat parameters
    private int life;
    private int damages;


    protected Boats(String type, BehaviourFight behaviourFight, BehaviourDefense behaviourDefense) {
        super(type, behaviourFight, behaviourDefense, null);

        switch (type) {
            case "porte-avions":
                this.life = 500;
                this.damages = 0;
                break;

            case "croiseur":
                this.life = 300;
                this.damages = 90;
                break;

            case "sous-marin":
                this.life = 450;
                this.damages = 70;
                break;

            case "fregate":
                this.life = 200;
                this.damages = 100;
                break;
        }
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

    @Override
    public void register(Observer observer) {

    }

    @Override
    public void unregister(Observer observer) {

    }

    @Override
    public void emit() {

    }
}
