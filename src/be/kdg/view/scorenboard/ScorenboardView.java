package be.kdg.view.scorenboard;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ScorenboardView extends BorderPane {
    private Label lblTitel;
    private Button btnBack;
    private Label lblScorenboard;

    public ScorenboardView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label("Scorenboard");
        //Het label moet gecentreerd worden, een solid border hebben en groot genoeg zijn
        //normaal oke

        Image backButton = new Image("./backbutton.png");
        ImageView imageView = new ImageView(backButton);
        //imageView.setFitHeight(10);
        //imageView.setFitWidth(10);
        this.btnBack = new Button("", imageView);
        btnBack.setStyle("-fx-font-size: 36");
        btnBack.setAlignment(Pos.TOP_LEFT);
        btnBack.setContentDisplay(ContentDisplay.CENTER);
        //kleiner en links vanboven gecentreerd
        //normaal oke

        this.lblScorenboard = new Label("test");
        //label moet groot genoeg en gecnetreerd zijn met een solid border
        //normaal oke
    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setLeft(btnBack);
        this.setCenter(lblScorenboard);

        lblTitel.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblTitel, Pos.CENTER);
        lblTitel.setContentDisplay(ContentDisplay.CENTER);

        lblScorenboard.setStyle("-fx-font-size: 36");
        BorderPane.setAlignment(lblScorenboard, Pos.CENTER);
        lblScorenboard.setContentDisplay(ContentDisplay.CENTER);
        lblTitel.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
    }

    public Button getBtnBack() {
        return btnBack;
    }

    public Label getLblScorenboard() {
        return lblScorenboard;
    }
}
