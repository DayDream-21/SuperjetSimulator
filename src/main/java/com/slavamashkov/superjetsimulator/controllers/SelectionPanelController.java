package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.controllers.bottom_layer.BottomInfoPaneController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsConnectionsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.BatsConnectionsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.UpperInfoPaneController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.slavamashkov.superjetsimulator.enums.MyColor.*;

/**
 *
 */
@Getter
@Component
@RequiredArgsConstructor
public class SelectionPanelController extends FxController {
    private final String source = "fxml/selection-panel-pane.fxml";

    @FXML private AnchorPane selectionPanelMainPane;

    private final BottomInfoPaneController bottomInfoPaneController;
    private final BatsConnectionsController batsConnectionsController;
    private final UpperInfoPaneController upperInfoPaneController;

    private ElecUnitsConnectionsController elecUnitsConnectionsController;
    private ElecScreenController elecScreenController;

    @Autowired
    public void setElecUnitsConnectionsController(@Lazy ElecUnitsConnectionsController elecUnitsConnectionsController) {
        this.elecUnitsConnectionsController = elecUnitsConnectionsController;
    }

    @Autowired
    public void setElecScreenController(@Lazy ElecScreenController elecScreenController) {
        this.elecScreenController = elecScreenController;
    }

    @FXML private Pane buttons;
    @FXML private ImageView overheadPanelImageView;
    @FXML private ImageView selectorImageView;

    @FXML private Label batteryVoltageIndicator;
    @FXML private Group selectorGroup;

    @FXML private Rectangle fuelLeftMAINButton;
    @FXML private Rectangle fuelRightMAINButton;

    @FXML private Rectangle galleyButton;

    @FXML private Rectangle galleyLowerLight;

    @FXML private Rectangle bat1UpperLight;
    @FXML private Rectangle bat1LowerLight;

    @FXML private Rectangle bat2UpperLight;
    @FXML private Rectangle bat2LowerLight;

    @FXML private Rectangle bat3UpperLight;
    @FXML private Rectangle bat3LowerLight;

    @FXML private Rectangle bat4UpperLight;
    @FXML private Rectangle bat4LowerLight;

    @FXML private Rectangle leftGenUpperLight;
    @FXML private Rectangle leftGenLowerLight;

    @FXML private Rectangle rightGenUpperLight;
    @FXML private Rectangle rightGenLowerLight;

    @FXML private Rectangle extPwrUpperLight;
    @FXML private Rectangle extPwrLowerLight;

    @FXML private Rectangle apuGenUpperLight;
    @FXML private Rectangle apuGenLowerLight;

    @FXML private Rectangle fuelLeftADDTLowerLight;

    @FXML private Rectangle fuelLeftAUXLowerLight;

    @FXML private Rectangle fuelLeftMAINLowerLight;

    @FXML private Rectangle fuelRightMAINLowerLight;

    @FXML private Rectangle fuelRightAUXLowerLight;

    @FXML private Rectangle fuelRightADDTLowerLight;

    // todo включать подсветку кнопок только после того как подключается питание
    @Override
    public void init() {
        buttons.getStylesheets().add("css/selection-panel.css");

        overheadPanelImageView.setImage(new Image("images/overhead_panel_2.png"));
        selectorImageView.setImage(new Image("images/selector.png"));
    }

    private boolean bat1SwitchedPressed = false;

    @FXML private void switchBat1Button() {
        bat1SwitchedPressed = !bat1SwitchedPressed;

        if (bat1SwitchedPressed) {
            upperInfoPaneController.batOn(1);
            bat1LowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        } else {
            upperInfoPaneController.batOff(1);
            bat1LowerLight.setFill(OFF_LIGHT_COLOR.color);
        }
    }

    private boolean bat2SwitchedPressed = false;

    @FXML private void switchBat2Button() {
        bat2SwitchedPressed = !bat2SwitchedPressed;

        if (bat2SwitchedPressed) {
            upperInfoPaneController.batOn(2);
            bat2LowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        } else {
            upperInfoPaneController.batOff(2);
            bat2LowerLight.setFill(OFF_LIGHT_COLOR.color);
        }
    }

    private boolean bat3SwitchedPressed = false;

    @FXML private void switchBat3Button() {
        bat3SwitchedPressed = !bat3SwitchedPressed;

        if (bat3SwitchedPressed) {
            upperInfoPaneController.batOn(3);
            bat3LowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        } else {
            upperInfoPaneController.batOff(3);
            bat3LowerLight.setFill(OFF_LIGHT_COLOR.color);
        }
    }

    private boolean bat4SwitchedPressed = false;

    @FXML private void switchBat4Button() {
        bat4SwitchedPressed = !bat4SwitchedPressed;

        if (bat4SwitchedPressed) {
            upperInfoPaneController.batOn(4);
            bat4LowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        } else {
            upperInfoPaneController.batOff(4);
            bat4LowerLight.setFill(OFF_LIGHT_COLOR.color);
        }
    }

