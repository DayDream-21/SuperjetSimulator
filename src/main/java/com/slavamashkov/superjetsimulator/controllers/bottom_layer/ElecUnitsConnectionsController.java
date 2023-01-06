package com.slavamashkov.superjetsimulator.controllers.bottom_layer;

import com.slavamashkov.superjetsimulator.controllers.ElecScreenController;
import com.slavamashkov.superjetsimulator.controllers.FxController;
import com.slavamashkov.superjetsimulator.controllers.SelectionPanelController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * The class responsible for displaying the connections of units that
 * supply power to the system, such as the left and right drives, the
 * external power supply and the APU. For the drives the voltages,
 * frequencies and load percentage is displayed. {@link ElecUnitsController}
 * is responsible for displaying APU and external power information.
 */
@Getter
@Component
@RequiredArgsConstructor
public class ElecUnitsConnectionsController extends FxController {
    private final String source = "fxml/bottom_layer/bottom-info-elec-units-connections-pane.fxml";
    @FXML private Pane bottomInfoElecUnitsConnectionsMainPane;

    private ElecScreenController elecScreenController;
    private SelectionPanelController selectionPanelController;

    @Autowired
    public void setElecScreenController(@Lazy ElecScreenController elecScreenController) {
        this.elecScreenController = elecScreenController;
    }

    @Autowired
    public void setSelectionPanelController(@Lazy SelectionPanelController selectionPanelController) {
        this.selectionPanelController = selectionPanelController;
    }

    @FXML private Pane fromExtToLeft;
    @FXML private Pane fromExtToRight;
    @FXML private Pane fromApuToLeft;
    @FXML private Pane fromApuToRight;
    @FXML private Pane fromLeftDriveToLeft;
    @FXML private Pane fromLeftDriveToLeftRight;
    @FXML private Pane leftDriveInfoPane;
    @FXML private Pane fromRightDriveToRight;
    @FXML private Pane fromRightDriveToLeftRight;
    @FXML private Pane rightDriveInfoPane;

    public void activateExtPwrConnectionToLeft() {
        fromExtToLeft.setOpacity(1.0);
    }

    public void deactivateExtPwrConnectionToLeft() {
        fromExtToLeft.setOpacity(0.0);
    }

    public void activateExtPwrConnectionToRight() {
        fromExtToRight.setOpacity(1.0);
    }

    public void deactivateExtPwrConnectionToRight() {
        fromExtToRight.setOpacity(0.0);
    }

    public void activateApuGenConnectionToLeft() {
        fromApuToLeft.setOpacity(1.0);
    }

    public void deactivateApuGenConnectionToLeft() {
        fromApuToLeft.setOpacity(0.0);
    }

    public void activateApuGenConnectionToRight() {
        fromApuToRight.setOpacity(1.0);
    }

    public void deactivateApuGenConnectionToRight() {
        fromApuToRight.setOpacity(0.0);
    }

    public void connectApuGenConnection() {
        activateApuGenConnectionToLeft();
        activateApuGenConnectionToRight();
    }

    public void disconnectApuGenConnection() {
        deactivateApuGenConnectionToLeft();
        deactivateApuGenConnectionToRight();

        // Если после отжатия кнопки APU GEN, кнопка EXT PWR все еще нажата, тогда
        if (selectionPanelController.isExtPwrSwitchedPressed()) {
            if (elecScreenController.isLeftEngineConnected() && !elecScreenController.isRightEngineConnected()) {
                // Если левый двигатель включен, а правый нет, тогда подключаем внешнее питание к правой подсети
                activateExtPwrConnectionToRight();
            } else if (elecScreenController.isRightEngineConnected() && !elecScreenController.isLeftEngineConnected()) {
                // Если правый двигатель включен, а левый нет, тогда подключаем внешнее питание к левой подсети
                activateExtPwrConnectionToLeft();
            } else if (!elecScreenController.isLeftEngineConnected() && !elecScreenController.isRightEngineConnected()) {
                // Если оба двигателя отключены, тогда подключаем внешнее питание к левой и правой подсети
                activateExtPwrConnection();
            }
        }
    }

    public void activateExtPwrConnection() {
        activateExtPwrConnectionToLeft();
        activateExtPwrConnectionToRight();
    }

    public void disconnectExtPwrConnection() {
        deactivateExtPwrConnectionToLeft();
        deactivateExtPwrConnectionToRight();
    }

