package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.controllers.bottom_layer.BottomInfoPaneController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsConnectionsController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsController;
import com.slavamashkov.superjetsimulator.controllers.middle_layer.MiddleInfoPaneController;
import com.slavamashkov.superjetsimulator.controllers.name_layer.SystemNamePaneController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.UpperInfoPaneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ACTIVE_LIGHT_COLOR;
import static com.slavamashkov.superjetsimulator.enums.MyColor.INACTIVE_LIGHT_COLOR;

/**
 * The main task of this class is to combine the controllers responsible
 * for certain layers of the screen, such as
 * {@link SystemNamePaneController}, {@link UpperInfoPaneController},
 * {@link MiddleInfoPaneController} and {@link BottomInfoPaneController}.
 * <i>We can say that the class implements the Facade pattern, in relation
 * to the functions that implement the above classes.</i>
 * <p>
 * This class also serves to merge the fxml files into one.
 */
@Getter
@Component
@RequiredArgsConstructor
public class ElecScreenController extends FxController {
    private final String source = "fxml/elec-screen.fxml";
    @FXML private AnchorPane elecScreenMainPane;

    private final SystemNamePaneController systemNamePaneController;
    private final UpperInfoPaneController upperInfoPaneController;
    private final MiddleInfoPaneController middleInfoPaneController;
    private final BottomInfoPaneController bottomInfoPaneController;
    private final ElecUnitsController elecUnitsController;
    private final ElecUnitsConnectionsController elecUnitsConnectionsController;
    private final SelectionPanelController selectionPanelController;

    @FXML private Pane systemNamePane;
    @FXML private Pane upperInfoPane;
    @FXML private Pane middleInfoPane;
    @FXML private Pane bottomInfoPane;

    @FXML private ToggleButton leftEngineToggleButton;
    @FXML private ToggleButton rightEngineToggleButton;

    /**
     * Combines system-name-pane.fxml, upper-info-pane.fxml,
     * middle-info-pane.fxml, and bottom-info-pane.fxml into
     * one fxml file
     */
    @Override
    public void init() {
        systemNamePane.getChildren().add(systemNamePaneController.getSystemNameMainPane());
        upperInfoPane.getChildren().add(upperInfoPaneController.getUpperInfoMainPane());
        middleInfoPane.getChildren().add(middleInfoPaneController.getMiddleInfoMainPane());
        bottomInfoPane.getChildren().add(bottomInfoPaneController.getBottomInfoMainPane());
    }

    @FXML public void onActionActivateLeftEngine() {
        if (selectionPanelController.isLeftGenButtonPressed()) {
            bottomInfoPaneController.activateLeftEngine();
            selectionPanelController.getLeftGenLowerLight().setFill(INACTIVE_LIGHT_COLOR.color);

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                elecUnitsConnectionsController.deactivateApuGenConnectionToLeft();
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                elecUnitsConnectionsController.deactivateExtPwrConnectionToLeft();
            }
        } else {
            bottomInfoPaneController.deactivateLeftEngine();
            selectionPanelController.getLeftGenLowerLight().setFill(ACTIVE_LIGHT_COLOR.color);

            if (selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                elecUnitsConnectionsController.activateApuGenConnectionToLeft();
            } else if (selectionPanelController.isExtPwrSwitchedPressed() && !selectionPanelController.isApuGenSwitchedPressed()) {
                elecUnitsConnectionsController.activateExtPwrConnectionToLeft();
            } else if (selectionPanelController.isApuGenSwitchedPressed() && selectionPanelController.isExtPwrSwitchedPressed()) {
                elecUnitsConnectionsController.activateApuGenConnectionToLeft();
            }
        }
    }

    @FXML public void onActionActivateRightEngine() {
        if (selectionPanelController.isRightGenButtonPressed()) {
            bottomInfoPaneController.activateRightEngine();
            selectionPanelController.getRightGenLowerLight().setFill(INACTIVE_LIGHT_COLOR.color);

            if (selectionPanelController.isApuGenSwitchedPressed()) {
                elecUnitsConnectionsController.deactivateApuGenConnectionToRight();
            }

            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                elecUnitsConnectionsController.deactivateExtPwrConnectionToRight();
            }
        } else {
            bottomInfoPaneController.deactivateRightEngine();
            selectionPanelController.getRightGenLowerLight().setFill(ACTIVE_LIGHT_COLOR.color);

            if (selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                elecUnitsConnectionsController.activateApuGenConnectionToRight();
            } else if (selectionPanelController.isExtPwrSwitchedPressed() && !selectionPanelController.isApuGenSwitchedPressed()) {
                elecUnitsConnectionsController.activateExtPwrConnectionToRight();
            } else if (selectionPanelController.isApuGenSwitchedPressed() && selectionPanelController.isExtPwrSwitchedPressed()) {
                elecUnitsConnectionsController.activateApuGenConnectionToRight();
            }
        }
    }

    public boolean isLeftEngineConnected() {
        return elecUnitsConnectionsController.getFromLeftDriveToLeft().getOpacity() > 0.0 ||
                elecUnitsConnectionsController.getFromLeftDriveToLeftRight().getOpacity() > 0.0;
    }

    public boolean isRightEngineConnected() {
        return elecUnitsConnectionsController.getFromRightDriveToRight().getOpacity() > 0.0 ||
                elecUnitsConnectionsController.getFromRightDriveToLeftRight().getOpacity() > 0.0;
    }

    @FXML private void switchToSceneOne(ActionEvent actionEvent) {
        System.out.println("Switch");
    }
}
