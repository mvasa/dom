package logiccalculator.gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author chavez
 */
public class InterpretationComponent implements ActionListener {
    JCheckBox[] chkArray;
    List<Character> lstVariables;
    JTable tblInterpretation;
    JButton btnAll;
    
    public InterpretationComponent(List<Character> variables) {
        this.lstVariables = variables;
    }
    
    public JComponent[] getComponents2() {
        tblInterpretation = new JTable(lstVariables.size(), 2);
        
        for (int i = 0; i < lstVariables.size(); i++) {
            tblInterpretation.setValueAt(lstVariables.get(i), i, 0);
            tblInterpretation.setValueAt("", i, 1);
        }
        
        tblInterpretation.getColumnModel().getColumn(0).setHeaderValue("Variable");
        tblInterpretation.getColumnModel().getColumn(1).setHeaderValue("Value");
        tblInterpretation.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblInterpretation.setShowVerticalLines(true);
        JScrollPane scroll = new JScrollPane(tblInterpretation);
        scroll.setPreferredSize(new Dimension(100,200));
        JComponent[] inputs = new JComponent[] {new JLabel("Variable values:"), scroll};
        return inputs;
    }
    
    public boolean[][] getVariableValues2() {
        boolean[][] truthTable = new boolean[1][lstVariables.size()];
        for (int i = 0; i < lstVariables.size(); i++) {
            /* 1 = true; false otherwise */
            truthTable[0][i] = tblInterpretation.getValueAt(i, 1).toString().trim().startsWith("1");
        }
        return truthTable;
    }
    
    public JComponent[] getComponents() {
        JPanel panel = new JPanel( new GridLayout(lstVariables.size()+3, 2) );
        
        btnAll = new JButton("All True");
        btnAll.addActionListener(this);
        panel.add( new JLabel() );
        panel.add( btnAll );
        
        Font f = new Font(null, Font.BOLD, 12);
        JLabel lblVariable = new JLabel("Variable", JLabel.CENTER);
        lblVariable.setFont(f);
        panel.add( lblVariable );
        JLabel lblValue = new JLabel("Value", JLabel.CENTER);
        lblValue.setFont(f);
        panel.add( lblValue );
        /* - \u2013 */
        /* _ \u23AF */
        /*panel.add( new JLabel("\u2015\u2015\u2015\u2015\u2015", JLabel.CENTER) );*/
        panel.add( new JLabel("\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA", JLabel.CENTER) );
        panel.add( new JLabel("\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA\u23BA", JLabel.CENTER) );
        
        chkArray = new JCheckBox[lstVariables.size()];
        int i = 0;
        for (Character c:lstVariables) {
            panel.add( new JLabel(" "+c+" ", JLabel.CENTER) );
            chkArray[i] = new JCheckBox("True");
            panel.add( chkArray[i++] );
        }
        
        JComponent[] inputs = new JComponent[] { panel };
        return inputs;
    }
    
    public boolean[][] getVariableValues() {
        boolean[][] truthTable = new boolean[1][lstVariables.size()];
        for (int i = 0; i < lstVariables.size(); i++) {
            /* 1 = true; false otherwise */
            truthTable[0][i] = chkArray[i].isSelected();
        }
        return truthTable;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnAll) {
            for (JCheckBox chk : chkArray) {
                chk.setSelected(true);
            }
        }
    }
    
}
