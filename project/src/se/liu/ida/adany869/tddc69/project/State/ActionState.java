package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.*;

import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.io.Serializable;

public abstract class ActionState implements Serializable{
    protected RiskWorld risk;
    protected ActionState(RiskWorld risk) {
        this.risk = risk;
    }

    public abstract void doSomething(Region region);
    public abstract boolean isRelevantNeighbour(Region focused, Region neighbour);
}
