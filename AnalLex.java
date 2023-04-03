package app6;

/** @author Ahmed Khoumsi, William Pépin, Gabriel Vachon, Matthieu Daoust */
import app6.AnalLexState;

import java.util.ArrayList;


/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

  // Attributs
  private String chaine;
  private int pointer;

  private AnalLexState state;

  private ArrayList<Character> operators;
  private ArrayList<Character> parentheses;
  private ArrayList<Character> nombres;
  private ArrayList<Character> alphabetVariable;
  private ArrayList<Character> lowerCase;
  private ArrayList<Character> upperCase;

  private ArrayList<Character> transitionChars;

/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex(String chaine) {         // arguments possibles
    operators = new ArrayList<>();        // ensemble des opérateurs +-*/
    parentheses = new ArrayList<>();      // ensemble des parenthèses ()
    nombres = new ArrayList<>();          // ensemble des nombres 0-9
    alphabetVariable = new ArrayList<>(); // ensemble de l'alphabet des variables upperCase + lowerCase + _
    lowerCase = new ArrayList<>();        // ensemble des lettres minuscules
    upperCase = new ArrayList<>();        // ensemble des lettres majuscules
    transitionChars = new ArrayList<>();  // ensemble des symboles de transition (espace, retour de chariot, etc)
    initAlphabet();

    pointer = 0;                          // pointeur de la chaine
    state = AnalLexState.INIT;            // état initial

    this.chaine = chaine; // chaine à analyser
  }

  /**
   * Méthode qui permet d'initialiser l'alphabet de l'analyseur lexical
   */
  public void initAlphabet()
  {
    // operators
    operators.add('+');
    operators.add('-');
    operators.add('*');
    operators.add('/');

    // parentheses
    parentheses.add('(');
    parentheses.add(')');

    // nombres
    nombres.add('0');
    nombres.add('1');
    nombres.add('2');
    nombres.add('3');
    nombres.add('4');
    nombres.add('5');
    nombres.add('6');
    nombres.add('7');
    nombres.add('8');
    nombres.add('9');

    // lowerCase
    lowerCase.add('a');
    lowerCase.add('b');
    lowerCase.add('c');
    lowerCase.add('d');
    lowerCase.add('e');
    lowerCase.add('f');
    lowerCase.add('g');
    lowerCase.add('h');
    lowerCase.add('i');
    lowerCase.add('j');
    lowerCase.add('k');
    lowerCase.add('l');
    lowerCase.add('m');
    lowerCase.add('n');
    lowerCase.add('o');
    lowerCase.add('p');
    lowerCase.add('q');
    lowerCase.add('r');
    lowerCase.add('s');
    lowerCase.add('t');
    lowerCase.add('u');
    lowerCase.add('v');
    lowerCase.add('w');
    lowerCase.add('x');
    lowerCase.add('y');
    lowerCase.add('z');

    // upperCase
    upperCase.add('A');
    upperCase.add('B');
    upperCase.add('C');
    upperCase.add('D');
    upperCase.add('E');
    upperCase.add('F');
    upperCase.add('G');
    upperCase.add('H');
    upperCase.add('I');
    upperCase.add('J');
    upperCase.add('K');
    upperCase.add('L');
    upperCase.add('M');
    upperCase.add('N');
    upperCase.add('O');
    upperCase.add('P');
    upperCase.add('Q');
    upperCase.add('R');
    upperCase.add('S');
    upperCase.add('T');
    upperCase.add('U');
    upperCase.add('V');
    upperCase.add('W');
    upperCase.add('X');
    upperCase.add('Y');
    upperCase.add('Z');

    // alphabetVariable
    alphabetVariable.add('_');
    alphabetVariable.addAll(lowerCase);
    alphabetVariable.addAll(upperCase);

    // transition
    transitionChars.add(' ');
    transitionChars.add('\n');
    transitionChars.add('\r');
    transitionChars.add('\b');
  }


/** resteTerminal() retourne :
      false  si tous les terminaux de l'expression arithmetique ont ete retournes
      true s'il reste encore au moins un terminal qui n'a pas ete retourne 
 */
  public boolean resteTerminal( ) {
    return !(pointer == chaine.length() - 1);
  }
  
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  public Terminal prochainTerminal( ) {
    StringBuilder terminal = new StringBuilder();
    TerminalType type = null;
    state = AnalLexState.INIT;
    char current = 0;
    while (resteTerminal()) {
      current = chaine.charAt(pointer);
      if(!transitionChars.contains(current)) {
        switch (state) {

          // état INIT
          case INIT -> {
            if (operators.contains(current))
              return EtatNonFiniRetour(String.valueOf(current), TerminalType.OPERATEUR);

            else if (parentheses.contains(current))
              return EtatNonFiniRetour(String.valueOf(current), TerminalType.PARENTHESE);

            else if (nombres.contains(current)) {
              state = AnalLexState.NUMBER; // prochain état
              terminal.append(current);
            } else if (upperCase.contains(current)) {
              state = AnalLexState.VAR;
              terminal.append(current);
            } else {
              ErreurLex("Le caractère " + current + " n'est pas un caractère connu du compilateur.");
            }
          }
          case NUMBER -> {
            if (nombres.contains(current)) {
              terminal.append(current);
            } else {
              return EtatFiniRetour(terminal.toString(), TerminalType.NOMBRE);
            }

          }
          case VAR -> {
            if (upperCase.contains(current) || lowerCase.contains(current)) {
              state = AnalLexState.VAR;
              terminal.append(current);
            } else if (current == '_') {
              state = AnalLexState.VAR_ER;
              terminal.append(current);
            } else {
              return EtatFiniRetour(terminal.toString(), TerminalType.VARIABLE);
            }
          }
          case VAR_ER -> {
            if (upperCase.contains(current) || lowerCase.contains(current)) {
              state = AnalLexState.VAR;
              terminal.append(current);
            } else
              ErreurLex("Il ne peut y avoir deux fois le caractère " + current + " de suite.");
          }
          default -> {}
        }
      }
      pointer++;
    }
    return new Terminal(terminal.toString(), type);
  }

  public Terminal EtatFiniRetour(String terminal, TerminalType type)
  {
    return new Terminal(terminal, type);
  }

  public Terminal EtatNonFiniRetour(String terminal, TerminalType type)
  {
    pointer++;
    return new Terminal(terminal, type);
  }


 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {
    System.out.println(s);
  }

  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    String toWrite = "";
    System.out.println("Debut d'analyse lexicale");
    if (args.length == 0){
    args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
    }
    Reader r = new Reader(args[0]);

    AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

    // Execution de l'analyseur lexical
    Terminal t = null;
    while(lexical.resteTerminal()){
      t = lexical.prochainTerminal();
      toWrite += t.getChaine() + "\n" ;  // toWrite contient le resultat
    }           //    d'analyse lexicale
    System.out.println(toWrite);   // Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
