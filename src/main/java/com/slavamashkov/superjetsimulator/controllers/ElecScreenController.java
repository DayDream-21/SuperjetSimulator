package com.slavamashkov.superjetsimulator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

    public void onActionActivateLeftEngine(ActionEvent event) {
        if (leftEngineToggleButton.isSelected()) {
            bottomInfoPaneController.activateLeftEngine();
        } else {
            bottomInfoPaneController.deactivateLeftEngine();
        }
    }

    public void onActionActivateRightEngine(ActionEvent event) {
        if (rightEngineToggleButton.isSelected()) {
            bottomInfoPaneController.activateRightEngine();
        } else {
            bottomInfoPaneController.deactivateRightEngine();
        }
    }
}
