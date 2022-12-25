package com.slavamashkov.superjetsimulator.malfunctions;

import com.slavamashkov.superjetsimulator.controllers.upper_layer.BatsConnectionsController;
import com.slavamashkov.superjetsimulator.controllers.upper_layer.BatsController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.slavamashkov.superjetsimulator.enums.MyColor.WARNING_COLOR;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class BatteryMalfunction extends Malfunction {
    private final BatsController batsController;
    private final BatsConnectionsController batsConnectionsController;

    @Override
    public void executeMalfunction() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> {
            batsController.getBat1Frame().setStroke(WARNING_COLOR.color);
            batsConnectionsController.getBat1Arrow().setFill(WARNING_COLOR.color);
            batsConnectionsController.getBat1ArrowUp().setFill(WARNING_COLOR.color);
        };
        executor.schedule(task, 5, TimeUnit.SECONDS);
        executor.shutdown();
    }

    @Override
    public void restoreSystem() {

    }

    @Override
    public String toString() {
        return "Battery Malfunction";
    }
}
