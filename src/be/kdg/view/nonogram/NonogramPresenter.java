package be.kdg.view.nonogram;

import be.kdg.model.Gebruiker;
import be.kdg.model.GebruikersLijst;
import be.kdg.view.felicitatie.FelicitatiePresenter;
import be.kdg.view.felicitatie.FelicitatieView;
import be.kdg.view.scorenboard.ScorenboardPresenter;
import be.kdg.view.scorenboard.ScorenboardView;
import be.kdg.view.start.StartPresenter;
import be.kdg.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
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

        this.view.getLblTitel().setText("Nonogram lvl "+speler.getLevel());

        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Gebruiker wilt terug naar startscherm gaan, waarschuwing");
                Alert cancelNonogram = new Alert(Alert.AlertType.WARNING);
                cancelNonogram.setTitle("Warning!");
                cancelNonogram.setHeaderText("Nonogram afsluiten.");
                cancelNonogram.setContentText("Weet u zeker dat u wilt afsluiten? Uw vooruitgang zal niet worden opgeslagen.");

                cancelNonogram.getButtonTypes().clear();
                ButtonType neeButton = new ButtonType("Nee, ik speel door.");
                ButtonType jaButton = new ButtonType("Ja, ik wil stoppen.");
                cancelNonogram.getButtonTypes().add(jaButton);
                cancelNonogram.getButtonTypes().add(neeButton);

                cancelNonogram.showAndWait();

                if(cancelNonogram.getResult().equals(jaButton)){
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
                System.out.println("Gebruiker wilt scorenboard openen tijdens spel, waarschuwing");
                Alert cancelNonogram = new Alert(Alert.AlertType.WARNING);
                cancelNonogram.setTitle("Warning!");
                cancelNonogram.setHeaderText("Nonogram afsluiten.");
                cancelNonogram.setContentText("Weet u zeker dat u wilt afsluiten? Uw vooruitgang zal niet worden opgeslagen.");

                cancelNonogram.getButtonTypes().clear();
                ButtonType neeButton = new ButtonType("Nee, ik speel door.");
                ButtonType jaButton = new ButtonType("Ja, ik wil stoppen.");
                cancelNonogram.getButtonTypes().add(jaButton);
                cancelNonogram.getButtonTypes().add(neeButton);

                cancelNonogram.showAndWait();

                if(cancelNonogram.getResult().equals(jaButton)){
                    ScorenboardView scorenboardView = new ScorenboardView();
                    ScorenboardPresenter scorenboardPresenter = new ScorenboardPresenter(model,scorenboardView);

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
                spelregelsAlert.setContentText("alle spelregels komen hier");
                spelregelsAlert.setTitle("Spelregels");
                spelregelsAlert.setHeaderText("Hieronder worden de spregels van het nonogram uitgelegd:");
                spelregelsAlert.showAndWait();
                //!! ipv van een alert een niew scherm?
            }
        });

        for (int rij = 0; rij < view.getNonogram().size(); rij++) {
            ArrayList<Label> lbllijst = view.getNonogram().get(rij);
            for (int kolom = 0; kolom < lbllijst.size(); kolom++) {
                final int frij = rij;
                final int fkolom = kolom;
                lbllijst.get(kolom).setOnMouseReleased(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        if(speler.getOpgeslagenSpel().getMijnNonogram().kleurIn(frij, fkolom)){
                            view.getNonogram().get(frij).get(fkolom).setStyle("-fx-background-color: black");
                        }else{
                            view.getNonogram().get(frij).get(fkolom).setStyle("-fx-background-color: none");
                        }
                        if(speler.getOpgeslagenSpel().getMijnNonogram().controlleren(frij,fkolom)){
                           System.out.println("Gewonnen!");
                           speler.getOpgeslagenSpel().startVolgendSpel(speler);
                            FelicitatieView felicitatieView = new FelicitatieView(speler);
                            FelicitatiePresenter felicitatiePresenter = new FelicitatiePresenter(model,felicitatieView,speler.getGebruikersnaam());

                            view.getScene().setRoot(felicitatieView);
                            felicitatieView.getScene().getWindow().sizeToScene();
                            felicitatieView.getScene().getWindow().setHeight(800);
                            felicitatieView.getScene().getWindow().setWidth(900);
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
                System.out.println(windowEvent.getEventType().getName());
                Alert cancelNonogram = new Alert(Alert.AlertType.WARNING);
                cancelNonogram.setTitle("Warning!");
                cancelNonogram.setHeaderText("Nonogram afsluiten.");
                cancelNonogram.setContentText("Weet u zeker dat u wilt afsluiten? Uw vooruitgang zal niet worden opgeslagen.");

                cancelNonogram.getButtonTypes().clear();
                ButtonType neeButton = new ButtonType("Nee, ik speel door.");
                ButtonType jaButton = new ButtonType("Ja, ik wil stoppen.");
                cancelNonogram.getButtonTypes().add(jaButton);
                cancelNonogram.getButtonTypes().add(neeButton);

                cancelNonogram.showAndWait();

                if(cancelNonogram.getResult().equals(neeButton)){
                    windowEvent.consume();
                }
            }
        });
    }
}
