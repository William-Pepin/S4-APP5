package app6;

/**
 * Classe qui défini les exceptions retournés par l'analyseur syntaxique
 */
public class AnalSyntException extends Exception {
  /**
   * Construction de l'exception
   *
   * @param message message de l'exception
   */
  public AnalSyntException(String message) {
    super(message);
  }
}