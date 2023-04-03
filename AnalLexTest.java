package app6;

import static org.junit.jupiter.api.Assertions.*;

class AnalLexTest {

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenVariableEndsWithUnderScore() throws AnalLexException {
    String stringToTest = "X_ + X";
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
    assertArrayEquals(expected,result);
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenNumberIsOk() {
    String stringToTest = "9876543210 - 0123456789";
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
    assertArrayEquals(expected,result);
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenParentheses() {
    String stringToTest = "()";
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
    assertArrayEquals(expected,result);
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenOperators() {
    String stringToTest = "+-*/";
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
    assertArrayEquals(expected,result);
  }

  @org.junit.jupiter.api.Test
  void prochainTerminalWhenCharNotInAlphabet() {
    String stringToTest = ": + X";
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

}