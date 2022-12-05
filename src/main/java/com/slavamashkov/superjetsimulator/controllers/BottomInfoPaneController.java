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
        elecUnitsController.activateExtPwrUnit();
    }

    public void deactivateExtPwr() {
        elecUnitsConnectionsController.deactivateExtPwrConnection();
        elecUnitsController.deactivateExtPwrUnit();
    }

    public void activateApuGen() {
        elecUnitsConnectionsController.activateApuGenConnection();
        elecUnitsController.activateApuGenUnit();
    }

    public void deactivateApuGen() {
        elecUnitsConnectionsController.deactivateApuGenConnection();
        elecUnitsController.deactivateApuGenUnit();
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
}
