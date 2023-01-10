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
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ACTIVE_LIGHT_COLOR;
import static com.slavamashkov.superjetsimulator.enums.MyColor.INACTIVE_LIGHT_COLOR;

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
    // todo включать подсветку кнопок только после того как подключается питание
    @Override
    public void init() {
        buttons.getStylesheets().add("css/selection-panel.css");

        extPwrUpperLight.setFill(ACTIVE_LIGHT_COLOR.color);
        apuGenLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);

        leftGenLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
        rightGenLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);

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
            bat1LowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
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
            bat2LowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
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
            bat3LowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
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
            bat4LowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
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

    @Setter
    private boolean leftGenButtonPressed = false;

    @FXML public void switchLeftGenButton() {
        leftGenButtonPressed = !leftGenButtonPressed;

        elecScreenController.getLeftEngineToggleButton().fire();
    }

    @Setter
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

            extPwrUpperLight.setFill(INACTIVE_LIGHT_COLOR.color);
            extPwrLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
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

            apuGenLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
        }
    }

    private boolean galleyButtonSwitched = false;

    @FXML private void switchGalleyButton() {
        galleyButtonSwitched = !galleyButtonSwitched;

        if (galleyButtonSwitched) {
            Label leftEngineLoadValueLabel = elecUnitsConnectionsController.getLeftDrivePercentValue();
            Label rightEngineLoadValueLabel = elecUnitsConnectionsController.getRightDrivePercentValue();

            int prevLeftEngineLoadValue = Integer.parseInt(leftEngineLoadValueLabel.getText());
            int nextLeftEngineLoadValue = prevLeftEngineLoadValue - 20;

            int prevRightEngineLoadValue = Integer.parseInt(rightEngineLoadValueLabel.getText());
            int nextRightEngineLoadValue = prevRightEngineLoadValue - 20;

            leftEngineLoadValueLabel.setText(String.valueOf(nextLeftEngineLoadValue));
            rightEngineLoadValueLabel.setText(String.valueOf(nextRightEngineLoadValue));

            galleyLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
        } else {
            Label leftEngineLoadValueLabel = elecUnitsConnectionsController.getLeftDrivePercentValue();
            Label rightEngineLoadValueLabel = elecUnitsConnectionsController.getRightDrivePercentValue();

            int prevLeftEngineLoadValue = Integer.parseInt(leftEngineLoadValueLabel.getText());
            int nextLeftEngineLoadValue = prevLeftEngineLoadValue + 20;

            int prevRightEngineLoadValue = Integer.parseInt(rightEngineLoadValueLabel.getText());
            int nextRightEngineLoadValue = prevRightEngineLoadValue + 20;

            leftEngineLoadValueLabel.setText(String.valueOf(nextLeftEngineLoadValue));
            rightEngineLoadValueLabel.setText(String.valueOf(nextRightEngineLoadValue));

            galleyLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    // TODO: Стоит перенести методы относящиеся к топливной системе в отдельный
    //  контроллер, а также сделать для кнопок отдельный Pane

    private boolean fuelLeftADDTButtonSwitched = false;

    @FXML private void switchFuelLeftADDTButton() {
        fuelLeftADDTButtonSwitched = !fuelLeftADDTButtonSwitched;

        if (fuelLeftADDTButtonSwitched) {

        } else {

        }
    }

    private boolean fuelLeftAUXButtonSwitched = false;

    @FXML private void switchFuelLeftAUXButton() {
        fuelLeftAUXButtonSwitched = !fuelLeftAUXButtonSwitched;

        if (fuelLeftAUXButtonSwitched) {

        } else {

        }
    }

    private boolean fuelLeftMAINButtonSwitched = false;

    @FXML private void switchFuelLeftMAINButton() {
        fuelLeftMAINButtonSwitched = !fuelLeftMAINButtonSwitched;

        if (fuelLeftMAINButtonSwitched) {

        } else {

        }
    }

    private boolean fuelRightMAINButtonSwitched = false;

    @FXML private void switchFuelRightMAINButton() {
        fuelRightMAINButtonSwitched = !fuelRightMAINButtonSwitched;

        if (fuelRightMAINButtonSwitched) {

        } else {

        }
    }

    private boolean fuelRightAUXButtonSwitched = false;

    @FXML private void switchFuelRightAUXButton() {
        fuelRightAUXButtonSwitched = !fuelRightAUXButtonSwitched;

        if (fuelRightAUXButtonSwitched) {

        } else {

        }
    }

    private boolean fuelRightADDTButtonSwitched = false;

    @FXML private void switchFuelRightADDTButton() {
        fuelRightADDTButtonSwitched = !fuelRightADDTButtonSwitched;

        if (fuelRightADDTButtonSwitched) {

        } else {

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