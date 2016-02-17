package fr.esgi.meta.engine.simulations;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.observer.Observer;
import fr.esgi.meta.utils.logger.AbstractLogger;
import fr.esgi.meta.utils.logger.ConsoleLogger;
import fr.esgi.meta.utils.logger.LogLevel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public abstract class Simulator {
    /**
     * List of observers, called when each turn of the simulation is done.
     */
    List<Observer> observerList = new ArrayList<Observer>();

    protected String name;
    protected Board board;
    protected List<Faction> factions;

    public Simulator() {
        factions = new ArrayList<>();
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addFaction(Faction faction) {
        factions.add(faction);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void run() {
        int turn = 1;
        Logger.log(LogLevel.INFO, name + " simulation launched");
        Logger.log(LogLevel.INFO, this.toString());

        List<Unit> allUnits = factions.stream().<Unit>flatMap(f -> f.getUnits().stream()).collect(Collectors.toList());
        board.randomDispatch(allUnits);

        while(true) {
            Logger.log(LogLevel.INFO, "Turn " + turn + " ------------------------");
            Logger.log(LogLevel.VERBOSE, board.toString());

            for(Faction faction : factions) {
                for(Unit unit : faction.getUnits()) {
                    // Move the unit on the board
                    unit.move(board);

                    // Attack every other unit in the neighbors zones
                    unit.getNeighborsUnits().forEach(unit::fight);
                }
            }

            // Update every observer
            observerList.forEach(Observer::update);

            // Sleep waiting for the next step
            try {
                Thread.sleep(Long.MAX_VALUE);
            } catch (InterruptedException ignored) {}

            turn++;
        }
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ", "[", "]");
        sj.add("Simulator").add(name);
        factions.stream().forEach(f -> sj.add(f.toString()));
        return sj.toString();
    }
}
