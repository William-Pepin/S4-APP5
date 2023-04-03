package app6;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
  private ElemAST gauche;
  private ElemAST droite;

  private final Terminal terminal;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal terminal ) throws Exception { // avec arguments
    if(terminal.getType() != TerminalType.OPERATEUR ||
       terminal.getType() != TerminalType.PARENTHESE) {
      throw new Exception("a changer eventuellement...");
    }
    this.terminal = terminal;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
     //
    return 0;
  }

  public TerminalType getType() {
    return terminal.getType();
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
    return getGauche().LectAST() + terminal.toString() + getDroite().LectAST();
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


