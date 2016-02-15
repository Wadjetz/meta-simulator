package fr.esgi.meta.engine.simulations;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.observer.Observable;
import fr.esgi.meta.pattern.observer.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 626 on 15/02/2016.
 */
public class BattleShipSimulator extends Simulator implements Observable{

    List<Observer> observerList = observerList = new ArrayList<Observer>();


    @Override
    public void run() {
        System.out.println(name + " simulation run");
        //System.out.println(this);

        List<Unit> allUnits = factions.stream().<Unit>flatMap(f -> f.getUnits().stream()).collect(Collectors.toList());
        board.randomDispatch(allUnits);
        for (Unit unit : allUnits) {

        }
        //System.out.println(board);

        getFactions().get(0).getUnits().get(0).figth(getFactions().get(1).getUnits().get(0));
    }

    @Override
    public void register(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void emit() {
        for (Observer observer : observerList) {
            observer.update();
        }
    }
}
