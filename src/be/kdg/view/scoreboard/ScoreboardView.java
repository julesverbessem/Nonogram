package be.kdg.view.scoreboard;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ScoreboardView extends BorderPane {
    private Label lblTitel;
    private Button btnBack;
    private Label lblScorenboard;

    public ScoreboardView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label("Scoreboard");

        Image backButton = new Image("foto/backbutton.png");
        ImageView imageView = new ImageView(backButton);
        this.btnBack = new Button("", imageView);

        this.lblScorenboard = new Label("");

    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setLeft(btnBack);
        this.setCenter(lblScorenboard);

        //layout label Titel
        lblTitel.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));

        //layout scorenboard
        lblScorenboard.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblScorenboard, Pos.CENTER);
        lblScorenboard.setContentDisplay(ContentDisplay.CENTER);
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLblScorenboard() {
        return lblScorenboard;
    }
}
