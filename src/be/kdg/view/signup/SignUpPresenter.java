package be.kdg.view.signup;

import be.kdg.model.Gebruiker;
import be.kdg.model.GebruikersLijst;
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
                for (Gebruiker huidigeGebruiker:model.getLijst()) {
                    if(huidigeGebruiker.getGebruikersnaam().equals(view.getTxtUsername().getText())){
                        Alert cancelLogInAlert = new Alert(Alert.AlertType.ERROR);
                        cancelLogInAlert.setContentText("Uw gebruikersnaam: "+ view.getTxtUsername().getText()+" is al ingebruik!");
                        cancelLogInAlert.setTitle("Login error.");
                        cancelLogInAlert.setHeaderText("Er is een fout bij het inloggen.");

                        cancelLogInAlert.getButtonTypes().clear();
                        ButtonType opniewButton = new ButtonType("Probeer opniew");
                        cancelLogInAlert.getButtonTypes().add(opniewButton);
                        cancelLogInAlert.showAndWait();
                    }
                }

                if(!view.getPwfPasswoord().getText().equals(view.getPwfConfirmPassword().getText())){
                    Alert cancelLogInAlert = new Alert(Alert.AlertType.ERROR);
                    cancelLogInAlert.setContentText("Uw passwoord is foutief voor de gebruiker: "+ view.getTxtUsername().getText()+"!");
                    cancelLogInAlert.setTitle("Login error.");
                    cancelLogInAlert.setHeaderText("Er is een fout bij het inloggen.");

                    cancelLogInAlert.getButtonTypes().clear();
                    ButtonType opniewButton = new ButtonType("Probeer opniew");
                    cancelLogInAlert.getButtonTypes().add(opniewButton);

                    cancelLogInAlert.showAndWait();
                }

                Gebruiker nieuweSpeler = new Gebruiker(view.getTxtUsername().getText(),view.getPwfConfirmPassword().getText());
                model.setGebruiker(nieuweSpeler);
            }
        });
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                StartView startView = new StartView();
                StartPresenter startPresenter = new StartPresenter(model,startView);

                Stage stage = new Stage();
                stage.setTitle("Nonogram startscherm");
                stage.initOwner(view.getScene().getWindow());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(startView));
                stage.setWidth(900);
                stage.setHeight(800);
                stage.showAndWait();
            }
        });
    }
}
