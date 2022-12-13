package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The class responsible for connecting and disconnecting the batteries
 * from the power grid, as well as displaying and controlling the voltage
 * and current value
 */
@Getter
@Component
@RequiredArgsConstructor
public class BatsConnectionsController extends FxController {
    private final String source = "fxml/upper-info-bats-connections-pane.fxml";
    @FXML private Pane upperInfoBatsConnectionsMainPane;

    // Bat 1
    @FXML private Label bat1VoltageSign;
    @FXML private Label bat1VoltageValue;
    @FXML private Polygon bat1ArrowEnd;
    @FXML private Rectangle bat1Arrow;
    @FXML private Label bat1CurrentSign;
    @FXML private Label bat1CurrentValue;

    // Bat 2
    @FXML private Label bat2VoltageSign;
    @FXML private Label bat2VoltageValue;
    @FXML private Rectangle bat2Arrow;
    @FXML private Polygon bat2ArrowEnd;
    @FXML private Label bat2CurrentSign;
    @FXML private Label bat2CurrentValue;

    // Bat 3
    @FXML private Label bat3VoltageSign;
    @FXML private Label bat3VoltageValue;
    @FXML private Polygon bat3ArrowEnd;
    @FXML private Rectangle bat3Arrow;
    @FXML private Label bat3CurrentSign;
    @FXML private Label bat3CurrentValue;

    // Bat 4
    @FXML private Label bat4VoltageSign;
    @FXML private Label bat4VoltageValue;
    @FXML private Polygon bat4ArrowEnd;
    @FXML private Rectangle bat4Arrow;
    @FXML private Label bat4CurrentSign;
    @FXML private Label bat4CurrentValue;

    /**
     * Allows you to disconnect a certain battery from the power grid
     *
     * @param i the battery number must be an integer between 1 and 4 inclusive
     */
    public void batOff(int i) {
        switch (i) {
            case 1 -> {
                bat1VoltageSign.setOpacity(0.0); 
                bat1VoltageValue.setOpacity(0.0);
                bat1ArrowEnd.setOpacity(0.0);
                bat1Arrow.setOpacity(0.0);
                bat1CurrentSign.setOpacity(0.0);
                bat1CurrentValue.setOpacity(0.0);
            }
            case 2 -> {
                bat2VoltageSign.setOpacity(0.0);
                bat2VoltageValue.setOpacity(0.0);
                bat2ArrowEnd.setOpacity(0.0);
                bat2Arrow.setOpacity(0.0);
                bat2CurrentSign.setOpacity(0.0);
                bat2CurrentValue.setOpacity(0.0);
            }
            case 3 -> {
                bat3VoltageSign.setOpacity(0.0);
                bat3VoltageValue.setOpacity(0.0);
                bat3ArrowEnd.setOpacity(0.0);
                bat3Arrow.setOpacity(0.0);
                bat3CurrentSign.setOpacity(0.0);
                bat3CurrentValue.setOpacity(0.0);
            } 
            case 4 -> {
                bat4VoltageSign.setOpacity(0.0);
                bat4VoltageValue.setOpacity(0.0);
                bat4ArrowEnd.setOpacity(0.0);
                bat4Arrow.setOpacity(0.0);
                bat4CurrentSign.setOpacity(0.0);
                bat4CurrentValue.setOpacity(0.0);
            } 
        }
    }

    /**
     * Allows you to connect a specific battery to the power grid
     *
     * @param i The battery number must be an integer between 1 and 4 inclusive
     */
    public void batOn(int i) {
        switch (i) {
            case 1 -> {
                bat1VoltageSign.setOpacity(1.0);
                bat1VoltageValue.setOpacity(1.0);
                bat1ArrowEnd.setOpacity(1.0);
                bat1Arrow.setOpacity(1.0);
                bat1CurrentSign.setOpacity(1.0);
                bat1CurrentValue.setOpacity(1.0);
            }
            case 2 -> {
                bat2VoltageSign.setOpacity(1.0);
                bat2VoltageValue.setOpacity(1.0);
                bat2ArrowEnd.setOpacity(1.0);
                bat2Arrow.setOpacity(1.0);
                bat2CurrentSign.setOpacity(1.0);
                bat2CurrentValue.setOpacity(1.0);
            }
            case 3 -> {
                bat3VoltageSign.setOpacity(1.0);
                bat3VoltageValue.setOpacity(1.0);
                bat3ArrowEnd.setOpacity(1.0);
                bat3Arrow.setOpacity(1.0);
                bat3CurrentSign.setOpacity(1.0);
                bat3CurrentValue.setOpacity(1.0);
            }
            case 4 -> {
                bat4VoltageSign.setOpacity(1.0);
                bat4VoltageValue.setOpacity(1.0);
                bat4ArrowEnd.setOpacity(1.0);
                bat4Arrow.setOpacity(1.0);
                bat4CurrentSign.setOpacity(1.0);
                bat4CurrentValue.setOpacity(1.0);
            }
        }
    }

    private int batsCounter = 0;

    public void activateAllBatsDemo() {
        bat1VoltageValue.setText(String.valueOf(21));
        bat2VoltageValue.setText(String.valueOf(22));
        bat3VoltageValue.setText(String.valueOf(23));
        bat4VoltageValue.setText(String.valueOf(24));

        bat1CurrentValue.setText(String.valueOf(-15));
        bat2CurrentValue.setText(String.valueOf(-15));
        bat3CurrentValue.setText(String.valueOf(-15));
        bat4CurrentValue.setText(String.valueOf(-15));

        batsCounter++;
    }

    public void deactivateAllBatsDemo() {
        batsCounter--;

        if (batsCounter < 1) {
            bat1VoltageValue.setText(String.valueOf(0));
            bat2VoltageValue.setText(String.valueOf(0));
            bat3VoltageValue.setText(String.valueOf(0));
            bat4VoltageValue.setText(String.valueOf(0));

            bat1CurrentValue.setText(String.valueOf(0));
            bat2CurrentValue.setText(String.valueOf(0));
            bat3CurrentValue.setText(String.valueOf(0));
            bat4CurrentValue.setText(String.valueOf(0));
        }
    }

    /**
     * Allows you to obtain the voltage of a certain battery, provided
     * that it is connected to the power grid
     *
     * @param i the battery number must be an integer between 1 and 4 inclusive
     * @return battery voltage as double value if it is connected, if not it returns double value 0.0
     */
    public double getBatVoltage(int i) {
        switch (i) {
            case 1 -> {
                if (bat1VoltageValue.getOpacity() != 0.0) {
                    return Double.parseDouble(bat1VoltageValue.getText());
                } else {
                    return 0.0;
                }
            }
            case 2 -> {
                if (bat2VoltageValue.getOpacity() != 0.0) {
                    return Double.parseDouble(bat2VoltageValue.getText());
                } else {
                    return 0.0;
                }
            }
            case 3 -> {
                if (bat3VoltageValue.getOpacity() != 0.0) {
                    return Double.parseDouble(bat3VoltageValue.getText());
                } else {
                    return 0.0;
                }
            }
            case 4 -> {
                if (bat4VoltageValue.getOpacity() != 0.0) {
                    return Double.parseDouble(bat4VoltageValue.getText());
                } else {
                    return 0.0;
                }
            }
        }

        return 0.0;
    }
}
