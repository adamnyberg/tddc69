package se.liu.ida.adany869.tddc69.project;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Run {
    public static void main(String[] args) {
        Map map = new Map();

        RiskWorld risk = new RiskWorld(map.getRegions(), map.getPlayers());
        RiskFrame riskFrame = new RiskFrame(risk);
        riskFrame.setMenuObserver(new MenuController());

    }
}
