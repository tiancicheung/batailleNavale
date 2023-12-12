package batailleNavale;


public class Coordonnee implements Comparable<Coordonnee> {
   // Attributes
   private int ligne;
   private int colonne;


   // Constructor
   public Coordonnee(int ligne, int colonne) {
       if (ligne < 0 || colonne < 0) {
               throw new IllegalArgumentException("Coordonée négative ne peut pas être acceptée ! );
       }
      this.ligne = ligne;
       this.colonne = colonne;
   }


   public Coordonnee(String s) {
       if (s.length() == 2 || s.length() == 3) { // quand la longueur de la coordonnée est de 2 ou 3 caractères
           s = s.toUpperCase(); // on convertit la coordonnée en majuscule
           char charColonne = s.charAt(0); // on récupère le premier caractère qui constitue la colonne
           int numLigne = Integer.parseInt(s.substring(1)); // ensuite on récupère le reste de la coordonnée qui constitue la ligne
           if (charColonne >= 'A' && charColonne <= 'Z' && numLigne >= 1 && numLigne <= 26) { // on vérifie que la coordonnée est valide
               ligne = numLigne - 1; // on affecte les valeurs à la ligne et à la colonne
               colonne = charColonne - 'A'; // on soustrait le code ASCII de 'A' pour avoir la valeur de la colonne
           } else {
               throw new IllegalArgumentException("Coordonnée invalide !"); // si la coordonnée n'est pas valide on lève une exception
           }
       } else {
           throw new IllegalArgumentException("La longueur de la coordonnée doit comprendre 2 caractères."); // si la longueur de la coordonnée n'est pas de 2 caractères on lève une exception
       }
   }


   // Methods
   public String toString() {
       char colonneChar = (char) (getColonne() + 'A'); // on ajoute le code ASCII de 'A' à la valeur de la colonne pour avoir le caractère correspondant
       return "" + colonneChar + (getLigne() + 1); // on retourne la coordonnée
   }


   public int getLigne() {
       return ligne; // on retourne la ligne
   }


   public int getColonne() {
       return colonne; // on retourne la colonne
   }


   public boolean equals(Object obj) {
       if (obj instanceof Coordonnee) { // on vérifie que l'objet est une coordonnée
           Coordonnee c = (Coordonnee) obj; // on cast l'objet en coordonnée
           return (this.ligne == c.getLigne() && this.colonne == c.getColonne()); // on compare les lignes et les colonnes
       } else {
           return false; // si l'objet n'est pas une coordonnée on retourne false
       }
   }


   public boolean voisine(Coordonnee c) {
       int diffLigne = this.ligne - c.getLigne();
       int diffColonne = this.colonne - c.getColonne();


       if ((diffLigne == 1 || diffLigne == -1) && diffColonne == 0) {
           return true; // voisin sur la même ligne
       } else if ((diffColonne == 1 || diffColonne == -1) && diffLigne == 0) {
           return true; // voisin sur la même colonne
       } else {
           return false; // sinon ce n'est pas un voisin
       }
   }
   public int compareTo(Coordonnee c) {


        if (c.getLigne() < 0 || c.getColonne() < 0) {
			   throw new IllegalArgumentException("La ligne ou la colonne ne peut pas être négative, ligne = " + c.getLigne() + " , colonne = " + c.getColonne());
		   }
       // compare les lignes
       if (this.ligne < c.getLigne()) {
           return -1; // this est plus petit que c
       } else if (this.ligne > c.getLigne()) {
           return 1; // this est plus grand que c
       } else {
           // les lignes sont égales, on compare les colonnes
           if (this.colonne < c.getColonne()) {
               return -1; // this est plus petit que c
           } else if (this.colonne > c.getColonne()) {
               return 1; // this est plus grand que c
           } else {
               return 0; // this est égal à c
           }
       }
   }
}
