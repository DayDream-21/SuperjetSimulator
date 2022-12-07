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
        elecUnitsController.activateExtPwrUnit();
        elecUnitsConnectionsController.activateExtPwrConnection();
    }

    public void deactivateExtPwr() {
        elecUnitsController.deactivateExtPwrUnit();
        elecUnitsConnectionsController.deactivateExtPwrConnection();
    }

    public void activateApuGen() {
        elecUnitsController.activateApuGenUnit();
        elecUnitsConnectionsController.activateApuGenConnection();
    }

    public void deactivateApuGen() {
        elecUnitsController.deactivateApuGenUnit();
        elecUnitsConnectionsController.deactivateApuGenConnection();
    }

    public void activateLeftEngine() {
        elecUnitsController.activateLeftEngine();
        elecUnitsConnectionsController.activateLeftDriveToLeftConnection();
    }

    public void deactivateLeftEngine() {
        elecUnitsController.deactivateLeftEngine();
        elecUnitsConnectionsController.deactivateLeftDriveToLeftConnection();
    }

    public void activateRightEngine() {
        elecUnitsController.activateRightEngine();
        elecUnitsConnectionsController.activateRightDriveToRightConnection();
    }

    public void deactivateRightEngine() {
        elecUnitsController.deactivateRightEngine();
        elecUnitsConnectionsController.deactivateRightDriveToRightConnection();
    }
}
