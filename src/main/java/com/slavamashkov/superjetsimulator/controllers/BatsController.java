package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class BatsController extends FxController {
    private final String source = "fxml/upper-info-bats-pane.fxml";
    @FXML private Pane upperInfoBatsPaneMainPane;

    @FXML private Rectangle bat1Frame;
    @FXML private Rectangle bat3Frame;
    @FXML private Rectangle bat2Frame;
    @FXML private Rectangle bat4Frame;

    @Override
    public void init() {}

    public void batOff(int i) {
        switch (i) {
            case 1 -> bat1Frame.setStroke(Color.WHITE);
            case 2 -> bat2Frame.setStroke(Color.WHITE);
            case 3 -> bat3Frame.setStroke(Color.WHITE);
            case 4 -> bat4Frame.setStroke(Color.WHITE);
        }
    }

    public void batOn(int i) {
        switch (i) {
            case 1 -> bat1Frame.setStroke(Color.LIME);
            case 2 -> bat2Frame.setStroke(Color.LIME);
            case 3 -> bat3Frame.setStroke(Color.LIME);
            case 4 -> bat4Frame.setStroke(Color.LIME);
        }
    }
}
