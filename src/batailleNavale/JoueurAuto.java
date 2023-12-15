package batailleNavale;

public class JoueurAuto extends JoueurAvecGrille {
    protected int nbTirs;
    protected Coordonnee[] dejaTirs ;

    protected Coordonnee[] resteATirer;

    protected int nbResteATirer;
    protected GrilleNavale g;




    public JoueurAuto(GrilleNavale g, String nom) {
        super(g, nom);
        this.g = g;
        nbTirs = 0;
        dejaTirs = new Coordonnee[g.getTaille()*g.getTaille()];
        nbResteATirer = g.getTaille()*g.getTaille();
        resteATirer = new Coordonnee[nbResteATirer];
    }
    public JoueurAuto(GrilleNavale g) {
        super(g);
        this.g = g;
        nbTirs = 0;
        dejaTirs = new Coordonnee[g.getTaille()*g.getTaille()];
        nbResteATirer = g.getTaille()*g.getTaille();
        resteATirer = new Coordonnee[nbResteATirer];
    }

    protected void retourAttaque(Coordonnee c, int etat) {
        if (etat == Joueur.TOUCHE)
            System.out.println("Tir " + c+" IA attaque : Vous avez touché un navire");
        else if (etat == Joueur.COULE)
            System.out.println("Tir " + c+" IA attaque : Vous avez coulé un navire");
        else if (etat == Joueur.A_L_EAU)
            System.out.println("Tir " + c+" IA attaque : Vous avez tiré dans l'eau");
        else if (etat == Joueur.GAMEOVER)
            System.out.println("Tir " + c+" : l'IA a gagné :) ");

    }
    protected void retourDefense(Coordonnee c, int etat) {
        if (etat == Joueur.TOUCHE)
            System.out.println("Tir " + c+ " : Un navire de l'IA a été touché");
        else if (etat == Joueur.COULE)
            System.out.println("Tir " + c+ " : Un navire de l'IA a été coulé");
        else if (etat == Joueur.A_L_EAU)
            System.out.println("Tir " + c+" IA : Un tir par l'attaquant est tombé dans l'eau");
        else if (etat == Joueur.GAMEOVER)
            System.out.println("Tir " + c+" : l'IA a perdu :( ");
    }


    public Coordonnee choixAttaque() {
        if (nbTirs < g.getTaille() * g.getTaille()) {
            Coordonnee coordonnee;

            // L'IA choisit une coordonnee aleatoire si l'indice contient la coordonnee
            do {
                coordonnee = new Coordonnee((int) (Math.random() * g.getTaille()), (int) (Math.random() * g.getTaille()));
            } while (containsCoordonnee(dejaTirs, nbTirs, coordonnee));

            nbTirs++;
            dejaTirs[nbTirs - 1] = coordonnee; // On stocke la coordonnee
            return coordonnee;
        } else {
            return null; // toutes les cases ont déja été touchées
        }
    }

    // On test si l'indice contient une cible valide
    private boolean containsCoordonnee(Coordonnee[] array, int length, Coordonnee target) {
        for (int i = 0; i < length; i++) {
            if (array[i] != null && array[i].equals(target)) {
                return true;
            }
        }
        return false;
    }


}
