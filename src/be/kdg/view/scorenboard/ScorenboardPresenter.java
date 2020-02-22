package be.kdg.view.scorenboard;

import be.kdg.model.GebruikersLijst;

public class ScorenboardPresenter {
    private GebruikersLijst model;
    private ScorenboardView view;

    public ScorenboardPresenter(GebruikersLijst model, ScorenboardView view){
        this.model = model;
        this.view = view;
    }
}
