package batailleNavale;

import java.awt.EventQueue;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
//import java.util.Arrays;
import java.util.Random;


public class BatailleNavale {

    private JFrame frameBatailleNavale;
    private JTextField fdTailleGrille;
    private JTextField txtfdNomJ1;
    private JTextField txtfdNomJ2;

    private Joueur joueur1, joueur2;
    private JRadioButton rabGraphJ1, rabTextJ1, rabAutoJ1;
    private JRadioButton rabGraphJ2, rabTextJ2, rabAutoJ2;
    private int tailleGrille;

    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final ButtonGroup buttonGroup_1 = new ButtonGroup();

    /**
     * Launch the application.
     */


    private void demarrerPartie() {
        //joueur1.jouerAvec(joueur2);

        String nomJoueurUn = txtfdNomJ1.getText();
        String nomJoueurDeux = txtfdNomJ2.getText();
        Random commencePremierJ1 = new Random();

        if (rabGraphJ1.isSelected()) {
            JOptionPane.showMessageDialog(null, nomJoueurUn + " commence en premier.", "Premier joueur", JOptionPane.INFORMATION_MESSAGE);
            joueur1.jouerAvec(joueur2);
        } else if (rabGraphJ2.isSelected()) {
            JOptionPane.showMessageDialog(null, nomJoueurDeux + " commence en premier.", "Premier joueur", JOptionPane.INFORMATION_MESSAGE);
            joueur2.jouerAvec(joueur1);
        } else {
            if ((commencePremierJ1.nextBoolean())) {
                JOptionPane.showMessageDialog(null, nomJoueurUn + " commence en premier.", "Premier joueur", JOptionPane.INFORMATION_MESSAGE);
                joueur1.jouerAvec(joueur2);
            } else {
                JOptionPane.showMessageDialog(null, nomJoueurDeux + " commence en premier.", "Premier joueur", JOptionPane.INFORMATION_MESSAGE);
                joueur2.jouerAvec(joueur1);
            }
        }


    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BatailleNavale window = new BatailleNavale();
                    window.frameBatailleNavale.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public BatailleNavale() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frameBatailleNavale = new JFrame();
        frameBatailleNavale.setTitle("Bataille Navale");
        frameBatailleNavale.setBounds(200, 200, 550, 400);
        frameBatailleNavale.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panneauHaut = new JPanel();
        frameBatailleNavale.getContentPane().add(panneauHaut, BorderLayout.NORTH);
        panneauHaut.setLayout(new BorderLayout(0, 0));

        JPanel panneauNord = new JPanel();
        panneauNord.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panneauHaut.add(panneauNord, BorderLayout.NORTH);
        panneauNord.setLayout(new BorderLayout(0, 0));

        JPanel reglage = new JPanel();
        panneauNord.add(reglage, BorderLayout.NORTH);
        reglage.setLayout(new GridLayout(2, 0, 0, 0));

        JPanel reglageGrille = new JPanel();
        reglage.add(reglageGrille);
        reglageGrille.setLayout(new BoxLayout(reglageGrille, BoxLayout.X_AXIS));

        JLabel lbTailleGrille = new JLabel("Taille de Grille :");
        reglageGrille.add(lbTailleGrille);

        fdTailleGrille = new JTextField();
        fdTailleGrille.setText("10");
        reglageGrille.add(fdTailleGrille);
        fdTailleGrille.setColumns(10);

        JPanel panneauCentre = new JPanel();
        panneauHaut.add(panneauCentre, BorderLayout.CENTER);
        panneauCentre.setLayout(new GridLayout(2, 1, 0, 0));

        JPanel panneauJ1 = new JPanel();
        panneauCentre.add(panneauJ1);
        panneauJ1.setLayout(new GridLayout(4, 0, 0, 0));

        JPanel nomJ1 = new JPanel();
        panneauJ1.add(nomJ1);
        nomJ1.setLayout(new BoxLayout(nomJ1, BoxLayout.X_AXIS));

        JLabel lbJ1Nom = new JLabel("Nom :");
        nomJ1.add(lbJ1Nom);

        txtfdNomJ1 = new JTextField();
        txtfdNomJ1.setText("Joueur 1");
        nomJ1.add(txtfdNomJ1);
        txtfdNomJ1.setColumns(10);

        rabGraphJ1 = new JRadioButton("Joueur Graphique");
        buttonGroup.add(rabGraphJ1);
        rabGraphJ1.setSelected(true);
        panneauJ1.add(rabGraphJ1);

        rabTextJ1 = new JRadioButton("Joueur Texte");
        buttonGroup.add(rabTextJ1);
        panneauJ1.add(rabTextJ1);

        rabAutoJ1 = new JRadioButton("Joueur Auto");
        buttonGroup.add(rabAutoJ1);
        panneauJ1.add(rabAutoJ1);

        JPanel panneauJ2 = new JPanel();
        panneauCentre.add(panneauJ2);
        panneauJ2.setLayout(new GridLayout(4, 0, 0, 0));

        JPanel nomJ2 = new JPanel();
        panneauJ2.add(nomJ2);
        nomJ2.setLayout(new BoxLayout(nomJ2, BoxLayout.X_AXIS));

        JLabel lbJ2Nom = new JLabel("Nom :");
        nomJ2.add(lbJ2Nom);

        txtfdNomJ2 = new JTextField();
        txtfdNomJ2.setText("Joueur 2");
        nomJ2.add(txtfdNomJ2);
        txtfdNomJ2.setColumns(10);

        rabGraphJ2 = new JRadioButton("Joueur Graphique");
        buttonGroup_1.add(rabGraphJ2);
        rabGraphJ2.setSelected(true);
        panneauJ2.add(rabGraphJ2);

        rabTextJ2 = new JRadioButton("Joueur Texte");
        buttonGroup_1.add(rabTextJ2);
        panneauJ2.add(rabTextJ2);

        rabAutoJ2 = new JRadioButton("Joueur Auto");
        buttonGroup_1.add(rabAutoJ2);
        panneauJ2.add(rabAutoJ2);

        JPanel PanneauSud = new JPanel();
        panneauHaut.add(PanneauSud, BorderLayout.SOUTH);

        JButton btnDemarrer = new JButton("Demarrer le jeu");
        PanneauSud.add(btnDemarrer);
        btnDemarrer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (fdTailleGrille.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "La taille ne peut pas être égale à zero", "Taille vide",
                            JOptionPane.ERROR_MESSAGE);
                }

