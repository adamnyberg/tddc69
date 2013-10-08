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
        if (this.parent.hasFocused()){
            if (this.parent.getFocused() == region){
                this.parent.resetFocus();
            }
            else if (parent.getActivePlayer() == region.getPlayer()){
                Region fortifier = this.parent.getFocused();
                SliderPane slider = new SliderPane(0, fortifier.getArmies()-1, "Select amount to move: ");
                int fortifySize = slider.getValue();
                region.addArmy(fortifySize);
                fortifier.addArmy(-fortifySize);
                this.parent.resetFocus();
            }
        }
        else parent.setFocused(region);
    }

    @Override
    public void highlightRelevantNeighbours() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
