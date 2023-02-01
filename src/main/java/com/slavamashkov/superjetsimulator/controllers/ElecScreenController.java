package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.controllers.bottom_layer.BottomInfoPaneController;
import com.slavamashkov.superjetsimulator.controllers.middle_layer.MiddleInfoPaneController;
import com.slavamashkov.superjetsimulator.controllers.name_layer.SystemNamePaneController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.UpperInfoPaneController;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.slavamashkov.superjetsimulator.enums.MyColor.*;

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
        if (leftEngineToggleButton.isSelected()) {
            bottomInfoPaneController.activateLeftEngine();
            selectionPanelController.getLeftGenLowerLight().setFill(INACTIVE_LIGHT_COLOR.color);
        } else {
            bottomInfoPaneController.deactivateLeftEngine();
            selectionPanelController.getLeftGenLowerLight().setFill(OFF_LIGHT_COLOR.color);
        }
    }

    @FXML public void onActionActivateRightEngine() {
        if (rightEngineToggleButton.isSelected()) {
            bottomInfoPaneController.activateRightEngine();
            selectionPanelController.getRightGenLowerLight().setFill(INACTIVE_LIGHT_COLOR.color);

        } else {
            bottomInfoPaneController.deactivateRightEngine();
            selectionPanelController.getRightGenLowerLight().setFill(OFF_LIGHT_COLOR.color);
        }
    }

    @FXML private void switchToSceneOne() {
        System.out.println("Switch");
    }
}
