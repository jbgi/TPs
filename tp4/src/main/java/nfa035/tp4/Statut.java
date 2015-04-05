package nfa035.tp4;

import common.io.Terminal;

public enum Statut implements Affichable {
    DansCourse,
    Arrive,
    Abandon,
    Disqualification;

    @Override
    public void afficher() {
        Terminal.ecrireStringln(this.name());
    }
}
