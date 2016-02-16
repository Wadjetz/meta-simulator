package fr.esgi.meta.engine.simulations;

import fr.esgi.meta.Logger;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.pattern.observer.Observable;
import fr.esgi.meta.pattern.observer.Observer;
import fr.esgi.meta.utils.logger.LogLevel;
import fr.esgi.meta.view.TileSet;
import fr.esgi.meta.view.TileType;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public abstract class Simulator implements Observable<Simulator.SimulatorEvent> {

    /**
     * List of observers, called when each turn of the simulation is done.
     */
    List<Observer<SimulatorEvent>> observerList = new ArrayList<>();

    @Override
    public void register(Observer<SimulatorEvent> observer) {
        observerList.add(observer);
    }

    @Override
    public void unregister(Observer<SimulatorEvent> observer) {
        observerList.remove(observer);
    }

    @Override
    public void emit(SimulatorEvent simulatorEvent) {
        observerList.forEach(simulatorEventObserver -> simulatorEventObserver.update(simulatorEvent));
    }

    public enum SimulatorEvent {
        TURN, GAME_OVER
    }

    public static final boolean DEBUG = false;

    protected String name;
    protected Board board;
    protected List<Faction> factions;

    private TileSet tileSet;
    private List<TileType> tileTypeList;

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

            // Clean dead units
            factions.forEach(Faction::clearDeadUnit);
            factions = factions.stream().filter(f -> f.getUnits().size() != 0).collect(Collectors.toList());

            for(Faction faction : factions) {
                for(Unit unit : faction.getUnits()) {
                    // Move the unit on the board
                    unit.move(board);

                    // Attack every other unit in the neighbors zones
                    unit.getNeighborsUnits().forEach(unit::fight);
                }
            }

            // Update every observer
            emit(SimulatorEvent.TURN);

            // Log the current board
            Logger.log(LogLevel.VERBOSE, board.toString());

            // Sleep waiting for the next step
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {}

            if (isGameOver()) {
                emit(SimulatorEvent.GAME_OVER);

                Logger.log(LogLevel.INFO, "Game Over");
                Logger.log(LogLevel.INFO, "The Winner is : " + factions.stream().map(Faction::getName).collect(Collectors.toList()));
                break;
            }

            turn++;
        }
    }

    public TileSet getTileSet() {
        return tileSet;
    }

    public void setTileSet(TileSet tileSet) {
        this.tileSet = tileSet;
    }

    public List<TileType> getTileTypeList() {
        return tileTypeList;
    }

    public void setTileTypeList(List<TileType> tileTypeList) {
        this.tileTypeList = tileTypeList;
    }

    private boolean isGameOver() {
        for (Faction faction : factions) {
            for (Faction others : factions) {
                if (faction != others) {
                    if (faction.getAffiliation(others) == -1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ", "[", "]");
        sj.add("Simulator").add(name);
        factions.stream().forEach(f -> sj.add(f.toString()));
        return sj.toString();
    }
}
