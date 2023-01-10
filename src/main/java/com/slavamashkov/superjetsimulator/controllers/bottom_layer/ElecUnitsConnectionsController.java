package com.slavamashkov.superjetsimulator.controllers.bottom_layer;

import com.slavamashkov.superjetsimulator.controllers.FxController;
import com.slavamashkov.superjetsimulator.controllers.SelectionPanelController;
import io.reactivex.Observable;
import io.reactivex.rxjavafx.observables.JavaFxObservable;
import io.reactivex.rxjavafx.sources.Change;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import static com.slavamashkov.superjetsimulator.enums.MyColor.ACTIVE_COLOR;
import static com.slavamashkov.superjetsimulator.enums.MyColor.WARNING_COLOR;

/**
 * The class responsible for displaying the connections of units that
 * supply power to the system, such as the left and right drives, the
 * external power supply and the APU. For the drives the voltages,
 * frequencies and load percentage is displayed. {@link ElecUnitsController}
 * is responsible for displaying APU and external power information.
 */
@Getter
@Component
@RequiredArgsConstructor
public class ElecUnitsConnectionsController extends FxController {
    private final String source = "fxml/bottom_layer/bottom-info-elec-units-connections-pane.fxml";

    @FXML private Pane bottomInfoElecUnitsConnectionsMainPane;

    private SelectionPanelController selectionPanelController;

    @Autowired
    public void setSelectionPanelController(@Lazy SelectionPanelController selectionPanelController) {
        this.selectionPanelController = selectionPanelController;
    }

    @Override
    public void init() {
        super.init();

        subscribeOnValueLabel(leftDrivePercentValue, 0, 100);
        subscribeOnValueLabel(rightDrivePercentValue, 0, 100);
    }

    @FXML private Pane fromExtToLeft;
    @FXML private Pane fromExtToRight;
    @FXML private Pane fromApuToLeft;
    @FXML private Pane fromApuToRight;
    @FXML private Pane fromLeftDriveToLeft;
    @FXML private Pane fromLeftDriveToRight;
    @FXML private Pane leftDriveInfoPane;
    @FXML private Pane fromRightDriveToRight;
    @FXML private Pane fromRightDriveToLeft;
    @FXML private Pane rightDriveInfoPane;

    @FXML private Label leftDrivePercentValue;

    @FXML private Label rightDrivePercentValue;

