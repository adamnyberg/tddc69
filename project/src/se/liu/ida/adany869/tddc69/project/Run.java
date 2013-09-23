package se.liu.ida.adany869.tddc69.project;

public class Run {
    public static void main(String[] args) {
        // create players
        Player[] players = {};

        // create regions
        Region[] regions = {
            new Region(),
            new Region(),
            new Region(),
            new Region()
        };

        RiskWorld risk = new RiskWorld(regions, players);
        RiskFrame riskFrame = new RiskFrame(risk);
    }
}