    public boolean isBatButtonPressed(int i) {
        switch (i) {
            case 1 -> {
                return bat1LowerLight.getFill().equals(INACTIVE_LIGHT_COLOR.color);
            }
            case 2 -> {
                return bat2LowerLight.getFill().equals(INACTIVE_LIGHT_COLOR.color);
            }
            case 3 -> {
                return bat3LowerLight.getFill().equals(INACTIVE_LIGHT_COLOR.color);
            }
            case 4 -> {
                return bat4LowerLight.getFill().equals(INACTIVE_LIGHT_COLOR.color);
            }
        }
        return false;
    }

    private boolean leftGenButtonPressed = false;

    @FXML public void switchLeftGenButton() {
        leftGenButtonPressed = !leftGenButtonPressed;

        elecScreenController.getLeftEngineToggleButton().fire();
    }

    private boolean rightGenButtonPressed = false;

    @FXML public void switchRightGenButton() {
        rightGenButtonPressed = !rightGenButtonPressed;

        elecScreenController.getRightEngineToggleButton().fire();
    }

    private boolean extPwrSwitchedPressed = false;

    @FXML public void switchExtPwrButton() {
        extPwrSwitchedPressed = !extPwrSwitchedPressed;

        if (extPwrSwitchedPressed) {
            bottomInfoPaneController.activateExtPwrUnit();

            elecScreenController.getSystemNamePane().setOpacity(1.0);
            elecScreenController.getUpperInfoPane().setOpacity(1.0);
            elecScreenController.getMiddleInfoPane().setOpacity(1.0);
            elecScreenController.getBottomInfoPane().setOpacity(1.0);

            extPwrUpperLight.setFill(INACTIVE_LIGHT_COLOR.color);
            extPwrLowerLight.setFill(EXT_PWR_ON_LIGHT_COLOR.color);
        } else {
            bottomInfoPaneController.deactivateExtPwrUnit();

            extPwrUpperLight.setFill(ACTIVE_LIGHT_COLOR.color);
            extPwrLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    private boolean apuGenSwitchedPressed = false;

    @FXML private void switchApuGenButton() {
        apuGenSwitchedPressed = !apuGenSwitchedPressed;

        if (apuGenSwitchedPressed) {
            bottomInfoPaneController.activateApuGenUnit();

            apuGenLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        } else {
            bottomInfoPaneController.deactivateApuGenUnit();

            apuGenLowerLight.setFill(OFF_LIGHT_COLOR.color);
        }
    }

    private boolean galleyButtonSwitched = false;

    @FXML private void switchGalleyButton() {
        galleyButtonSwitched = !galleyButtonSwitched;

        if (galleyButtonSwitched) {
            galleyLowerLight.setFill(OFF_LIGHT_COLOR.color);
        } else {
            galleyLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    // TODO: Стоит перенести методы относящиеся к топливной системе в отдельный
    //  контроллер, а также сделать для кнопок отдельный Pane

    private boolean fuelLeftADDTButtonSwitched = false;

    @FXML private void switchFuelLeftADDTButton() {
        fuelLeftADDTButtonSwitched = !fuelLeftADDTButtonSwitched;

        if (fuelLeftADDTButtonSwitched) {
            fuelLeftADDTLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
        } else {
            fuelLeftADDTLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    private boolean fuelLeftAUXButtonSwitched = false;

    @FXML private void switchFuelLeftAUXButton() {
        fuelLeftAUXButtonSwitched = !fuelLeftAUXButtonSwitched;

        if (fuelLeftAUXButtonSwitched) {
            fuelLeftAUXLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
        } else {
            fuelLeftAUXLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    private boolean fuelLeftMAINButtonSwitched = false;

    @FXML private void switchFuelLeftMAINButton() {
        fuelLeftMAINButtonSwitched = !fuelLeftMAINButtonSwitched;

        if (fuelLeftMAINButtonSwitched) {
            fuelLeftMAINLowerLight.setFill(OFF_LIGHT_COLOR.color);
        } else {
            fuelLeftMAINLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    private boolean fuelRightMAINButtonSwitched = false;

    @FXML private void switchFuelRightMAINButton() {
        fuelRightMAINButtonSwitched = !fuelRightMAINButtonSwitched;

        if (fuelRightMAINButtonSwitched) {
            fuelRightMAINLowerLight.setFill(OFF_LIGHT_COLOR.color);
        } else {
            fuelRightMAINLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    private boolean fuelRightAUXButtonSwitched = false;

    @FXML private void switchFuelRightAUXButton() {
        fuelRightAUXButtonSwitched = !fuelRightAUXButtonSwitched;

        if (fuelRightAUXButtonSwitched) {
            fuelRightAUXLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
        } else {
            fuelRightAUXLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    private boolean fuelRightADDTButtonSwitched = false;

    @FXML private void switchFuelRightADDTButton() {
        fuelRightADDTButtonSwitched = !fuelRightADDTButtonSwitched;

        if (fuelRightADDTButtonSwitched) {
            fuelRightADDTLowerLight.setFill(ACTIVE_COLOR.color);
        } else {
            fuelRightADDTLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    private static final List<Double> allowedDegrees = Arrays.asList(45.0, 90.0, 135.0, 180.0);
    private Iterator<Double> listIterator = allowedDegrees.listIterator();

    @FXML private void changeDegree() {
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
            batteryVoltageIndicator.setText(String.valueOf(0.0));
        }
    }
}