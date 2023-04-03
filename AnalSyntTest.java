package app6;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class AnalSyntTest {

  // X_a = 9
  // Y_b = 10
  // Z_c = 2
  private final String Case1 = "(X_a + Y_b) * Z_c / 2"; // 19
  private final String Case1PostFix = "X_aY_bZ_c*2/";
  private final String Case1Lecture = "";
  private final String Case2 = "(X_a - Y_b / Z_c + 59) + 4"; // 67
  private final String Case2PostFix = "X_AY_bZ_c/-59+4+";

  private final String Case2Lecture = "";
  private final String Case3 = "4 * 4 * 4 + 12 / 12"; // 65
  private final String Case3PostFix = "44*4*1212/+";

  private final String Case3Lecture = "";
  private final String parentheseOuvranteTrop = "((4+4)";
  private final String parentheseFermanteTrop = "((4+4)";
  private final String operateurFini = "2+4*";
  private final String nombreSuivi = "4 4";
  private final String variableSuivi = "X_a Y_b";

  @BeforeEach
  void setUp() {
    VariableLookupTable.lut.put("X_a", 9);
    VariableLookupTable.lut.put("Y_b", 10);
    VariableLookupTable.lut.put("Z_c", 2);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostFixCase1() {
    String stringToTest = Case1;
    String expected = Case1PostFix;
    String result = "";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.PostFix();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostFixCase2() {
    String stringToTest = Case2;
    String expected = Case2PostFix;
    String result = "";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.PostFix();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostFixCase3() {
    String stringToTest = Case3;
    String expected = Case3PostFix;
    String result = "";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.PostFix();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostLectureAST1() {
    String stringToTest = Case1;
    String expected = Case1Lecture;
    String result = "";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.LectAST();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostLectureAST2() {
    String stringToTest = Case2;
    String expected = Case2Lecture;
    String result = "";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.LectAST();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostLectureAST3() {
    String stringToTest = Case3;
    String expected = Case3Lecture;
    String result = "";
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.LectAST();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostEvalAST1() {
    String stringToTest = Case1;
    int expected = 19;
    int result = 0;
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.EvalAST();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostEvalAST2() {
    String stringToTest = Case2;
    int expected = 67;
    int result = 0;
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.EvalAST();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestPostEvalAST3() {
    String stringToTest = Case3;
    int expected = 65;
    int result = 0;
    System.out.println("Tests du cas suivant : " + stringToTest);
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      ElemAST root = subject.AnalSynt();
      result = root.EvalAST();
    } catch (AnalLexException | AnalSyntException e) {
      fail();
    }
    assertEquals(expected, result);
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestSyntaxeParentheseOuvranteTrop() {
    String stringToTest = parentheseOuvranteTrop;
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      subject.AnalSynt();
    } catch (AnalSyntException expected) {
      assertTrue(true);
      System.out.println(expected);
      return;
    } catch (AnalLexException e) {
      fail();
    }
    fail();
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestSyntaxeParentheseFermanteTrop() {
    String stringToTest = parentheseFermanteTrop;
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      subject.AnalSynt();
    } catch (AnalSyntException expected) {
      assertTrue(true);
      System.out.println(expected);
      return;
    } catch (AnalLexException e) {
      fail();
    }
    fail();
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestSyntaxeNombreSuivi() {
    String stringToTest = nombreSuivi;
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      subject.AnalSynt();
    } catch (AnalSyntException expected) {
      assertTrue(true);
      System.out.println(expected);
      return;
    } catch (AnalLexException e) {
      fail();
    }
    fail();
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestSyntaxeOperateurFini() {
    String stringToTest = operateurFini;
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      subject.AnalSynt();
    } catch (AnalSyntException expected) {
      assertTrue(true);
      System.out.println(expected);
      return;
    } catch (AnalLexException e) {
      fail();
    }
    fail();
  }

  @org.junit.jupiter.api.Test
  void AnalSyntTestSyntaxeVariableSuivi() {
    String stringToTest = variableSuivi;
    System.out.print("\nRésultats : ");
    DescenteRecursive subject = new DescenteRecursive(stringToTest);
    try {
      subject.AnalSynt();
    } catch (AnalSyntException expected) {
      assertTrue(true);
      System.out.println(expected);
      return;
    } catch (AnalLexException e) {
      fail();
    }
    fail();
  }

}
