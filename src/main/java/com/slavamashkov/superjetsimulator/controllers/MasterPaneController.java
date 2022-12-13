package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.malfunctions.Malfunction;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * The main task of this class is to combine the controllers responsible
 * for the panel, ({@link SelectionPanelController}) through which the
 * user interacts with the application, and the screen
 * ({@link ElecScreenController}) on which the user monitors the status
 * of the power system.
 * <p>
 * <i>We can say that the class implements the Facade pattern, in relation
 * to the functions that implement the above classes.</i>
 * <p>
 * This class also serves to merge the fxml files into one.
 */
@Getter
@Component
@RequiredArgsConstructor
public class MasterPaneController extends FxController {
    private final String source = "fxml/master-pane.fxml";

    private final SelectionPanelController selectionPanelController;
    private final ElecScreenController elecScreenController;

    @FXML private Pane selectionPanelPane;
    @FXML private Pane elecScreen;

    /**
     * Combines selection-panel-pane.fxml and elec-screen.fxml
     * into one fxml file
     */
    @Override
    public void init() {
        super.init();
        //getStage().setOnCloseRequest(e -> returnToDefault());
        selectionPanelPane.getChildren().add(selectionPanelController.getSelectionPanelMainPane());
        elecScreen.getChildren().add(elecScreenController.getElecScreenMainPane());
    }

    /*// todo implement method to call Malfunction#restoreSystem()
    // Return stage to its default condition
    private void returnToDefault() {
    }*/

    /**
     * Accepts the fault selected on the initial screen and
     * calls the method that activates it.
     *
     * @param malfunction object with an implemented method, which
     *                   is responsible for exactly how the fault
     *                   will manifest itself during the operation
     *                   of the system
     */
    public void receiveData(Malfunction malfunction) {
        malfunction.executeMalfunction();
    }
}
