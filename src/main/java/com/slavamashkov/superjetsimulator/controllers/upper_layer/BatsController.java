package com.slavamashkov.superjetsimulator.controllers.upper_layer;

import com.slavamashkov.superjetsimulator.controllers.FxController;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.slavamashkov.superjetsimulator.enums.MyColor.*;

/**
 * The class responsible for the battery display and the color change
 * depending on the presence or absence of a fault in the system
 */
@Getter
@Component
@RequiredArgsConstructor
public class BatsController extends FxController {
    private final String source = "fxml/upper-info-bats-pane.fxml";
    @FXML private Pane upperInfoBatsMainPane;

    @FXML private Rectangle bat1Frame;
    @FXML private Rectangle bat2Frame;
    @FXML private Rectangle bat3Frame;
    @FXML private Rectangle bat4Frame;

    /**
     * Allows you to turn on a certain battery
     *
     * @param i the battery number must be an integer between 1 and 4 inclusive
     */
    public void batOn(int i) {
        switch (i) {
            case 1 -> bat1Frame.setStroke(ACTIVE_COLOR.color);
            case 2 -> bat2Frame.setStroke(ACTIVE_COLOR.color);
            case 3 -> bat3Frame.setStroke(ACTIVE_COLOR.color);
            case 4 -> bat4Frame.setStroke(ACTIVE_COLOR.color);
        }
    }

    public void batOnInverse(int i) {
        switch (i) {
            case 1 -> bat1Frame.setStroke(WARNING_COLOR.color);
            case 2 -> bat2Frame.setStroke(WARNING_COLOR.color);
            case 3 -> bat3Frame.setStroke(WARNING_COLOR.color);
            case 4 -> bat4Frame.setStroke(WARNING_COLOR.color);
        }
    }

    /**
     * Allows you to turn off a certain battery
     *
     * @param i the battery number must be an integer between 1 and 4 inclusive
     */
    public void batOff(int i) {
        switch (i) {
            case 1 -> bat1Frame.setStroke(INACTIVE_COLOR.color);
            case 2 -> bat2Frame.setStroke(INACTIVE_COLOR.color);
            case 3 -> bat3Frame.setStroke(INACTIVE_COLOR.color);
            case 4 -> bat4Frame.setStroke(INACTIVE_COLOR.color);
        }
    }
}
