package be.kdg.view.signup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SignUpView extends BorderPane {
    private Label lblTitel;

    private HBox usernameBox;
    private HBox passwordBox;
    private HBox confirmPasswordBox;
    private VBox spelerLogInBox;
    private VBox buttonBox;

    private Label lblUsername;
    private TextField txtUsername;

    private Label lblPassword;
    private PasswordField pwfPasswoord;

    private Label lblConfirmPassword;
    private PasswordField pwfConfirmPassword;

    private Button btnLogIn;
    private Button btnBack;

    public SignUpView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label("Sign up");

        this.usernameBox = new HBox();
        this.passwordBox = new HBox();
        this.confirmPasswordBox = new HBox();
        this.spelerLogInBox = new VBox();
        this.buttonBox = new VBox();

        this.lblUsername = new Label("Username:");
        this.txtUsername = new TextField();

        this.lblPassword = new Label("Password:");
        this.pwfPasswoord = new PasswordField();

        this.lblConfirmPassword = new Label("Confirm password:");
        this.pwfConfirmPassword = new PasswordField();

        this.btnLogIn = new Button("Log in");

        Image backButton = new Image("./backbutton.png");
        ImageView imageView = new ImageView(backButton);
        this.btnBack = new Button("",imageView);
    }

    private void layoutNodes() {
        //Plaatsing nodes
        this.setTop(lblTitel);
        this.setLeft(btnBack);

        usernameBox.getChildren().add(lblUsername);
        usernameBox.getChildren().add(txtUsername);

        passwordBox.getChildren().add(lblPassword);
        passwordBox.getChildren().add(pwfPasswoord);

        confirmPasswordBox.getChildren().add(lblConfirmPassword);
        confirmPasswordBox.getChildren().add(pwfConfirmPassword);

        spelerLogInBox.getChildren().add(usernameBox);
        spelerLogInBox.getChildren().add(passwordBox);
        spelerLogInBox.getChildren().add(confirmPasswordBox);
        this.setCenter(spelerLogInBox);

        buttonBox.getChildren().add(btnLogIn);
        this.setRight(buttonBox);

        //layout label titel
        lblTitel.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        //layout inloggegevens
        lblUsername.setStyle("-fx-font-size: 24");
        lblPassword.setStyle("-fx-font-size: 24");
        lblConfirmPassword.setStyle("-fx-font-size: 24");
        txtUsername.setStyle("-fx-font-size: 14");
        pwfPasswoord.setStyle("-fx-font-size: 14");
        pwfPasswoord.setPromptText("Your password");
        pwfConfirmPassword.setStyle("-fx-font-size: 14");
        pwfConfirmPassword.setPromptText("Confirm your password");
        btnLogIn.setStyle("-fx-font-size: 24");

        //layout boxes
        buttonBox.setSpacing(15);
        spelerLogInBox.setSpacing(15);
        confirmPasswordBox.setSpacing(10);
        passwordBox.setSpacing(102);
        usernameBox.setSpacing(95);
        spelerLogInBox.setPadding(new Insets(180,0,50,20));
        buttonBox.setPadding(new Insets(230,100,50,50));
    }

    public TextField getTxtUsername() {
        return txtUsername;
    }

    public PasswordField getPwfPasswoord() {
        return pwfPasswoord;
    }

    public PasswordField getPwfConfirmPassword() {
        return pwfConfirmPassword;
    }

    public Button getBtnLogIn() {
        return btnLogIn;
    }

    public Button getBtnBack() {
        return btnBack;
    }
}
