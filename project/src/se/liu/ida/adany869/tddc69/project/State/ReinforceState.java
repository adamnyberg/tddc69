package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.Region;

public class ReinforceState implements ActionState {
    @Override
    public void doSomething(Region regionA, Region regionB) {
        regionA.getPlayer().addArmyToRegion(regionA);
    }

    @Override
    public void highlightRelevantNeighbours() {

    }
}
