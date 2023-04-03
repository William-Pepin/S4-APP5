package app6;

/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {

  // Constantes et attributs
  private final String chaine;
  private final TerminalType type;
//  ....


  /** Un ou deux constructeurs (ou plus, si vous voulez)
   *   pour l'initalisation d'attributs
   */
  public Terminal(String chaine, TerminalType type) {
    this.chaine = chaine;
    this.type = type;
  }

  public String getChaine() {
    return chaine;
  }

  @Override
  public String toString() {
    return "[ Token: " + chaine + ", Type: " + type + " ]";
  }

  public TerminalType getType()
  {
    return type;
  }



}
