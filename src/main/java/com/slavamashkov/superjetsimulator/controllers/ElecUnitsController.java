package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.slavamashkov.superjetsimulator.enums.Color.ACTIVE_COLOR;
import static com.slavamashkov.superjetsimulator.enums.Color.INACTIVE_COLOR;

@Getter
@Component
@RequiredArgsConstructor
public class ElecUnitsController extends FxController {
    private final String source = "fxml/bottom-info-elec-units-pane.fxml";
    @FXML private Pane bottomInfoElecUnitsPaneMainPane;

    private final BatsConnectionsController batsConnectionsController;

    // External Power
    @FXML private Rectangle extPwrRectangle;
    @FXML private CubicCurve extPwrTransformerSign;
    @FXML private Label extLabel;
    @FXML private Label pwrLabel;
    @FXML private Label extPwrVoltageValue;
    @FXML private Label extPwrVoltageSign;
    @FXML private Label extPwrFrequencyValue;
    @FXML private Label extPwrFrequencySign;

    // APU Generator
    @FXML private Circle apuGenCircle;
    @FXML private Label apuLabel;
    @FXML private Label genLabel;
    @FXML private CubicCurve apuGenTransformerSign;
    @FXML private Pane apuGenInfoPane;

    // Left Engine
    @FXML private Rectangle leftDriveConnection;
    @FXML private Circle leftDriveTransformerCircle;
    @FXML private CubicCurve leftDriveTransformerSign;
    @FXML private Rectangle leftDriveRectangle;

    // Right Engine
    @FXML private Rectangle rightDriveConnection;
    @FXML private Circle rightDriveTransformerCircle;
    @FXML private Rectangle rightDriveRectangle;
    @FXML private CubicCurve rightDriveTransformerSign;

    public void activateExtPwrUnit() {
        extPwrRectangle.setOpacity(1.0);
        extPwrTransformerSign.setOpacity(1.0);
        extLabel.setOpacity(1.0);
        pwrLabel.setOpacity(1.0);
        extPwrVoltageValue.setOpacity(1.0);
        extPwrVoltageSign.setOpacity(1.0);
        extPwrFrequencyValue.setOpacity(1.0);
        extPwrFrequencySign.setOpacity(1.0);

        batsConnectionsController.activateAllBatsDemo();
    }

    public void deactivateExtPwrUnit() {
        extPwrRectangle.setOpacity(0.0);
        extPwrTransformerSign.setOpacity(0.0);
        extLabel.setOpacity(0.0);
        pwrLabel.setOpacity(0.0);
        extPwrVoltageValue.setOpacity(0.0);
        extPwrVoltageSign.setOpacity(0.0);
        extPwrFrequencyValue.setOpacity(0.0);
        extPwrFrequencySign.setOpacity(0.0);

        batsConnectionsController.deactivateAllBatsDemo();
    }

    public void activateApuGenUnit() {
        apuGenCircle.setStroke(Color.web(ACTIVE_COLOR.hexCode));
        apuGenTransformerSign.setOpacity(1.0);
        apuGenInfoPane.setOpacity(1.0);

        batsConnectionsController.activateAllBatsDemo();
    }

    public void deactivateApuGenUnit() {
        apuGenCircle.setStroke(Color.web(INACTIVE_COLOR.hexCode));
        apuGenTransformerSign.setOpacity(0.0);
        apuGenInfoPane.setOpacity(0.0);

        batsConnectionsController.deactivateAllBatsDemo();
    }

    public void activateLeftEngine() {
        leftDriveConnection.setOpacity(1.0);
        leftDriveTransformerSign.setOpacity(1.0);
        leftDriveTransformerSign.setStroke(Color.web(ACTIVE_COLOR.hexCode));
        leftDriveTransformerCircle.setStroke(Color.web(ACTIVE_COLOR.hexCode));
        leftDriveRectangle.setStroke(Color.web(ACTIVE_COLOR.hexCode));

        batsConnectionsController.activateAllBatsDemo();
    }

    public void deactivateLeftEngine() {
        leftDriveConnection.setOpacity(0.0);
        leftDriveTransformerSign.setOpacity(0.0);
        leftDriveTransformerCircle.setStroke(Color.web(INACTIVE_COLOR.hexCode));
        leftDriveRectangle.setStroke(Color.web(INACTIVE_COLOR.hexCode));

        batsConnectionsController.deactivateAllBatsDemo();
    }

    public void activateRightEngine() {
        rightDriveConnection.setOpacity(1.0);
        rightDriveTransformerSign.setOpacity(1.0);
        rightDriveTransformerSign.setStroke(Color.web(ACTIVE_COLOR.hexCode));
        rightDriveTransformerCircle.setStroke(Color.web(ACTIVE_COLOR.hexCode));
        rightDriveRectangle.setStroke(Color.web(ACTIVE_COLOR.hexCode));

        batsConnectionsController.activateAllBatsDemo();
    }

    public void deactivateRightEngine() {
        rightDriveConnection.setOpacity(0.0);
        rightDriveTransformerSign.setOpacity(0.0);
        rightDriveTransformerCircle.setStroke(Color.web(INACTIVE_COLOR.hexCode));
        rightDriveRectangle.setStroke(Color.web(INACTIVE_COLOR.hexCode));

        batsConnectionsController.deactivateAllBatsDemo();
    }
}
