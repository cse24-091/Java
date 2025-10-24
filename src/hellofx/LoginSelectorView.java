package hellofx;

import hellofx.view.CustomerLoginView;
import hellofx.view.TellerLoginView;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginSelectorView {

    public void start(Stage stage) {
        Label title = new Label("Welcome to the Banking System");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Button customerLoginButton = new Button("Customer Login");
        Button tellerLoginButton = new Button("Bank Teller Login");

        customerLoginButton.setOnAction(e -> new CustomerLoginView().start(stage));
        tellerLoginButton.setOnAction(e -> new TellerLoginView().start(stage));

        VBox layout = new VBox(15, title, customerLoginButton, tellerLoginButton);
        layout.setStyle("-fx-padding: 30;");
        layout.setAlignment(javafx.geometry.Pos.CENTER);

        Scene scene = new Scene(layout, 400, 250);
        stage.setScene(scene);
        stage.setTitle("Login Selector");
        stage.show();
    }
}