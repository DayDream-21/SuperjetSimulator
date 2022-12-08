package com.slavamashkov.superjetsimulator.malfunctions;

import com.slavamashkov.superjetsimulator.controllers.MiddleInfoPaneController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ERROR_COLOR;
import static com.slavamashkov.superjetsimulator.enums.MyColor.WARNING_COLOR;

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
        middleInfoPaneController.getLeftMainBusDC().setStroke(ERROR_COLOR.color);
        middleInfoPaneController.getRightEmrgBusDC().setStroke(WARNING_COLOR.color);
    }

    @Override
    public void restoreSystem() {

    }
}
