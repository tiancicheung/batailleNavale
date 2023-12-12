package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {
  // Attributs :
  private GrilleNavale grille;

  // Constructeurs :
  public JoueurAvecGrille(GrilleNavale g, String nom) {
      super(nom);
      this.grille = g;
  }
  public JoueurAvecGrille(GrilleNavale g) {
      super();
      this.grille = g;
  }
  public void defendre(Coordonnee c) {
      //TO-DO
  }
  public boolean perd() {
      return false;
  }
}
