import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


public class ArbreBicolore<E> extends AbstractCollection<E> {
  Noeud racine;
  private int taille;
  private Comparator<? super E> cmp;
  Noeud sentinelle;

  class Noeud {
    E cle;
    Noeud gauche;
    Noeud droit;
    Noeud pere;
    char couleur;

    private Noeud(E cle) {
      this.cle = cle;
      gauche = null;
      droit = null;
      pere = null;
      couleur = 'R';
    }

    Noeud minimum() {
      Noeud courrant = this;
      while (courrant.gauche != sentinelle) {
        courrant = courrant.gauche;
      }
      return courrant;
    }

    Noeud minimum(Noeud courrant) {
      while (courrant.gauche != sentinelle) {
        courrant = courrant.gauche;
      }
      return courrant;
    }

    Noeud suivant() {
      Noeud courrant = this;
      if (courrant.droit != sentinelle) {
        return courrant.droit.minimum();
      }
      Noeud pereCourrant = courrant.pere;
      while (pereCourrant != sentinelle && courrant == pereCourrant.droit) {
        courrant = pereCourrant;
        pereCourrant = pereCourrant.pere;
      }
      return pereCourrant;
    }
  }

  public Noeud sentinelle() {
    Noeud sentinelle = new Noeud(null);
    sentinelle.gauche = null;
    sentinelle.droit = null;
    sentinelle.couleur = 'N';
    return sentinelle;
  }

  public ArbreBicolore() {
    sentinelle = sentinelle();
    racine = sentinelle;
    taille = 0;
    cmp = (e1, e2) -> ((Comparable) e1).compareTo(e2);
  }

  public ArbreBicolore(Comparator<? super E> cmp) {
    sentinelle = sentinelle();
    racine = sentinelle;
    taille = 0;
    this.cmp = cmp;
  }

  public ArbreBicolore(Collection<? extends E> c) {
    this();
    Iterator<? extends E> it = c.iterator();
    while (it.hasNext()) {
      E noeudToAppend = (E) it.next();
      this.add(noeudToAppend);
    }
  }

  @SuppressWarnings("unused")
  private class ARNIterator implements Iterator<E> {
    Noeud courrant;

    ARNIterator() {
      Noeud courant = racine;
    }

    public boolean hasNext() {
		Noeud tampon = courrant;
		boolean test = tampon.suivant() != sentinelle;
		return test;
    }

    public E next() {
      E tmp;
      if (courrant == sentinelle) {
        return null;
      }
      tmp = courrant.cle;
      this.courrant.suivant();
      return tmp;
    }
    /*
     * public void remove() { this.courrant =
     * ArbreBicolore.this.supprimer(courrant); }
     */
  }

  @Override
  public Iterator<E> iterator() {
    return new ARNIterator();
  }

  @Override
  public boolean add(E key) {
	  Noeud noeudToAppend = new Noeud(key);
	  Noeud tampon = sentinelle;
	  Noeud courrant = racine;

	  while(courrant != sentinelle) {
	    tampon = courrant;
	    if(cmp.compare(noeudToAppend.cle, courrant.cle) < 0)
	      courrant = courrant.gauche;
	    else
	      courrant = courrant.droit;
	  }
	  noeudToAppend.pere = tampon;
	  if(tampon == sentinelle) {
	    racine = noeudToAppend;
	  }
	  else if(cmp.compare(noeudToAppend.cle, tampon.cle) < 0) //data of child is less than its parent, left child
	    tampon.gauche = noeudToAppend;
	  else
	    tampon.droit = noeudToAppend;
	  noeudToAppend.droit = sentinelle;
	  noeudToAppend.gauche = sentinelle;
	  ajouterCorrection(noeudToAppend);
	  return true;
	}


