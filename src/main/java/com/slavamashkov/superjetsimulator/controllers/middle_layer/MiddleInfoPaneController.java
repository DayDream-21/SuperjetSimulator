package com.slavamashkov.superjetsimulator.controllers.middle_layer;

import com.slavamashkov.superjetsimulator.controllers.FxController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 *
 */
@Getter
@Component
@RequiredArgsConstructor
public class MiddleInfoPaneController extends FxController {
    private final String source = "fxml/middle_layer/middle-info-pane.fxml";
    @FXML private Pane middleInfoMainPane;

    @FXML private Rectangle leftMainBusDC;
    @FXML private Rectangle rightEmrgBusDC;
}
