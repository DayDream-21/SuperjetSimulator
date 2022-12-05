package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class UpperInfoPaneController extends FxController {
    private final String source = "fxml/upper-info-pane.fxml";
    @FXML private Pane upperInfoPaneMainPane;

    @FXML private Label bat1VoltageValue;
    @FXML private Label bat2VoltageValue;
    @FXML private Label bat3VoltageValue;
    @FXML private Label bat4VoltageValue;

    @FXML private Label bat1CurrentValue;
    @FXML private Label bat2CurrentValue;
    @FXML private Label bat3CurrentValue;
    @FXML private Label bat4CurrentValue;

    public void activateAllBatsDemo() {
        bat1VoltageValue.setText(String.valueOf(21));
        bat2VoltageValue.setText(String.valueOf(22));
        bat3VoltageValue.setText(String.valueOf(23));
        bat4VoltageValue.setText(String.valueOf(24));

        bat1CurrentValue.setText(String.valueOf(-15));
        bat2CurrentValue.setText(String.valueOf(-15));
        bat3CurrentValue.setText(String.valueOf(-15));
        bat4CurrentValue.setText(String.valueOf(-15));
    }

    public void deactivateAllBatsDemo() {
        bat1VoltageValue.setText(String.valueOf(0));
        bat2VoltageValue.setText(String.valueOf(0));
        bat3VoltageValue.setText(String.valueOf(0));
        bat4VoltageValue.setText(String.valueOf(0));

        bat1CurrentValue.setText(String.valueOf(0));
        bat2CurrentValue.setText(String.valueOf(0));
        bat3CurrentValue.setText(String.valueOf(0));
        bat4CurrentValue.setText(String.valueOf(0));
    }

    public double getBat1Voltage() {
        return Double.parseDouble(bat1VoltageValue.getText());
    }

    public double getBat2Voltage() {
        return Double.parseDouble(bat2VoltageValue.getText());
    }

    public double getBat3Voltage() {
        return Double.parseDouble(bat3VoltageValue.getText());
    }

    public double getBat4Voltage() {
        return Double.parseDouble(bat4VoltageValue.getText());
    }
}
