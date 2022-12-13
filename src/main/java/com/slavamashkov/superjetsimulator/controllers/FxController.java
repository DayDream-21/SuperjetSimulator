package com.slavamashkov.superjetsimulator.controllers;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lombok.Getter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

/**
 * The class that each controller must inherit. When inherited, the
 * getSource method must be overridden.
 * <p>
 * It is recommended to add Lombok annotation @Getter for overriding
 * getSource method. Also inherited classes should be annotated as @Component
 * and @RequiredArgsConstructor to support DI.
 */
@Getter
abstract public class FxController {
    private final Stage stage = new Stage();
    private Scene scene;

    /**
     * To override this method, an inherited class only needs
     * the @Getter annotation and the source field declared as
     * <p>
     * {@code private final String source = "fxml/<file-name>"}
     *
     * @return fxml file location
     */
    protected abstract String getSource();

    // Init FX components
    {
        FXMLLoader fxmlLoader = new FXMLLoader();

        try (InputStream inputStream = fxmlLoader.getClass().getClassLoader().getResourceAsStream(getSource())) {
            fxmlLoader.setControllerFactory(param -> this);

            Parent root = fxmlLoader.load(inputStream);

            scene = new Scene(root);

            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
            Platform.exit();
        }
    }

    /**
     * FXML loader invoke method "initialize" by reflection if
     * it exists after read full context. Not recommended for use.
     */
    public void initialize() {
    }

    /**
     * Invoke after all initializations. Recommended used for
     * configuration components.
     * <p>
     * In the basic version, this method is responsible for setting
     * up the main application window.
     */
    @PostConstruct
    public void init() {
        getStage().setResizable(false);
        getStage().setTitle("SSJ-100");
        getStage().getIcons().add(new Image(
                "images/su_logo.png"
        ));
    }
}
