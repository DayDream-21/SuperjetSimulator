package com.slavamashkov.superjetsimulator.malfunctions;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Malfunction3 extends Malfunction {
    @Override
    public String toString() {
        return "Malfunction 3";
    }

    @Override
    public void executeMalfunction() {

    }

    @Override
    public void restoreSystem() {

    }
}
