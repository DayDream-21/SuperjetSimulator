package com.slavamashkov.superjetsimulator.malfunctions;

import com.slavamashkov.superjetsimulator.controllers.ElecScreenController;
import com.slavamashkov.superjetsimulator.controllers.SelectionPanelController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ACTIVE_COLOR;
import static com.slavamashkov.superjetsimulator.enums.MyColor.WARNING_COLOR;

@Component
@RequiredArgsConstructor
public class ElecRightGenFault extends Malfunction {
    private final ElecUnitsController elecUnitsController;
    private final ElecScreenController elecScreenController;
    private final SelectionPanelController selectionPanelController;

    @Override
    public void executeMalfunction() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Runnable task = () -> {
            while (true) {
                if (selectionPanelController.isRightGenButtonPressed()) {
                    selectionPanelController.setRightGenButtonPressed(false);
                    elecScreenController.onActionActivateRightEngine();

                    elecUnitsController.getRightDriveTransformerSign().setOpacity(1.0);
                    elecUnitsController.getRightDriveConnection().setOpacity(1.0);
                    elecUnitsController.getRightDriveRectangle().setStroke(ACTIVE_COLOR.color);
                    elecUnitsController.getRightDriveTransformerCircle().setStroke(WARNING_COLOR.color);
                    elecUnitsController.getRightDriveTransformerSign().setStroke(WARNING_COLOR.color);
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        executor.submit(task);
        executor.shutdown();
    }

    @Override
    public void restoreSystem() {

    }

    @Override
    public String toString() {
        return "ELEC R GEN FAULT";
    }
}
