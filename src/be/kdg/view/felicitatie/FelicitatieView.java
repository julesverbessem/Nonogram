package be.kdg.view.felicitatie;

import be.kdg.model.Gebruiker;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class FelicitatieView extends BorderPane {
    private Label lblTitel;
    private Label lblFoto;
    private ImageView imageView;

    private Button btnNieuwLevel;
    private Button btnScorenboard;
    private Button btnBeginScherm;
    private HBox buttonBox;

    public FelicitatieView(Gebruiker speler){
        this.initialiseNodes(speler);
        this.layoutNodes();
    }

    private void initialiseNodes(Gebruiker speler){
        this.lblTitel = new Label(speler.getOpgeslagenSpel().getMijnNonogram().feliciteren());


        this.imageView = new ImageView(speler.getOpgeslagenSpel().toonNonogramFoto(speler));//De foto is afhankelijk van de user level
        this.lblFoto = new Label("",imageView);

        this.btnNieuwLevel = new Button("Nieuw Level");
        this.btnScorenboard = new Button("Scoreboard");
        this.btnBeginScherm = new Button("Begin scherm");
        this.buttonBox = new HBox();
    }

    private void layoutNodes() {
        this.setTop(lblTitel);
        this.setCenter(lblFoto);

        buttonBox.getChildren().add(btnNieuwLevel);
        buttonBox.getChildren().add(btnScorenboard);
        buttonBox.getChildren().add(btnBeginScherm);
        this.setBottom(buttonBox);
    }



    public Button getBtnNieuwLevel() {
        return btnNieuwLevel;
    }

    public Button getBtnScorenboard() {
        return btnScorenboard;
    }

    public Button getBtnBeginScherm() {
        return btnBeginScherm;
    }
}
