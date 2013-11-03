package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.continent.Continent;
import se.liu.ida.adany869.tddc69.project.regions.Region;

/**
 * A map holds the logical information about the game. The RiskWorld class' constructor takes an AbstractMap.
 * To make a map, create a class which implements AbstractMap and all its methods.
 */
public interface AbstractMap {
    public Region[] getRegions();

    public Player[] getPlayers();

    public Continent[] getContinents();
}
