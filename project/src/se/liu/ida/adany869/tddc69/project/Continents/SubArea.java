package se.liu.ida.adany869.tddc69.project.Continents;

import se.liu.ida.adany869.tddc69.project.Player;
import se.liu.ida.adany869.tddc69.project.regions.Region;

import java.util.ArrayList;

public interface SubArea {
    public Player getOwner();
    public void addAreaReserves(Player player);
    public boolean containsRegion(Region region);
    public ArrayList<Continent> allSubContinents();
    public ArrayList<Continent> getContinentsWhichContains(Region region);
    //TODO: Temp function
    public String getName();
}
