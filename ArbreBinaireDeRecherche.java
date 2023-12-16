import java.util.AbstractCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;


public class ArbreBinaireDeRecherche<E> extends AbstractCollection<E> {
  Noeud racine;
  private int taille;
  private Comparator<? super E> cmp;

  class Noeud {
    E cle;
    Noeud gauche;
    Noeud droit;
    Noeud pere;
    
    private Noeud() {
    	cle = null;
    	gauche = null;
    	droit = null;
    	pere = null;
    }
    
    private Noeud(E cle) {
      this.cle = cle;
      gauche = null;
      droit = null;
      pere = null;

    }
 
    
    Noeud minimum() {
      Noeud courrant = this;
      while (courrant.gauche != null) {
        courrant = courrant.gauche;
      }
      return courrant;
    }

    Noeud suivant() {
      Noeud courrant = this;
      if (courrant.droit != null) {
        return courrant.droit.minimum();
      }
      Noeud pereCourrant = courrant.pere;
      while (pereCourrant != null && courrant == pereCourrant.droit) {
        courrant = pereCourrant;
        pereCourrant = pereCourrant.pere;
      }
      return pereCourrant;
    }

    boolean isFeuille() {
      return gauche == null && droit == null;
    }

  }
  
  @SuppressWarnings({ "unchecked", "rawtypes" })
  public ArbreBinaireDeRecherche() {
    racine = null;
    taille = 0;
    cmp = (e1, e2) -> ((Comparable) e1).compareTo(e2);
  }
  
  public ArbreBinaireDeRecherche(Comparator<? super E> cmp) {
    racine = null;
    taille = 0;
    this.cmp = cmp;
  }
  
  public ArbreBinaireDeRecherche(Collection<? extends E> c) {
    Iterator<? extends E> it = c.iterator();
    while (it.hasNext()) {
      E noeudToAppend = (E) it.next();
      this.add(noeudToAppend);
    }
  }
  
  
  @Override
  public Iterator<E> iterator() {
    return new ABRIterator();
  }

  @Override
  public int size() {
    return taille;
  }
  
  @Override
  public boolean add(E element)	{
		if( element == null ) return false;
		Noeud noeudtoAppend = new Noeud(element);
		Noeud tampon = null;
		Noeud courrant = racine;
		while (courrant != null){
			tampon = courrant;
			courrant = this.cmp.compare(noeudtoAppend.cle, courrant.cle) < 0 ? courrant.gauche : courrant.droit;
		}
		noeudtoAppend.pere = tampon;
		if(tampon == null)
			racine = noeudtoAppend;
		else{
			if(this.cmp.compare(noeudtoAppend.cle, tampon.cle) < 0) 
				tampon.gauche = noeudtoAppend;
			else tampon.droit  = noeudtoAppend;
			noeudtoAppend.pere = tampon;
		}
		noeudtoAppend.gauche = noeudtoAppend.droit = null;
		taille++;
		return true;
	}
  
	@SuppressWarnings("unchecked")
	public Noeud rechercher(Object o) {
		if(o == null) return null;
		Noeud courrant = racine;
		while (courrant != null && courrant.cle != (E) o) {
			courrant = cmp.compare(courrant.cle, (E) o) > 0 ? courrant.gauche : courrant.droit;
		}
		return courrant;
	}

  private boolean supprimer(Noeud toDelete) {
	    if (racine == null || toDelete == null) {
	        return false;
	    }
	    Noeud courrant;
	    Noeud fils;
	    // Si le noeud a supprimer est enfant unique on le supprime direct.
	    courrant = toDelete.gauche == null || toDelete.droit == null ? toDelete : toDelete.suivant();
	    if (courrant == null) {
	        return false;
	    }
	    // On recupere le fils du noeud courant.
	    fils = courrant.gauche != null ? courrant.gauche : courrant.droit;
	    if (fils != null) {
	        // Le pere du fils devient le pere de courant.
	        fils.pere = courrant.pere;
	    }
	    if (courrant.pere == null) {
	        // Si noeud courrant -> racine.
	        this.racine = fils;
	    } else {
	        // Sinon -> le fils gauche ou droit du pere devient le fils.
	        if (courrant.equals(courrant.pere.gauche)) {
	            courrant.pere.gauche = fils;
	        } else {
	            courrant.pere.droit = fils;
	        }
	    }
	    if (!courrant.equals(toDelete)) {
	        // la cle a supprimer vaut la cle de courrant
	        toDelete.cle = courrant.cle;
	    }
	    taille--; // desincrement la taille de 1 car on vient de supprimer le noeud toDelete
	    return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object o){ // Appel de la fonction supprimer par le biais de collection
		return this.supprimer(this.rechercher((E) o));
	}
	
	
  private class ABRIterator implements Iterator<E> {
		 Noeud courrant;
		 ABRIterator(){
			courrant = racine;
		 }
		 
		@Override
		public boolean hasNext() {
			Noeud tampon = courrant;
			boolean test = tampon.suivant() != null;
			return test;
		}
		
		@Override
		public E next() {
		 	if (courrant == null){
		 		return null;
			}
			this.courrant.suivant();
			return courrant.cle;
		}
	}

  @Override
  public String toString() {
    StringBuffer buf = new StringBuffer();
    toString(racine, buf, "", maxStrLen(racine));
    return buf.toString();
  }

  private void toString(Noeud x, StringBuffer buf, String path, int len) {
    if (x == null)
      return;
    toString(x.droit, buf, path + "D", len);
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
    buf.append("-- " + x.cle.toString());
    if (x.gauche != null || x.droit != null) {
      buf.append(" --");
      for (int j = x.cle.toString().length(); j < len; j++)
        buf.append('-');
      buf.append('|');
    }
    buf.append("\n");
    toString(x.gauche, buf, path + "G", len);
  }

  private int maxStrLen(Noeud x) {
    return x == null ? 0
        : Math.max(x.cle.toString().length(), Math.max(maxStrLen(x.gauche), maxStrLen(x.droit)));
  }
}
