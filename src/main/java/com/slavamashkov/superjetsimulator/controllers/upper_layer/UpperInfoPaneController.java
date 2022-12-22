package com.slavamashkov.superjetsimulator.controllers.upper_layer;

import com.slavamashkov.superjetsimulator.controllers.FxController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
@RequiredArgsConstructor
public class UpperInfoPaneController extends FxController {
    private final String source = "fxml/upper-info-pane.fxml";
    @FXML private Pane upperInfoMainPane;

    private final BatsController batsController;
    private final BatsConnectionsController batsConnectionsController;

    @FXML private Pane batsPane;
    @FXML private Pane batsConnectionsPane;

    /**
     *
     */
    @Override
    public void init() {
        batsPane.getChildren().add(
                batsController.getUpperInfoBatsMainPane()
        );
        batsConnectionsPane.getChildren().add(
                batsConnectionsController.getUpperInfoBatsConnectionsMainPane()
        );
    }

    public void batOff(int i) {
        batsController.batOff(i);
        batsConnectionsController.batOff(i);
    }

    public void batOn(int i) {
        batsController.batOn(i);
        batsConnectionsController.batOn(i);
    }
}
