package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.errors.BusMalfunction;
import com.slavamashkov.superjetsimulator.errors.Malfunction;
import com.slavamashkov.superjetsimulator.errors.NoMalfunction;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
        selectionPanelPane.getChildren().add(selectionPanelController.getSelectionPanelMainPane());
        elecScreen.getChildren().add(elecScreenController.getElecScreenMainPane());
    }

    public void receiveData(Malfunction malfunction) {
        if (!(malfunction instanceof NoMalfunction)) {
            BusMalfunction busMalfunction = (BusMalfunction) malfunction;
            middleInfoPaneController.getLeftMainBusDC().setStroke(
                    Paint.valueOf(toHexString(busMalfunction.parameters.get("leftMainBusDC").get(0).getFxValue()))
            );
            middleInfoPaneController.getRightEmrgBusDC().setStroke(
                    Paint.valueOf(toHexString(busMalfunction.parameters.get("rightEmrgBusDC").get(0).getFxValue()))
            );
        }
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
