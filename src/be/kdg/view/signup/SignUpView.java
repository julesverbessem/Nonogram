package be.kdg.view.signup;

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
        //lblTitel.setStyle("-fx-border-style: solid");
        //Het label moet gecentreerd worden, een solid border hebben en groot genoeg zijn
        //moet ook normaal oke zijn zo

        this.usernameBox = new HBox();
        usernameBox.setSpacing(10);
        this.passwordBox = new HBox();
        passwordBox.setSpacing(10);
        this.confirmPasswordBox = new HBox();
        confirmPasswordBox.setSpacing(10);
        this.spelerLogInBox = new VBox();
        spelerLogInBox.setSpacing(15);

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
        //btnBack.setPrefWidth(10); =>werkt nog niet
        //btnBack.setPrefHeight(10);
        //kleiner en links vanboven gecentreerd

        //Er moet genoeg ruimte tussen die lables en textfields en buttons komen
        //die labels moeten groter
        //textfields moeten langer
        //buttons moten groter
        // alles moet meer gecentreerd zijn
    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setRight(btnLogIn);
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

        lblTitel.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
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
