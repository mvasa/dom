package logiccalculator.core;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextArea;

/**
 *
 * @author chavez
 */
public class Constants {

    public static final JTextArea TXT_OUTPUT;
    public static final JTextArea TXT_DEBUG;
    
    static {
        TXT_OUTPUT = new JTextArea();
        TXT_OUTPUT.setFont(new Font("Monospaced", Font.PLAIN, 14));
        TXT_DEBUG = new JTextArea();
        TXT_DEBUG.setFont(new Font("Monospaced", Font.PLAIN, 13));
        TXT_DEBUG.setForeground(Color.RED);
    }
    
    public static char NOT       = '\u00AC'; /* '¬'; */
    public static char AND       = '\u2227'; /* '^'; */
    public static char OR        = '\u2228'; /* 'v'; */
    public static char IMPLIES   = '\u2192'; /* '>'; */
    public static char BIMPLIES  = '\u2194'; /* '-'; */
    public static char DEDUCTION = '\u22A7'; /* '='; */
    public static char SEPARATOR = ',';
    public static char LEFT_PAR  = '(';
    public static char RIGHT_PAR = ')';
    
    public static String VISUAL_NOT       = "\u00AC";
    public static String VISUAL_AND       = "\u2227";
    public static String VISUAL_OR        = "\u2228";   /* \u22C1  */
    public static String VISUAL_IMPLIES   = "\u2192";
    public static String VISUAL_BIMPLIES  = "\u2194";
    public static String VISUAL_DEDUCTION = "\u22A7";   /* \u21D2 */
    public static String VISUAL_SEPARATOR = ",";
    public static String VISUAL_LEFT_PAR  = "(";
    public static String VISUAL_RIGHT_PAR = ")";
    public static String VISUAL_OK        = "\u2713";   /* \u2714 Bold */
    public static String VISUAL_ERR       = "\u2717";   /* \u2718 Bold */
    public static String VISUAL_BLANK     = "\u0020";   /* " "; */
    
    public static int LOGIC_MODE               = 0;
    public static int LOGICAL_ENTAILMENT_MODE  = 1;
    public static int NORMAL_FORM_MODE         = 2;
    public static int MODELS_MODE              = 3;
    
    public static boolean isVariable(char token) {
        return !isOperator(token) && !isPar(token) && !isVoid(token);
    }

    public static boolean isOperator(char token) {
        return (token == NOT) || (token == AND) || (token == OR)
                || (token == IMPLIES) || (token == BIMPLIES)
                || (token == DEDUCTION) || (token == SEPARATOR);
    }
    
    public static boolean isPar(char token) {
        return (token == LEFT_PAR) || (token == RIGHT_PAR);
    }
    
    public static boolean isNOT(char operator) {
        return (operator == NOT);
    }

    public static boolean isAND(char operator) {
        return (operator == AND);
    }

    public static boolean isOR(char operator) {
        return (operator == OR);
    }

    public static boolean isIMPLIES(char operator) {
        return (operator == IMPLIES);
    }

    public static boolean isBIMPLIES(char operator) {
        return (operator == BIMPLIES);
    }

    public static boolean isDEDUCTION(char operator) {
        return (operator == DEDUCTION);
    }

    public static boolean isSEPARATOR(char operator) {
        return (operator == SEPARATOR);
    }
    
    public static boolean isLEFT_PAR(char operator) {
        return (operator == LEFT_PAR);
    }
    
    public static boolean isRIGHT_PAR(char operator) {
        return (operator == RIGHT_PAR);
    }
    
    public static boolean isVoid(char c) {
        return (c == ' ') || (c == '\t');
    }
    
    public static String toString(char operator) {
        return "" + operator;
    }
    
    public static void print(String cad) {
        TXT_OUTPUT.append(cad);
    }
    
    public static void println(String cad) {
        TXT_OUTPUT.append(cad + "\n");
    }
    
    public static void println() {
        TXT_OUTPUT.append("\n");
    }
    
    public static void printBoolean(boolean value) {
        if ( value ) {
            TXT_OUTPUT.append("1 ");
        }
        else {
            TXT_OUTPUT.append("0 ");
        }
    }
    
    public static void printlnBoolean(boolean value) {
        if ( value ) {
            TXT_OUTPUT.append("1\n");
        }
        else {
            TXT_OUTPUT.append("0\n");
        }
    }
    
    public static void printLineSeparator(int numAtoms, int resultLenght) {
        for (int i = 0; i < numAtoms; i++) {
            TXT_OUTPUT.append("--");
        }
        TXT_OUTPUT.append("|-");
        for (int i = 0; i < resultLenght; i++) {
            TXT_OUTPUT.append("-");
        }
    }
    
    public static void printlnLineSeparator(int numAtoms, int resultLength) {
        printLineSeparator(numAtoms, resultLength);
        TXT_OUTPUT.append("\n");
    }
    
    public static void printlnSeparator(int lenght) {
        for (int i = 0; i < lenght; i++) {
            TXT_OUTPUT.append("-");
        }
        TXT_OUTPUT.append("\n");
    }
    
    public static void printLineNumber(String blanks, int lineNo) {
        TXT_OUTPUT.append(blanks + lineNo + "] ");
    }
    
    public static String getBlanksLine(int numLines) {
        String blanks = "";
        if (numLines > 999999) {
            blanks = "      ";
        } else if (numLines > 99999) {
            blanks = "     ";
        } else if (numLines > 9999) {
            blanks = "    ";
        } else if (numLines > 999) {
            blanks = "   ";
        } else if (numLines > 99) {
            blanks = "  ";
        } else if (numLines > 9) {
            blanks = " ";
        }
        return blanks;
    }
    
    public static String getBlanks(int spaces) {
        String blanks = "";
        for (int i=1; i<=spaces; i++) {
            blanks += " ";
        }
        return blanks;
    }
    
    public static void debug(String cad) {
        TXT_DEBUG.append(cad);
    }
    
    public static void debugln(String cad) {
        TXT_DEBUG.append(cad + "\n");
    }
    
    public static void debugln() {
        TXT_DEBUG.append("\n");
    }
    
}
