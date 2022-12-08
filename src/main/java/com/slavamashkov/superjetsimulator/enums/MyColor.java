package com.slavamashkov.superjetsimulator.enums;

import javafx.scene.paint.Color;

public enum MyColor {
    ACTIVE_COLOR(Color.LIME), // LIME
    WARNING_COLOR(Color.ORANGE),
    INACTIVE_COLOR(Color.WHITE),
    ERROR_COLOR(Color.RED),
    ACTIVE_LIGHT_COLOR(Color.LIME),
    INACTIVE_LIGHT_COLOR(Color.GREY);

    public final Color color;

    MyColor(Color color) {
        this.color = color;
    }
}
