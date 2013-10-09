package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.*;

import se.liu.ida.adany869.tddc69.project.regions.Region;

public abstract class ActionState {
    protected RiskWorld risk;
    protected ActionState(RiskWorld risk) {
        this.risk = risk;
    }

    public abstract void doSomething(Region region);
    public abstract boolean isRelevantNeighbour(Region focused, Region neighbour);
}
