package com.slavamashkov.superjetsimulator.errors;

import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusMalfunction extends Malfunction {
    public Map<String, List<ErrorParameter>> parameters = new HashMap<>();

    public BusMalfunction() {
        List<ErrorParameter> leftMainBusDCList = List.of(
                new ErrorParameter("stroke", Color.RED)
        );

        List<ErrorParameter> rightEmrgBusDC = List.of(
                new ErrorParameter("stroke", Color.ORANGE)
        );

        parameters.put("leftMainBusDC", leftMainBusDCList);
        parameters.put("rightEmrgBusDC", rightEmrgBusDC);
    }

    @Override
    public String toString() {
        return "Bus Malfunction";
    }
}
