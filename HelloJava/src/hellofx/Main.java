package hellofx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        // Open the Java-based Login screen (no FXML)
        new LoginView().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
