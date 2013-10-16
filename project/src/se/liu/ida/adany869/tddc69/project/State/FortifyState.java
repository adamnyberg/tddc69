package se.liu.ida.adany869.tddc69.project.state;

import se.liu.ida.adany869.tddc69.project.RiskWorld;
import se.liu.ida.adany869.tddc69.project.SliderPane;
import se.liu.ida.adany869.tddc69.project.regions.Region;

public class FortifyState extends ActionState {
    public FortifyState(RiskWorld parent) {
        super(parent);
    }

    @Override
    public void doSomething(Region region) {
        if (this.risk.hasFocused()){
            if (this.risk.getFocused() == region){
                this.risk.resetFocus();
            }
            else if (risk.getActivePlayer() == region.getOwner()){
                Region fortifier = this.risk.getFocused();
                if (fortifier.isNeighbour(region)){
                    SliderPane slider = new SliderPane(0, fortifier.getArmySize()-1, "Select amount to move: ");
                    int fortifySize = slider.getValue();
                    region.addArmy(fortifySize);
                    fortifier.addArmy(-fortifySize);
                    this.risk.resetFocus();
                }
            }
        }
        else if (this.risk.getActivePlayer() == region.getOwner()) {
            this.risk.setFocused(region);
        }
    }

    @Override
    public boolean isRelevantNeighbour(Region focused, Region neighbour) {
        return focused.getNeighbours().contains(neighbour) &&
                focused.getOwner() == neighbour.getOwner();
    }
}
