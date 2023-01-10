package com.slavamashkov.superjetsimulator.malfunctions;

import com.slavamashkov.superjetsimulator.controllers.SelectionPanelController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsConnectionsController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@RequiredArgsConstructor
public class ElecGenRightOverload extends Malfunction {
    private final ElecUnitsConnectionsController elecUnitsConnectionsController;
    private final SelectionPanelController selectionPanelController;

    @Override
    public void executeMalfunction() {
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Label loadValueLabel = elecUnitsConnectionsController.getRightDrivePercentValue();

        Runnable task = () -> {
            while (true) {
                if (elecUnitsConnectionsController.isRightEngineConnected()) {
                    Platform.runLater(() -> {
                        int maxLoad = 110;

                        if (selectionPanelController.isGalleyButtonSwitched()) {
                            maxLoad = 100;
                        }

                        if (Integer.parseInt(loadValueLabel.getText()) < maxLoad) {
                            int prevValue = Integer.parseInt(loadValueLabel.getText());
                            int nextValue = prevValue + 1;
                            loadValueLabel.setText(String.valueOf(nextValue));
                        }
                    });

                    waitStep(200L);
                } else {
                    waitStep(3000L);
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
        return "ELEC GEN R OVERLOAD";
    }
}
