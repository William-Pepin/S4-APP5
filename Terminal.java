package app6;

/**
 * @author Ahmed Khoumsi
 */

/**
 * Cette classe identifie les terminaux reconnus et retournes par
 * l'analyseur lexical
 */
public class Terminal {

  // Constantes et attributs
  private final String chaine;
  private final TerminalType type;
//  ....


  /**
   * Un ou deux constructeurs (ou plus, si vous voulez)
   * pour l'initalisation d'attributs
   */
  public Terminal(String chaine, TerminalType type) {
    this.chaine = chaine;
    this.type = type;
  }

  /**
   * Permet de retrouver la chaine brute du terminal
   *
   * @return la chaine du terminal
   */
  public String getChaine() {
    return chaine;
  }

  /**
   * Permet de faire une chaine de caractères contenant la chaine et le type du terminal
   *
   * @return "[ Token: " + getChaine() + ", Type: " + getType() + " ]"
   */
  @Override
  public String toString() {
    return "[ Token: " + chaine + ", Type: " + type + " ]";
  }

  /**
   * Permet de retrouver le type du terminal
   *
   * @return le type du terminal
   */
  public TerminalType getType() {
    return type;
  }


}
