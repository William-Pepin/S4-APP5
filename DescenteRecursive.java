package app6;

/**
 * @author Ahmed Khoumsi
 */

import java.util.ArrayList;

/**
 * Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  private static ArrayList<Terminal> tokens; // unités lexicales
  String chaine;
  private int pointer;
  private Terminal current;

  /**
   * Constructeur de DescenteRecursive :
   * - recoit en argument le nom du fichier contenant l'expression a analyser
   * - pour l'initalisation d'attribut(s)
   */
  public DescenteRecursive(String in) {
    Reader r = new Reader(in);
    chaine = r.toString().replaceAll("\\ \\t", "");
    pointer = 0;
    tokens = new ArrayList<Terminal>();
  }

  /**
   * Constructeur de DescenteRecursive :
   * - recoit en argument le nom du fichier contenant l'expression a analyser
   * - ne sert qu'à passer directement à un string au lieu d'un fichier texte
   */
  public DescenteRecursive(String chaine, boolean ce_bool_sert_a_rien) {
    this.chaine = chaine;
    pointer = 0;
    tokens = new ArrayList<Terminal>();
  }

  /**
   * AnalSynt() effectue l'analyse syntaxique et construit l'AST.
   * Elle retourne une reference sur la racine de l'AST construit
   */
  public ElemAST AnalSynt() throws AnalLexException, AnalSyntException {
    AnalLex analLex = new AnalLex(chaine);
    while (analLex.resteTerminal()) {
      tokens.add(analLex.prochainTerminal());
    }
    tokens.add(new Terminal("$", TerminalType.END));
    return E();
  }

  /**
   * Fonction de l'élément E, permet de retourner T | T + E | T - E
   *
   * @return Un élément de l'arbre
   * @throws AnalSyntException
   */
  public ElemAST E() throws AnalSyntException {
    ElemAST n1 = T();
    Terminal token = tokens.get(pointer);
    if (token.getChaine().equals("+") || token.getChaine().equals("-")) {
      pointer++;
      ElemAST n2 = E();
      n1 = new NoeudAST(token, n1, n2);
    }
    return n1;
  }

  /**
   * Fonction de l'élément T, permet de retourner F | F + T | F - T
   *
   * @return Un élément de l'arbre
   * @throws AnalSyntException
   */
  public ElemAST T() throws AnalSyntException {
    ElemAST n1 = F();
    Terminal token = tokens.get(pointer);
    if (token.getChaine().equals("*") || token.getChaine().equals("/")) {
      pointer++;
      ElemAST n2 = T();
      try {
        n1 = new NoeudAST(token, n1, n2);
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    return n1;
  }

  /**
   * Fonction de l'élément F, permet de retourner (E) | id | number
   *
   * @return Un élément de l'arbre
   * @throws AnalSyntException
   */
  public ElemAST F() throws AnalSyntException {

    Terminal token = tokens.get(pointer);
    ElemAST n = null;
    token = tokens.get(pointer);
    if (token.getType() == TerminalType.NOMBRE || token.getType() == TerminalType.VARIABLE) {
      n = new FeuilleAST(token);
      pointer++;
    } else if (token.getType() == TerminalType.PARENTHESE && token.getChaine().equals("(")) {
      pointer++;
      n = E();
      token = tokens.get(++pointer);
      if (token.getType() == TerminalType.PARENTHESE && token.getChaine().equals(")")) {
        token = tokens.get(++pointer);
      }
    } else {
      ErreurSynt("Erreur : " + chaine + ", erreur de syntaxe au token " + pointer + " "  + token + ". Un Nombre ou une variable est attendu.");
    }
    if (token.getType() == TerminalType.END) {
      throw new AnalSyntException("\"Erreur : " + chaine + ", erreur de syntaxe au token " + pointer + ", Les parenthèses ne sont pas en pairs.");
    }
    return n;
  }

  /**
   * ErreurSynt() envoie un message d'erreur syntaxique
   */
  public void ErreurSynt(String s) throws AnalSyntException {
    throw new AnalSyntException(s);
  }

  //Methode principale a lancer pour tester l'analyseur syntaxique 
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";
    String toWritePost = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0) {
      args = new String[2];
      args[0] = "ExpArith.txt";
      args[1] = "ResultatSyntaxique.txt";
    }

    VariableLookupTable.lut.put("X_a", 9);
    VariableLookupTable.lut.put("Y_b", 10);
    VariableLookupTable.lut.put("Z_c", 2);

    DescenteRecursive dr = new DescenteRecursive(args[0]);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);

      toWritePost += "PostFix de l'AST trouve : " + RacineAST.PostFix() + "\n";
      System.out.println(toWritePost);

      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      System.out.println(toWriteEval);
      Writer w = new Writer(args[1], toWriteLect + toWriteEval); // Ecriture de toWrite
      // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

