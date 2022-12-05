package com.slavamashkov.superjetsimulator.errors;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BatteryMalfunction extends Malfunction {
    @Override
    public String toString() {
        return "Battery Malfunction";
    }

    @Override
    public void executeMalfunction() {

    }

    @Override
    public void restoreSystem() {

    }
}
