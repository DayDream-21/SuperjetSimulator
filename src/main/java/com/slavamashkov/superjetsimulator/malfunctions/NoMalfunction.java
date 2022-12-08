package com.slavamashkov.superjetsimulator.malfunctions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoMalfunction extends Malfunction {
    @Override
    public String toString() {
        return "No Malfunction";
    }

    @Override
    public void executeMalfunction() {}

    @Override
    public void restoreSystem() {}
}
