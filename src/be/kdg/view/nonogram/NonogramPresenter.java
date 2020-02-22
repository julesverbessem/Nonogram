package be.kdg.view.nonogram;

import be.kdg.model.GebruikersLijst;

public class NonogramPresenter {
    private GebruikersLijst model;
    private NonogramView view;

    public NonogramPresenter(GebruikersLijst model, NonogramView view) {
        this.model = model;
        this.view = view;
    }
}
