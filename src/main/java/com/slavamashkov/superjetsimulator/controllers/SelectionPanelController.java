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
    private final BatsConnectionsController batsConnectionsController;
    private final UpperInfoPaneController upperInfoPaneController;

    @FXML private Pane buttons;
    @FXML private ImageView overheadPanelImageView;
    @FXML private ImageView selectorImageView;

    @FXML private Label batteryVoltageIndicator;
    @FXML private Group selectorGroup;

    @FXML private Rectangle bat1UpperLight;
    @FXML private Rectangle bat1LowerLight;

    @FXML private Rectangle bat2UpperLight;
    @FXML private Rectangle bat2LowerLight;

    @FXML private Rectangle bat3UpperLight;
    @FXML private Rectangle bat3LowerLight;

    @FXML private Rectangle bat4UpperLight;
    @FXML private Rectangle bat4LowerLight;

    @FXML private Rectangle extPwrUpperLight;
    @FXML private Rectangle extPwrLowerLight;

    @FXML private Rectangle apuGenUpperLight;
    @FXML private Rectangle apuGenLowerLight;

    @Override
    public void init() {
        buttons.getStylesheets().add("css/selection-panel.css");
        extPwrUpperLight.setFill(Color.LIME);
        apuGenLowerLight.setFill(Color.LIME);

        overheadPanelImageView.setImage(new Image("images/overhead_panel_2.png"));
        selectorImageView.setImage(new Image("images/selector.png"));
    }

    boolean bat1SwitchedPressed = false;

    public void switchBat1Button(MouseEvent mouseEvent) {
        bat1SwitchedPressed = !bat1SwitchedPressed;

        if (bat1SwitchedPressed) {
            upperInfoPaneController.batOff(1);

            bat1LowerLight.setFill(Color.LIME);
        } else {
            upperInfoPaneController.batOn(1);

            bat1LowerLight.setFill(Color.GREY);
        }
    }

    boolean bat2SwitchedPressed = false;

    public void switchBat2Button(MouseEvent mouseEvent) {
        bat2SwitchedPressed = !bat2SwitchedPressed;

        if (bat2SwitchedPressed) {
            upperInfoPaneController.batOff(2);

            bat2LowerLight.setFill(Color.LIME);
        } else {
            upperInfoPaneController.batOn(2);

            bat2LowerLight.setFill(Color.GREY);
        }
    }

    boolean bat3SwitchedPressed = false;

    public void switchBat3Button(MouseEvent mouseEvent) {
        bat3SwitchedPressed = !bat3SwitchedPressed;

        if (bat3SwitchedPressed) {
            upperInfoPaneController.batOff(3);

            bat3LowerLight.setFill(Color.LIME);
        } else {
            upperInfoPaneController.batOn(3);

            bat3LowerLight.setFill(Color.GREY);
        }
    }

    boolean bat4SwitchedPressed = false;

    public void switchBat4Button(MouseEvent mouseEvent) {
        bat4SwitchedPressed = !bat4SwitchedPressed;

        if (bat4SwitchedPressed) {
            upperInfoPaneController.batOff(4);

            bat4LowerLight.setFill(Color.LIME);
        } else {
            upperInfoPaneController.batOn(4);

            bat4LowerLight.setFill(Color.GREY);
        }
    }

    boolean extPwrSwitchedPressed = false;

    @FXML private void switchExtPwrButton(MouseEvent mouseEvent) {
        extPwrSwitchedPressed = !extPwrSwitchedPressed;

        if (extPwrSwitchedPressed) {
            bottomInfoPaneController.activateExtPwr();

            extPwrUpperLight.setFill(Color.GREY);
            extPwrLowerLight.setFill(Color.LIME);
        } else {
            bottomInfoPaneController.deactivateExtPwr();

            extPwrUpperLight.setFill(Color.LIME);
            extPwrLowerLight.setFill(Color.GREY);
        }
    }

    boolean apuGenSwitchedPressed = false;

    @FXML private void switchApuGenButton(MouseEvent mouseEvent) {
        apuGenSwitchedPressed = !apuGenSwitchedPressed;

        if (apuGenSwitchedPressed) {
            bottomInfoPaneController.activateApuGen();

            apuGenUpperLight.setFill(Color.LIME);
            apuGenLowerLight.setFill(Color.GREY);
        } else {
            bottomInfoPaneController.deactivateApuGen();

            apuGenUpperLight.setFill(Color.GREY);
            apuGenLowerLight.setFill(Color.LIME);
        }
    }

    private static final List<Double> allowedDegrees = Arrays.asList(45.0, 90.0, 135.0, 180.0);
    private Iterator<Double> listIterator = allowedDegrees.listIterator();

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
            batteryVoltageIndicator.setText(String.valueOf(batsConnectionsController.getBatVoltage(1)));
        } else if (rotationDegree == 90.0) {
            batteryVoltageIndicator.setText(String.valueOf(batsConnectionsController.getBatVoltage(2)));
        } else if (rotationDegree == 135.0) {
            batteryVoltageIndicator.setText(String.valueOf(batsConnectionsController.getBatVoltage(3)));
        } else if (rotationDegree == 180.0) {
            batteryVoltageIndicator.setText(String.valueOf(batsConnectionsController.getBatVoltage(4)));
        } else {
            // todo
            // залогировать неподдерживаемое значение селектора
            batteryVoltageIndicator.setText(String.valueOf(0.0));
        }
    }

}