                tailleGrille = Integer.parseInt(fdTailleGrille.getText());
                String nomJoueurUn = txtfdNomJ1.getText();
                String nomJoueurDeux = txtfdNomJ2.getText();

//				int[] navires = creerNavires(tailleGrille);
//
//				if (navires[0] == 0) {
//					return;
//				}

                if (rabGraphJ1.isSelected()) {


                    GrilleNavaleGraphique gng1 = new GrilleNavaleGraphique(tailleGrille);
                    GrilleGraphique gg1 = new GrilleGraphique(tailleGrille);

                    // placement auto des bateaux en fonction de la taille
                    int[] liste = gng1.ListeNavires();
                    gng1.placementAuto(liste);

                    joueur1 = new JoueurGraphique(gng1, gg1, nomJoueurUn);

                    FenetreJoueur fenetreJoueur1 = new FenetreJoueur(nomJoueurUn, gg1,gng1.getGrilleGraphique());
                    fenetreJoueur1.pack();
                    fenetreJoueur1.setVisible(true);
                    System.out.println(gng1);

                }

                if (rabGraphJ2.isSelected()) {


                    GrilleNavaleGraphique gng2 = new GrilleNavaleGraphique(tailleGrille);
                    GrilleGraphique gg2 = new GrilleGraphique(tailleGrille);

                    // placement auto des bateaux en fonction de la taille
                    int[] liste = gng2.ListeNavires();
                    gng2.placementAuto(liste);

                    joueur2 = new JoueurGraphique(gng2, gg2, nomJoueurDeux);


                    FenetreJoueur fenetreJoueur2 = new FenetreJoueur(nomJoueurDeux, gg2,gng2.getGrilleGraphique());
                    fenetreJoueur2.pack();
                    fenetreJoueur2.setVisible(true);
                    System.out.println(gng2);
                }

                // A completer
                if (rabTextJ1.isSelected()) {
                    int[] naviresJtexte = {2, 2, 3, 3, 4};
                    GrilleNavale grilleNavaleJ1 = new GrilleNavale(tailleGrille, naviresJtexte);
                    joueur1 = new JoueurTexte(grilleNavaleJ1, nomJoueurUn);
                    System.out.println(nomJoueurUn);
                    System.out.println(grilleNavaleJ1);
                }

                if (rabTextJ2.isSelected()) {
                    int[] naviresJ2texte = {2, 2, 3, 3, 4};
                    GrilleNavale grilleNavaleJ2 = new GrilleNavale(tailleGrille, naviresJ2texte);
                    joueur2 = new JoueurTexte(grilleNavaleJ2, nomJoueurDeux);
                    System.out.println(nomJoueurDeux);
                    System.out.println(grilleNavaleJ2);
                }
                if (rabAutoJ1.isSelected() && rabAutoJ2.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Au moins un joueur humain doit être dans la partie",
                            "IA vs IA", JOptionPane.ERROR_MESSAGE);
                    throw new IllegalArgumentException("Il faut au moins un Joueur Texte (Humain)");
                }

                if (rabAutoJ1.isSelected() && !rabAutoJ2.isSelected()) {
                    int[] naviresJauto = {2, 2, 3, 3, 4};
                    GrilleNavale grilleNavaleJauto = new GrilleNavale(tailleGrille, naviresJauto);
                    joueur1 = new JoueurAuto(grilleNavaleJauto, nomJoueurUn);
                    System.out.println(nomJoueurUn);
                    System.out.println(grilleNavaleJauto);
                }

                if (!rabAutoJ1.isSelected() && rabAutoJ2.isSelected()) {
                    int[] naviresJ2auto = {2, 2, 3, 3, 4};
                    GrilleNavale grilleNavaleJ2 = new GrilleNavale(tailleGrille, naviresJ2auto);
                    joueur2 = new JoueurAuto(grilleNavaleJ2, nomJoueurDeux);
                    System.out.println(nomJoueurDeux);
                    System.out.println(grilleNavaleJ2);
                }

                new Thread() {
                    public void run() {
                        demarrerPartie();
                    }
                }.start();


            }
        });
        PanneauSud.add(btnDemarrer);

    }


}
