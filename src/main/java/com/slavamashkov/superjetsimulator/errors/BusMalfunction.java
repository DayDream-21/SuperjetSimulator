package com.slavamashkov.superjetsimulator.errors;

import com.slavamashkov.superjetsimulator.controllers.MiddleInfoPaneController;
import javafx.scene.paint.Color;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BusMalfunction extends Malfunction {
    private final MiddleInfoPaneController middleInfoPaneController;

    @Override
    public String toString() {
        return "Bus Malfunction";
    }

    @Override
    public void executeMalfunction() {
        middleInfoPaneController.getLeftMainBusDC().setStroke(Color.RED);
        middleInfoPaneController.getRightEmrgBusDC().setStroke(Color.ORANGE);
    }

    @Override
    public void restoreSystem() {

    }
}
