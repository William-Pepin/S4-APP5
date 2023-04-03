package app6;

/**
 * @author Ahmed Khoumsi
 */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
  private final Terminal terminal;

  /**Constructeur pour l'initialisation d'attribut(s)
   */
  public FeuilleAST(Terminal terminal) throws AnalSyntException {  // avec arguments
    if (terminal.getType() != TerminalType.NOMBRE ||
        terminal.getType() != TerminalType.VARIABLE)
      throw new AnalSyntException("a changer eventuellement....");

    this.terminal = terminal;
  }

  /** Evaluation de feuille d'AST
   */
  public int EvalAST() throws AnalSyntException {
    if (terminal.getType() == TerminalType.VARIABLE)
      if (VariableLookupTable.lut.containsKey(terminal.getChaine()))
        return VariableLookupTable.lut.get(terminal.getChaine());
      else
        throw new AnalSyntException("La variable " + terminal.getChaine() + "n'est pas défini dans la table de " +
            "correspondance.");

    return Integer.parseInt(terminal.getChaine());
  }

  /** Lecture de chaine de caracteres correspondant a la feuille d'AST
   */
  public String LectAST() {
    return terminal.toString();
  }
}
