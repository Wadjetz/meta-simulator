package fr.esgi.meta.engine.simulations;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.engine.units.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public abstract class Simulator {

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

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ", "[", "]");
        sj.add("Simulator").add(name);
        factions.stream().forEach(f -> sj.add(f.toString()));
        return sj.toString();
    }

    public void run() {
        System.out.println(name + " simulation run");
        System.out.println(this);

        List<Unit> allUnits = factions.stream().<Unit>flatMap(f -> f.getUnits().stream()).collect(Collectors.toList());
        board.randomDispatch(allUnits);
        System.out.println(board);

        getFactions().get(0).getUnits().get(0).figth(getFactions().get(1).getUnits().get(0));
    }
}