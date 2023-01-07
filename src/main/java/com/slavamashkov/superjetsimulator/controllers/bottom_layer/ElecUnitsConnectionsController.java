package com.slavamashkov.superjetsimulator.controllers.bottom_layer;

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

    private SelectionPanelController selectionPanelController;

    @Autowired
    public void setSelectionPanelController(@Lazy SelectionPanelController selectionPanelController) {
        this.selectionPanelController = selectionPanelController;
    }

    @FXML private Pane fromExtToLeft;
    @FXML private Pane fromExtToRight;
    @FXML private Pane fromApuToLeft;
    @FXML private Pane fromApuToRight;
    @FXML private Pane fromLeftDriveToLeft;
    @FXML private Pane fromLeftDriveToRight;
    @FXML private Pane leftDriveInfoPane;
    @FXML private Pane fromRightDriveToRight;
    @FXML private Pane fromRightDriveToLeft;
    @FXML private Pane rightDriveInfoPane;

    public void connectLeftEngine() {
        if (isRightEngineConnected()) {
            deactivateRightDriveToLeftConnection();

            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateLeftDriveToLeftConnection();
                return;
            }

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateLeftDriveToLeftConnection();
                connectApuGenUnit();
                return;
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateLeftDriveToLeftConnection();
                connectExtPwrUnit();
            }
        } else {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateLeftDriveToLeftRightConnection();
                return;
            }

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateLeftDriveToLeftConnection();
                connectApuGenUnit();
                return;
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateLeftDriveToLeftConnection();
                connectExtPwrUnit();
            }
        }
    }

    public void disconnectLeftEngine() {
        deactivateLeftDriveToLeftConnection();

        if (isRightEngineConnected()) {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateRightDriveToLeftRightConnection();
                return;
            }

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateRightDriveToRightConnection();
                connectApuGenUnit();
                return;
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateRightDriveToRightConnection();
                connectExtPwrUnit();
            }
        } else {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                return;
            }

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                connectApuGenUnit();
                return;
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                connectExtPwrUnit();
            }
        }
    }

    public void connectRightEngine() {
        if (isLeftEngineConnected()) {
            deactivateLeftDriveToRightConnection();

            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateRightDriveToRightConnection();
                return;
            }

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateRightDriveToRightConnection();
                connectApuGenUnit();
                return;
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateRightDriveToRightConnection();
                connectExtPwrUnit();
            }
        } else {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateRightDriveToLeftRightConnection();
                return;
            }

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateRightDriveToRightConnection();
                connectApuGenUnit();
                return;
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateRightDriveToRightConnection();
                connectExtPwrUnit();
            }
        }
    }

    public void disconnectRightEngine() {
        deactivateRightDriveToRightConnection();

        if (isLeftEngineConnected()) {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateLeftDriveToLeftRightConnection();
                return;
            }

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateLeftDriveToLeftConnection();
                connectApuGenUnit();
                return;
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateLeftDriveToLeftConnection();
                connectExtPwrUnit();
            }

        } else {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                return;
            }

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                connectApuGenUnit();
                return;
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                connectExtPwrUnit();
            }
        }
    }

    public void connectApuGenUnit() {
        if (isLeftEngineConnected() && isRightEngineConnected()) {
            deactivateApuGenConnection();
        } else if (isLeftEngineConnected() && !isRightEngineConnected()) {
            activateApuGenConnectionToRight();
            deactivateApuGenConnectionToLeft();

            deactivateLeftDriveToRightConnection();
        } else if (isRightEngineConnected() && !isLeftEngineConnected()) {
            activateApuGenConnectionToLeft();
            deactivateApuGenConnectionToRight();

            deactivateRightDriveToLeftConnection();
        } else if (!isLeftEngineConnected() && !isRightEngineConnected()) {
            activateApuGenConnection();
        }

        if (selectionPanelController.isExtPwrSwitchedPressed()) {
            deactivateExtPwrConnection();
        }
    }

    public void disconnectApuGenUnit() {
        deactivateApuGenConnection();

        if (isLeftEngineConnected() && !isRightEngineConnected()) {
            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateExtPwrConnectionToRight();
            } else {
                activateLeftDriveToRightConnection();
            }
        } else if (!isLeftEngineConnected() && isRightEngineConnected()) {
            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateExtPwrConnectionToLeft();
            } else {
                activateRightDriveToLeftConnection();
            }
        } else if (!isLeftEngineConnected() && !isRightEngineConnected()) {
            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateExtPwrConnection();
            }
        }
    }

    public void connectExtPwrUnit() {
        if (isLeftEngineConnected() && isRightEngineConnected()) {
            deactivateExtPwrConnection();
        } else if (isLeftEngineConnected() && !isRightEngineConnected()) {
            activateExtPwrConnectionToRight();
            deactivateExtPwrConnectionToLeft();

            deactivateLeftDriveToRightConnection();
        } else if (isRightEngineConnected() && !isLeftEngineConnected()) {
            activateExtPwrConnectionToLeft();
            deactivateExtPwrConnectionToRight();

            deactivateRightDriveToLeftConnection();
        } else if (!isLeftEngineConnected() && !isRightEngineConnected()) {
            activateExtPwrConnection();
        }

        if (selectionPanelController.isApuGenSwitchedPressed()) {
            deactivateExtPwrConnection();
        }
    }

    public void disconnectExtPwrUnit() {
        deactivateExtPwrConnection();

        if (isLeftEngineConnected() && !isRightEngineConnected()) {
            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateApuGenConnectionToRight();
            } else {
                activateLeftDriveToRightConnection();
            }
        } else if (!isLeftEngineConnected() && isRightEngineConnected()) {
            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateApuGenConnectionToLeft();
            } else {
                activateRightDriveToLeftConnection();
            }
        } else if (!isLeftEngineConnected() && !isRightEngineConnected()) {
            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateApuGenConnection();
            }
        }
    }

    // Private methods
    private boolean isLeftEngineConnected() {
        return getFromLeftDriveToLeft().getOpacity() > 0.0 || getFromLeftDriveToRight().getOpacity() > 0.0;
    }

    private boolean isRightEngineConnected() {
        return getFromRightDriveToRight().getOpacity() > 0.0 || getFromRightDriveToLeft().getOpacity() > 0.0;
    }

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

    private void activateLeftDriveToLeftRightConnection() {
        activateLeftDriveToLeftConnection();
        activateLeftDriveToRightConnection();
    }

    private void activateRightDriveToLeftRightConnection() {
        activateRightDriveToRightConnection();
        activateRightDriveToLeftConnection();
    }

    private void deactivateLeftDriveToLeftRightConnection() {
        deactivateLeftDriveToLeftConnection();
        deactivateLeftDriveToRightConnection();
    }

    private void deactivateRightDriveToLeftRightConnection() {
        deactivateRightDriveToRightConnection();
        deactivateRightDriveToLeftConnection();
    }

    private void activateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(1.0);
        fromLeftDriveToLeft.setOpacity(1.0);
    }

    private void deactivateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(0.0);
        fromLeftDriveToLeft.setOpacity(0.0);
    }

    private void activateLeftDriveToRightConnection() {
        fromLeftDriveToRight.setOpacity(1.0);
    }

    private void deactivateLeftDriveToRightConnection() {
        fromLeftDriveToRight.setOpacity(0.0);
    }

    private void activateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(1.0);
        fromRightDriveToRight.setOpacity(1.0);
    }

    private void deactivateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(0.0);
        fromRightDriveToRight.setOpacity(0.0);
    }

    private void activateRightDriveToLeftConnection() {
        fromRightDriveToLeft.setOpacity(1.0);
    }

    private void deactivateRightDriveToLeftConnection() {
        fromRightDriveToLeft.setOpacity(0.0);
    }
}