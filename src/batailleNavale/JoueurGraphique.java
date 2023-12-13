package batailleNavale;

import java.awt.Color; // Pour pouvoir utiliser Color.GREEN, Color.BLUE, Color.RED
import javax.swing.*;

public class JoueurGraphique extends JoueurAvecGrille {
    private GrilleGraphique grilleTirs;
    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
        super(grilleDefense, nom);
        this.grilleTirs = grilleTirs;
    }
    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs) {
        super(grilleDefense);
        this.grilleTirs = grilleTirs;
    }
    public Coordonnee choixAttaque() {
        return grilleTirs.getCoordonneeSelectionnee();
    }
    protected void retourDefense(Coordonnee c, int etat) {
        // Affichage d'un JOptionPane lorsque le tir a touché ou coulé un navire,
        // ou lorsque la partie est perdue.
    }
    protected void retourAttaque(Coordonnee c, int etat) {
        Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
        grilleTirs.colorie(c, couleur);
        switch (etat) {
            case TOUCHE:
                JOptionPane.showMessageDialog(grilleTirs, "Vous avez touché un navire en " + c);
                break;
            case COULE:
                JOptionPane.showMessageDialog(grilleTirs, "Vous avez coulé un navire en " + c);
                break;
            case GAMEOVER:
                JOptionPane.showMessageDialog(grilleTirs, "Vous avez gagné!!!");
        }
    }
}
