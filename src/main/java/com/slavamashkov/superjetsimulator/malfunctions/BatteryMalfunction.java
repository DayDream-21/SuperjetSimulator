package com.slavamashkov.superjetsimulator.malfunctions;

import com.slavamashkov.superjetsimulator.controllers.BatsController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ERROR_COLOR;

/**
 *
 */
@Component
@RequiredArgsConstructor
public class BatteryMalfunction extends Malfunction {
    private final BatsController batsController;

    @Override
    public void executeMalfunction() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = () -> batsController.getBat1Frame().setStroke(ERROR_COLOR.color);
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
