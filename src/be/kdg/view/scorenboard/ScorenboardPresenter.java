package be.kdg.view.scorenboard;

import be.kdg.model.GebruikersLijst;
import be.kdg.view.start.StartPresenter;
import be.kdg.view.start.StartView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ScorenboardPresenter {
    private GebruikersLijst model;
    private ScorenboardView view;

    public ScorenboardPresenter(GebruikersLijst model, ScorenboardView view){
        this.model = model;
        this.view = view;

        this.addEventHandlers();
    }

    private void addEventHandlers() {
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

    public void addWindowEventHandlers(){
        view.getScene().getWindow().setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                view.getLblScorenboard().setText(model.overzichtSpelers());
            }
        });
    }
}
