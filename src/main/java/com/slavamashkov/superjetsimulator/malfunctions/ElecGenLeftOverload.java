package com.slavamashkov.superjetsimulator.malfunctions;

import com.slavamashkov.superjetsimulator.controllers.SelectionPanelController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsConnectionsController;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@RequiredArgsConstructor
public class ElecGenLeftOverload extends Malfunction {
    private final ElecUnitsConnectionsController elecUnitsConnectionsController;
    private final SelectionPanelController selectionPanelController;

    @Override
    public void executeMalfunction() {
        Observable<MouseEvent> observableGalley = JavaFxObservable.eventsOf(
                selectionPanelController.getGalleyButton(), MouseEvent.MOUSE_CLICKED
        );

        Observable<MouseEvent> observableMain = JavaFxObservable.eventsOf(
                selectionPanelController.getFuelLeftMAINButton(), MouseEvent.MOUSE_CLICKED
        );

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Label loadValueLabel = elecUnitsConnectionsController.getLeftDrivePercentValue();

        observableGalley.subscribe(a -> dropValue(loadValueLabel, 20));

        observableMain.subscribe(a -> dropValue(loadValueLabel, 40));

        Runnable task = () -> {
            while (true) {
                final int maxLoad = 130;
                AtomicInteger newLoad = new AtomicInteger(100);
                if (elecUnitsConnectionsController.isLeftEngineConnected()) {
                    Platform.runLater(() -> {
                        if (selectionPanelController.isGalleyButtonSwitched()) {
                            newLoad.set(maxLoad - 10);

                            if (selectionPanelController.isFuelLeftADDTButtonSwitched() &&
                                    selectionPanelController.isFuelLeftMAINButtonSwitched()) {
                                newLoad.set(maxLoad - 30);
                            } else {
                                newLoad.set(maxLoad - 10);
                            }
                        } else {
                            if (selectionPanelController.isFuelLeftADDTButtonSwitched() &&
                                    selectionPanelController.isFuelLeftMAINButtonSwitched()) {

                                newLoad.set(maxLoad - 20);
                            } else {
                                newLoad.set(maxLoad);
                            }
                            newLoad.set(maxLoad);
                        }

                        if (Integer.parseInt(loadValueLabel.getText()) < newLoad.get()) {
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
        return "ELEC GEN L OVERLOAD";
    }

    private void dropValue(Label label, int value) {
        int prevValue = Integer.parseInt(label.getText());

        if (prevValue < 90) {
            return;
        }

        int nextValue = prevValue - value;
        label.setText(String.valueOf(nextValue));
    }
}
