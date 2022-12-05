package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.errors.Malfunction;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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

    private String format(double val) {
        String in = Integer.toHexString((int) Math.round(val * 255));
        return in.length() == 1 ? "0" + in : in;
    }

    private String toHexString(Color value) {
        return "#" + (format(value.getRed()) + format(value.getGreen()) + format(value.getBlue()) + format(value.getOpacity()))
                .toUpperCase();
    }
}
