package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class MiddleInfoPaneController extends FxController {
    private final String source = "fxml/middle-info-pane.fxml";
    @FXML private Pane middleInfoPaneMainPane;

    @FXML private Rectangle leftMainBusDC;
    @FXML private Rectangle rightEmrgBusDC;
}
