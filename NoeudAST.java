package app6;

/**
 * @author Ahmed Khoumsi
 */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
  private ElemAST gauche;
  private ElemAST droite;

  private final Terminal terminal;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal terminal, ElemAST pG, ElemAST pD) throws AnalSyntException {
      if (terminal.getType() == TerminalType.OPERATEUR) {
        this.terminal = terminal;
        this.gauche = pG;
        this.droite = pD;
      }
      else
        throw new AnalSyntException("a changer eventuellement....");
    }



  /** Evaluation de noeud d'AST
   */
  public int EvalAST() throws AnalSyntException {
    int result = 0;
    switch (this.terminal.getChaine()) {
      case "+" -> result = gauche.EvalAST() + droite.EvalAST();
      case "-" -> result = gauche.EvalAST() - droite.EvalAST();
      case "*" -> result = gauche.EvalAST() * droite.EvalAST();
      case "/" -> result = gauche.EvalAST() / droite.EvalAST();
      default -> throw new AnalSyntException("L'opération n'est pas défini dans la liste des opérateurs ( + - * / )");
    }
    return result;
  }

  public TerminalType getType() {
    return terminal.getType();
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST() {
    return "( " + getGauche().LectAST() + terminal.getChaine() + getDroite().LectAST() + " )";
  }

  /** Lecture PostFix de noeud d'AST
   */
  public String PostFix() {
    return  getGauche().PostFix() + getDroite().PostFix() + terminal.getChaine();
  }

  public ElemAST getDroite() {
    return droite;
  }

  public void setDroite(ElemAST droite) {
    this.droite = droite;
  }

  public ElemAST getGauche() {
    return gauche;
  }

  public void setGauche(ElemAST gauche) {
    this.gauche = gauche;
  }
}


