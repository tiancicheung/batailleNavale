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
    private JRadioButton J1GraphiqueRbtn;
    private JRadioButton J1texteRbtn;
    private JRadioButton J1autoRbtn;
    private JTextField textField1;
    private JRadioButton J2graphiqueRbtn;
    private JRadioButton J2texteRbtn;
    private JRadioButton J2autoRbtn;
    private JButton lancerbtn;

    public BatailleNavale() {
        lancerbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (J1GraphiqueRbtn.isSelected()) {
                    joueur1 = new JoueurGraphique(new GrilleNavaleGraphique(tailleGrille), new GrilleGraphique(tailleGrille), nomtext.getText());
                } else if (J1texteRbtn.isSelected()) {
                    joueur1 = new JoueurTexte(new GrilleNavaleTexte(tailleGrille), nomtext.getText());
                } else if (J1autoRbtn.isSelected()) {
                    joueur1 = new JoueurAuto(new GrilleNavaleAuto(tailleGrille), nomtext.getText());
                }
                if (J2graphiqueRbtn.isSelected()) {
                    joueur2 = new JoueurGraphique(new GrilleNavaleGraphique(tailleGrille), new GrilleGraphique(tailleGrille), textField1.getText());
                } else if (J2texteRbtn.isSelected()) {
                    joueur2 = new JoueurTexte(new GrilleNavaleTexte(tailleGrille), textField1.getText());
                } else if (J2autoRbtn.isSelected()) {
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
