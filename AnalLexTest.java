package app6;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AnalLexTest {

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenVariableEndsWithUnderScore() throws AnalLexException {
    String stringToTest = "X_ + X";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    AnalLex subject = new AnalLex(stringToTest);
    try{
      while (subject.resteTerminal())
        subject.prochainTerminal();
    }
    catch(AnalLexException expected) {
      assertTrue(true);
      System.out.println(expected);
      return;
    }
    fail();
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenVariableHasTwoUnderScores() {
    String stringToTest = "X__a + X";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    AnalLex subject = new AnalLex(stringToTest);
    try {
      while (subject.resteTerminal())
        subject.prochainTerminal();
    } catch (AnalLexException expected) {
      assertTrue(true);
      System.out.println(expected);
      return;
    }
    fail();
  }

    @org.junit.jupiter.api.Test
    void prochainTerminalWhenVariableStartsWithLowercase() {
      String stringToTest = "aA + X";
      System.out.println("Tests du cas suivant : " + stringToTest);
      System.out.print("\nRésultats : ");
      AnalLex subject = new AnalLex(stringToTest);
      try{
        while (subject.resteTerminal())
          subject.prochainTerminal();
      }
      catch(AnalLexException expected) {
        assertTrue(true);
        System.out.println(expected);
        return;
      }
      fail();
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenVariableIsOk() {
    String stringToTest = "X + X";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    AnalLex subject = new AnalLex(stringToTest);
    String[] expected = new String[]{"X","+","X"};
    String[] result = new String[3];
    int i = 0;
    try{
      while (subject.resteTerminal())
        result[i++] = subject.prochainTerminal().getChaine();
    }
    catch(AnalLexException e) {
      fail();
      System.out.println(e);
    }
    System.out.println(Arrays.toString(result));
    assertArrayEquals(expected,result);
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenNumberIsOk() {
    String stringToTest = "9876543210 - 0123456789";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    AnalLex subject = new AnalLex(stringToTest);
    String[] expected = new String[]{"9876543210","-","0123456789"};
    String[] result = new String[3];
    int i = 0;
    try{
      while (subject.resteTerminal())
        result[i++] = subject.prochainTerminal().getChaine();
    }
    catch(AnalLexException e) {
      fail();
      System.out.println(e);
    }
    System.out.println(Arrays.toString(result));
    assertArrayEquals(expected,result);
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenParentheses() {
    String stringToTest = "()";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    AnalLex subject = new AnalLex(stringToTest);
    String[] expected = new String[]{"(",")"};
    String[] result = new String[2];
    int i = 0;
    try{
      while (subject.resteTerminal())
        result[i++] = subject.prochainTerminal().getChaine();
    }
    catch(AnalLexException e) {
      fail();
      System.out.println(e);
    }
    System.out.println(Arrays.toString(result));
    assertArrayEquals(expected,result);
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenOperators() {
    String stringToTest = "+-*/";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    AnalLex subject = new AnalLex(stringToTest);
    String[] expected = new String[]{"+","-","*","/"};
    String[] result = new String[4];
    int i = 0;
    try{
      while (subject.resteTerminal())
        result[i++] = subject.prochainTerminal().getChaine();
    }
    catch(AnalLexException e) {
      fail();
      System.out.println(e);
      return;
    }
    System.out.println(Arrays.toString(result));
    assertArrayEquals(expected,result);
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenCharNotInAlphabet() {
    String stringToTest = ": + X";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    AnalLex subject = new AnalLex(stringToTest);
    try{
      while (subject.resteTerminal())
        subject.prochainTerminal();
    }
    catch(AnalLexException expected) {
      assertTrue(true);
      System.out.println(expected);
    }
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalGeneralCase() {
    String stringToTest = "((X_a + Y_b) * Z_c / 59) - 4";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    AnalLex subject = new AnalLex(stringToTest);
    String[] expected = new String[]{"(","(","X_a","+","Y_b",")","*","Z_c","/","59",")","-","4"};
    String[] result = new String[expected.length];
    int i = 0;
    try{
      while (subject.resteTerminal())
        result[i++] = subject.prochainTerminal().getChaine();
    }
    catch(AnalLexException e) {
      fail();
      System.out.println(e);
    }
    System.out.println(Arrays.toString(result));
    assertArrayEquals(expected,result);
  }

}