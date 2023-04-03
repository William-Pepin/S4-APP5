package app6;

/**
 * Classe qui défini les exceptions retournés par l'analyseur lexical
 */
public class AnalLexException extends Exception {
  /**
   * Construction de l'exception
   *
   * @param message message de l'exception
   */
  public AnalLexException(String message) {
    super(message);
  }
}
