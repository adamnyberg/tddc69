package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.*;

import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.io.Serializable;

/**
 * The RiskWorld object holds an ActionState which defines in which state the game is in.
 *  There are several different states each specifying what should happen at clicks etc.
 */
public abstract class ActionState implements Serializable{
    protected RiskWorld risk;
    protected ActionState(RiskWorld risk) {
        this.risk = risk;
    }

    public abstract void onRegionClick(Region region);
    public abstract boolean isRelevantNeighbour(Region focused, Region neighbour);
}
