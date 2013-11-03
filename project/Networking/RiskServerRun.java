package se.liu.ida.adany869.tddc69.project.Networking;

public class RiskServerRun {
    public static void main(String[] args) {
        RiskServer riskServer = new RiskServer();
        riskServer.setPort(6606);
        riskServer.run();
    }
}
