package batailleNavale;

import java.awt.Color; // Pour pouvoir utiliser Color.GREEN, Color.BLUE, Color.RED
import javax.swing.*;

public class JoueurGraphique extends JoueurAvecGrille {

    private GrilleGraphique grilleTirs;

    //    Constructeurs :
    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
        super(grilleDefense, nom);
        this.grilleTirs = grilleTirs;
    }

    public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs) {
        super(grilleDefense);
        this.grilleTirs = grilleTirs;
    }

    //    Methodes :
    public Coordonnee choixAttaque() {
        return grilleTirs.getCoordonneeSelectionnee();
    }

    public void retourDefense(Coordonnee c, int etat) {
        if (etat == Joueur.TOUCHE)
            System.out.println("Tir " + c+ " du défenseur : Votre navire a été touché par un tir");
        else if (etat == Joueur.COULE)
            System.out.println("Tir " + c+ " du défenseur : Votre navire a été coulé");
        else if (etat == Joueur.A_L_EAU)
            System.out.println("Tir " + c+" du défenseur : Un tir par l'attaquant est tombé dans l'eau");
        else if (etat == Joueur.GAMEOVER)
            System.out.println("Tir " + c+" du défenseur : Perdu 😦 ");


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
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}