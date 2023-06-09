package app6;

/**
 * @author Ahmed Khoumsi
 */

/** Classe Abstraite dont heriteront les classes FeuilleAST et NoeudAST
 */
public abstract class ElemAST {


  /** Evaluation d'AST
   */
  public abstract int EvalAST() throws AnalSyntException;


  /** Lecture d'AST
   */
  public abstract String LectAST();


  /** PostFix d'AST
   */
  public abstract String PostFix();

  /** ErreurEvalAST() envoie un message d'erreur lors de la construction d'AST
   */
  public void ErreurEvalAST(String s) throws Exception {
    throw new Exception("A changer eventuellement: " + s);
  }
}
