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
        usernameBox.setSpacing(95);
        this.passwordBox = new HBox();
        passwordBox.setSpacing(102);
        this.confirmPasswordBox = new HBox();
        confirmPasswordBox.setSpacing(10);
        this.spelerLogInBox = new VBox();
        spelerLogInBox.setSpacing(15);
        this.buttonBox = new VBox();
        buttonBox.setSpacing(15);

        this.lblUsername = new Label("Username:");
        this.txtUsername = new TextField();

        this.lblPassword = new Label("Password:");
        this.pwfPasswoord = new PasswordField();
        pwfPasswoord.setPromptText("Your password");

        this.lblConfirmPassword = new Label("Confirm password:");
        this.pwfConfirmPassword = new PasswordField();
        pwfConfirmPassword.setPromptText("Confirm your password");

        this.btnLogIn = new Button("Log in");

        Image backButton = new Image("./backbutton.png");
        ImageView imageView = new ImageView(backButton);
        this.btnBack = new Button("",imageView);
        //kleiner en links vanboven gecentreerd

        //Er moet genoeg ruimte tussen die lables en textfields en buttons komen
        //die labels moeten groter
        //textfields moeten langer
        //buttons moten groter
        // alles moet meer gecentreerd zijn
    }

    private void layoutNodes() {
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

        lblTitel.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        lblUsername.setStyle("-fx-font-size: 24");
        lblPassword.setStyle("-fx-font-size: 24");
        lblConfirmPassword.setStyle("-fx-font-size: 24");

        txtUsername.setStyle("-fx-font-size: 14");
        pwfPasswoord.setStyle("-fx-font-size: 14");
        pwfConfirmPassword.setStyle("-fx-font-size: 14");

        btnLogIn.setStyle("-fx-font-size: 24");

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
