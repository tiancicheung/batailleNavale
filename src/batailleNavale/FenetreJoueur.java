package batailleNavale;

import javax.swing.*;
import java.awt.*;
public class FenetreJoueur extends JFrame {
    private JPanel contentPane;
    private GrilleGraphique grilleTirs;
    private GrilleGraphique grilleDefense;
    public FenetreJoueur() {
        this("Nom du joueur", 10);
    }
    public FenetreJoueur(String nom, int taille) {
        super(nom);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);
        this.contentPane = new JPanel();
        this.contentPane.setLayout(new GridLayout(1, 2));
        this.grilleTirs = new GrilleGraphique(taille);
        this.grilleDefense = new GrilleGraphique(taille);
        this.contentPane.add(this.grilleTirs);
        this.contentPane.add(this.grilleDefense);
        this.setContentPane(this.contentPane);

    }
    public GrilleGraphique getGrilleTirs() {
        return this.grilleTirs;
    }
    public GrilleGraphique getGrilleDefense() {
        return this.grilleDefense;
    }
}