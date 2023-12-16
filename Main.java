import java.util.ArrayList;
import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;

public class Main {

  /*-----------------*
   * TESTS UNITAIRES *
   *-----------------*/

  public static void main(String[] args) {
    System.out.println("Test Unitaire ABR: test_addABR -> " + test_addABR().toString());
    System.out
        .println("Test Unitaire ABR: test_rechercheABR -> " + test_rechercheABR().toString());
    System.out.println("Test Unitaire ABR: test_removeABR -> " + test_removeABR().toString());
    System.out.println("Test Unitaire ARN: test_addARN -> " + test_addARN().toString());
    System.out
        .println("Test Unitaire ARN: test_rechercheARN -> " + test_rechercheARN().toString());
    System.out.println("Test Unitaire ARN: test_removeARN -> " + test_removeARN().toString());
    ArbreBicolore<Integer> Arbre = new ArbreBicolore<>();
    Arbre.add(1);
    Arbre.add(2);
    Arbre.add(3);
    Arbre.add(4);
    Arbre.add(5);
    Arbre.add(2);
    Arbre.add(3);
    Arbre.add(4);
    System.out.println(Arbre);
    //buildDataABR();
    // Visualisation de l'arbre.
    /*
     * ArbreBicolore<Integer> Arbre = new ArbreBicolore<>(); Arbre.add(1);
     * System.out.println(Arbre); Arbre.remove(1); System.out.println(Arbre);
     * Arbre.add(50); Arbre.add(25); Arbre.add(75); System.out.println(Arbre);
     * Arbre.remove(25); Arbre.remove(75); System.out.println(Arbre); Arbre.add(25);
     * Arbre.add(75); Arbre.add(24); Arbre.add(26); Arbre.add(74); Arbre.add(76);
     * System.out.println(Arbre); Arbre.remove(50); System.out.println(Arbre);
     */
  }

  public static Boolean test_addABR() {

    boolean tests = false;
    ArbreBinaireDeRecherche<Integer> Arbre = new ArbreBinaireDeRecherche<>();
    tests = Arbre.size() == 0;
    if (tests) {
      // Niveau 0
      Arbre.add(50);
      tests = Arbre.racine.cle == 50;
      if (tests) {
        // Niveau 1
        Arbre.add(49);
        tests = Arbre.racine.gauche.cle == 49;
        if (tests) {
          Arbre.add(51);
          tests = Arbre.racine.droit.cle == 51;
          // Par récurrence, c'est vrai pour tout Niveau de l'arbre.
        }
      }
    }
    return tests;
  }

  public static Boolean test_addARN() {
    boolean tests = false;
    ArbreBicolore<Integer> Arbre = new ArbreBicolore<>();
    tests = Arbre.size() == 0;
    if (tests) {
      // Niveau 0
      Arbre.add(50);
      tests = Arbre.racine.cle == 50;
      if (tests) {
        // Niveau 1
        Arbre.add(49);
        tests = Arbre.racine.gauche.cle == 49;
        if (tests) {
          Arbre.add(51);
          tests = Arbre.racine.droit.cle == 51;
          // Par récurrence, c'est vrai pour tout Niveau de l'arbre.
        }
      }
    }
    return tests;
  }

  public static Boolean test_rechercheABR() {
    boolean tests = false;
    ArbreBinaireDeRecherche<Integer> Arbre = new ArbreBinaireDeRecherche<>();
    // Recherche pour valeur inexistante -> null
    tests = Arbre.rechercher(0) == null;
    if (tests) {
      Arbre.add(0);
      // Recherche pour valeur existante != null
      tests = Arbre.rechercher(0) != null;
    }
    return tests;
  }

  public static Boolean test_rechercheARN() {
    boolean tests = false;
    ArbreBicolore<Integer> Arbre = new ArbreBicolore<>();
    // Recherche pour valeur inexistante -> null
    tests = Arbre.rechercher(0) == Arbre.sentinelle;
    if (tests) {
      Arbre.add(0);
      // Recherche pour valeur existante != null
      tests = Arbre.rechercher(0) != Arbre.sentinelle;
    }
    return tests;
  }

