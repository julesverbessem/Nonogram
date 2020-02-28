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

        this.addEventHandlers();
    }

    private void addEventHandlers() {
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
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

                    Stage stage = new Stage();
                    stage.setTitle("Nonogram scorenboard");
                    stage.initOwner(view.getScene().getWindow());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(scorenboardView));
                    scorenboardPresenter.addWindowEventHandlers();
                    stage.setWidth(900);
                    stage.setHeight(800);
                    stage.showAndWait();
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
            }
        });
    }

    public void addWindowEventHandlers(){
        view.getScene().getWindow().setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                view.getLblTitel().setText("Nonogram lvl "+speler.getLevel());

                int grote = speler.getOpgeslagenSpel().startSpel(speler);

                initialiseGrid(grote);
                layoutGrid(grote);

                /*boolean isKlaar = false;

                while(!isKlaar){
                    for(int rij = 1; rij<= grote; rij++){
                        for(int kolom = 1; kolom<= grote; kolom++){
                            int r = rij;
                            int k = kolom;
                            view.getGrid().getChildren().get(r-1);
                        }
                    }
                }*/
            }
        });
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
                else{
                    FelicitatieView felicitatieView = new FelicitatieView();
                    FelicitatiePresenter felicitatiePresenter = new FelicitatiePresenter(model,felicitatieView,speler.getGebruikersnaam());

                    Stage stage = new Stage();
                    stage.setTitle("Felicitatiescherm");
                    stage.initOwner(view.getScene().getWindow());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(felicitatieView));
                    felicitatiePresenter.addWindowEventHandlers();
                    stage.setWidth(900);
                    stage.setHeight(800);
                    stage.showAndWait();                }
            }
        });
    }

    private void initialiseGrid(int grote){
        for(int rij = 1; rij<= grote; rij++){
            ArrayList<Label> kolomLijst = new ArrayList<>();
            for(int kolom = 1; kolom<= grote; kolom++){
                kolomLijst.add(new Label("x"));
                int r = rij;
                int k = kolom;
                view.getNonogramGrid().add(new Label("x"),k-1,r-1);
            }
            view.getNonogram().add(kolomLijst);
        }

        for(int rij =1; rij<=grote; rij++){
            int r = rij;
            view.getRijGrid().add(new Label("rij"),0,r-1);
        }

        for(int kolom =1; kolom<=grote; kolom++){
            int k = kolom;
            view.getKolomGrid().add(new Label("kolom"),k-1,0);
        }
    }

    private void layoutGrid(int grote){
        for(int kolom = 1; kolom<= grote; kolom++){
            view.getNonogramGrid().getColumnConstraints().add(new ColumnConstraints(100));
        }
        for(int rij = 1; rij<= grote; rij++){
            view.getNonogramGrid().getRowConstraints().add(new RowConstraints(100));
        }

        for(int kolom = 1; kolom<= grote; kolom++){
            view.getKolomGrid().getColumnConstraints().add(new ColumnConstraints(100));
        }
        view.getKolomGrid().getRowConstraints().add(new RowConstraints(100));

        view.getRijGrid().getColumnConstraints().add(new ColumnConstraints(100));
        for(int rij = 1; rij<= grote; rij++){
            view.getRijGrid().getRowConstraints().add(new RowConstraints(100));
        }
    }
}
