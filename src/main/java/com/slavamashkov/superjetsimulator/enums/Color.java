package com.slavamashkov.superjetsimulator.enums;

public enum Color {
    ACTIVE_COLOR("#00ff25"), // LIME
    WARNING_COLOR("#ff8300"),
    INACTIVE_COLOR("#ffffff");

    public final String hexCode;

    Color(String hexCode) {
        this.hexCode = hexCode;
    }
}
