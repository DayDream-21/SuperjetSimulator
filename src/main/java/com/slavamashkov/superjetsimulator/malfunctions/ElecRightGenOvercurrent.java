package com.slavamashkov.superjetsimulator.malfunctions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElecRightGenOvercurrent extends Malfunction {
    @Override
    public void executeMalfunction() {

    }

    @Override
    public void restoreSystem() {

    }

    @Override
    public String toString() {
        return "ELEC R GEN OVERCURRENT";
    }
}
