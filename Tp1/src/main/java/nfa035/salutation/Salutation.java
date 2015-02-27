package nfa035.salutation;

import utils.io.TabUtils;
import utils.io.Terminal;

public class Salutation {

    public static void main(final String[] args) {
        Terminal.ecrireString("Quel est votre nom ?");
        final String nomUtilisateur = Terminal.lireString();
        Terminal.ecrireString("Bonjour " + nomUtilisateur);
        TabUtils.saisirTabInt(3);
    }
}
