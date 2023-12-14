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
            System.out.println("Tir " + c+" de l'attaquant : Vous avez touché un navire");
        else if (etat == Joueur.COULE)
            System.out.println("Tir " + c+" de l'attaquant : Vous avez coulé un navire");
        else if (etat == Joueur.A_L_EAU)
            System.out.println("Tir " + c+" de l'attaquant : Vous avez tiré dans l'eau");
        else if (etat == Joueur.GAMEOVER)
            System.out.println("Tir " + c+" de l'attaquant : Gagné 🙂 ");
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
    public Coordonnee choixAttaque() {
        System.out.println("Entrez grille : "+this.getGrille());
        System.out.println("Vous etes : " + getNom());
        System.out.println("Entrez les coordonnées de vous voulez attaquer : ");
        String coord = sc.nextLine();//lire les coordonnées
        Coordonnee c = new Coordonnee(coord);//créer un objet coordonnée
        return c;//retourner les coordonnées
    }
}
