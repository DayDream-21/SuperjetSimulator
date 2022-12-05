package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class ElecUnitsConnectionsController extends FxController {
    private final String source = "fxml/bottom-info-elec-units-connections-pane.fxml";
    @FXML private Pane bottomInfoElecUnitsConnectionsPaneMainPane;

    @FXML private Pane fromExtToLeft;
    @FXML private Pane fromExtToRight;
    @FXML private Pane fromApuToLeft;
    @FXML private Pane fromApuToRight;
    @FXML private Pane fromLeftDriveToLeft;
    @FXML private Pane leftDriveInfoPane;
    @FXML private Pane fromRightDriveToRight;
    @FXML private Pane rightDriveInfoPane;

    public void activateExtPwrConnection() {
        fromExtToLeft.setOpacity(1.0);
        fromExtToRight.setOpacity(1.0);
    }

    public void deactivateExtPwrConnection() {
        fromExtToLeft.setOpacity(0.0);
        fromExtToRight.setOpacity(0.0);
    }

    public void activateApuGenConnection() {
        fromApuToLeft.setOpacity(1.0);
        fromApuToRight.setOpacity(1.0);
    }

    public void deactivateApuGenConnection() {
        fromApuToLeft.setOpacity(0.0);
        fromApuToRight.setOpacity(0.0);
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
