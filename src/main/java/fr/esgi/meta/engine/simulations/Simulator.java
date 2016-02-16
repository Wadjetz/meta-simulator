package fr.esgi.meta.engine.simulations;

import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.Faction;
import fr.esgi.meta.engine.Zone;
import fr.esgi.meta.engine.units.Unit;
import fr.esgi.meta.view.TileSet;
import fr.esgi.meta.view.TileType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public abstract class Simulator {

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

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ", "[", "]");
        sj.add("Simulator").add(name);
        factions.stream().forEach(f -> sj.add(f.toString()));
        return sj.toString();
    }

    public void run() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int turn = 1;
        System.out.println(name + " simulation run");
        System.out.println(this);

        List<Unit> allUnits = factions.stream().<Unit>flatMap(f -> f.getUnits().stream()).collect(Collectors.toList());
        board.randomDispatch(allUnits);

        while(true) {
            System.out.println("Turn " + turn + " ------------------------------------------------------");
            System.out.println(board);

            // Clean dead units
            factions.forEach(Faction::clearDeadUnit);

            System.out.println("Stats" );
            for(Faction faction : factions) {
                System.out.println(faction.getName() + " Units=" + faction.getUnits().size());
            }


            for(Faction faction : factions) {
                for(Unit unit : faction.getUnits()) {
                    // Move the unit on the board
                    unit.move(board);

                    // Attack every other unit in the neighbors zones
                    unit.getNeighborsUnits().forEach(unit::figth);
                }
            }

            // Enter to continue..
            //br.readLine();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }



            turn++;
        }


        //getFactions().get(0).getUnits().get(0).figth(getFactions().get(1).getUnits().get(0));
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
}
