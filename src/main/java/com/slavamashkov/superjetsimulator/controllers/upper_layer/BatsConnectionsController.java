package com.slavamashkov.superjetsimulator.controllers.upper_layer;

import com.slavamashkov.superjetsimulator.controllers.FxController;
import com.slavamashkov.superjetsimulator.controllers.bottom_layer.ElecUnitsController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ACTIVE_COLOR;
import static com.slavamashkov.superjetsimulator.enums.MyColor.WARNING_COLOR;

/**
 * The class responsible for connecting and disconnecting the batteries
 * from the power grid, as well as displaying and controlling the voltage
 * and current value
 */
@Getter
@Component
@RequiredArgsConstructor
public class BatsConnectionsController extends FxController {
    private final String source = "fxml/upper-info-bats-connections-pane.fxml";
    @FXML private Pane upperInfoBatsConnectionsMainPane;

    private ElecUnitsController elecUnitsController;

    @Autowired
    public void setElecUnitsController(@Lazy ElecUnitsController elecUnitsController) {
        this.elecUnitsController = elecUnitsController;
    }

    // Bat 1
    @FXML private Label bat1VoltageSign;
    @FXML private Label bat1VoltageValue;
    @FXML private Polygon bat1ArrowUp;
    @FXML private Rectangle bat1Arrow;
    @FXML private Label bat1CurrentSign;
    @FXML private Label bat1CurrentValue;
    @FXML private Polygon bat1ArrowDown;

    // Bat 2
    @FXML private Label bat2VoltageSign;
    @FXML private Label bat2VoltageValue;
    @FXML private Rectangle bat2Arrow;
    @FXML private Polygon bat2ArrowUp;
    @FXML private Label bat2CurrentSign;
    @FXML private Label bat2CurrentValue;
    @FXML private Polygon bat2ArrowDown;

    // Bat 3
    @FXML private Label bat3VoltageSign;
    @FXML private Label bat3VoltageValue;
    @FXML private Polygon bat3ArrowUp;
    @FXML private Rectangle bat3Arrow;
    @FXML private Label bat3CurrentSign;
    @FXML private Label bat3CurrentValue;
    @FXML private Polygon bat3ArrowDown;

    // Bat 4
    @FXML private Label bat4VoltageSign;
    @FXML private Label bat4VoltageValue;
    @FXML private Polygon bat4ArrowUp;
    @FXML private Rectangle bat4Arrow;
    @FXML private Label bat4CurrentSign;
    @FXML private Label bat4CurrentValue;
    @FXML private Polygon bat4ArrowDown;

    /**
     * Allows you to connect a specific battery to the power grid
     *
     * @param i The battery number must be an integer between 1 and 4 inclusive
     */
    public void batConnect(int i) {
        switch (i) {
            case 1 -> {
                bat1VoltageSign.setOpacity(1.0);
                bat1VoltageValue.setTextFill(ACTIVE_COLOR.color);
                bat1VoltageValue.setOpacity(1.0);

                bat1ArrowUp.setFill(ACTIVE_COLOR.color);
                bat1ArrowUp.setOpacity(1.0);
                bat1ArrowDown.setOpacity(0.0);

                bat1Arrow.setFill(ACTIVE_COLOR.color);
                bat1Arrow.setOpacity(1.0);

                bat1CurrentSign.setOpacity(1.0);
                bat1CurrentValue.setTextFill(ACTIVE_COLOR.color);
                bat1CurrentValue.setOpacity(1.0);
            }
            case 2 -> {
                bat2VoltageSign.setOpacity(1.0);
                bat2VoltageValue.setTextFill(ACTIVE_COLOR.color);
                bat2VoltageValue.setOpacity(1.0);

                bat2ArrowUp.setFill(ACTIVE_COLOR.color);
                bat2ArrowUp.setOpacity(1.0);
                bat2ArrowDown.setOpacity(0.0);

                bat2Arrow.setFill(ACTIVE_COLOR.color);
                bat2Arrow.setOpacity(1.0);

                bat2CurrentSign.setOpacity(1.0);
                bat2CurrentValue.setTextFill(ACTIVE_COLOR.color);
                bat2CurrentValue.setOpacity(1.0);
            }
            case 3 -> {
                bat3VoltageSign.setOpacity(1.0);
                bat3VoltageValue.setTextFill(ACTIVE_COLOR.color);
                bat3VoltageValue.setOpacity(1.0);

                bat3ArrowUp.setFill(ACTIVE_COLOR.color);
                bat3ArrowUp.setOpacity(1.0);
                bat3ArrowDown.setOpacity(0.0);

                bat3Arrow.setFill(ACTIVE_COLOR.color);
                bat3Arrow.setOpacity(1.0);

                bat3CurrentSign.setOpacity(1.0);
                bat3CurrentValue.setTextFill(ACTIVE_COLOR.color);
                bat3CurrentValue.setOpacity(1.0);
            }
            case 4 -> {
                bat4VoltageSign.setOpacity(1.0);
                bat4VoltageValue.setTextFill(ACTIVE_COLOR.color);
                bat4VoltageValue.setOpacity(1.0);

                bat4ArrowUp.setFill(ACTIVE_COLOR.color);
                bat4ArrowUp.setOpacity(1.0);
                bat4ArrowDown.setOpacity(0.0);

                bat4Arrow.setFill(ACTIVE_COLOR.color);
                bat4Arrow.setOpacity(1.0);

                bat4CurrentSign.setOpacity(1.0);
                bat4CurrentValue.setTextFill(ACTIVE_COLOR.color);
                bat4CurrentValue.setOpacity(1.0);
            }
        }
    }

