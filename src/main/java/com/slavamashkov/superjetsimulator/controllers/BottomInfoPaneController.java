package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class BottomInfoPaneController extends FxController {
    private final String source = "fxml/bottom-info-pane.fxml";
    @FXML private Pane bottomInfoPaneMainPane;

    private final ElecUnitsController elecUnitsController;
    private final ElecUnitsConnectionsController elecUnitsConnectionsController;

    @FXML private Pane elecUnitsPane;
    @FXML private Pane elecUnitsConnectionsPane;

    @Override
    public void init() {
        elecUnitsPane.getChildren().add(
                elecUnitsController.getBottomInfoElecUnitsPaneMainPane()
        );
        elecUnitsConnectionsPane.getChildren().add(
                elecUnitsConnectionsController.getBottomInfoElecUnitsConnectionsPaneMainPane()
        );
    }

    public void activateExtPwr() {
        elecUnitsConnectionsController.activateExtPwrConnection();
        elecUnitsController.activateExtPwr();
    }

    public void deactivateExtPwr() {
        elecUnitsConnectionsController.deactivateExtPwrConnection();
        elecUnitsController.deactivateExtPwr();
    }

    public void activateLeftEngine() {
        elecUnitsConnectionsController.activateLeftDriveToLeftConnection();
        elecUnitsController.activateLeftEngine();
    }

    public void deactivateLeftEngine() {
        elecUnitsConnectionsController.deactivateLeftDriveToLeftConnection();
        elecUnitsController.deactivateLeftEngine();
    }

    public void activateRightEngine() {
        elecUnitsConnectionsController.activateRightDriveToRightConnection();
        elecUnitsController.activateRightEngine();
    }

    public void deactivateRightEngine() {
        elecUnitsConnectionsController.deactivateRightDriveToRightConnection();
        elecUnitsController.deactivateRightEngine();
    }

    public void activateErrorLeftEngine() {
        elecUnitsConnectionsController.deactivateLeftDriveToLeftConnection();
        // elecUnitsController.activateErrorLeftEngine();
    }

    public void deactivateErrorLeftEngine() {
        elecUnitsConnectionsController.activateLeftDriveToLeftConnection();
        // elecUnitsController.deactivateErrorLeftEngine();
    }

    public void activateErrorRightEngine() {
        elecUnitsConnectionsController.deactivateRightDriveToRightConnection();
        // elecUnitsController.activateErrorRightEngine();
    }

    public void deactivateErrorRightEngine() {
        elecUnitsConnectionsController.activateRightDriveToRightConnection();
        // elecUnitsController.deactivateErrorRightEngine();
    }
}