  private void ajouterCorrection(Noeud noeudToAppend) {
      Noeud tampon;
      while (noeudToAppend.pere.couleur == 'R') {
          if (noeudToAppend.pere.equals(noeudToAppend.pere.pere.gauche)) {
              tampon = noeudToAppend.pere.pere.droit; // l'oncle de noeudToAppend
              if (tampon.couleur == 'R') {
                  // cas 1
                  noeudToAppend.pere.couleur = 'N';
                  tampon.couleur = 'N';
                  noeudToAppend.pere.pere.couleur = 'R';
                  noeudToAppend = noeudToAppend.pere.pere;
              } else {
                  if (noeudToAppend.equals(noeudToAppend.pere.droit)) {
                      // cas 2
                      noeudToAppend = noeudToAppend.pere;
                      rotationGauche(noeudToAppend);
                  }
                  // cas 3
                  noeudToAppend.pere.couleur = 'N';
                  noeudToAppend.pere.pere.couleur = 'R';
                  rotationDroite(noeudToAppend.pere.pere);
              }
          } else {
              tampon = noeudToAppend.pere.pere.gauche; // l'oncle de noeudToAppend
              if (tampon.couleur == 'R') {
                  // cas 1
                  noeudToAppend.pere.couleur = 'N';
                  tampon.couleur = 'N';
                  noeudToAppend.pere.pere.couleur = 'R';
                  noeudToAppend = noeudToAppend.pere.pere;
              } else {
                  if (noeudToAppend.equals(noeudToAppend.pere.pere)) {
                      // cas 2
                      noeudToAppend = noeudToAppend.pere;
                      rotationDroite(noeudToAppend);
                  }
                  // cas 3
                  noeudToAppend.pere.couleur = 'N';
                  noeudToAppend.pere.pere.couleur = 'R';
                  rotationGauche(noeudToAppend.pere.pere);
              }
          }
      }
      racine.couleur = 'N';
  }
  
  

  private void rotationGauche(Noeud courrant) {
    Noeud tampon = courrant.droit;
    courrant.droit = tampon.gauche;
    if (tampon.gauche != sentinelle) {
      tampon.gauche.pere = courrant;
    }
    tampon.pere = courrant.pere;

    if (courrant.pere == sentinelle) {
      this.racine = tampon;
    } else if (courrant == courrant.pere.gauche) {
      courrant.pere.gauche = tampon;
    } else {
      courrant.pere.droit = tampon;
    }
    tampon.gauche = courrant;
    courrant.pere = tampon;
  }

  private void rotationDroite(Noeud courrant) {
    Noeud tampon = courrant.gauche;
    courrant.gauche = tampon.droit;
    if (tampon.droit != sentinelle) {
      tampon.droit.pere = courrant;
    }
    tampon.pere = courrant.pere;
    if (courrant.pere == sentinelle) {
      this.racine = tampon;
    } else if (courrant == courrant.pere.droit) {
      courrant.pere.droit = tampon;
    } else {
      // avant : else courrant.pere.droit = tampon;
      courrant.pere.gauche = tampon;
    }
    tampon.droit = courrant;
    courrant.pere = tampon;
  }

  public void supprimerCorrection(Noeud courrant) {
    while (courrant != racine && courrant.couleur == 'N') {
      // (*) est vérifié ici
      if (courrant == courrant.pere.gauche) {
        Noeud brother = courrant.pere.droit; // le frère de courrant
        if (brother.couleur == 'R') {
          // cas 1
          brother.couleur = 'N';
          courrant.pere.couleur = 'R';
          rotationGauche(courrant.pere);
          brother = courrant.pere.droit;
        }
        if (brother.gauche.couleur == 'N' && brother.droit.couleur == 'N') {
          // cas 2
          brother.couleur = 'R';
          courrant = courrant.pere;
        } else {
          if (brother.droit.couleur == 'N') {
            // cas 3
            brother.gauche.couleur = 'N';
            brother.couleur = 'R';
            rotationDroite(brother);
            brother = courrant.pere.droit;
          }
          // cas 4
          brother.couleur = courrant.pere.couleur;
          courrant.pere.couleur = 'N';
          brother.droit.couleur = 'N';
          rotationGauche(courrant.pere);
          courrant = racine;
        }
      } else {
        // idem en miroir, gauche <-> droite
        // cas 1', 2', 3', 4'
        Noeud brother = courrant.pere.gauche; // le frère de courrant
        if (brother.couleur == 'R') {
          // cas 1
          brother.couleur = 'N';
          courrant.pere.couleur = 'R';
          rotationGauche(courrant.pere);
          brother = courrant.pere.gauche;
        }
        if (brother.droit.couleur == 'N' && brother.gauche.couleur == 'N') {
          // cas 2
          brother.couleur = 'R';
          courrant = courrant.pere;
        } else {
          if (brother.gauche.couleur == 'N') {
            // cas 3
            brother.droit.couleur = 'N';
            brother.couleur = 'R';
            rotationGauche(brother);
            brother = courrant.pere.gauche;
          }
          // cas 4
          brother.couleur = courrant.pere.couleur;
          courrant.pere.couleur = 'N';
          brother.droit.couleur = 'N';
          rotationDroite(courrant.pere);
          courrant = racine;
        }
      }
    }
    // (**) est vérifié ici
    courrant.couleur = 'N';
  }

