package batailleNavale;
import javax.swing.*;

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
    private JButton button1;

    private void demarrerPartie() {


        new Thread() {
            public void run() {
                joueur1.jouerAvec(joueur2);

            }
        }.start();
    }
}