    public void activateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(1.0);
        fromLeftDriveToLeft.setOpacity(1.0);
    }

    public void deactivateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(0.0);
        fromLeftDriveToLeft.setOpacity(0.0);
    }

    public void activateLeftDriveToLeftRightConnection() {
        leftDriveInfoPane.setOpacity(1.0);
        fromLeftDriveToLeftRight.setOpacity(1.0);
    }

    public void deactivateLeftDriveToLeftRightConnection() {
        leftDriveInfoPane.setOpacity(0.0);
        fromLeftDriveToLeftRight.setOpacity(0.0);
    }

    public void activateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(1.0);
        fromRightDriveToRight.setOpacity(1.0);
    }

    public void deactivateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(0.0);
        fromRightDriveToRight.setOpacity(0.0);
    }

    public void activateRightDriveToLeftRightConnection() {
        rightDriveInfoPane.setOpacity(1.0);
        fromRightDriveToLeftRight.setOpacity(1.0);
    }

    public void deactivateRightDriveToLeftRightConnection() {
        rightDriveInfoPane.setOpacity(0.0);
        fromRightDriveToLeftRight.setOpacity(0.0);
    }

    public void connectLeftEngine() {
        if (elecScreenController.isRightEngineConnected()) {
            activateLeftDriveToLeftConnection();

            deactivateRightDriveToLeftRightConnection();
            activateRightDriveToRightConnection();
        } else {
            activateLeftDriveToLeftRightConnection();
        }
    }

    public void disconnectLeftEngine() {
        if (elecScreenController.isRightEngineConnected()) {
            deactivateLeftDriveToLeftConnection();
            deactivateLeftDriveToLeftRightConnection();

            deactivateRightDriveToRightConnection();
            activateRightDriveToLeftRightConnection();
        } else {
            deactivateLeftDriveToLeftConnection();
            deactivateLeftDriveToLeftRightConnection();
        }
    }

    public void connectRightEngine() {
        if (elecScreenController.isLeftEngineConnected()) {
            activateRightDriveToRightConnection();

            deactivateLeftDriveToLeftRightConnection();
            activateLeftDriveToLeftConnection();
        } else {
            activateRightDriveToLeftRightConnection();
        }
    }

    public void disconnectRightEngine() {
        if (elecScreenController.isLeftEngineConnected()) {
            deactivateRightDriveToRightConnection();
            deactivateRightDriveToLeftRightConnection();

            deactivateLeftDriveToLeftConnection();
            activateLeftDriveToLeftRightConnection();
        } else {
            deactivateRightDriveToRightConnection();
            deactivateRightDriveToLeftRightConnection();
        }
    }

    public void connectExtPwrUnit() {
        if (elecScreenController.isLeftEngineConnected() && !elecScreenController.isRightEngineConnected()) {
            // Если левый двигатель включен, а правый нет, тогда подключаем к правой подсети
            activateExtPwrConnectionToRight();
        } else if (elecScreenController.isRightEngineConnected() && !elecScreenController.isLeftEngineConnected()) {
            // Если правый двигатель включен, а левый нет, тогда подключаем к левой подсети
            activateExtPwrConnectionToLeft();
        } else if (!elecScreenController.isLeftEngineConnected() && !elecScreenController.isRightEngineConnected()) {
            // Если оба двигателя отключены, тогда подключаем к левой и правой подсети
            activateExtPwrConnection();
        }
        // Если одновременно с внешним питанием включено ВСУ, тогда отдаем приоритет ВСУ
        if (selectionPanelController.isApuGenSwitchedPressed()) {
            disconnectExtPwrConnection();
        }
    }

    public void connectApuGenUnit() {
        if (elecScreenController.isLeftEngineConnected() && !elecScreenController.isRightEngineConnected()) {
            // Если левый двигатель включен, а правый нет, тогда подключаем к правой подсети
            activateApuGenConnectionToRight();
        } else if (elecScreenController.isRightEngineConnected() && !elecScreenController.isLeftEngineConnected()) {
            // Если правый двигатель включен, а левый нет, тогда подключаем к левой подсети
            activateApuGenConnectionToLeft();
        } else if (!elecScreenController.isLeftEngineConnected() && !elecScreenController.isRightEngineConnected()) {
            // Если оба двигателя отключены, тогда подключаем к левой и правой подсети
            connectApuGenConnection();
        }
        // Если одновременно с ВСУ включено внешнее питание, тогда отдаем приоритет ВСУ
        if (selectionPanelController.isExtPwrSwitchedPressed()) {
            disconnectExtPwrConnection();
        }
    }
}
