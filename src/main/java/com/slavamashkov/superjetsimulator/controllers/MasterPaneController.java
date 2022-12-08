package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.malfunctions.Malfunction;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class MasterPaneController extends FxController {
    private final String source = "fxml/master-pane.fxml";

    private final SelectionPanelController selectionPanelController;
    private final ElecScreenController elecScreenController;
    private final MiddleInfoPaneController middleInfoPaneController;

    @FXML
    private Pane selectionPanelPane;
    @FXML
    private Pane elecScreen;

    @Override
    public void init() {
        super.init();
        //getStage().setOnCloseRequest(e -> returnToDefault());
        selectionPanelPane.getChildren().add(selectionPanelController.getSelectionPanelMainPane());
        elecScreen.getChildren().add(elecScreenController.getElecScreenMainPane());
    }

    /*// todo implement method to call Malfunction#restoreSystem()
    // Return stage to its default condition
    private void returnToDefault() {
    }*/

    public void receiveData(Malfunction malfunction) {
        malfunction.executeMalfunction();
    }
}
