package com.slavamashkov.superjetsimulator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class ElecScreenController extends FxController {
    private final String source = "fxml/elec-screen.fxml";
    @FXML private AnchorPane elecScreenMainPane;

    private final SystemNamePaneController systemNamePaneController;
    private final UpperInfoPaneController upperInfoPaneController;
    private final MiddleInfoPaneController middleInfoPaneController;
    private final BottomInfoPaneController bottomInfoPaneController;

    @FXML private Pane systemNamePane;
    @FXML private Pane upperInfoPane;
    @FXML private Pane middleInfoPane;
    @FXML private Pane bottomInfoPane;

    @FXML private ToggleButton leftEngineToggleButton;
    @FXML private ToggleButton rightEngineToggleButton;

    @Override
    public void init() {
        systemNamePane.getChildren().add(systemNamePaneController.getSystemNamePaneMainPane());
        upperInfoPane.getChildren().add(upperInfoPaneController.getUpperInfoPaneMainPane());
        middleInfoPane.getChildren().add(middleInfoPaneController.getMiddleInfoPaneMainPane());
        bottomInfoPane.getChildren().add(bottomInfoPaneController.getBottomInfoPaneMainPane());
    }

    public void onActionActivateLeftEngine(ActionEvent event) {
        if (leftEngineToggleButton.isSelected()) {
            bottomInfoPaneController.activateLeftEngine();
        } else {
            bottomInfoPaneController.deactivateLeftEngine();
        }
    }

    public void onActionActivateRightEngine(ActionEvent event) {
        if (rightEngineToggleButton.isSelected()) {
            bottomInfoPaneController.activateRightEngine();
        } else {
            bottomInfoPaneController.deactivateRightEngine();
        }
    }
}
