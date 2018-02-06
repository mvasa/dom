package logiccalculator.core;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chavez
 */
public class AtomList {
    
    public AtomList() {
        
    }
    
    /*************************************************************************/
    /*                                                                       */
    /*  Returns the list with different atoms of an expression               */
    /*                                                                       */
    /*  Example:                                                             */
    /*                                                                       */
    /*  expr = a ^ b -> ( c v b);                                            */
    /*  List<Character> atoms = parseAtoms(expr);                            */
    /*  [a, b, c]                                                            */
    /*                                                                       */
    /*                                                                       */
    /* @param expr The expression to evaluate                                */
    /* @return An list with all different atoms                              */
    /*                                                                       */
    /*************************************************************************/
    public List<Character> parseAtoms(String expr) {
        List<Character> list = new ArrayList<Character>();
        for (int i = 0; i < expr.length(); i++) {
            char token = expr.charAt(i);
            boolean found;
            if (Constants.isVariable(token)) {
                found = false;
                for (char c : list) {
                    if (token == c) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    list.add(token);
                }
            }
        }
        return list;
    }
    
}
