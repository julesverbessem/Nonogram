package be.kdg.view.start;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
        this.btnScorenboard = new Button("Scorenboard");


        this.usernameBox = new HBox();
        this.passwordBox = new HBox();
        this.spelerLogInBox = new VBox();
        this.buttonBox = new VBox();

        this.lblUsernaam = new Label("Username:");
        this.txtUsernaam = new TextField();
        txtUsernaam.setText("Jules");//delete

        this.lblPassword = new Label("Password:");
        this.pwfPassword = new PasswordField();
        pwfPassword.setText("Test");//delete

        this.btnLogIn = new Button("Log in");
        this.btnSignUp = new Button("Sign up");
    }

    private void layoutNodes() {
        //Nodes plaatsen
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

        //Layout button scorenboard
        btnScorenboard.setAlignment(Pos.CENTER);
        btnScorenboard.setContentDisplay(ContentDisplay.CENTER);
        btnScorenboard.setStyle("-fx-font-size: 28");
        BorderPane.setAlignment(btnScorenboard, Pos.CENTER);

        //Layout label Titel
        lblTitel.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));


        //Layout inloggegevens
        lblUsernaam.setStyle("-fx-font-size: 24");
        lblPassword.setStyle("-fx-font-size: 24");
        txtUsernaam.setStyle("-fx-font-size: 18");
        pwfPassword.setStyle("-fx-font-size: 18");
        pwfPassword.setPromptText("Your password");
        btnLogIn.setStyle("-fx-font-size: 24");
        btnSignUp.setStyle("-fx-font-size: 24");

        //Box layout
        spelerLogInBox.setSpacing(15);
        passwordBox.setSpacing(16);
        usernameBox.setSpacing(10);
        spelerLogInBox.setPadding(new Insets(200,100,50,50));
        buttonBox.setPadding(new Insets(180,100,50,50));
        buttonBox.setSpacing(15);
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
