package com.slavamashkov.superjetsimulator.malfunctions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElecGenRightOverload extends Malfunction {
    @Override
    public void executeMalfunction() {

    }

    @Override
    public void restoreSystem() {

    }

    @Override
    public String toString() {
        return "ELEC GEN R OVERLOAD";
    }
}
