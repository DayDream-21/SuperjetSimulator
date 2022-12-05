package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

@Getter
@Component
@RequiredArgsConstructor
public class SelectionPanelController extends FxController {
    private final String source = "fxml/selection-panel-pane.fxml";
    @FXML private AnchorPane selectionPanelMainPane;
    private final BottomInfoPaneController bottomInfoPaneController;
    private final UpperInfoPaneController upperInfoPaneController;

    @FXML private Pane buttons;
    @FXML private ImageView overheadPanelImageView;
    @FXML private ImageView selectorImageView;

    @FXML private Label batteryVoltageIndicator;
    @FXML private Group selectorGroup;

    @FXML private Rectangle extPwrUpperLight;
    @FXML private Rectangle extPwrLowerLight;

    @Override
    public void init() {
        buttons.getStylesheets().add("css/selection-panel.css");
        extPwrUpperLight.setFill(Color.LIME);

        overheadPanelImageView.setImage(new Image("images/overhead_panel_2.png"));
        selectorImageView.setImage(new Image("images/selector.png"));
    }

    private static final List<Double> allowedDegrees = Arrays.asList(45.0, 90.0, 135.0, 180.0);
    private Iterator<Double> listIterator = allowedDegrees.listIterator();

    boolean isSwitchExtPowButtonPressed = false;

    @FXML private void switchExtPowButton(MouseEvent mouseEvent) {
        isSwitchExtPowButtonPressed = !isSwitchExtPowButtonPressed;

        if (isSwitchExtPowButtonPressed) {
            bottomInfoPaneController.activateExtPwr();

            extPwrUpperLight.setFill(Color.GREY);
            extPwrLowerLight.setFill(Color.LIME);
        } else {
            bottomInfoPaneController.deactivateExtPwr();
            extPwrUpperLight.setFill(Color.LIME);
            extPwrLowerLight.setFill(Color.GREY);
        }
    }

    @FXML private void changeDegree(MouseEvent mouseEvent) {
        if (!listIterator.hasNext()) {
            listIterator = allowedDegrees.listIterator();
            selectorGroup.setRotate(0.0);
            displayBatteryVoltage(selectorGroup.getRotate());
        } else {
            selectorGroup.setRotate(listIterator.next());
            displayBatteryVoltage(selectorGroup.getRotate());
        }
    }

    private void displayBatteryVoltage(double rotationDegree) {
        if (rotationDegree == 0.0) {
            batteryVoltageIndicator.setText(String.valueOf(0.0));
        } else if (rotationDegree == 45.0) {
            batteryVoltageIndicator.setText(String.valueOf(upperInfoPaneController.getBat1Voltage()));
        } else if (rotationDegree == 90.0) {
            batteryVoltageIndicator.setText(String.valueOf(upperInfoPaneController.getBat2Voltage()));
        } else if (rotationDegree == 135.0) {
            batteryVoltageIndicator.setText(String.valueOf(upperInfoPaneController.getBat3Voltage()));
        } else if (rotationDegree == 180.0) {
            batteryVoltageIndicator.setText(String.valueOf(upperInfoPaneController.getBat4Voltage()));
        } else {
            // todo
            // залогировать неподдерживаемое значение селектора
            batteryVoltageIndicator.setText(String.valueOf(0.0));
        }
    }
}
