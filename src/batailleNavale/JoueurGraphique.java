package batailleNavale;

import javax.swing.*;
import java.awt.*;

public class JoueurGraphique extends JoueurAvecGrille {
        private GrilleGraphique grilleTirs;
        public JoueurGraphique(GrilleNavaleGraphique grilleDefense,
                               GrilleGraphique grilleTirs, String nom) {
            super(grilleDefense, nom);

        }
        public JoueurGraphique(GrilleNavaleGraphique grilleDefense,
                               GrilleGraphique grilleTirs) {
            this(grilleDefense, grilleTirs, "Joueur Graphique");
        }
        public Coordonnee choixAttaque() {
            return grilleTirs.getCoordonneeSelectionnee();
        }
        /*protected void retourDefense(Coordonnee c, int etat) {
            Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
            Component grilleDefense = null;
            grilleDefense.colorie(c, couleur);
            switch (etat) {
                case TOUCHE:
                    JOptionPane.showMessageDialog(grilleDefense, "Vous avez été touché en " + c);
                    break;
                case COULE:
                    JOptionPane.showMessageDialog(grilleDefense, "Vous avez été coulé en " + c);
                    break;
                case GAMEOVER:
                    JOptionPane.showMessageDialog(grilleDefense, "Vous avez perdu!!!");
            }
        }
        */
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

    @Override
    protected void retourDefense(Coordonnee c, int etat) {

    }
}

