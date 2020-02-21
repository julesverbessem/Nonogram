package be.kdg.view.start;

import be.kdg.model.Spel;

public class StartPresenter {
    private Spel model;
    private StartView view;

    public StartPresenter(Spel model,StartView view){
        this.model = model;
        this.view = view;

        this.addEventHandlers();
    }

    private void addEventHandlers() {
    }
}
