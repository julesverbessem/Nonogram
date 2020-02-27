package be.kdg.view.felicitatie;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;


public class FelicitatieView extends BorderPane {
    private Label lblTitel;
    private Label lblFoto;

    private Button btnNieuwLevel;
    private Button btnScorenboard;
    private Button btnBeginScherm;
    private HBox buttonBox;

    public FelicitatieView(){
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.lblTitel = new Label("Proficiat!");
        this.lblFoto = new Label();// in de presenter foto toewijzen aan label

        this.btnNieuwLevel = new Button("Nieuw Level");
        this.btnScorenboard = new Button("Scorenboard");
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

    public Label getLblFoto() {
        return lblFoto;
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
