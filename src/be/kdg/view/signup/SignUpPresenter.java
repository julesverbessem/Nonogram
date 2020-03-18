package be.kdg.view.signup;

import be.kdg.model.Gebruiker;
import be.kdg.model.GebruikersLijst;
import be.kdg.view.nonogram.NonogramPresenter;
import be.kdg.view.nonogram.NonogramView;
import be.kdg.view.start.StartPresenter;
import be.kdg.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SignUpPresenter {
    private GebruikersLijst model;
    private SignUpView view;

    public SignUpPresenter(GebruikersLijst model, SignUpView view){
        this.model = model;
        this.view = view;

        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnLogIn().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert cancelLogInAlert = new Alert(Alert.AlertType.ERROR);
                cancelLogInAlert.setTitle("Login error.");
                cancelLogInAlert.setHeaderText("Er is een fout bij het inloggen.");
                cancelLogInAlert.getButtonTypes().clear();
                ButtonType opniewButton = new ButtonType("Probeer opniew");
                cancelLogInAlert.getButtonTypes().add(opniewButton);

                for (Gebruiker huidigeGebruiker:model.getLijst()) {//Controlleren of de gebruikersnaam al bestaad
                    if(huidigeGebruiker.getGebruikersnaam().equals(view.getTxtUsername().getText())){
                        cancelLogInAlert.setContentText("Uw gebruikersnaam: "+ view.getTxtUsername().getText()+" is al ingebruik!");
                        cancelLogInAlert.showAndWait();
                    }
                }

                if(!view.getPwfPasswoord().getText().equals(view.getPwfConfirmPassword().getText())){//Controlleren of de de ingegeven passwoorden overeenkomen
                    cancelLogInAlert.setContentText("Uw passwoord is foutief voor de gebruiker: "+ view.getTxtUsername().getText()+"!");
                    cancelLogInAlert.showAndWait();
                }

                //De nieuwe gebruiker aanmaken, toevoegen, nonogram maken & starten
                Gebruiker nieuweSpeler = new Gebruiker(view.getTxtUsername().getText(),view.getPwfConfirmPassword().getText());
                model.setGebruiker(nieuweSpeler);
                model.getGebruiker(view.getTxtUsername().getText()).getOpgeslagenSpel().startSpel(model.getGebruiker(view.getTxtUsername().getText()));
                model.getGebruiker(view.getTxtUsername().getText()).getOpgeslagenSpel().getMijnNonogram().schrijfGebruikerNonogramWeg(view.getTxtUsername().getText());


                NonogramView nonogramView = new NonogramView(model.getGebruiker(view.getTxtUsername().getText()).getOpgeslagenSpel().getMijnNonogram());
                NonogramPresenter nonogramPresenter = new NonogramPresenter(model,nonogramView,view.getTxtUsername().getText());
                view.getScene().setRoot(nonogramView);
                nonogramView.getScene().getWindow().sizeToScene();
                nonogramView.getScene().getWindow().setHeight(800);
                nonogramView.getScene().getWindow().setWidth(900);
            }
        });
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartView startView = new StartView();
                StartPresenter startPresenter = new StartPresenter(model,startView);

                view.getScene().setRoot(startView);
                startView.getScene().getWindow().sizeToScene();
                startView.getScene().getWindow().setHeight(800);
                startView.getScene().getWindow().setWidth(900);
            }
        });
    }
}
