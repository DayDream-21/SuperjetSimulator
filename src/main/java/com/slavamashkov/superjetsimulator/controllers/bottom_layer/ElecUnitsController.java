package com.slavamashkov.superjetsimulator.controllers.bottom_layer;

import com.slavamashkov.superjetsimulator.controllers.FxController;
import com.slavamashkov.superjetsimulator.controllers.SelectionPanelController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.BatsConnectionsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.BatsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.UpperInfoPaneController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurve;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ACTIVE_COLOR;
import static com.slavamashkov.superjetsimulator.enums.MyColor.INACTIVE_COLOR;

/**
 * The class responsible for displaying the units that supply power
 * to the system, such as the left and right drives, the external power
 * supply and the APU. For the external power supply the voltages and
 * frequencies are displayed, for the APU additionally the load percentage
 * is displayed. {@link ElecUnitsConnectionsController} is responsible for
 * displaying drives information.
 */
@Getter
@Component
@RequiredArgsConstructor
public class ElecUnitsController extends FxController {
    private final String source = "fxml/bottom_layer/bottom-info-elec-units-pane.fxml";
    @FXML private Pane bottomInfoElecUnitsMainPane;

    private final BatsController batsController;
    private final BatsConnectionsController batsConnectionsController;
    private SelectionPanelController selectionPanelController;
    private UpperInfoPaneController upperInfoPaneController;

    @Autowired
    public void setSelectionPanelController(@Lazy SelectionPanelController selectionPanelController) {
        this.selectionPanelController = selectionPanelController;
    }

    @Autowired
    public void setUpperInfoPaneController(@Lazy UpperInfoPaneController upperInfoPaneController) {
        this.upperInfoPaneController = upperInfoPaneController;
    }

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

    private int isPowered = 0;

    public boolean isPowered() {
        return isPowered > 0;
    }

    public void activateExtPwrUnit() {
        extPwrRectangle.setOpacity(1.0);
        extPwrTransformerSign.setOpacity(1.0);
        extLabel.setOpacity(1.0);
        pwrLabel.setOpacity(1.0);
        extPwrVoltageValue.setOpacity(1.0);
        extPwrVoltageSign.setOpacity(1.0);
        extPwrFrequencyValue.setOpacity(1.0);
        extPwrFrequencySign.setOpacity(1.0);

        ++isPowered;

        for (int i = 1; i < 5; i++) {
            if (batsConnectionsController.isBatConnectedInverse(i) &&
                    selectionPanelController.isBatButtonPressed(i)) {
                upperInfoPaneController.batOn(i);
            }
        }
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

        --isPowered;

        for (int i = 1; i < 5; i++) {
            if (!batsConnectionsController.isBatConnectedInverse(i) &&
                    selectionPanelController.isBatButtonPressed(i)) {
                upperInfoPaneController.batOn(i);
            }
        }
    }

    public void activateApuGenUnit() {
        apuGenCircle.setStroke(ACTIVE_COLOR.color);
        apuGenTransformerSign.setOpacity(1.0);
        apuGenInfoPane.setOpacity(1.0);

        ++isPowered;

        for (int i = 1; i < 5; i++) {
            if (batsConnectionsController.isBatConnectedInverse(i) &&
                    selectionPanelController.isBatButtonPressed(i)) {
                upperInfoPaneController.batOn(i);
            }
        }
    }

    public void deactivateApuGenUnit() {
        apuGenCircle.setStroke(INACTIVE_COLOR.color);
        apuGenTransformerSign.setOpacity(0.0);
        apuGenInfoPane.setOpacity(0.0);

        --isPowered;

        for (int i = 1; i < 5; i++) {
            if (!batsConnectionsController.isBatConnectedInverse(i) &&
                    selectionPanelController.isBatButtonPressed(i)) {
                upperInfoPaneController.batOn(i);
            }
        }
    }

    public void activateLeftEngine() {
        leftDriveConnection.setStroke(ACTIVE_COLOR.color);
        leftDriveConnection.setOpacity(1.0);
        leftDriveTransformerSign.setOpacity(1.0);
        leftDriveTransformerSign.setStroke(ACTIVE_COLOR.color);
        leftDriveTransformerCircle.setStroke(ACTIVE_COLOR.color);
        leftDriveRectangle.setStroke(ACTIVE_COLOR.color);

        ++isPowered;

        for (int i = 1; i < 5; i++) {
            if (batsConnectionsController.isBatConnectedInverse(i) &&
                    selectionPanelController.isBatButtonPressed(i)) {
                upperInfoPaneController.batOn(i);
            }
        }
    }

    public void deactivateLeftEngine() {
        leftDriveConnection.setOpacity(0.0);
        leftDriveTransformerSign.setOpacity(0.0);
        leftDriveTransformerCircle.setStroke(INACTIVE_COLOR.color);
        leftDriveRectangle.setStroke(INACTIVE_COLOR.color);

        --isPowered;

        for (int i = 1; i < 5; i++) {
            if (!batsConnectionsController.isBatConnectedInverse(i) &&
                    selectionPanelController.isBatButtonPressed(i)) {
                upperInfoPaneController.batOn(i);
            }
        }
    }

    public void activateRightEngine() {
        rightDriveConnection.setStroke(ACTIVE_COLOR.color);
        rightDriveConnection.setOpacity(1.0);
        rightDriveTransformerSign.setOpacity(1.0);
        rightDriveTransformerSign.setStroke(ACTIVE_COLOR.color);
        rightDriveTransformerCircle.setStroke(ACTIVE_COLOR.color);
        rightDriveRectangle.setStroke(ACTIVE_COLOR.color);

        ++isPowered;

        for (int i = 1; i < 5; i++) {
            if (batsConnectionsController.isBatConnectedInverse(i) &&
                    selectionPanelController.isBatButtonPressed(i)) {
                upperInfoPaneController.batOn(i);
            }
        }
    }

    public void deactivateRightEngine() {
        rightDriveConnection.setOpacity(0.0);
        rightDriveTransformerSign.setOpacity(0.0);
        rightDriveTransformerCircle.setStroke(INACTIVE_COLOR.color);
        rightDriveRectangle.setStroke(INACTIVE_COLOR.color);

        --isPowered;

        for (int i = 1; i < 5; i++) {
            if (!batsConnectionsController.isBatConnectedInverse(i) &&
                    selectionPanelController.isBatButtonPressed(i)) {
                upperInfoPaneController.batOn(i);
            }
        }
    }
}