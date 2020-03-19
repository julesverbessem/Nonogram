package be.kdg.view.nonogram;

import be.kdg.model.Gebruiker;
import be.kdg.model.GebruikersLijst;
import be.kdg.view.felicitatie.FelicitatiePresenter;
import be.kdg.view.felicitatie.FelicitatieView;
import be.kdg.view.scoreboard.ScoreboardPresenter;
import be.kdg.view.scoreboard.ScoreboardView;
import be.kdg.view.start.StartPresenter;
import be.kdg.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

public class NonogramPresenter {
    private GebruikersLijst model;
    private NonogramView view;
    private Gebruiker speler;

    public NonogramPresenter(GebruikersLijst model, NonogramView view, String gebruikersnaam) {
        this.model = model;
        this.view = view;

        this.speler = model.getGebruiker(gebruikersnaam);
        this.view.getLblTitel().setText("Nonogram lvl "+speler.getLevel());//Tekst in de titel inladen

        this.addEventHandlers();
    }

    private Alert initialiseAlertPopUp(ButtonType jaButton) {//Pop up, wordt getoond bij het afsluiten van het nonogram
        System.out.println("Gebruiker wilt terug naar startscherm gaan, waarschuwing");
        Alert cancelNonogram = new Alert(Alert.AlertType.WARNING);
        cancelNonogram.setTitle("Warning!");
        cancelNonogram.setHeaderText("Nonogram afsluiten.");
        cancelNonogram.setContentText("Weet u zeker dat u wilt afsluiten? Uw vooruitgang zal worden opgeslagen.");
        cancelNonogram.getButtonTypes().clear();
        ButtonType neeButton = new ButtonType("Nee, ik speel door.");
        cancelNonogram.getButtonTypes().add(jaButton);
        cancelNonogram.getButtonTypes().add(neeButton);
        return cancelNonogram;
    }

    private void addEventHandlers() {
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonType jaButton = new ButtonType("Ja, ik wil stoppen.");
                Alert cancelNonogram = initialiseAlertPopUp(jaButton);
                cancelNonogram.showAndWait();

                if(cancelNonogram.getResult().equals(jaButton)){
                    speler.getOpgeslagenSpel().getMijnNonogram().pauzeerGebruikerNonogram(speler.getGebruikersnaam());
                    StartView startView = new StartView();
                    StartPresenter startPresenter = new StartPresenter(model,startView);

                    view.getScene().setRoot(startView);
                    startView.getScene().getWindow().sizeToScene();
                    startView.getScene().getWindow().setHeight(800);
                    startView.getScene().getWindow().setWidth(900);
                }
            }
        });
        view.getBtnScorenboard().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ButtonType jaButton = new ButtonType("Ja, ik wil stoppen.");
                Alert cancelNonogram = initialiseAlertPopUp(jaButton);
                cancelNonogram.showAndWait();

                if(cancelNonogram.getResult().equals(jaButton)){
                    speler.getOpgeslagenSpel().getMijnNonogram().pauzeerGebruikerNonogram(speler.getGebruikersnaam());
                    ScoreboardView scorenboardView = new ScoreboardView();
                    ScoreboardPresenter scorenboardPresenter = new ScoreboardPresenter(model,scorenboardView);

                    view.getScene().setRoot(scorenboardView);
                    scorenboardView.getScene().getWindow().sizeToScene();
                    scorenboardView.getScene().getWindow().setHeight(800);
                    scorenboardView.getScene().getWindow().setWidth(900);
                }
            }
        });

        view.getBtnSpelregels().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert spelregelsAlert = new Alert(Alert.AlertType.INFORMATION);
                spelregelsAlert.setContentText(model.inlezenSpelrgels());
                spelregelsAlert.setTitle("Spelregels");
                spelregelsAlert.setHeaderText("Hieronder worden de spregels van het nonogram uitgelegd:");
                spelregelsAlert.showAndWait();
            }
        });

        //Lopen over elk label in de lijst
        for (int rij = 0; rij < view.getNonogram().size(); rij++) {
            ArrayList<Label> lbllijst = view.getNonogram().get(rij);
            for (int kolom = 0; kolom < lbllijst.size(); kolom++) {
                final int frij = rij;
                final int fkolom = kolom;
                lbllijst.get(kolom).setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(mouseEvent.getButton()== MouseButton.SECONDARY){//bij een recht muisclick: grijze achtergrond + aanduiden
                            if(speler.getOpgeslagenSpel().getMijnNonogram().duidAan(frij, fkolom)){
                                view.getNonogram().get(frij).get(fkolom).setStyle("-fx-background-color: grey");
                            }else{
                                view.getNonogram().get(frij).get(fkolom).setStyle("-fx-background-color: none");
                            }
                        }else {//bij een links muisclick: zwart achtergrond + inkleuren
                            if(speler.getOpgeslagenSpel().getMijnNonogram().kleurIn(frij, fkolom)){
                                view.getNonogram().get(frij).get(fkolom).setStyle("-fx-background-color: black");
                            }else{
                                view.getNonogram().get(frij).get(fkolom).setStyle("-fx-background-color: none");
                            }
                            if(speler.getOpgeslagenSpel().getMijnNonogram().controlleren(frij,fkolom)){//Controlleren of het nonogram juist is
                                System.out.println("Gewonnen!");
                                speler.getOpgeslagenSpel().startVolgendSpel(speler);
                                model.updateGebruikers();

                                FelicitatieView felicitatieView = new FelicitatieView(speler);
                                FelicitatiePresenter felicitatiePresenter = new FelicitatiePresenter(model,felicitatieView,speler.getGebruikersnaam());

                                view.getScene().setRoot(felicitatieView);
                                felicitatieView.getScene().getWindow().sizeToScene();
                                felicitatieView.getScene().getWindow().setHeight(800);
                                felicitatieView.getScene().getWindow().setWidth(900);
                            }
                        }


                    }
                });
            }

        }
    }

    public void addWindowEventHandlers(){
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                ButtonType jaButton = new ButtonType("Ja, ik wil stoppen.");
                Alert cancelNonogram = initialiseAlertPopUp(jaButton);
                cancelNonogram.showAndWait();

                if(cancelNonogram.getResult().equals(jaButton)){
                    speler.getOpgeslagenSpel().getMijnNonogram().pauzeerGebruikerNonogram(speler.getGebruikersnaam());
                    windowEvent.consume();
                }
            }
        });
    }
}
