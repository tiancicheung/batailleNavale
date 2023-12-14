package batailleNavale;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BatailleNavale {
    private Joueur joueur1, joueur2;
    private int tailleGrille;
    private JTextField grilletext;
    private JLabel Joueur1;
    private JTextField nomtext;
    private JRadioButton joueurGraphiqueRadioButton;
    private JRadioButton joueurTexteRadioButton;
    private JRadioButton joueurAutoRadioButton;
    private JTextField textField1;
    private JRadioButton joueurGraphiqueRadioButton1;
    private JRadioButton joueurTexteRadioButton1;
    private JRadioButton joueurAutoRadioButton1;
    private JButton lancerbtn;

    public BatailleNavale() {
        lancerbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (joueurGraphiqueRadioButton.isSelected()) {
                    joueur1 = new JoueurGraphique(new GrilleNavaleGraphique(tailleGrille), new GrilleGraphique(tailleGrille), nomtext.getText());
                } else if (joueurTexteRadioButton.isSelected()) {
                    joueur1 = new JoueurTexte(new GrilleNavaleTexte(tailleGrille), nomtext.getText());
                } else if (joueurAutoRadioButton.isSelected()) {
                    joueur1 = new JoueurAuto(new GrilleNavaleAuto(tailleGrille), nomtext.getText());
                }
                if (joueurGraphiqueRadioButton1.isSelected()) {
                    joueur2 = new JoueurGraphique(new GrilleNavaleGraphique(tailleGrille), new GrilleGraphique(tailleGrille), textField1.getText());
                } else if (joueurTexteRadioButton1.isSelected()) {
                    joueur2 = new JoueurTexte(new GrilleNavaleTexte(tailleGrille), textField1.getText());
                } else if (joueurAutoRadioButton1.isSelected()) {
                    joueur2 = new JoueurAuto(new GrilleNavaleAuto(tailleGrille), textField1.getText());
                }
                demarrerPartie();
            }
        });
    }

    private void demarrerPartie() {


        new Thread() {
            public void start(JButton lancerbtn) {
            }

            public void run() {
                joueur1.jouerAvec(joueur2);

            }
        }.start(lancerbtn);
    }
}
