package se.liu.ida.adany869.tddc69.project;

public class Run {
    public static void main(String[] args) {
        Map map = new Map();

        RiskWorld risk = new RiskWorld(map.getRegions(), map.getPlayers(), map.getContinents());
        RiskFrame riskFrame = new RiskFrame(risk);
        riskFrame.setMenuObserver(new MenuController());
    }
}
