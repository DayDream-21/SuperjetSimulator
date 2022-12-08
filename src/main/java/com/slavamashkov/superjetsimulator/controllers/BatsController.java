package com.slavamashkov.superjetsimulator.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ACTIVE_COLOR;
import static com.slavamashkov.superjetsimulator.enums.MyColor.INACTIVE_COLOR;

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
            case 1 -> bat1Frame.setStroke(INACTIVE_COLOR.color);
            case 2 -> bat2Frame.setStroke(INACTIVE_COLOR.color);
            case 3 -> bat3Frame.setStroke(INACTIVE_COLOR.color);
            case 4 -> bat4Frame.setStroke(INACTIVE_COLOR.color);
        }
    }

    public void batOn(int i) {
        switch (i) {
            case 1 -> bat1Frame.setStroke(ACTIVE_COLOR.color);
            case 2 -> bat2Frame.setStroke(ACTIVE_COLOR.color);
            case 3 -> bat3Frame.setStroke(ACTIVE_COLOR.color);
            case 4 -> bat4Frame.setStroke(ACTIVE_COLOR.color);
        }
    }
}
