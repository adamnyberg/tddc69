package se.liu.ida.adany869.tddc69.project;

import net.miginfocom.swing.MigLayout;

import java.awt.*;

/**
 * The content pane of the RiskFrame. The graphical game is split into two parts; a RiskInformationComponent above a RiskBoardComponent. These
 * holds different graphical game objects.
 */
public class RiskContainer extends Container {

    public RiskContainer(RiskWorld risk) {
        this.setLayout(new MigLayout());
        this.add(new RiskInformationComponent(risk), "wrap");
        this.add(new RiskBoardComponent(risk));
    }
}
