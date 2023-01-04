package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.SuperjetSimulatorApplication;
import com.slavamashkov.superjetsimulator.malfunctions.*;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <b>
 * This class is called immediately after calling the main
 * method in the {@link SuperjetSimulatorApplication} class
 * </b>
 * <p>
 * This class is responsible for receiving and processing
 * the user's actions on the initial application screen. For
 * example, selecting the error that will be embedded in the
 * system at startup.
 * <p>
 * <i>
 * Because this class implements the CommandLineRunner
 * interface, the Spring framework understands that this is the
 * class where user interaction with the application should start.
 * </i>
 */
@Getter
@Component
@RequiredArgsConstructor
public class LandingPageController extends FxController implements CommandLineRunner {
    private final String source = "fxml/landing-page.fxml";

    private final MasterPaneController masterPaneController;

    @FXML private ImageView logo;
    @FXML private ComboBox<Malfunction> comboBox;

    private final NoMalfunction noMalfunction;
    private final ElecLeftGenDriveFault elecLeftGenDriveFault;
    private final ElecRightGenDriveFault elecRightGenDriveFault;
    private final ElecLeftGenFault elecLeftGenFault;
    private final ElecRightGenFault elecRightGenFault;
    private final ElecApuGenFault elecApuGenFault;
    private final ElecBat1Overheat elecBat1Overheat;
    private final ElecBat2Overheat elecBat2Overheat;
    private final ElecBat3Overheat elecBat3Overheat;
    private final ElecBat4Overheat elecBat4Overheat;
    private final ElecDcBusOnBat elecDcBusOnBat;
    private final ElecLeftGenOvercurrent elecLeftGenOvercurrent;
    private final ElecRightGenOvercurrent elecRightGenOvercurrent;
    private final ElecGenLeftOverload elecGenLeftOverload;
    private final ElecGenRightOverload elecGenRightOverload;
    private final ElecLeftAcBusFault elecLeftAcBusFault;
    private final ElecRightAcBusFault elecRightAcBusFault;
    private final ElecLeftDcBusFault elecLeftDcBusFault;
    private final ElecRightDcBusFault elecRightDcBusFault;
    private final ElecLeftDcEssBusFault elecLeftDcEssBusFault;
    private final ElecRightDcEssBusFault elecRightDcEssBusFault;

    @Override
    public void init() {
        super.init();
        getStage().initModality(Modality.APPLICATION_MODAL);
        getScene().getStylesheets().add("css/landing-page.css");
        logo.setImage(new Image("images/sukhoi_logo.png"));

        comboBox.getItems().addAll(
                noMalfunction,
                elecLeftGenDriveFault,
                elecRightGenDriveFault,
                elecLeftGenFault,
                elecRightGenFault,
                elecApuGenFault,
                elecBat1Overheat,
                elecBat2Overheat,
                elecBat3Overheat,
                elecBat4Overheat,
                elecDcBusOnBat,
                elecLeftGenOvercurrent,
                elecRightGenOvercurrent,
                elecGenLeftOverload,
                elecGenRightOverload,
                elecLeftAcBusFault,
                elecRightAcBusFault,
                elecLeftDcBusFault,
                elecRightDcBusFault,
                elecLeftDcEssBusFault,
                elecRightDcEssBusFault
        );

        comboBox.setVisibleRowCount(10);
    }

    @Override
    public void run(String... args) {
        getStage().show();
    }

    /**
     * Activated by pressing the start button. Opens a window
     * with a control panel and a screen showing the status of
     * the power supply system.
     * <p>
     * Transmits the selected malfunction to the application
     * @param event
     */
    @FXML private void switchToSceneTwo(MouseEvent event) {
        if (comboBox.getSelectionModel().getSelectedIndex() == -1) {
            comboBox.getSelectionModel().select(0);
            masterPaneController.receiveData(comboBox.getValue());
        } else {
            masterPaneController.receiveData(comboBox.getValue());
        }

        getStage().setScene(masterPaneController.getStage().getScene());
    }
}
