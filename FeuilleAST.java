package app6;

/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
  private final Terminal terminal;

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(Terminal terminal ) throws Exception {  // avec arguments
    if( terminal.getType() != TerminalType.NOMBRE ||
        terminal.getType() != TerminalType.VARIABLE)
      throw new Exception("a changer eventuellement....");

    this.terminal = terminal;
  }

  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
    if(terminal.getType() == TerminalType.VARIABLE) {
      // trouver une manière de parse la valeur jsp;
    }
    return Integer.parseInt(terminal.getChaine());
  }

 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return terminal.toString();
  }
}
