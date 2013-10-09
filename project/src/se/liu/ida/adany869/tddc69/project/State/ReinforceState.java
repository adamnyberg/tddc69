package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.RiskWorld;
import se.liu.ida.adany869.tddc69.project.regions.Region;

public class ReinforceState extends ActionState{
    public ReinforceState(RiskWorld parent) {
        super(parent);
    }

    @Override
    public void doSomething(Region region) {
        if (region.getOwner() == this.parent.getActivePlayer()){
            region.getOwner().addArmyToRegion(region);
        }
    }

    @Override
    public void highlightRelevantNeighbours() {

    }
}
