package com.slavamashkov.superjetsimulator;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SuperjetSimulatorApplication extends Application {
    private static String[] args;

    public static void main(String[] args) {
        SuperjetSimulatorApplication.args = args;
        SuperjetSimulatorApplication.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        SpringApplication.run(SuperjetSimulatorApplication.class, args);
    }
}
