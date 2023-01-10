package com.slavamashkov.superjetsimulator.malfunctions;

import com.slavamashkov.superjetsimulator.controllers.SelectionPanelController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.slavamashkov.superjetsimulator.enums.MyColor.*;

@Component
@RequiredArgsConstructor
public class ElecLeftGenDriveFault extends Malfunction {
    private final ElecUnitsController elecUnitsController;
    private final SelectionPanelController selectionPanelController;

    @Override
    public void executeMalfunction() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        AtomicBoolean flag = new AtomicBoolean(false);

        Runnable task = () -> {
            while (true) {
                if (selectionPanelController.isLeftGenButtonPressed()) {
                    flag.set(true);

                    selectionPanelController.getLeftGenUpperLight().setFill(WARNING_LIGHT_COLOR.color);

                    elecUnitsController.getLeftDriveConnection().setOpacity(1.0);
                    elecUnitsController.getLeftDriveConnection().setStroke(WARNING_COLOR.color);
                    elecUnitsController.getLeftDriveRectangle().setStroke(WARNING_COLOR.color);

                    waitStep(1000L);
                } else {
                    if (flag.get()) {
                        selectionPanelController.getLeftGenUpperLight().setFill(WARNING_LIGHT_COLOR.color);

                        elecUnitsController.getLeftDriveTransformerCircle().setStroke(ACTIVE_COLOR.color);
                        elecUnitsController.getLeftDriveTransformerSign().setOpacity(1.0);
                        elecUnitsController.getLeftDriveConnection().setOpacity(1.0);
                        elecUnitsController.getLeftDriveConnection().setStroke(WARNING_COLOR.color);
                        elecUnitsController.getLeftDriveRectangle().setStroke(WARNING_COLOR.color);
                    }

                    waitStep(1000L);
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
        return "ELEC L GEN DRIVE FAULT";
    }
}
