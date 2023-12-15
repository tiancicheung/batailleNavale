package batailleNavale;

import javax.swing.*;

import java.awt.*;
import javax.swing.border.TitledBorder;

public class FenetreJoueur extends JFrame {



    public FenetreJoueur() {
        this("Nom du joueur", new GrilleGraphique(10),new GrilleGraphique(10));
    }

    public FenetreJoueur(String nom, GrilleGraphique gTirs, GrilleGraphique gDef) {
        super(nom);
        getContentPane().setLayout(new GridLayout(1, 2, 0, 0));

        JPanel pTir = gTirs;
        pTir.setBorder(new TitledBorder(null, "Grille de Tirs :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pTir);

        gDef.setClicActive(false);
        JPanel pDef = gDef;
        pDef.setBorder(new TitledBorder(null, "Grille de Defense :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        getContentPane().add(pDef);

    }

//    public static void main(String[] args) {
//    	FenetreJoueur test = new FenetreJoueur();
//
//    }

}