package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.controllers.bottom_layer.BottomInfoPaneController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsConnectionsController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.BatsConnectionsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.UpperInfoPaneController;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
    private final ElecUnitsController elecUnitsController;
    private final ElecUnitsConnectionsController elecUnitsConnectionsController;

    private ElecScreenController elecScreenController;

    @Autowired
    public void setElecScreenController(@Lazy ElecScreenController elecScreenController) {
        this.elecScreenController = elecScreenController;
    }

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

    boolean bat1SwitchedPressed = false;

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

    boolean bat2SwitchedPressed = false;

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

    boolean bat3SwitchedPressed = false;

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

    boolean bat4SwitchedPressed = false;

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
    // TODO: переместить логику подключения в другой класс
    @FXML public void switchExtPwrButton() {
        extPwrSwitchedPressed = !extPwrSwitchedPressed; // Если метод был вызван, значит меняем состояние кнопки
        // Если состояние кнопки "нажата"
        if (extPwrSwitchedPressed) {
            elecUnitsController.activateExtPwrUnit(); // Включаем блок внешнего питания
            elecUnitsConnectionsController.connectExtPwrUnit(); // и подключаем его
            // Переключаем подсветку кнопки
            extPwrUpperLight.setFill(INACTIVE_LIGHT_COLOR.color);
            extPwrLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
        } else { // Если состояние кнопки "отжата"
            elecUnitsController.deactivateExtPwrUnit(); // Отключаем блок внешнего питания
            elecUnitsConnectionsController.disconnectExtPwrConnection(); // Отсоединяем блок от левой и правой подсети

            extPwrUpperLight.setFill(ACTIVE_LIGHT_COLOR.color);
            extPwrLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        }
    }

    private boolean apuGenSwitchedPressed = false;
    // TODO: переместить логику подключения в другой класс
    @FXML private void switchApuGenButton() {
        apuGenSwitchedPressed = !apuGenSwitchedPressed; // Если метод был вызван, значит меняем состояние кнопки
        // Если состояние кнопки "нажата"
        if (apuGenSwitchedPressed) {
            elecUnitsController.activateApuGenUnit(); // Включаем блок ВСУ
            elecUnitsConnectionsController.connectApuGenUnit(); // и подключаем его
            // Переключаем подсветку кнопки
            apuGenLowerLight.setFill(INACTIVE_LIGHT_COLOR.color);
        } else { // Если состояние кнопки "отжата"
            elecUnitsController.deactivateApuGenUnit(); // Отключаем блок ВСУ
            elecUnitsConnectionsController.disconnectApuGenConnection(); // Отсоединяем блок от левой и правой подсети
            // Переключаем подсветку кнопки
            apuGenLowerLight.setFill(ACTIVE_LIGHT_COLOR.color);
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