package com.slavamashkov.superjetsimulator.errors;

import javafx.scene.paint.Color;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Error1 extends Error {
    public Map<String, List<ErrorParameter>> parameters = new HashMap<>();

    public Error1() {
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
        return "Error 1";
    }
}