    public void connectLeftEngine() {
        if (isRightEngineConnected()) {
            deactivateRightDriveToLeftConnection();
            activateLeftDriveToLeftConnection();

            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                return;
            }
        } else {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateLeftDriveToLeftRightConnection();
                return;
            }
            activateLeftDriveToLeftConnection();
        }

        dealWithApuAndExtPwr();
    }

    public void disconnectLeftEngine() {
        deactivateLeftDriveToLeftRightConnection();

        if (isRightEngineConnected()) {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateRightDriveToLeftRightConnection();
                return;
            }

            activateRightDriveToRightConnection();
        }

        dealWithApuAndExtPwr();
    }

    public void connectRightEngine() {
        if (isLeftEngineConnected()) {
            deactivateLeftDriveToRightConnection();
            activateRightDriveToRightConnection();

            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                return;
            }
        } else {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateRightDriveToLeftRightConnection();
                return;
            }

            activateRightDriveToRightConnection();
        }

        dealWithApuAndExtPwr();
    }

    public void disconnectRightEngine() {
        deactivateRightDriveToLeftRightConnection();

        if (isLeftEngineConnected()) {
            if (!selectionPanelController.isApuGenSwitchedPressed() && !selectionPanelController.isExtPwrSwitchedPressed()) {
                activateLeftDriveToLeftRightConnection();
                return;
            }

            activateLeftDriveToLeftConnection();
        }

        dealWithApuAndExtPwr();
    }

    public void connectApuGenUnit() {
        if (isLeftEngineConnected() && isRightEngineConnected()) {
            deactivateApuGenConnection();
        } else if (isLeftEngineConnected() && !isRightEngineConnected()) {
            activateApuGenConnectionToRight();
            deactivateApuGenConnectionToLeft();

            deactivateLeftDriveToRightConnection();
        } else if (isRightEngineConnected() && !isLeftEngineConnected()) {
            activateApuGenConnectionToLeft();
            deactivateApuGenConnectionToRight();

            deactivateRightDriveToLeftConnection();
        } else if (!isLeftEngineConnected() && !isRightEngineConnected()) {
            activateApuGenConnection();
        }

        if (selectionPanelController.isExtPwrSwitchedPressed()) {
            deactivateExtPwrConnection();
        }
    }

    public void disconnectApuGenUnit() {
        deactivateApuGenConnection();

        if (isLeftEngineConnected() && !isRightEngineConnected()) {
            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateExtPwrConnectionToRight();
            } else {
                activateLeftDriveToRightConnection();
            }
        } else if (!isLeftEngineConnected() && isRightEngineConnected()) {
            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateExtPwrConnectionToLeft();
            } else {
                activateRightDriveToLeftConnection();
            }
        } else if (!isLeftEngineConnected() && !isRightEngineConnected()) {
            if (selectionPanelController.isExtPwrSwitchedPressed()) {
                activateExtPwrConnection();
            }
        }
    }

    public void connectExtPwrUnit() {
        if (isLeftEngineConnected() && isRightEngineConnected()) {
            deactivateExtPwrConnection();
        } else if (isLeftEngineConnected() && !isRightEngineConnected()) {
            activateExtPwrConnectionToRight();
            deactivateExtPwrConnectionToLeft();

            deactivateLeftDriveToRightConnection();
        } else if (isRightEngineConnected() && !isLeftEngineConnected()) {
            activateExtPwrConnectionToLeft();
            deactivateExtPwrConnectionToRight();

            deactivateRightDriveToLeftConnection();
        } else if (!isLeftEngineConnected() && !isRightEngineConnected()) {
            activateExtPwrConnection();
        }

        if (selectionPanelController.isApuGenSwitchedPressed()) {
            deactivateExtPwrConnection();
        }
    }

    public void disconnectExtPwrUnit() {
        deactivateExtPwrConnection();

        if (isLeftEngineConnected() && !isRightEngineConnected()) {
            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateApuGenConnectionToRight();
            } else {
                activateLeftDriveToRightConnection();
            }
        } else if (!isLeftEngineConnected() && isRightEngineConnected()) {
            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateApuGenConnectionToLeft();
            } else {
                activateRightDriveToLeftConnection();
            }
        } else if (!isLeftEngineConnected() && !isRightEngineConnected()) {
            if (selectionPanelController.isApuGenSwitchedPressed()) {
                activateApuGenConnection();
            }
        }
    }

    public boolean isLeftEngineConnected() {
        return getFromLeftDriveToLeft().getOpacity() > 0.0 || getFromLeftDriveToRight().getOpacity() > 0.0;
    }

    public boolean isRightEngineConnected() {
        return getFromRightDriveToRight().getOpacity() > 0.0 || getFromRightDriveToLeft().getOpacity() > 0.0;
    }

    // Private methods
    // TODO: возможно стоит перенести этот метод в абстрактный класс Malfunction
    //  (с реализацией) и вызывать его в реализациях
    /**
     * The method is used to monitor the value of the Label,
     * when the value is within the range with the borders of
     * leftBorder and rightBorder, the color of the displayed
     * value will be green, when leaving the specified range,
     * the value will be colored in orange
     *
     * @param label values of which we monitor
     * @param leftBorder left limit of the normal range of values
     * @param rightBorder right limit of the normal range of values
     */
    private void subscribeOnValueLabel(Label label, int leftBorder, int rightBorder) {
        Observable<Change<String>> observable = JavaFxObservable.changesOf(new ObservableValue<>() {
            @Override
            public void addListener(ChangeListener<? super String> changeListener) {
                label.textProperty().addListener(changeListener);
            }

            @Override
            public void removeListener(ChangeListener<? super String> changeListener) {}

            @Override
            public String getValue() { return null; }

            @Override
            public void addListener(InvalidationListener invalidationListener) {}

            @Override
            public void removeListener(InvalidationListener invalidationListener) {}
        });

        observable.subscribe(i -> {
            if (Integer.parseInt(i.getNewVal()) < leftBorder || Integer.parseInt(i.getNewVal()) > rightBorder) {
                label.setTextFill(WARNING_COLOR.color);
            } else {
                label.setTextFill(ACTIVE_COLOR.color);
            }
        });
    }

    private void dealWithApuAndExtPwr() {
        if (selectionPanelController.isApuGenSwitchedPressed()) {
            connectApuGenUnit();
            return;
        }
        if (selectionPanelController.isExtPwrSwitchedPressed()) {
            connectExtPwrUnit();
        }
    }

    private void activateExtPwrConnection() {
        activateExtPwrConnectionToLeft();
        activateExtPwrConnectionToRight();
    }

    private void deactivateApuGenConnection() {
        deactivateApuGenConnectionToLeft();
        deactivateApuGenConnectionToRight();
    }

    private void deactivateExtPwrConnection() {
        deactivateExtPwrConnectionToLeft();
        deactivateExtPwrConnectionToRight();
    }

    private void activateApuGenConnection() {
        activateApuGenConnectionToLeft();
        activateApuGenConnectionToRight();
    }

    private void activateExtPwrConnectionToLeft() {
        fromExtToLeft.setOpacity(1.0);
    }

    private void deactivateExtPwrConnectionToLeft() {
        fromExtToLeft.setOpacity(0.0);
    }

    private void activateExtPwrConnectionToRight() {
        fromExtToRight.setOpacity(1.0);
    }

    private void deactivateExtPwrConnectionToRight() {
        fromExtToRight.setOpacity(0.0);
    }

    private void activateApuGenConnectionToLeft() {
        fromApuToLeft.setOpacity(1.0);
    }

    private void deactivateApuGenConnectionToLeft() {
        fromApuToLeft.setOpacity(0.0);
    }

    private void activateApuGenConnectionToRight() {
        fromApuToRight.setOpacity(1.0);
    }

    private void deactivateApuGenConnectionToRight() {
        fromApuToRight.setOpacity(0.0);
    }

    private void activateLeftDriveToLeftRightConnection() {
        activateLeftDriveToLeftConnection();
        activateLeftDriveToRightConnection();
    }

    private void activateRightDriveToLeftRightConnection() {
        activateRightDriveToRightConnection();
        activateRightDriveToLeftConnection();
    }

    private void deactivateLeftDriveToLeftRightConnection() {
        deactivateLeftDriveToLeftConnection();
        deactivateLeftDriveToRightConnection();
    }

    private void deactivateRightDriveToLeftRightConnection() {
        deactivateRightDriveToRightConnection();
        deactivateRightDriveToLeftConnection();
    }

    private void activateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(1.0);
        fromLeftDriveToLeft.setOpacity(1.0);
    }

    private void deactivateLeftDriveToLeftConnection() {
        leftDriveInfoPane.setOpacity(0.0);
        fromLeftDriveToLeft.setOpacity(0.0);
    }

    private void activateLeftDriveToRightConnection() {
        fromLeftDriveToRight.setOpacity(1.0);
    }

    private void deactivateLeftDriveToRightConnection() {
        fromLeftDriveToRight.setOpacity(0.0);
    }

    private void activateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(1.0);
        fromRightDriveToRight.setOpacity(1.0);
    }

    private void deactivateRightDriveToRightConnection() {
        rightDriveInfoPane.setOpacity(0.0);
        fromRightDriveToRight.setOpacity(0.0);
    }

    private void activateRightDriveToLeftConnection() {
        fromRightDriveToLeft.setOpacity(1.0);
    }

    private void deactivateRightDriveToLeftConnection() {
        fromRightDriveToLeft.setOpacity(0.0);
    }
}