  public static Boolean test_removeABR() {
    boolean tests = false;
    ArbreBinaireDeRecherche<Integer> Arbre = new ArbreBinaireDeRecherche<>();
    // Supprimer une valeur inexistante -> false
    tests = Arbre.remove(0) == false;
    if (tests) {
      Arbre.add(65);
      tests = Arbre.remove(65) == true; // Parce qu'il n'existe pas de suivant.
      if (tests) {
        Arbre.add(65);
        Arbre.add(55);
        Arbre.add(75);
        Arbre.remove(55);
        Arbre.remove(75);
        tests = Arbre.racine.droit == null && Arbre.racine.gauche == null;
        if (tests) {

          // 55 a deux fils: 44(g) et 56(d)
          Arbre.add(55);
          Arbre.add(44);
          Arbre.add(56);

          // 75 a deux fils: 74(g) et 76(g)
          Arbre.add(75);
          Arbre.add(74);
          Arbre.add(76);

          // System.out.println(Arbre); -> Visu de l'arbre.
          Arbre.remove(55);
          Arbre.remove(75);
          System.out.println(Arbre);
          tests = Arbre.racine.droit.cle == 76 && Arbre.racine.gauche.cle == 56;
        }
      }
    }
    return tests;
  }

  public static Boolean test_removeARN() {
    boolean tests = false;
    ArbreBicolore<Integer> Arbre = new ArbreBicolore<>();
    // Supprimer une valeur inexistante -> false
    tests = Arbre.remove(0) == false;
    if (tests) {
      Arbre.add(65);
      tests = Arbre.remove(65) == true;
      if (tests) {
        Arbre.add(65);
        Arbre.add(55);
        Arbre.add(75);
        Arbre.remove(55);
        Arbre.remove(75);
        tests = Arbre.racine.droit == Arbre.sentinelle && Arbre.racine.gauche == Arbre.sentinelle;
        if (tests) {
          // 55 a deux fils: 44(g) et 56(d)
          Arbre.add(55);
          Arbre.add(44);
          Arbre.add(56);

          // 75 a deux fils: 74(g) et 76(d)
          Arbre.add(75);
          Arbre.add(74);
          Arbre.add(76);

          Arbre.remove(55);
          Arbre.remove(75);
          tests = Arbre.racine.droit.cle == 74 && Arbre.racine.gauche.cle == 44;
        }
      }
    }
    return tests;
  }

