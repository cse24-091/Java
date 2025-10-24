package hellofx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        new LoginSelectorView().start(stage); 
    }

    public static void main(String[] args) {
        launch(args);
    }
}