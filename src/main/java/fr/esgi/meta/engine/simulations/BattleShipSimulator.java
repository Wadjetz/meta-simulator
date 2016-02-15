package fr.esgi.meta.engine.simulations;

import fr.esgi.meta.battleship.unit.BattleShipBorad;
import fr.esgi.meta.engine.Board;
import fr.esgi.meta.engine.units.Unit;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 626 on 15/02/2016.
 */
public class BattleShipSimulator extends Simulator{

    Board board;

    public void setBoard(Board board) {
        this.board = board;
    }

    @Override
    public void run() {
        System.out.println(name + " simulation run");
        System.out.println(this);

        List<Unit> allUnits = factions.stream().<Unit>flatMap(f -> f.getUnits().stream()).collect(Collectors.toList());
        board.randomDispatch(allUnits);
        System.out.println(board);

        getFactions().get(0).getUnits().get(0).figth(getFactions().get(1).getUnits().get(0));
    }
}
