package be.kdg.view.start;

import be.kdg.model.GebruikersLijst;
import be.kdg.model.Spel;
import be.kdg.view.nonogram.NonogramPresenter;
import be.kdg.view.nonogram.NonogramView;
import be.kdg.view.scorenboard.ScorenboardPresenter;
import be.kdg.view.scorenboard.ScorenboardView;
import be.kdg.view.signup.SignUpPresenter;
import be.kdg.view.signup.SignUpView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class StartPresenter {
    private GebruikersLijst model;
    private StartView view;

    public StartPresenter(GebruikersLijst model,StartView view){
        this.model = model;
        this.view = view;

        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnScorenboard().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ScorenboardView scorenboardView = new ScorenboardView();
                ScorenboardPresenter scorenboardPresenter = new ScorenboardPresenter(model,scorenboardView);

                Stage stage = new Stage();
                stage.setTitle("Nonogram scorenboard");
                stage.initOwner(view.getScene().getWindow());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(scorenboardView));
                stage.setWidth(1000);
                stage.setHeight(900);
                stage.showAndWait();
            }
        });

        view.getBtnLogIn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!model.login(view.getTxtUsernaam().getText(),view.getPwfPassword().getText())){
                    Alert cancelLogInAlert = new Alert(Alert.AlertType.ERROR);
                    cancelLogInAlert.setContentText("Uw passwoord is foutief voor de gebruiker: "+ view.getTxtUsernaam().getText()+"!");
                    cancelLogInAlert.setTitle("Login error.");
                    cancelLogInAlert.setHeaderText("Er is een fout bij het inloggen.");

                    cancelLogInAlert.getButtonTypes().clear();
                    ButtonType opniewButton = new ButtonType("Probeer opniew");
                    ButtonType singUpButton = new ButtonType("Sign up");
                    cancelLogInAlert.getButtonTypes().add(opniewButton);
                    cancelLogInAlert.getButtonTypes().add(singUpButton);

                    cancelLogInAlert.showAndWait();

                    if(cancelLogInAlert.getResult().equals(singUpButton)){
                        SignUpView signUpView = new SignUpView();
                        SignUpPresenter signUpPresenter = new SignUpPresenter(model,signUpView);

                        Stage stage = new Stage();
                        stage.setTitle("Sign-up scherm");
                        stage.initOwner(view.getScene().getWindow());
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(signUpView));
                        stage.setWidth(1000);
                        stage.setHeight(900);
                        stage.showAndWait();
                    }
                }else {
                    NonogramView nonogramView = new NonogramView();
                    NonogramPresenter nonogramPresenter = new NonogramPresenter(model,nonogramView);

                    Stage stage = new Stage();
                    stage.setTitle("Nonogram speelscherm");
                    stage.initOwner(view.getScene().getWindow());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(nonogramView));
                    stage.setWidth(1000);
                    stage.setHeight(900);
                    stage.showAndWait();
                }
            }
        });

        view.getBtnSignUp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SignUpView signUpView = new SignUpView();
                SignUpPresenter signUpPresenter = new SignUpPresenter(model,signUpView);

                Stage stage = new Stage();
                stage.setTitle("Sign-up scherm");
                stage.initOwner(view.getScene().getWindow());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(signUpView));
                stage.setWidth(1000);
                stage.setHeight(900);
                stage.showAndWait();
            }
        });
    }
}
