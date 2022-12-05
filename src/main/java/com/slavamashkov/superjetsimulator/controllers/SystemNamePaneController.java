package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class SystemNamePaneController extends FxController {
    private final String source = "fxml/system-name-pane.fxml";
    @FXML private Pane systemNamePaneMainPane;
}
