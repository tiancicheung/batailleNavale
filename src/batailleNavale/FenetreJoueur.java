package batailleNavale;
public class FenetreJoueur extends JFrame {
    private JPanel contentPane;
    private GrilleGraphique grilleTirs;
    private GrilleNavaleGraphique grilleDefense;
    public FenetreJoueur() {
        this("Nom du joueur", 10);
    }
    public FenetreJoueur(String nom, int taille) {...}
    public GrilleGraphique getGrilleTirs() {...}
    public GrilleNavaleGraphique getGrilleDefense() {...}
}