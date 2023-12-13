package batailleNavale;
import java.util.Scanner;
public abstract class JoueurTexte extends JoueurAvecGrille {
    private Scanner sc;
    public JoueurTexte(GrilleNavale g, String nom) {
        super(g, nom);//inicialiser le nom
        this.sc = new Scanner(System.in);//inicialiser le scanner
    }


    public JoueurTexte(GrilleNavale g) {
        super(g);
        this.sc = new Scanner(System.in);
    }
    public void retourAttaque(Coordonnee c, int etat) {
        if (etat == 0) {
            System.out.println("Raté");//raté
        } else if (etat == 1) {
            System.out.println("Touché");//touché
        } else if (etat == 2) {
            System.out.println("Touché Coulé");//touché coulé
        }
    }
    public void retourDefense(Coordonnee c, int etat) {
        if (etat == 0) {
            System.out.println("L'adversaire a raté");//raté
        } else if (etat == 1) {
            System.out.println("L'adversaire a touché en " + c);//touché
        } else if (etat == 2) {
            System.out.println("L'adversaire a coulé votre navire en " + c);//touché coulé
        }


    }
    public Coordonnee choixAttaque() {
        System.out.println("Entrez les coordonnées de votre attaque :");//choisir l'attaque
        String coord = sc.nextLine();//lire les coordonnées
        Coordonnee c = new Coordonnee(coord);//créer un objet coordonnée
        return c;//retourner les coordonnées
    }
}
