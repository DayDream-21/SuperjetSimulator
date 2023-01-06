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

    public void connectLeftEngine() {
        if (selectionPanelController.isApuGenSwitchedPressed()) {
            deactivateApuGenConnectionToLeft();
        }

        if (selectionPanelController.isExtPwrSwitchedPressed()) {
            deactivateExtPwrConnectionToLeft();
        }

        if (elecScreenController.isRightEngineConnected()) {
            activateLeftDriveToLeftConnection();

            deactivateRightDriveToLeftRightConnection();
            activateRightDriveToRightConnection();
        } else {
            activateLeftDriveToLeftRightConnection();
        }
    }

    public void disconnectLeftEngine() {
        if (selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
            activateApuGenConnectionToLeft();
        } else if (selectionPanelController.isExtPwrSwitchedPressed() && !selectionPanelController.isApuGenSwitchedPressed()) {
            activateExtPwrConnectionToLeft();
        } else if (selectionPanelController.isApuGenSwitchedPressed() && selectionPanelController.isExtPwrSwitchedPressed()) {
            activateApuGenConnectionToLeft();
        }

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
        if (selectionPanelController.isApuGenSwitchedPressed()) {
            deactivateApuGenConnectionToRight();
        }

        if (selectionPanelController.isExtPwrSwitchedPressed()) {
            deactivateExtPwrConnectionToRight();
        }

        if (elecScreenController.isLeftEngineConnected()) {
            activateRightDriveToRightConnection();

            deactivateLeftDriveToLeftRightConnection();
            activateLeftDriveToLeftConnection();
        } else {
            activateRightDriveToLeftRightConnection();
        }
    }

    public void disconnectRightEngine() {
        if (selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
            activateApuGenConnectionToRight();
        } else if (selectionPanelController.isExtPwrSwitchedPressed() && !selectionPanelController.isApuGenSwitchedPressed()) {
            activateExtPwrConnectionToRight();
        } else if (selectionPanelController.isApuGenSwitchedPressed() && selectionPanelController.isExtPwrSwitchedPressed()) {
            activateApuGenConnectionToRight();
        }

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

    public void connectApuGenUnit() {
        if (elecScreenController.isLeftEngineConnected() && !elecScreenController.isRightEngineConnected()) {
            // Если левый двигатель включен, а правый нет, тогда подключаем к правой подсети
            activateApuGenConnectionToRight();
        } else if (elecScreenController.isRightEngineConnected() && !elecScreenController.isLeftEngineConnected()) {
            // Если правый двигатель включен, а левый нет, тогда подключаем к левой подсети
            activateApuGenConnectionToLeft();
        } else if (!elecScreenController.isLeftEngineConnected() && !elecScreenController.isRightEngineConnected()) {
            // Если оба двигателя отключены, тогда подключаем к левой и правой подсети
            activateApuGenConnection();
        }
        // Если одновременно с ВСУ включено внешнее питание, тогда отдаем приоритет ВСУ
        if (selectionPanelController.isExtPwrSwitchedPressed()) {
            deactivateExtPwrConnection();
        }
    }

    public void disconnectApuGenUnit() {
        deactivateApuGenConnection();

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
            deactivateExtPwrConnection();
        }
    }

    public void disconnectExtPwrUnit() {
        deactivateExtPwrConnection();
    }

    // Private methods

    private void activateExtPwrConnection() {
        activateExtPwrConnectionToLeft();
        activateExtPwrConnectionToRight();
    }

    private void deactivateApuGenConnection() {
        deactivateApuGenConnectionToLeft();
        deactivateApuGenConnectionToRight();
    }

    private void deactivateExtPwrConnection() {
        deactivateExtPwrConnectionToLeft();
        deactivateExtPwrConnectionToRight();
    }

    private void activateApuGenConnection() {
        activateApuGenConnectionToLeft();
        activateApuGenConnectionToRight();
    }

    private void activateExtPwrConnectionToLeft() {
        fromExtToLeft.setOpacity(1.0);
    }

    private void deactivateExtPwrConnectionToLeft() {
        fromExtToLeft.setOpacity(0.0);
    }

    private void activateExtPwrConnectionToRight() {
        fromExtToRight.setOpacity(1.0);
    }

    private void deactivateExtPwrConnectionToRight() {
        fromExtToRight.setOpacity(0.0);
    }

    private void activateApuGenConnectionToLeft() {
        fromApuToLeft.setOpacity(1.0);
    }

    private void deactivateApuGenConnectionToLeft() {
        fromApuToLeft.setOpacity(0.0);
    }

    private void activateApuGenConnectionToRight() {
        fromApuToRight.setOpacity(1.0);
    }

    private void deactivateApuGenConnectionToRight() {
        fromApuToRight.setOpacity(0.0);
    }

    private void activateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(1.0);
        fromLeftDriveToLeft.setOpacity(1.0);
    }

    private void deactivateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(0.0);
        fromLeftDriveToLeft.setOpacity(0.0);
    }

    private void activateLeftDriveToLeftRightConnection() {
        leftDriveInfoPane.setOpacity(1.0);
        fromLeftDriveToLeftRight.setOpacity(1.0);
    }

    private void deactivateLeftDriveToLeftRightConnection() {
        leftDriveInfoPane.setOpacity(0.0);
        fromLeftDriveToLeftRight.setOpacity(0.0);
    }

    private void activateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(1.0);
        fromRightDriveToRight.setOpacity(1.0);
    }

    private void deactivateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(0.0);
        fromRightDriveToRight.setOpacity(0.0);
    }

    private void activateRightDriveToLeftRightConnection() {
        rightDriveInfoPane.setOpacity(1.0);
        fromRightDriveToLeftRight.setOpacity(1.0);
    }

    private void deactivateRightDriveToLeftRightConnection() {
        rightDriveInfoPane.setOpacity(0.0);
        fromRightDriveToLeftRight.setOpacity(0.0);
    }
}
