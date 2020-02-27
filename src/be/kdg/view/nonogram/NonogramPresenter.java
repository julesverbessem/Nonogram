package be.kdg.view.nonogram;

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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class NonogramPresenter {
    private GebruikersLijst model;
    private NonogramView view;
    private String gebruikersnaam;

    public NonogramPresenter(GebruikersLijst model, NonogramView view, String gebruikersnaam) {
        this.model = model;
        this.view = view;
        this.gebruikersnaam = gebruikersnaam;

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
                cancelNonogram.setContentText("Weet u zeker dat u wilt afsluiten?");

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
                    stage.setWidth(1000);
                    stage.setHeight(900);
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
                cancelNonogram.setContentText("Weet u zeker dat u wilt afsluiten?");

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
                    stage.setWidth(1000);
                    stage.setHeight(900);
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
                view.getLblTitel().setText("Nonogram lvl "+model.getGebruiker(gebruikersnaam));
            }
        });
        view.getScene().getWindow().setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                System.out.println(windowEvent.getEventType().getName());
                Alert cancelNonogram = new Alert(Alert.AlertType.WARNING);
                cancelNonogram.setTitle("Warning!");
                cancelNonogram.setHeaderText("Nonogram afsluiten.");
                cancelNonogram.setContentText("Weet u zeker dat u wilt afsluiten?");

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
                    FelicitatiePresenter felicitatiePresenter = new FelicitatiePresenter(model,felicitatieView,gebruikersnaam);

                    Stage stage = new Stage();
                    stage.setTitle("Felicitatiescherm");
                    stage.initOwner(view.getScene().getWindow());
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(new Scene(felicitatieView));
                    felicitatiePresenter.addWindowEventHandlers();
                    stage.setWidth(1000);
                    stage.setHeight(900);
                    stage.showAndWait();                }
            }
        });
    }
}