  @Override
  public boolean remove(Object key) {
    Noeud noeudToRemove = rechercher(key);
    if(noeudToRemove == sentinelle) {
      return false;}
    if(noeudToRemove == racine && racine.gauche == sentinelle && racine.droit == sentinelle) { //cas 0
      racine = sentinelle;
      return true;
    }
    Noeud tampon;
    Noeud courrant;
    if (noeudToRemove.gauche == sentinelle || noeudToRemove.droit == sentinelle) {
      tampon = noeudToRemove;
    } else {
      tampon = noeudToRemove.suivant();
    }
    // tampon est le nud à détacher
    if (tampon.gauche != sentinelle) {
      courrant = tampon.gauche;
    } else {
      courrant = tampon.droit;
    }
    // courrant est le fils unique de tampon ou la sentinelle si y n'a pas de fils
    courrant.pere = tampon.pere; // inconditionnelle
    if (tampon.pere == sentinelle) { // suppression de la racine
      racine = courrant;
    } else {
      if (tampon == tampon.pere.gauche) {
        tampon.pere.gauche = courrant;
      } else {
        tampon.pere.droit = courrant;
      }
    }

    if (tampon != noeudToRemove) {
      noeudToRemove.cle = tampon.cle;
    }
    if (tampon.couleur == 'N') {
      supprimerCorrection(courrant);
    }
    taille--;
    return true;
  }

  @SuppressWarnings("unchecked")
  public Noeud rechercher(Object o) {
    Noeud courrant = racine;
    while (courrant != sentinelle && courrant.cle != (E) o) {
      courrant = cmp.compare(courrant.cle, (E) o) > 0 ? courrant.gauche : courrant.droit;
    }
    return courrant;
  }

  @Override
  public int size() {
    return taille;
  }

  @Override
  public String toString() {
    StringBuffer buf = new StringBuffer();
    toString(racine, buf, "", maxStrLen(racine));
    return buf.toString();
  }

  private void toString(Noeud courrant, StringBuffer buf, String path, int len) {
    if (courrant == sentinelle)
      return;
    toString(courrant.droit, buf, path + "D", len);
    for (int i = 0; i < path.length(); i++) {
      for (int j = 0; j < len + 6; j++)
        buf.append(' ');
      char c = ' ';
      if (i == path.length() - 1)
        c = '+';
      else if (path.charAt(i) != path.charAt(i + 1))
        c = '|';
      buf.append(c);
    }
    if (courrant.couleur == 'R') {
      buf.append("-- " + "\u001B[31m" + courrant.cle.toString() + "\u001B[0m");
    } else {
      buf.append("-- " + courrant.cle.toString());
    }
    if (courrant.gauche != sentinelle || courrant.droit != sentinelle) {
      buf.append(" --");
      for (int j = courrant.cle.toString().length(); j < len; j++)
        buf.append('-');
      buf.append('|');
    }
    buf.append("\n");
    toString(courrant.gauche, buf, path + "G", len);
  }

  private int maxStrLen(Noeud courrant) {
    return courrant == sentinelle || courrant == sentinelle ? 0: Math.max(courrant.cle.toString().length(),
            Math.max(maxStrLen(courrant.gauche), maxStrLen(courrant.droit)));
  }
}