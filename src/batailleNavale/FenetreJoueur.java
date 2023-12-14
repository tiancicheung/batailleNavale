package batailleNavale;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.color.*;


    public class FenetreJoueur extends JFrame {
        private JPanel contentPane;
        private GrilleGraphique grilleTirs;
        private GrilleGraphique grilleDefense;
        private AbstractButton resetButton;

        public FenetreJoueur() {
            this("Nom du joueur", 10);
            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    grilleTirs.reset();
                    grilleDefense.reset();
                }
            });
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
            this.setVisible(true);
            this.contentPane.add(this.grilleTirs);
            this.contentPane.add(this.grilleDefense);
            this.setContentPane(this.contentPane);
        }

        public GrilleGraphique getGrilleTirs() {
            return grilleTirs;

        }

        public GrilleGraphique getGrilleDefense() {
            return grilleDefense;
        }
    }