package be.kdg.view.start;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class StartView extends BorderPane {
    private Label lblTitel;

    public StartView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label("Nonogram");
        lblTitel.setStyle("-fx-font-size: 36");
        //lblTitel.setAlignment(Pos.CENTER_RIGHT);
        //lblTitel.setStyle("-fx-border-style: solid");


    }

    private void layoutNodes() {
        this.setTop(lblTitel);
    }
}
