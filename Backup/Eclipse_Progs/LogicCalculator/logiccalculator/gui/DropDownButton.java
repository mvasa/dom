package logiccalculator.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JToggleButton;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import logiccalculator.core.Processor;

/**
 *
 * @author chavez
 */

public class DropDownButton extends JToggleButton {
    private static JPopupMenu popup;
    JMenuItem miRun;
    JMenuItem miRunNoTruthTable;
    JMenuItem miRankValues;
    JMenuItem miInterpretationValues;
    MainGUI mainGUI;
    
    public DropDownButton(String text, MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        this.setText(text);
        this.setToolTipText("Truth table printing options");
        popup = new JPopupMenu();
        MyMenuItemListener menuItemListener = new MyMenuItemListener();
        miRun = new JMenuItem("Process");
        miRun.setToolTipText("Process formula printing the overall truth table");
        miRun.addActionListener(menuItemListener);
        popup.add(miRun);
        popup.addSeparator();
        miRunNoTruthTable = new JMenuItem("Process (Only display results)");
        miRunNoTruthTable.setToolTipText("Process formula or interpretation without printting\nthe corresponding truth table");
        miRunNoTruthTable.addActionListener(menuItemListener);
        popup.add(miRunNoTruthTable);
        miRankValues = new JMenuItem("Display part of the truth table...");
        miRankValues.setToolTipText("Introduce init and end values for the resulting truth table");
        miRankValues.addActionListener(menuItemListener);
        popup.add(miRankValues);
        miInterpretationValues = new JMenuItem("Process one interpretation...");
        miInterpretationValues.setToolTipText("Introduce values for all the variables in the formula");
        miInterpretationValues.addActionListener(menuItemListener);
        popup.add(miInterpretationValues);
        popup.addPopupMenuListener(new MyPopupmenuListener(this));
        this.addItemListener(new MyItemListener(this));
    }
    
    public void activeDropdownAll(boolean active) {
        miRunNoTruthTable.setEnabled( active );
        miInterpretationValues.setEnabled( active );
        miRankValues.setEnabled( active );
    }
    
    public void activeDropdown2(boolean active) {
        miInterpretationValues.setEnabled( active );
        miRankValues.setEnabled( active );
    }
    
    private class MyPopupmenuListener implements PopupMenuListener {
        DropDownButton btn;
        
        MyPopupmenuListener(DropDownButton btn) {
            this.btn = btn;
        }
        
        public void popupMenuWillBecomeVisible(PopupMenuEvent pme) { }

        public void popupMenuWillBecomeInvisible(PopupMenuEvent pme) {
            btn.setSelected(false);
        }

        public void popupMenuCanceled(PopupMenuEvent pme) {
            btn.setSelected(false);
        }
    }
    
    private class MyItemListener implements ItemListener {
        DropDownButton btn;
        
        MyItemListener(DropDownButton btn) {
            this.btn = btn;
        }
        
        public void itemStateChanged(ItemEvent ie) {
            int state = ie.getStateChange();
                if (state == ItemEvent.SELECTED) {
                     /* Show popup menu on toggleButton at position: (0, height) */
                    popup.show(btn, 0, btn.getHeight());
                }
        }
    }
    
    private class MyMenuItemListener implements ActionListener {

        public void actionPerformed(ActionEvent ae) {
            if (ae.getSource() == miRun) {
                mainGUI.go(true);
            } else if (ae.getSource() == miRunNoTruthTable) {
                mainGUI.go(false);
            } else if (ae.getSource() == miInterpretationValues) {
                /* If there is no characters in expression */
                if (mainGUI.getTheExpression().length() == 0) {
                    return;
                }
                Processor p = new Processor(mainGUI.getTheExpression(), mainGUI);
                InterpretationComponent dlg = new InterpretationComponent( p.getAtomsList() );
                int result = JOptionPane.showConfirmDialog(mainGUI, dlg.getComponents(), "Process interpretation", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == 0) {
                    boolean[][] truthTable = dlg.getVariableValues();
                    mainGUI.goOneInterpretation(p, truthTable);
                }
            } else if (ae.getSource() == miRankValues) {
                /* If there is no characters in expression */
                if (mainGUI.getTheExpression().length() == 0) {
                    return;
                }
                Processor p = new Processor(mainGUI.getTheExpression(), mainGUI);
                RankValuesComponent dlg = new RankValuesComponent( p.getNumInterpretations() );
                int result = JOptionPane.showConfirmDialog(mainGUI, dlg.getComponents(), "Rank values", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == 0) {
                    int init = (Integer)dlg.txtInitRank.getValue();
                    int end  = (Integer)dlg.txtEndRank.getValue();
                    mainGUI.goWithRank(init, end);
                }
            }
        }
    }
    
    @Override
    public String getName() {
        return "Truth table printing options";
    }
    
}
