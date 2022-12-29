package com.slavamashkov.superjetsimulator.malfunctions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElecBatOverheat extends Malfunction {
    private int batNumber;

    public ElecBatOverheat(int batNumber) {
        this.batNumber = batNumber;
    }

    @Override
    public void executeMalfunction() {

    }

    @Override
    public void restoreSystem() {

    }

    @Override
    public String toString() {
        return "ELEC BAT " + batNumber + " OVHT";
    }
}
