package logiccalculator.gui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author chavez
 */
public class RankValuesComponent {
    JSpinner txtInitRank;
    JSpinner txtEndRank;
    JButton btnOk;
    JButton btnCancel;
    private int endRank;
    
    public RankValuesComponent(int endRank) {
        this.endRank = endRank;
    }
    
    /*public void createGUI() {
        JLabel lblInit = new JLabel("Init:");
        JLabel lblEnd  = new JLabel("End:");
        SpinnerModel modelInit = new SpinnerNumberModel(1, 1, endRank, 1);
        txtInitRank = new JSpinner(modelInit);
        SpinnerModel modelEnd = new SpinnerNumberModel(1, endRank, endRank, 1);
        txtEndRank  = new JSpinner(modelEnd);
        this.setLayout(new GridLayout(2, 2, 10, 10));
        this.add(lblInit);
        this.add(txtInitRank);
        this.add(lblEnd);
        this.add(txtEndRank);
        this.setModal(true);
        this.pack();
    }*/
    
    /*public JPanel getPanel() {
        JPanel pnl = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel lblInit = new JLabel("Initial:");
        JLabel lblEnd  = new JLabel("Final:");
        SpinnerModel modelInit = new SpinnerNumberModel(1, 1, endRank, 1);
        txtInitRank = new JSpinner(modelInit);
        SpinnerModel modelEnd = new SpinnerNumberModel(1, endRank, endRank, 1);
        txtEndRank  = new JSpinner(modelEnd);
        this.setLayout(new GridLayout(2, 2, 10, 10));
        pnl.add(lblInit);
        pnl.add(txtInitRank);
        pnl.add(lblEnd);
        pnl.add(txtEndRank);
        return pnl;
    }*/
    
    public JComponent[] getComponents() {
        SpinnerModel modelInit = new SpinnerNumberModel(1, 1, endRank, 1);
        txtInitRank = new JSpinner(modelInit);
        SpinnerModel modelEnd = new SpinnerNumberModel(endRank, 1, endRank, 1);
        txtEndRank  = new JSpinner(modelEnd);
        JComponent[] inputs = new JComponent[] {new JLabel("Init:"), txtInitRank, new JLabel("End:"), txtEndRank};
        return inputs;
    }
    
}
