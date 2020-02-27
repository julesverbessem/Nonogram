package be.kdg.view.start;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class StartView extends BorderPane {
    private Label lblTitel;
    private Button btnScorenboard;

    private HBox usernameBox;
    private HBox passwordBox;
    private VBox spelerLogInBox;
    private VBox buttonBox;

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
        lblTitel.setAlignment(Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.getStyleClass().add("outline");
        //lblTitel.setStyle("-fx-border-style: solid");
        //Het label moet gecentreerd worden, een solid border hebben en groot genoeg zijn
        //Is volgens mij oke zo, kan het niet testen want krijg de applicatie ni gestart en snap de foutmelding ni direct

        this.btnScorenboard = new Button("Scorenboard");
        btnScorenboard.setAlignment(Pos.CENTER);
        btnScorenboard.setContentDisplay(ContentDisplay.CENTER);
        Font font = new Font(30);
        btnScorenboard.setFont(font);
        //De button moet gecentreerd worden en groot genoeg zijn

        this.usernameBox = new HBox();
        usernameBox.setSpacing(10);
        this.passwordBox = new HBox();
        passwordBox.setSpacing(10);
        this.spelerLogInBox = new VBox();
        spelerLogInBox.setSpacing(15);
        this.buttonBox = new VBox();
        spelerLogInBox.setSpacing(15);

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
        //buttons moten groter
        // alles moet meer gecentreerd zijn
    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setBottom(btnScorenboard);

        usernameBox.getChildren().add(lblUsernaam);
        usernameBox.getChildren().add(txtUsernaam);

        passwordBox.getChildren().add(lblPassword);
        passwordBox.getChildren().add(pwfPassword);

        spelerLogInBox.getChildren().add(usernameBox);
        spelerLogInBox.getChildren().add(passwordBox);
        this.setCenter(spelerLogInBox);

        buttonBox.getChildren().add(btnLogIn);
        buttonBox.getChildren().add(btnSignUp);
        this.setRight(buttonBox);
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
