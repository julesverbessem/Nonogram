package be.kdg.view.start;

import be.kdg.model.GebruikersLijst;
import be.kdg.view.nonogram.NonogramPresenter;
import be.kdg.view.nonogram.NonogramView;
import be.kdg.view.scoreboard.ScoreboardPresenter;
import be.kdg.view.scoreboard.ScoreboardView;
import be.kdg.view.signup.SignUpPresenter;
import be.kdg.view.signup.SignUpView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

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
                ScoreboardView scorenboardView = new ScoreboardView();
                ScoreboardPresenter scorenboardPresenter = new ScoreboardPresenter(model,scorenboardView);

                view.getScene().setRoot(scorenboardView);
                scorenboardView.getScene().getWindow().sizeToScene();
                scorenboardView.getScene().getWindow().setHeight(800);
                scorenboardView.getScene().getWindow().setWidth(900);
            }
        });

        view.getBtnLogIn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(!model.login(view.getTxtUsernaam().getText(),view.getPwfPassword().getText())){//Als inloggegevens fout zijn => pop up: opnieuw of account aanmaken
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

                    if(cancelLogInAlert.getResult().equals(singUpButton)){//Maak een account aan
                        SignUpView signUpView = new SignUpView();
                        SignUpPresenter signUpPresenter = new SignUpPresenter(model,signUpView);

                        view.getScene().setRoot(signUpView);
                        signUpView.getScene().getWindow().sizeToScene();
                        signUpView.getScene().getWindow().setHeight(800);
                        signUpView.getScene().getWindow().setWidth(900);
                    }
                }else {//De inloggegevens zijn juist & het nonogram wordt opgestart
                    model.getGebruiker(view.getTxtUsernaam().getText()).getOpgeslagenSpel().startSpel(model.getGebruiker(view.getTxtUsernaam().getText()));
                    NonogramView nonogramView = new NonogramView(model.getGebruiker(view.getTxtUsernaam().getText()).getOpgeslagenSpel().getMijnNonogram());
                    NonogramPresenter nonogramPresenter = new NonogramPresenter(model,nonogramView,view.getTxtUsernaam().getText());

                    view.getScene().setRoot(nonogramView);
                    nonogramView.getScene().getWindow().sizeToScene();
                    nonogramView.getScene().getWindow().setHeight(800);
                    nonogramView.getScene().getWindow().setWidth(900);
                }
            }
        });

        view.getBtnSignUp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SignUpView signUpView = new SignUpView();
                SignUpPresenter signUpPresenter = new SignUpPresenter(model,signUpView);

                view.getScene().setRoot(signUpView);
                signUpView.getScene().getWindow().sizeToScene();
                signUpView.getScene().getWindow().setHeight(800);
                signUpView.getScene().getWindow().setWidth(900);
            }
        });
    }
}
