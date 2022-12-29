package com.slavamashkov.superjetsimulator.controllers.bottom_layer;

import com.slavamashkov.superjetsimulator.controllers.FxController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The class responsible for displaying the connections of units that
 * supply power to the system, such as the left and right drives, the
 * external power supply and the APU. For the drives the voltages,
 * frequencies and load percentage is displayed. {@link ElecUnitsController}
 * is responsible for displaying APU and external power information.
 */
@Getter
@Component
@RequiredArgsConstructor
public class ElecUnitsConnectionsController extends FxController {
    private final String source = "fxml/bottom_layer/bottom-info-elec-units-connections-pane.fxml";
    @FXML private Pane bottomInfoElecUnitsConnectionsMainPane;

    @FXML private Pane fromExtToLeft;
    @FXML private Pane fromExtToRight;
    @FXML private Pane fromApuToLeft;
    @FXML private Pane fromApuToRight;
    @FXML private Pane fromLeftDriveToLeft;
    @FXML private Pane leftDriveInfoPane;
    @FXML private Pane fromRightDriveToRight;
    @FXML private Pane rightDriveInfoPane;

    public void activateExtPwrConnectionToLeft() {
        fromExtToLeft.setOpacity(1.0);
    }

    public void deactivateExtPwrConnectionToLeft() {
        fromExtToLeft.setOpacity(0.0);
    }

    public void activateExtPwrConnectionToRight() {
        fromExtToRight.setOpacity(1.0);
    }

    public void deactivateExtPwrConnectionToRight() {
        fromExtToRight.setOpacity(0.0);
    }

    public void activateApuGenConnectionToLeft() {
        fromApuToLeft.setOpacity(1.0);
    }

    public void deactivateApuGenConnectionToLeft() {
        fromApuToLeft.setOpacity(0.0);
    }

    public void activateApuGenConnectionToRight() {
        fromApuToRight.setOpacity(1.0);
    }

    public void deactivateApuGenConnectionToRight() {
        fromApuToRight.setOpacity(0.0);
    }

    public void activateApuGenConnection() {
        activateApuGenConnectionToLeft();
        activateApuGenConnectionToRight();
    }

    public void deactivateApuGenConnection() {
        deactivateApuGenConnectionToLeft();
        deactivateApuGenConnectionToRight();
    }

    public void activateExtPwrConnection() {
        activateExtPwrConnectionToLeft();
        activateExtPwrConnectionToRight();
    }

    public void deactivateExtPwrConnection() {
        deactivateExtPwrConnectionToLeft();
        deactivateExtPwrConnectionToRight();
    }

    public void activateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(1.0);
        fromLeftDriveToLeft.setOpacity(1.0);
    }

    public void deactivateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(0.0);
        fromLeftDriveToLeft.setOpacity(0.0);
    }

    public void activateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(1.0);
        fromRightDriveToRight.setOpacity(1.0);
    }

    public void deactivateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(0.0);
        fromRightDriveToRight.setOpacity(0.0);
    }
}
