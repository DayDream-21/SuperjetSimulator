package com.slavamashkov.superjetsimulator.malfunctions;

import com.slavamashkov.superjetsimulator.controllers.SelectionPanelController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.BatsConnectionsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.BatsController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.slavamashkov.superjetsimulator.enums.MyColor.*;
import static com.slavamashkov.superjetsimulator.enums.MyColor.WARNING_COLOR;

@Component
@RequiredArgsConstructor
public class ElecBat3Overheat extends Malfunction {
    private final BatsController batsController;
    private final SelectionPanelController selectionPanelController;
    private final ElecUnitsController elecUnitsController;
    private final BatsConnectionsController batsConnectionsController;

    private static final int BAT_NUMBER = 3;

    @Override
    public void executeMalfunction() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Runnable task = () -> {
            while (true) {
                if ((selectionPanelController.isBatButtonPressed(BAT_NUMBER) && elecUnitsController.isPowered()) ||
                        selectionPanelController.isBatButtonPressed(BAT_NUMBER)) {

                    batsConnectionsController.getBatArrowUp(BAT_NUMBER).setFill(WARNING_COLOR.color);
                    batsConnectionsController.getBatArrow(BAT_NUMBER).setFill(WARNING_COLOR.color);
                    batsConnectionsController.getBatArrowDown(BAT_NUMBER).setFill(WARNING_COLOR.color);
                    batsController.getBatFrame(BAT_NUMBER).setStroke(ERROR_COLOR.color);
                } else {
                    try {
                        batsController.getBatFrame(BAT_NUMBER).setStroke(INACTIVE_COLOR.color);
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
        return "ELEC BAT 3 OVHT";
    }
}
