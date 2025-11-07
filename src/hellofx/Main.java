package hellofx;

import hellofx.database.DBUtil;
import hellofx.view.LoginSelectorView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        // ✅ Initialize SQLite schema
        DBUtil.initializeSchema();

        // ✅ Launch the login selector view
        new LoginSelectorView().start(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}