  // Pas de refactoring -> Aide à la visibilite pour le correcteur pour savoir ce
  // que fait chaque bout de mon algortihme.
  public static void buildDataABR() {
    int treeSize = 100;
    int reload = 1000;
    // Création des Listes où seront stockées chaque valeur.
    // On stocke reload fois: -> Permet d'avoir des valeurs "moyennes".
    // Le temps mis pour la création d'un arbre de manière aléatoire. -> aleaTime
    // Le temps mis pour la création d'un arbre avec des cles croissantes> ->
    // worstCase
    // Le temps mis pour rechercher des cles existants dans l'arbre alea. ->
    // searchInTimeAlea.
    // Le temps mis pour rechercher des cles inexistants dans l'arbre alea. ->
    // searchOutTimeAlea.
    // Le temps mis pour rechercher des cles existants dans l'arbre croissant. ->
    // searchInTimeWorst.
    // Le temps mis pour rechercher des cles inexistants dans l'arbre croissant. ->
    // searchOutTimeWorst.

    ArrayList<ArrayList<Double>> aleaTime = new ArrayList<>();
    for (int a = 0; a < 10; a++) {
      aleaTime.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> worstCase = new ArrayList<>();
    for (int b = 0; b < 10; b++) {
      worstCase.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> searchInTimeAlea = new ArrayList<>();
    for (int a = 0; a < 10; a++) {
      searchInTimeAlea.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> searchOutTimeAlea = new ArrayList<>();
    for (int b = 0; b < 10; b++) {
      searchOutTimeAlea.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> searchInTimeWorst = new ArrayList<>();
    for (int a = 0; a < 10; a++) {
      searchInTimeWorst.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> searchOutTimeWorst = new ArrayList<>();
    for (int b = 0; b < 10; b++) {
      searchOutTimeWorst.add(new ArrayList<Double>());
    }

    int indice = 0;
    for (int i = 0; i < reload; i++) {
      indice = 0;
      for (int nbfois = treeSize; nbfois < treeSize * 11; nbfois += treeSize) {
        // On réinitialise les arbres & les listes de cles existantes.
        ArbreBinaireDeRecherche<Integer> arbreAlea = new ArbreBinaireDeRecherche<>();
        ArbreBinaireDeRecherche<Integer> arbreWorst = new ArbreBinaireDeRecherche<>();
        ArrayList<Integer> keysExistingAlea = new ArrayList<>();
        ArrayList<Integer> keysExistingWorst = new ArrayList<>();

        // Temps mis pour aleaTime
        arbreAlea.add(treeSize / 2);
        double start = System.currentTimeMillis();
        for (int j = 0; j < nbfois; j++) {
          int nombreAlea = (int) (Math.random() * treeSize);
          arbreAlea.add(nombreAlea);
          keysExistingAlea.add(nombreAlea);
        }
        double end = System.currentTimeMillis();
        double time = (end - start);
        aleaTime.get(indice).add(time);
        System.out.println("Arbre Aléatoire de " + nbfois + " éléments crée.");
        // Temps mis pour worstCase
        double startWorst = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreWorst.add(k);
          keysExistingWorst.add(k);
        }
        System.out.println("Arbre Croissant de " + nbfois + " éléments crée.");
        double endWorst = System.currentTimeMillis();
        double timeWorst = (endWorst - startWorst);
        worstCase.get(indice).add(timeWorst);

        // ----------------------- Fonctions de Recherches IN & OU
        // ---------------------------------------

        // Temps mis pour rechercher des cles existentes dans un arbre creer de maniere
        // aleatoire.
        double startSearchInAlea = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreAlea.rechercher(keysExistingAlea.get(k));
        }
        double endSearchInAlea = System.currentTimeMillis();
        double timeSearchInAlea = (endSearchInAlea - startSearchInAlea);
        searchInTimeAlea.get(indice).add(timeSearchInAlea);

        // Temps mis pour rechercher des cles inexistentes dans un arbre creer de
        // maniere aleatoire.
        double startSearchOutAlea = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreAlea.rechercher(k + treeSize + 1);
        }
        double endSearchOutAlea = System.currentTimeMillis();
        double timeSearchOutAlea = (endSearchOutAlea - startSearchOutAlea);
        searchOutTimeAlea.get(indice).add(timeSearchOutAlea);

        // Temps mis pour rechercher des cles existentes dans un arbre creer par cles
        // croissantes.
        double startSearchInWorst = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreWorst.rechercher(keysExistingWorst.get(k));
        }
        double endSearchInWorst = System.currentTimeMillis();
        double timeSearchInWorst = (endSearchInWorst - startSearchInWorst);
        searchInTimeWorst.get(indice).add(timeSearchInWorst);

        // Temps mis pour rechercher des cles inexistentes dans un arbre creer par cles
        // croissantes.
        double startSearchOutWorst = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreWorst.rechercher(k + treeSize + 1);
        }
        double endSearchOutWorst = System.currentTimeMillis();
        double timeSearchOutWorst = (endSearchOutWorst - startSearchOutWorst);
        searchOutTimeWorst.get(indice).add(timeSearchOutWorst);
        indice++;
      }
    }

    // Recuperation des temps moyens pour chaque plage de donnees
    double[] nbElement = new double[10];
    double[] averageTimeAleaTime = new double[10];
    double[] averageTimeWorstCase = new double[10];
    double[] averageTimeAleaSearchIn = new double[10];
    double[] averageTimeAleaSearchOut = new double[10];
    double[] averageTimeWorstSearchIn = new double[10];
    double[] averageTimeWorstSearchOut = new double[10];

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    int index = 0;
    for (ArrayList<Double> Listes : aleaTime) {
      double sumAleaTime = 0.0;
      for (double number : Listes) {
        sumAleaTime += number;
      }
      double average = sumAleaTime / Listes.size();
      averageTimeAleaTime[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre avec des cles croissantes.
    index = 0;
    for (ArrayList<Double> Listes : worstCase) {
      double sumWorstCase = 0.0;
      for (double number : Listes) {
        sumWorstCase += number;
      }
      double average = sumWorstCase / Listes.size();
      averageTimeWorstCase[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    index = 0;
    for (ArrayList<Double> Listes : searchInTimeAlea) {
      double sumAleaTimeSearchIn = 0.0;
      for (double number : Listes) {
        sumAleaTimeSearchIn += number;
      }
      double average = sumAleaTimeSearchIn / Listes.size();
      averageTimeAleaSearchIn[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    index = 0;
    for (ArrayList<Double> Listes : searchOutTimeAlea) {
      double sumAleaTimeSearchOut = 0.0;
      for (double number : Listes) {
        sumAleaTimeSearchOut += number;
      }
      double average = sumAleaTimeSearchOut / Listes.size();
      averageTimeAleaSearchOut[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    index = 0;
    for (ArrayList<Double> Listes : searchInTimeWorst) {
      double sumWorstTimeSearchIn = 0.0;
      for (double number : Listes) {
        sumWorstTimeSearchIn += number;
      }
      double average = sumWorstTimeSearchIn / Listes.size();
      averageTimeWorstSearchIn[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    index = 0;
    for (ArrayList<Double> Listes : searchOutTimeWorst) {
      double sumWorstTimeSearchOut = 0.0;
      for (double number : Listes) {
        sumWorstTimeSearchOut += number;
      }
      double average = sumWorstTimeSearchOut / Listes.size();
      averageTimeWorstSearchOut[index] = average;
      index++;
    }

    // ------------------------------ Affichage Temps Constructions des Arbres
    // ------------------------------------------------
    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      nbElement[index] = nbfois2;
      System.out.println("Temps moyen (Aleatoire) pour " + nbfois2 + " elements: "
          + averageTimeAleaTime[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (WorstCase) pour " + nbfois2 + " elements: "
          + averageTimeWorstCase[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    // ------------------------------ Affichage Temps Recherche Cle Arbre Alea
    // ------------------------------------------------
    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (Aleatoire) pour rechercher " + nbfois2
          + " elements presents: " + averageTimeAleaSearchIn[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (Aleatoire) pour rechercher " + nbfois2
          + " elements absents: " + averageTimeAleaSearchOut[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    // ------------------------------ Affichage Temps Recherche Cle Arbre Worst
    // ------------------------------------------------
    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (WorstCase) pour rechercher " + nbfois2
          + " elements presents: " + averageTimeWorstSearchIn[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (WorstCase) pour rechercher " + nbfois2
          + " elements absents: " + averageTimeWorstSearchOut[index] + " ms.");
      index++;
    }

    // Affichage de manière graphique
    ArrayList<XYChart> graphiquesConstructions = new ArrayList<XYChart>();
    XYChart constructionAlea = new XYChartBuilder()
        .title("Graphique représentant la construction d'un ABR aléatoirement.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYChart constructionWorst = new XYChartBuilder()
        .title("Graphique représentant la construction d'un ABR de manière croissante.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYSeries seriesAlea = constructionAlea.addSeries("Algo construction Alea", nbElement,
        averageTimeAleaTime);
    XYSeries seriesWorst = constructionWorst.addSeries("Algo construction Croissant", nbElement,
        averageTimeWorstCase);
    /*
     * seriesAlea.setMarker(SeriesMarkers.NONE);
     * seriesWorst.setMarker(SeriesMarkers.NONE);
     */
    graphiquesConstructions.add(constructionAlea);
    graphiquesConstructions.add(constructionWorst);
    new SwingWrapper<XYChart>(graphiquesConstructions).displayChartMatrix();

    ArrayList<XYChart> graphiquesRecherches = new ArrayList<XYChart>();
    XYChart searchInAlea = new XYChartBuilder()
        .title(
            "Graphique représentant la recherche de N clés présentes dans un ABR aléatoirement.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYChart searchOutAlea = new XYChartBuilder()
        .title("Graphique représentant la recherche de N clés absentes dans un ABR aléatoirement.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYChart searchInWorst = new XYChartBuilder()
        .title("Graphique représentant la recherche de N clés présentes dans un ABR Croissant.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYChart searchOutWorst = new XYChartBuilder()
        .title("Graphique représentant la recherche de N clés absentes dans un ABR Croissant.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();

    XYSeries seriesSearchInAlea = searchInAlea.addSeries("Recherche présente Alea", nbElement,
        averageTimeAleaSearchIn);
    XYSeries seriesSearchOutAlea = searchOutAlea.addSeries("Recherche absente Alea", nbElement,
        averageTimeAleaSearchOut);
    XYSeries seriesSearchInWorst = searchInWorst.addSeries("Recherche présente Croissant",
        nbElement, averageTimeWorstSearchIn);
    XYSeries seriesSearchOutWorst = searchOutWorst.addSeries("Recherche absente Croissant",
        nbElement, averageTimeWorstSearchOut);

    /*
     * seriesSearchInAlea.setMarker(SeriesMarkers.NONE);
     * seriesSearchOutAlea.setMarker(SeriesMarkers.NONE);
     * seriesSearchInWorst.setMarker(SeriesMarkers.NONE);
     * seriesSearchOutWorst.setMarker(SeriesMarkers.NONE);
     */

    graphiquesRecherches.add(searchInAlea);
    graphiquesRecherches.add(searchOutAlea);
    graphiquesRecherches.add(searchInWorst);
    graphiquesRecherches.add(searchOutWorst);
    new SwingWrapper<XYChart>(graphiquesRecherches).displayChartMatrix();

  } // Fin BuilData

  // Pas de refactoring -> Aide à la visibilite pour le correcteur pour savoir ce
  // que fait chaque bout de mon algortihme.
  public static void buildDataARN() {
    int treeSize = 10000;
    int reload = 10;
    // Création des Listes où seront stockées chaque valeur.
    // On stocke reload fois: -> Permet d'avoir des valeurs "moyennes".
    // Le temps mis pour la création d'un arbre de manière aléatoire. -> aleaTime
    // Le temps mis pour la création d'un arbre avec des cles croissantes> ->
    // worstCase
    // Le temps mis pour rechercher des cles existants dans l'arbre alea. ->
    // searchInTimeAlea.
    // Le temps mis pour rechercher des cles inexistants dans l'arbre alea. ->
    // searchOutTimeAlea.
    // Le temps mis pour rechercher des cles existants dans l'arbre croissant. ->
    // searchInTimeWorst.
    // Le temps mis pour rechercher des cles inexistants dans l'arbre croissant. ->
    // searchOutTimeWorst.

    ArrayList<ArrayList<Double>> aleaTime = new ArrayList<>();
    for (int a = 0; a < 10; a++) {
      aleaTime.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> worstCase = new ArrayList<>();
    for (int b = 0; b < 10; b++) {
      worstCase.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> searchInTimeAlea = new ArrayList<>();
    for (int a = 0; a < 10; a++) {
      searchInTimeAlea.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> searchOutTimeAlea = new ArrayList<>();
    for (int b = 0; b < 10; b++) {
      searchOutTimeAlea.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> searchInTimeWorst = new ArrayList<>();
    for (int a = 0; a < 10; a++) {
      searchInTimeWorst.add(new ArrayList<Double>());
    }

    ArrayList<ArrayList<Double>> searchOutTimeWorst = new ArrayList<>();
    for (int b = 0; b < 10; b++) {
      searchOutTimeWorst.add(new ArrayList<Double>());
    }

    int indice = 0;
    for (int i = 0; i < reload; i++) {
      indice = 0;
      for (int nbfois = treeSize; nbfois < treeSize * 11; nbfois += treeSize) {
        // On réinitialise les arbres & les listes de cles existantes.
        ArbreBicolore<Integer> arbreAlea = new ArbreBicolore<>();
        ArbreBicolore<Integer> arbreWorst = new ArbreBicolore<>();
        ArrayList<Integer> keysExistingAlea = new ArrayList<>();
        ArrayList<Integer> keysExistingWorst = new ArrayList<>();

        // Temps mis pour aleaTime
        arbreAlea.add(treeSize / 2);
        double start = System.currentTimeMillis();
        for (int j = 0; j < nbfois; j++) {
          int nombreAlea = (int) (Math.random() * treeSize);
          arbreAlea.add(nombreAlea);
          keysExistingAlea.add(nombreAlea);
        }
        System.out.println("Arbre Aléatoire de " + nbfois + " éléments crée.");
        double end = System.currentTimeMillis();
        double time = (end - start);
        aleaTime.get(indice).add(time);

        // Temps mis pour worstCase
        double startWorst = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreWorst.add(k);
          keysExistingWorst.add(k);

        }
        System.out.println("Arbre Croissant de " + nbfois + " éléments crée.");
        double endWorst = System.currentTimeMillis();
        double timeWorst = (endWorst - startWorst);
        worstCase.get(indice).add(timeWorst);

        // ----------------------- Fonctions de Recherches IN & OU
        // ---------------------------------------

        // Temps mis pour rechercher des cles existentes dans un arbre creer de maniere
        // aleatoire.
        double startSearchInAlea = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreAlea.rechercher(keysExistingAlea.get(k));
        }
        double endSearchInAlea = System.currentTimeMillis();
        double timeSearchInAlea = (endSearchInAlea - startSearchInAlea);
        searchInTimeAlea.get(indice).add(timeSearchInAlea);

        // Temps mis pour rechercher des cles inexistentes dans un arbre creer de
        // maniere aleatoire.
        double startSearchOutAlea = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreAlea.rechercher(k + treeSize + 1);
        }
        double endSearchOutAlea = System.currentTimeMillis();
        double timeSearchOutAlea = (endSearchOutAlea - startSearchOutAlea);
        searchOutTimeAlea.get(indice).add(timeSearchOutAlea);

        // Temps mis pour rechercher des cles existentes dans un arbre creer par cles
        // croissantes.
        double startSearchInWorst = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreWorst.rechercher(keysExistingWorst.get(k));
        }
        double endSearchInWorst = System.currentTimeMillis();
        double timeSearchInWorst = (endSearchInWorst - startSearchInWorst);
        searchInTimeWorst.get(indice).add(timeSearchInWorst);

        // Temps mis pour rechercher des cles inexistentes dans un arbre creer par cles
        // croissantes.
        double startSearchOutWorst = System.currentTimeMillis();
        for (int k = 0; k < nbfois; k++) {
          arbreWorst.rechercher(k + treeSize + 1);
        }
        double endSearchOutWorst = System.currentTimeMillis();
        double timeSearchOutWorst = (endSearchOutWorst - startSearchOutWorst);
        searchOutTimeWorst.get(indice).add(timeSearchOutWorst);
        indice++;
      }
      
    }
    
    
    System.out.println("Je suis après les constructions des arbres.");
    // Recuperation des temps moyens pour chaque plage de donnees
    double[] nbElement = new double[10];
    double[] averageTimeAleaTime = new double[10];
    double[] averageTimeWorstCase = new double[10];
    double[] averageTimeAleaSearchIn = new double[10];
    double[] averageTimeAleaSearchOut = new double[10];
    double[] averageTimeWorstSearchIn = new double[10];
    double[] averageTimeWorstSearchOut = new double[10];

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    int index = 0;
    for (ArrayList<Double> Listes : aleaTime) {
      double sumAleaTime = 0.0;
      for (double number : Listes) {
        sumAleaTime += number;
      }
      double average = sumAleaTime / Listes.size();
      averageTimeAleaTime[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre avec des cles croissantes.
    index = 0;
    for (ArrayList<Double> Listes : worstCase) {
      double sumWorstCase = 0.0;
      for (double number : Listes) {
        sumWorstCase += number;
      }
      double average = sumWorstCase / Listes.size();
      averageTimeWorstCase[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    index = 0;
    for (ArrayList<Double> Listes : searchInTimeAlea) {
      double sumAleaTimeSearchIn = 0.0;
      for (double number : Listes) {
        sumAleaTimeSearchIn += number;
      }
      double average = sumAleaTimeSearchIn / Listes.size();
      averageTimeAleaSearchIn[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    index = 0;
    for (ArrayList<Double> Listes : searchOutTimeAlea) {
      double sumAleaTimeSearchOut = 0.0;
      for (double number : Listes) {
        sumAleaTimeSearchOut += number;
      }
      double average = sumAleaTimeSearchOut / Listes.size();
      averageTimeAleaSearchOut[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    index = 0;
    for (ArrayList<Double> Listes : searchInTimeWorst) {
      double sumWorstTimeSearchIn = 0.0;
      for (double number : Listes) {
        sumWorstTimeSearchIn += number;
      }
      double average = sumWorstTimeSearchIn / Listes.size();
      averageTimeWorstSearchIn[index] = average;
      index++;
    }

    // Calcul des temps moyens pour chaque n*treeSize donnees pour la construction
    // d'un arbre aleatoire.
    index = 0;
    for (ArrayList<Double> Listes : searchOutTimeWorst) {
      double sumWorstTimeSearchOut = 0.0;
      for (double number : Listes) {
        sumWorstTimeSearchOut += number;
      }
      double average = sumWorstTimeSearchOut / Listes.size();
      averageTimeWorstSearchOut[index] = average;
      index++;
    }

    // ------------------------------ Affichage Temps Constructions des Arbres
    // ------------------------------------------------
    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      nbElement[index] = nbfois2;
      System.out.println("Temps moyen (Aleatoire) pour " + nbfois2 + " elements: "
          + averageTimeAleaTime[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (WorstCase) pour " + nbfois2 + " elements: "
          + averageTimeWorstCase[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    // ------------------------------ Affichage Temps Recherche Cle Arbre Alea
    // ------------------------------------------------
    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (Aleatoire) pour rechercher " + nbfois2
          + " elements presents: " + averageTimeAleaSearchIn[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (Aleatoire) pour rechercher " + nbfois2
          + " elements absents: " + averageTimeAleaSearchOut[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    // ------------------------------ Affichage Temps Recherche Cle Arbre Worst
    // ------------------------------------------------
    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (WorstCase) pour rechercher " + nbfois2
          + " elements presents: " + averageTimeWorstSearchIn[index] + " ms.");
      index++;
    }

    System.out.println(
        "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

    index = 0;
    for (int nbfois2 = treeSize; nbfois2 < treeSize * 11; nbfois2 += treeSize) {
      System.out.println("Temps moyen (WorstCase) pour rechercher " + nbfois2
          + " elements absents: " + averageTimeWorstSearchOut[index] + " ms.");
      index++;
    }

    // Affichage de manière graphique
    ArrayList<XYChart> graphiquesConstructions = new ArrayList<XYChart>();
    XYChart constructionAlea = new XYChartBuilder()
        .title("Graphique représentant la construction d'un ARN aléatoirement.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYChart constructionWorst = new XYChartBuilder()
        .title("Graphique représentant la construction d'un ARN de manière croissante.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYSeries seriesAlea = constructionAlea.addSeries("Algo construction Alea", nbElement,
        averageTimeAleaTime);
    XYSeries seriesWorst = constructionWorst.addSeries("Algo construction Croissant", nbElement,
        averageTimeWorstCase);
    /*
     * seriesAlea.setMarker(SeriesMarkers.NONE);
     * seriesWorst.setMarker(SeriesMarkers.NONE);
     */
    graphiquesConstructions.add(constructionAlea);
    graphiquesConstructions.add(constructionWorst);
    new SwingWrapper<XYChart>(graphiquesConstructions).displayChartMatrix();

    ArrayList<XYChart> graphiquesRecherches = new ArrayList<XYChart>();
    XYChart searchInAlea = new XYChartBuilder()
        .title(
            "Graphique représentant la recherche de N clés présentes dans un ARN aléatoirement.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYChart searchOutAlea = new XYChartBuilder()
        .title("Graphique représentant la recherche de N clés absentes dans un ARN aléatoirement.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYChart searchInWorst = new XYChartBuilder()
        .title("Graphique représentant la recherche de N clés présentes dans un ARN Croissant.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();
    XYChart searchOutWorst = new XYChartBuilder()
        .title("Graphique représentant la recherche de N clés absentes dans un ARN Croissant.")
        .xAxisTitle("Nombre d'éléments").yAxisTitle("Temps en ms").width(600).height(400).build();

    XYSeries seriesSearchInAlea = searchInAlea.addSeries("Recherche présente Alea", nbElement,
        averageTimeAleaSearchIn);
    XYSeries seriesSearchOutAlea = searchOutAlea.addSeries("Recherche absente Alea", nbElement,
        averageTimeAleaSearchOut);
    XYSeries seriesSearchInWorst = searchInWorst.addSeries("Recherche présente Croissant",
        nbElement, averageTimeWorstSearchIn);
    XYSeries seriesSearchOutWorst = searchOutWorst.addSeries("Recherche absente Croissant",
        nbElement, averageTimeWorstSearchOut);

    /*
     * seriesSearchInAlea.setMarker(SeriesMarkers.NONE);
     * seriesSearchOutAlea.setMarker(SeriesMarkers.NONE);
     * seriesSearchInWorst.setMarker(SeriesMarkers.NONE);
     * seriesSearchOutWorst.setMarker(SeriesMarkers.NONE);
     */

    graphiquesRecherches.add(searchInAlea);
    graphiquesRecherches.add(searchOutAlea);
    graphiquesRecherches.add(searchInWorst);
    graphiquesRecherches.add(searchOutWorst);
    new SwingWrapper<XYChart>(graphiquesRecherches).displayChartMatrix();

  } // Fin BuilData
} // Fin Class