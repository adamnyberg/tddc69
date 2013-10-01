package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class RiskContainer extends Container {
    private RiskWorld risk;
    private RiskMenuComponent riskMenuComponent;
    private RiskBoardComponent riskBoardComponent;

    public RiskContainer(RiskWorld risk) {
        this.setLayout(new MigLayout());
        this.risk = risk;
        this.riskMenuComponent = new RiskMenuComponent(risk);
        this.riskBoardComponent = new RiskBoardComponent(risk);
        this.add(this.riskMenuComponent, "wrap");
        this.add(this.riskBoardComponent);
    }
}
