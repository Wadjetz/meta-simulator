package fr.esgi.meta.simulator;

import java.util.List;

public abstract class Simulator {
    private Board board;
    private List<Faction> factions;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void addFaction(Faction faction) {
        factions.add(faction);
    }
}
