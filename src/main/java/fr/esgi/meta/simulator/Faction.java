package fr.esgi.meta.simulator;

/**
 * Created by vuzi on 07/01/2016.
 */
public abstract class Faction {

    private Unit leader;

    public boolean hasLeader() {
        return leader != null;
    }

    public Unit getLeader() {
        return leader;
    }

    public void setLeader(Unit leader) {
        this.leader = leader;
    }
}
