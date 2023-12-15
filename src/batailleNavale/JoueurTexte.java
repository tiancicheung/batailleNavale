package batailleNavale;
import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille {

    private Scanner sc;

    public JoueurTexte(GrilleNavale g, String nom) {
        super(g, nom);
        this.sc = new Scanner(System.in);
    }
    public JoueurTexte(GrilleNavale g) {
        super(g);
        this.sc = new Scanner(System.in);
    }
    public void retourAttaque(Coordonnee c, int etat) {
        if (etat == Joueur.TOUCHE)
            System.out.println("Tir " + c+" : Vous avez touché un navire");
        else if (etat == Joueur.COULE)
            System.out.println("Tir " + c+" : Vous avez coulé un navire");
        else if (etat == Joueur.A_L_EAU)
            System.out.println("Tir " + c+" : Vous avez tiré dans l'eau");
        else if (etat == Joueur.GAMEOVER)
            System.out.println("Tir " + c+" : Gagné :) ");
    }


    public void retourDefense(Coordonnee c, int etat) {
        if (etat == Joueur.TOUCHE)
            System.out.println("Tir " + c+ " : Votre navire été touché par un tir");
        else if (etat == Joueur.COULE)
            System.out.println("Tir " + c+ " : Votre navire été coulé");
        else if (etat == Joueur.A_L_EAU)
            System.out.println("Tir " + c+" : Un tir par l'attaquant est tombé dans l'eau");
        else if (etat == Joueur.GAMEOVER)
            System.out.println("Tir " + c+" : Perdu :( ");


    }
    public Coordonnee choixAttaque() {
        System.out.println("Entrez grille : "+this.getGrille());
        System.out.println("Vous etes : " + getNom());
        System.out.println("Entrez les coordonnées de vous voulez attaquer : ");
        String coord = sc.nextLine();//lire les coordonnées
        Coordonnee c = new Coordonnee(coord);//créer un objet coordonnée
        return c;//retourner les coordonnées
    }
}