    /**
     * Allows you to disconnect a certain battery from the power grid
     *
     * @param i the battery number must be an integer between 1 and 4 inclusive
     */
    public void batDisconnect(int i) {
        switch (i) {
            case 1 -> {
                bat1VoltageSign.setOpacity(0.0);
                bat1VoltageValue.setTextFill(ACTIVE_COLOR.color);
                bat1VoltageValue.setOpacity(0.0);

                bat1ArrowUp.setFill(ACTIVE_COLOR.color);
                bat1ArrowUp.setOpacity(0.0);
                bat1ArrowDown.setOpacity(0.0);

                bat1Arrow.setFill(ACTIVE_COLOR.color);
                bat1Arrow.setOpacity(0.0);

                bat1CurrentSign.setOpacity(0.0);
                bat1CurrentValue.setTextFill(ACTIVE_COLOR.color);
                bat1CurrentValue.setOpacity(0.0);
            }
            case 2 -> {
                bat2VoltageSign.setOpacity(0.0);
                bat2VoltageValue.setTextFill(ACTIVE_COLOR.color);
                bat2VoltageValue.setOpacity(0.0);

                bat2ArrowUp.setFill(ACTIVE_COLOR.color);
                bat2ArrowUp.setOpacity(0.0);
                bat2ArrowDown.setOpacity(0.0);

                bat2Arrow.setFill(ACTIVE_COLOR.color);
                bat2Arrow.setOpacity(0.0);

                bat2CurrentSign.setOpacity(0.0);
                bat2CurrentValue.setTextFill(ACTIVE_COLOR.color);
                bat2CurrentValue.setOpacity(0.0);
            }
            case 3 -> {
                bat3VoltageSign.setOpacity(0.0);
                bat3VoltageValue.setTextFill(ACTIVE_COLOR.color);
                bat3VoltageValue.setOpacity(0.0);

                bat3ArrowUp.setFill(ACTIVE_COLOR.color);
                bat3ArrowUp.setOpacity(0.0);
                bat3ArrowDown.setOpacity(0.0);

                bat3Arrow.setFill(ACTIVE_COLOR.color);
                bat3Arrow.setOpacity(0.0);

                bat3CurrentSign.setOpacity(0.0);
                bat3CurrentValue.setTextFill(ACTIVE_COLOR.color);
                bat3CurrentValue.setOpacity(0.0);
            }
            case 4 -> {
                bat4VoltageSign.setOpacity(0.0);
                bat4VoltageValue.setTextFill(ACTIVE_COLOR.color);
                bat4VoltageValue.setOpacity(0.0);

                bat4ArrowUp.setFill(ACTIVE_COLOR.color);
                bat4ArrowUp.setOpacity(0.0);
                bat4ArrowDown.setOpacity(0.0);

                bat4Arrow.setFill(ACTIVE_COLOR.color);
                bat4Arrow.setOpacity(0.0);

                bat4CurrentSign.setOpacity(0.0);
                bat4CurrentValue.setTextFill(ACTIVE_COLOR.color);
                bat4CurrentValue.setOpacity(0.0);
            }
        }
    }

    public void batConnectInverse(int i) {
        switch (i) {
            case 1 -> {
                bat1VoltageSign.setOpacity(1.0);
                bat1VoltageValue.setTextFill(WARNING_COLOR.color);
                bat1VoltageValue.setOpacity(1.0);

                bat1ArrowDown.setFill(WARNING_COLOR.color);
                bat1ArrowDown.setOpacity(1.0);
                bat1ArrowUp.setOpacity(0.0);

                bat1Arrow.setFill(WARNING_COLOR.color);
                bat1Arrow.setOpacity(1.0);

                bat1CurrentSign.setOpacity(1.0);
                bat1CurrentValue.setTextFill(WARNING_COLOR.color);
                bat1CurrentValue.setOpacity(1.0);
            }
            case 2 -> {
                bat2VoltageSign.setOpacity(1.0);
                bat2VoltageValue.setTextFill(WARNING_COLOR.color);
                bat2VoltageValue.setOpacity(1.0);

                bat2ArrowDown.setFill(WARNING_COLOR.color);
                bat2ArrowDown.setOpacity(1.0);
                bat2ArrowUp.setOpacity(0.0);

                bat2Arrow.setFill(WARNING_COLOR.color);
                bat2Arrow.setOpacity(1.0);

                bat2CurrentSign.setOpacity(1.0);
                bat2CurrentValue.setTextFill(WARNING_COLOR.color);
                bat2CurrentValue.setOpacity(1.0);
            }
            case 3 -> {
                bat3VoltageSign.setOpacity(1.0);
                bat3VoltageValue.setTextFill(WARNING_COLOR.color);
                bat3VoltageValue.setOpacity(1.0);

                bat3ArrowDown.setFill(WARNING_COLOR.color);
                bat3ArrowDown.setOpacity(1.0);
                bat3ArrowUp.setOpacity(0.0);

                bat3Arrow.setFill(WARNING_COLOR.color);
                bat3Arrow.setOpacity(1.0);

                bat3CurrentSign.setOpacity(1.0);
                bat3CurrentValue.setTextFill(WARNING_COLOR.color);
                bat3CurrentValue.setOpacity(1.0);
            }
            case 4 -> {
                bat4VoltageSign.setOpacity(1.0);
                bat4VoltageValue.setTextFill(WARNING_COLOR.color);
                bat4VoltageValue.setOpacity(1.0);

                bat4ArrowDown.setFill(WARNING_COLOR.color);
                bat4ArrowDown.setOpacity(1.0);
                bat4ArrowUp.setOpacity(0.0);

                bat4Arrow.setFill(WARNING_COLOR.color);
                bat4Arrow.setOpacity(1.0);

                bat4CurrentSign.setOpacity(1.0);
                bat4CurrentValue.setTextFill(WARNING_COLOR.color);
                bat4CurrentValue.setOpacity(1.0);
            }
        }
    }

