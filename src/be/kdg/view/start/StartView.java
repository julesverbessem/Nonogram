package be.kdg.view.start;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StartView extends BorderPane {
    private Label lblTitel;
    private Button btnScorenboard;

    private HBox usernameBox;
    private HBox passwordBox;
    private VBox spelerLogInBox;

    private Label lblUsernaam;
    private TextField txtUsernaam;

    private Label lblPassword;
    private PasswordField pwfPassword;

    private Button btnLogIn;
    private Button btnSignUp;

    public StartView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label("Nonogram");
        lblTitel.setStyle("-fx-font-size: 36");
        //lblTitel.setAlignment(Pos.CENTER_RIGHT);
        //lblTitel.setStyle("-fx-border-style: solid");
        //Het label moet gecentreerd worden, een solid border hebben en groot genoeg zijn

        this.btnScorenboard = new Button("Scorenboard");
        //De button moet gecentreerd worden en groot genoeg zijn

        this.usernameBox = new HBox();
        this.passwordBox = new HBox();
        this.spelerLogInBox = new VBox();

        this.lblUsernaam = new Label("Username:");
        this.txtUsernaam = new TextField();

        this.lblPassword = new Label("Password:");
        this.pwfPassword = new PasswordField();
        pwfPassword.setPromptText("Your password");

        this.btnLogIn = new Button("Log in");
        this.btnSignUp = new Button("Sign up");
        //Er moet genoeg ruimte tussen die lables en textfields en buttons komen
        //die labels moeten groter
        //textfields moeten langer
        //buttons moten meer naar rechts & groter
        // alles moet meer gecentreerd zijn
    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setBottom(btnScorenboard);

        usernameBox.getChildren().add(lblUsernaam);
        usernameBox.getChildren().add(txtUsernaam);
        usernameBox.getChildren().add(btnLogIn);

        passwordBox.getChildren().add(lblPassword);
        passwordBox.getChildren().add(pwfPassword);
        passwordBox.getChildren().add(btnSignUp);

        spelerLogInBox.getChildren().add(usernameBox);
        spelerLogInBox.getChildren().add(passwordBox);
        this.setCenter(spelerLogInBox);
    }

    public Button getBtnScorenboard() {
        return btnScorenboard;
    }

    public PasswordField getPwfPassword() {
        return pwfPassword;
    }

    public TextField getTxtUsernaam() {
        return txtUsernaam;
    }

    public Button getBtnLogIn() {
        return btnLogIn;
    }

    public Button getBtnSignUp() {
        return btnSignUp;
    }
}
