package com.slavamashkov.superjetsimulator.errors;

import javafx.scene.paint.Color;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorParameter {
    private String fxName;
    private Color fxValue;
}
