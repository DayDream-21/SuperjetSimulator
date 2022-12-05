package com.slavamashkov.superjetsimulator.controllers;

import com.slavamashkov.superjetsimulator.errors.*;
import com.slavamashkov.superjetsimulator.errors.Malfunction;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Getter
@Component
@RequiredArgsConstructor
public class LandingPageController extends FxController implements CommandLineRunner {
    private final String source = "fxml/landing-page.fxml";
    private final MasterPaneController masterPaneController;

    @FXML private ImageView logo;
    @FXML private ComboBox<Malfunction> comboBox;

    @Override
    public void init() {
        super.init();
        getScene().getStylesheets().add("css/landing-page.css");
        logo.setImage(new Image("images/sukhoi_logo.png"));
        comboBox.getItems().addAll(
                new BusMalfunction(),
                new BatteryMalfunction()
        );
        comboBox.setVisibleRowCount(2);
    }

    @Override
    public void run(String... args) {
        getStage().show();
    }

    public void switchToSceneTwo(MouseEvent event) {
        masterPaneController.getStage().show();
        masterPaneController.receiveData(comboBox.getValue());
    }
}
