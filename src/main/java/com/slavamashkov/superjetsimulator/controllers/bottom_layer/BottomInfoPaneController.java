package com.slavamashkov.superjetsimulator.controllers.bottom_layer;

import com.slavamashkov.superjetsimulator.controllers.FxController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The main task of this class is to combine the controllers responsible
 * for the bottom layer of the screen, such as
 * {@link ElecUnitsController} and {@link ElecUnitsConnectionsController}.
 * <i>We can say that the class implements the Facade pattern, in relation
 * to the functions that implement the above classes.</i>
 * <p>
 * This class also serves to merge the fxml files into one.
 */
@Getter
@Component
@RequiredArgsConstructor
public class BottomInfoPaneController extends FxController {
    private final String source = "fxml/bottom_layer/bottom-info-pane.fxml";
    @FXML private Pane bottomInfoMainPane;

    private final ElecUnitsController elecUnitsController;
    private final ElecUnitsConnectionsController elecUnitsConnectionsController;

    @FXML private Pane elecUnitsPane;
    @FXML private Pane elecUnitsConnectionsPane;

    /**
     * Combines bottom-info-elec-units-pane.fxml
     * and bottom-info-elec-units-connections-pane.fxml into
     * one fxml file
     */
    @Override
    public void init() {
        elecUnitsPane.getChildren().add(
                elecUnitsController.getBottomInfoElecUnitsMainPane()
        );
        elecUnitsConnectionsPane.getChildren().add(
                elecUnitsConnectionsController.getBottomInfoElecUnitsConnectionsMainPane()
        );
    }

    public void activateExtPwr() {
        elecUnitsController.activateExtPwrUnit();
        elecUnitsConnectionsController.activateExtPwrConnection();
    }

    public void deactivateExtPwr() {
        elecUnitsController.deactivateExtPwrUnit();
        elecUnitsConnectionsController.deactivateExtPwrConnection();
    }

    public void activateApuGen() {
        elecUnitsController.activateApuGenUnit();
        elecUnitsConnectionsController.activateApuGenConnection();
    }

    public void deactivateApuGen() {
        elecUnitsController.deactivateApuGenUnit();
        elecUnitsConnectionsController.deactivateApuGenConnection();
    }

    public void activateLeftEngine() {
        elecUnitsController.activateLeftEngine();
        elecUnitsConnectionsController.activateLeftDriveToLeftConnection();
    }

    public void deactivateLeftEngine() {
        elecUnitsController.deactivateLeftEngine();
        elecUnitsConnectionsController.deactivateLeftDriveToLeftConnection();
    }

    public void activateRightEngine() {
        elecUnitsController.activateRightEngine();
        elecUnitsConnectionsController.activateRightDriveToRightConnection();
    }

    public void deactivateRightEngine() {
        elecUnitsController.deactivateRightEngine();
        elecUnitsConnectionsController.deactivateRightDriveToRightConnection();
    }
}
