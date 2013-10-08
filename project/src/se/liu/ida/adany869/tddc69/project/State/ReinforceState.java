package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.RiskWorld;
import se.liu.ida.adany869.tddc69.project.regions.Region;

public class ReinforceState extends ActionState{
    public ReinforceState(RiskWorld parent) {
        super(parent);
    }

    @Override
    public void doSomething(Region region) {
        if (region.getPlayer() == this.parent.getActivePlayer()){
            region.getPlayer().addArmyToRegion(region);
        }
    }

    @Override
    public void highlightRelevantNeighbours() {

    }
}
