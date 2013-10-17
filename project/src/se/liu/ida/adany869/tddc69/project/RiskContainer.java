package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import java.awt.*;

public class RiskContainer extends Container {

    public RiskContainer(RiskWorld risk) {
        this.setLayout(new MigLayout());
        this.add(new RiskMenuComponent(risk), "wrap");
        this.add(new RiskBoardComponent(risk));
    }
}
