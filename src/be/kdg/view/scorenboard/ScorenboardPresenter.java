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

        toonScorenboard();
        this.addEventHandlers();
    }

    private void addEventHandlers() {
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

    public void toonScorenboard(){
        view.getLblScorenboard().setText(model.overzichtSpelers());
    }
}
