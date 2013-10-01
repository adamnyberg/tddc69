package se.liu.ida.adany869.tddc69.project;

import java.awt.event.ActionEvent;
import java.util.*;

public class RegionController extends AbstractController implements Observer{
    private HashMap regionToComponentMap = new HashMap<Region, RegionComponent>();
    private Region focused;
    private Region region;
    private RegionComponent regionComponent;


    public RegionController() {
    }

    public void mapRegionToComponent(Region region, RegionComponent regionComponent){
        regionToComponentMap.put(region, regionComponent);
    }

    private void updateArmyRegionComponent(RegionComponent regionComponent){
        regionComponent.updateArmy();
    }

    public void update(ActionEvent e) {
        invokeMethod(e.getActionCommand(), new Object[]{region});
    }
}
