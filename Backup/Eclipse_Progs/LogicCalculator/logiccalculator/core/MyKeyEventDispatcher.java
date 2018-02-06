package logiccalculator.core;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import javax.swing.JTextArea;

/**
 *
 * @author chavez
 */
public class MyKeyEventDispatcher implements KeyEventDispatcher {
    private final JTextArea txtInput;
    
    public MyKeyEventDispatcher(JTextArea txtInput) {
        this.txtInput = txtInput;
    }    
    
    @Override
    public boolean dispatchKeyEvent(KeyEvent ke) {
        int id = ke.getID();
        if (id == KeyEvent.KEY_TYPED) {
            char c = ke.getKeyChar();
            if ( (c >= 'a') && (c <= 'n') ||
                 (c >= 'o') && (c <= 't') ||
                 (c >= 'w') && (c <= 'z')) {
                System.out.println("key character = '" + c + "'");
                txtInput.append(c + " ");
            }
        }
        return false;
    }
    
}
