package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.errors.Error;
import com.slavamashkov.superjetsimulator.errors.Error1;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Component
@RequiredArgsConstructor
public class MasterPaneController extends FxController {
    private final String source = "fxml/master-pane.fxml";

    private final SelectionPanelController selectionPanelController;
    private final ElecScreenController elecScreenController;
    private final MiddleInfoPaneController middleInfoPaneController;

    List<Integer> list = List.of(1, 2, 3, 5);

    @FXML private Pane selectionPanelPane;
    @FXML private Pane elecScreen;

    @Override
    public void init() {
        super.init();
        selectionPanelPane.getChildren().add(selectionPanelController.getSelectionPanelMainPane());
        elecScreen.getChildren().add(elecScreenController.getElecScreenMainPane());
    }

    public void receiveData(Error error) {
        Optional<Error> optionalError = Optional.of(error);

        if (optionalError.isPresent()) {
            Error1 error1 = (Error1) optionalError.get();
            middleInfoPaneController.getLeftMainBusDC().setStroke(
                    Paint.valueOf(toHexString(error1.parameters.get("leftMainBusDC").get(0).getFxValue()))
            );
            middleInfoPaneController.getRightEmrgBusDC().setStroke(
                    Paint.valueOf(toHexString(error1.parameters.get("rightEmrgBusDC").get(0).getFxValue()))
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