    private boolean isBatConnected(int batNumber) {
        switch (batNumber) {
            case 1 -> {
                return bat1ArrowUp.getOpacity() == 1.0;
            }
            case 2 -> {
                return bat2ArrowUp.getOpacity() == 1.0;
            }
            case 3 -> {
                return bat3ArrowUp.getOpacity() == 1.0;
            }
            case 4 -> {
                return bat4ArrowUp.getOpacity() == 1.0;
            }
        }
        return false;
    }

    public boolean isBatConnectedInverse(int i) {
        switch (i) {
            case 1 -> {
                return bat1ArrowDown.getOpacity() == 1.0;
            }
            case 2 -> {
                return bat2ArrowDown.getOpacity() == 1.0;
            }
            case 3 -> {
                return bat3ArrowDown.getOpacity() == 1.0;
            }
            case 4 -> {
                return bat4ArrowDown.getOpacity() == 1.0;
            }
        }
        return false;
    }

    public void batStartDischarge(int i) {
        switch (i) {
            case 1 -> startDischarge(bat1VoltageValue);
            case 2 -> startDischarge(bat2VoltageValue);
            case 3 -> startDischarge(bat3VoltageValue);
            case 4 -> startDischarge(bat4VoltageValue);
        }
    }

    public void batStartCharge(int i) {
        switch (i) {
            case 1 -> startCharge(bat1VoltageValue);
            case 2 -> startCharge(bat2VoltageValue);
            case 3 -> startCharge(bat3VoltageValue);
            case 4 -> startCharge(bat4VoltageValue);
        }
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    private void startDischarge(Label batVoltageValue) {
        executorService.submit(() -> {
            int batNumber = Integer.parseInt(String.valueOf(batVoltageValue.getId().charAt(3)));

            while (isBatConnectedInverse(batNumber) &&
                    Integer.parseInt(batVoltageValue.getText()) > 0) {
                Platform.runLater(() -> {
                    int prevValue = Integer.parseInt(batVoltageValue.getText());
                    int nextValue = prevValue - 1;
                    batVoltageValue.setText(String.valueOf(nextValue));
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void startCharge(Label batVoltageValue) {
        executorService.submit(() -> {
            int batNumber = Integer.parseInt(String.valueOf(batVoltageValue.getId().charAt(3)));

            while (isBatConnected(batNumber) &&
                    Integer.parseInt(batVoltageValue.getText()) < 27) {
                Platform.runLater(() -> {
                    int prevValue = Integer.parseInt(batVoltageValue.getText());
                    int nextValue = prevValue + 1;
                    batVoltageValue.setText(String.valueOf(nextValue));
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    /**
     * Allows you to obtain the voltage of a certain battery, provided
     * that it is connected to the power grid
     *
     * @param i the battery number must be an integer between 1 and 4 inclusive
     * @return battery voltage as double value if it is connected, if not it returns double value 0.0
     */
    public double getBatVoltage(int i) {
        switch (i) {
            case 1 -> {
                if (bat1VoltageValue.getOpacity() != 0.0) {
                    return Double.parseDouble(bat1VoltageValue.getText());
                } else {
                    return 0.0;
                }
            }
            case 2 -> {
                if (bat2VoltageValue.getOpacity() != 0.0) {
                    return Double.parseDouble(bat2VoltageValue.getText());
                } else {
                    return 0.0;
                }
            }
            case 3 -> {
                if (bat3VoltageValue.getOpacity() != 0.0) {
                    return Double.parseDouble(bat3VoltageValue.getText());
                } else {
                    return 0.0;
                }
            }
            case 4 -> {
                if (bat4VoltageValue.getOpacity() != 0.0) {
                    return Double.parseDouble(bat4VoltageValue.getText());
                } else {
                    return 0.0;
                }
            }
        }

        return 0.0;
    }
}
