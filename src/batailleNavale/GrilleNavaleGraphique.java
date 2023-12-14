package batailleNavale;

import java.awt.Color; // Pour pouvoir utiliser Color.GREEN, Color.BLUE, Color.RED
import javax.swing.JFrame;

public class GrilleNavaleGraphique extends GrilleNavale {

    private GrilleGraphique grille;

    //  Constructeurs :
    public GrilleNavaleGraphique(int taille) {
        // On prend 4 comme valeur de défaut pour nbNavires, car sinon on ne peut pas utiliser super(taille) tout seul
        // nbNavires = 4, du coup à changer plus tard ?
        super(taille, 4);
        this.grille = new GrilleGraphique(taille);
    }

    public GrilleGraphique getGrilleGraphique() {
        return grille;
    }

    //  Methodes :
    public boolean ajouteNavire(Navire n) {
        boolean ajouterNavire = super.ajouteNavire(n);

        if (ajouterNavire) {
            // Colorier les cases du navire en vert (Color.GREEN)
            Coordonnee debut = n.getDebut();
            Coordonnee fin = n.getFin();
            grille.colorie(debut, fin, Color.GREEN);
        }

        return ajouterNavire;
    }

    public boolean recoitTir(Coordonnee c) {
        boolean tirRecu = super.recoitTir(c);

        if (tirRecu) {
            // Colorie la case en rouge (Color.RED) si le tir touche un navire
            grille.colorie(c, Color.RED);
        } else {
            // Colorie la case en bleu (Color.BLUE) si le tir touche l'eau
            grille.colorie(c, Color.BLUE);
        }
        return tirRecu;
    }

    //  Test :
    public static void main(String[] args) {
        // Créez une grille navale graphique de taille 10
        GrilleNavaleGraphique grilleNavaleGraphique = new GrilleNavaleGraphique(10);

        // Ajoutez quelques navires à la grille
        Navire navire1 = new Navire(new Coordonnee(0, 0), 3, true);
        Navire navire2 = new Navire(new Coordonnee(5, 5), 4, false);

        grilleNavaleGraphique.ajouteNavire(navire1);
        grilleNavaleGraphique.ajouteNavire(navire2);

        // Affichez la grille graphique
        GrilleGraphique grilleGraphique = grilleNavaleGraphique.getGrilleGraphique();
        grilleGraphique.setClicActive(false); // Désactivez les clics pour l'instant
        JFrame frame = new JFrame("Test GrilleNavaleGraphique");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(grilleGraphique);
        frame.pack();
        frame.setVisible(true);

        // Attendez un moment pour que la fenêtre soit visible (peut nécessiter des ajustements)
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Réactivez les clics
        grilleGraphique.setClicActive(true);

        // Effectuez des tirs (vous devrez cliquer sur la grille dans la fenêtre)
        for (int i = 0; i < 5; i++) {
            Coordonnee tir = grilleGraphique.getCoordonneeSelectionnee();
            grilleNavaleGraphique.recoitTir(tir);
            // Vous pouvez également ajouter des pauses entre les tirs pour mieux voir les changements
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Fermez la fenêtre après le test
        frame.dispose();
    }

}

