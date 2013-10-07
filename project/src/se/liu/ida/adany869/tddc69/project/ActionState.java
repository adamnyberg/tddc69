package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.regions.Region;

public abstract class ActionState {
    protected RiskWorld parent;
    protected ActionState(RiskWorld parent) {
        this.parent = parent;
    }

    public abstract void doSomething(Region region);
    public abstract void highlightRelevantNeighbours();
}
