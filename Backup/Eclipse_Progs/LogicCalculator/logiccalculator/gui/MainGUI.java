package logiccalculator.gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import logiccalculator.core.Constants;
import logiccalculator.core.Processor;

/**
 *
 * @author chavez
 */
public class MainGUI extends JFrame implements ActionListener, PropertyChangeListener {

    private int mode = Constants.LOGIC_MODE;   /* LOGIC_MODE, LOGICAL_ENTAILMENT_MODE, NORMAL_FORM_MODE */
    private JTextArea txtInput;
    private JTabbedPane tabOutput;
    private JTextArea txtOutput;
    private JTextArea txtDebug;
    private JButton btnGo;
    protected DropDownButton ddbGo;
    private JButton btnBackspace;
    private JButton btnClear;
    private JButton btnP;
    private JButton btnQ;
    private JButton btnR;
    private JButton btnS;
    private JButton btnT;
    private JButton btnU;
    private JButton btnW;
    private JButton btnNOT;
    private JButton btnAND;
    private JButton btnOR;
    private JButton btnIMPLIES;
    private JButton btnBIMPLIES;
    private JButton btnDEDUCTION;
    private JButton btnSEPARATOR;
    private JButton btnLeftPar;
    private JButton btnRightPar;
    private JButton btnA;
    private JButton btnB;
    private JButton btnC;
    private JButton btnD;
    private JButton btnE;
    private JButton btnF;
    private JButton btnG;
    private JButton btnH;
    private JButton btnI;
    private JButton btnJ;
    private JButton btnK;
    private JButton btnL;
    private JButton btnM;
    private JButton btnN;
    private JButton btnO;
    private JButton btnX;
    private JButton btnY;
    private JButton btnZ;
    protected JLabel lblStatus;
    public JProgressBar progressBar;
    private JPanel pnlStatusBar;
    private JRadioButton rbLogic;
    private JRadioButton rbDeduction;
    private JRadioButton rbNormalForm;
    private ThreadWorker threadWorker;
    private JMenuItem miExp2;
    private JMenuItem miExp5;
    private JMenuItem miExp10;
    private JMenuItem miExp20;
    private JMenuItem miExp21;
    private JMenuItem miExp22;
    private JMenuItem miExp23;
    private JMenuItem miExp24;
    private JMenuItem miExit;
    private JMenuItem miHelp;
    private JMenuItem miAbout;

    /**
     * Constructor
     */
    public MainGUI() {
        super("Logic Calculator");
    }

    public void createGUI() {
        JPanel pnlInput = this.createInputPanel();
        btnP = new JButton("p");
        btnQ = new JButton("q");
        btnR = new JButton("r");
        btnS = new JButton("s");
        btnT = new JButton("t");
        btnU = new JButton("u");
        btnW = new JButton("w");
        btnNOT = new JButton(Constants.VISUAL_NOT);
        btnAND = new JButton(Constants.VISUAL_AND);
        btnOR = new JButton(Constants.VISUAL_OR);
        btnIMPLIES = new JButton(Constants.VISUAL_IMPLIES);
        btnBIMPLIES = new JButton(Constants.VISUAL_BIMPLIES);
        btnDEDUCTION = new JButton(Constants.VISUAL_DEDUCTION);
        btnSEPARATOR = new JButton(Constants.VISUAL_SEPARATOR);
        btnLeftPar = new JButton(Constants.VISUAL_LEFT_PAR);
        btnRightPar = new JButton(Constants.VISUAL_RIGHT_PAR);
        btnP.addActionListener(this);
        btnQ.addActionListener(this);
        btnR.addActionListener(this);
        btnS.addActionListener(this);
        btnT.addActionListener(this);
        btnU.addActionListener(this);
        btnW.addActionListener(this);
        btnLeftPar.addActionListener(this);
        btnRightPar.addActionListener(this);
        btnNOT.addActionListener(this);
        btnNOT.setToolTipText("Negation (not)");
        btnAND.addActionListener(this);
        btnAND.setToolTipText("Conjuntion (and)");
        btnOR.addActionListener(this);
        btnOR.setToolTipText("Disjunction (or)");
        btnIMPLIES.addActionListener(this);
        btnIMPLIES.setToolTipText("Implication (conditional)");
        btnBIMPLIES.addActionListener(this);
        btnBIMPLIES.setToolTipText("Equality (biconditional)");
        btnDEDUCTION.addActionListener(this);
        btnDEDUCTION.setToolTipText("Deduction");
        btnSEPARATOR.addActionListener(this);
        btnSEPARATOR.setToolTipText("formula separator");
        btnLeftPar.setToolTipText("Left par");
        btnRightPar.setToolTipText("Right par");

        JPanel pnlOperators = new JPanel(new GridLayout(3, 0, 5, 5));
        pnlOperators.setBorder(BorderFactory.createTitledBorder("Operators"));
        pnlOperators.add(btnNOT);
        pnlOperators.add(btnAND);
        pnlOperators.add(btnOR);
        pnlOperators.add(btnLeftPar);
        pnlOperators.add(btnRightPar);
        pnlOperators.add(btnIMPLIES);
        pnlOperators.add(btnBIMPLIES);
        pnlOperators.add(btnDEDUCTION);
        pnlOperators.add(btnSEPARATOR);

        btnA = new JButton("a");
        btnB = new JButton("b");
        btnC = new JButton("c");
        btnD = new JButton("d");
        btnE = new JButton("e");
        btnF = new JButton("f");
        btnG = new JButton("g");
        btnH = new JButton("h");
        btnI = new JButton("i");
        btnJ = new JButton("j");
        btnK = new JButton("k");
        btnL = new JButton("l");
        btnM = new JButton("m");
        btnN = new JButton("n");
        btnO = new JButton("o");
        btnX = new JButton("x");
        btnY = new JButton("y");
        btnZ = new JButton("z");

        btnA.addActionListener(this);
        btnB.addActionListener(this);
        btnC.addActionListener(this);
        btnD.addActionListener(this);
        btnE.addActionListener(this);
        btnF.addActionListener(this);
        btnG.addActionListener(this);
        btnH.addActionListener(this);
        btnI.addActionListener(this);
        btnJ.addActionListener(this);
        btnK.addActionListener(this);
        btnL.addActionListener(this);
        btnM.addActionListener(this);
        btnN.addActionListener(this);
        btnO.addActionListener(this);
        btnX.addActionListener(this);
        btnY.addActionListener(this);
        btnZ.addActionListener(this);

        JPanel pnlVariables = new JPanel(new GridLayout(3, 0, 5, 5));
        pnlVariables.setBorder(BorderFactory.createTitledBorder("Variables"));
        pnlVariables.add(btnP);
        pnlVariables.add(btnQ);
        pnlVariables.add(btnR);
        pnlVariables.add(btnS);
        pnlVariables.add(btnT);
        pnlVariables.add(btnA);
        pnlVariables.add(btnB);
        pnlVariables.add(btnC);
        pnlVariables.add(btnD);
        pnlVariables.add(btnE);
        pnlVariables.add(btnF);
        pnlVariables.add(btnG);
        pnlVariables.add(btnH);
        pnlVariables.add(btnI);
        pnlVariables.add(btnJ);
        pnlVariables.add(btnK);
        pnlVariables.add(btnL);
        pnlVariables.add(btnM);
        pnlVariables.add(btnN);
        pnlVariables.add(btnO);
        pnlVariables.add(btnW);
        pnlVariables.add(btnX);
        pnlVariables.add(btnY);
        pnlVariables.add(btnZ);

        JPanel pnlButtons = new JPanel(new BorderLayout());
        pnlButtons.add(pnlInput, "North");
        pnlButtons.add(pnlOperators, "West");
        pnlButtons.add(pnlVariables, "East");

        // Buttons and text fields
        JPanel pnlNorth = new JPanel(new BorderLayout());
        pnlNorth.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlNorth.add(pnlButtons);

        tabOutput = this.createOutputPanel();

        // Tabs
        JPanel pnlCenter = new JPanel(new BorderLayout());
        pnlCenter.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        pnlCenter.add(tabOutput);

        JMenuBar mnuBar = this.createMenu();
        this.setJMenuBar(mnuBar);

        btnDEDUCTION.setEnabled(false);
        btnSEPARATOR.setEnabled(false);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        lblStatus = new JLabel("", JLabel.TRAILING);
        lblStatus.setFont(new Font("", Font.PLAIN, 12));
        pnlStatusBar = new JPanel(new BorderLayout(10, 10));
        pnlStatusBar.add(progressBar, BorderLayout.WEST);
        pnlStatusBar.add(lblStatus, BorderLayout.EAST);
        pnlStatusBar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEtchedBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        this.add(pnlNorth, BorderLayout.NORTH);
        this.add(pnlCenter, BorderLayout.CENTER);
        this.add(pnlStatusBar, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocation(200, 100);
    }

    private JMenuBar createMenu() {
        JMenu menuCalculator = new JMenu("Calculator");
        menuCalculator.setMnemonic(KeyEvent.VK_C);
        miExit = new JMenuItem("Exit", KeyEvent.VK_E);
        miExit.addActionListener(this);
        menuCalculator.add(miExit);

        JMenu menuExamples = new JMenu("Examples");
        menuExamples.setMnemonic(KeyEvent.VK_E);
        miExp2 = new JMenuItem("2 variables formula", KeyEvent.VK_2);
        miExp2.addActionListener(this);
        menuExamples.add(miExp2);
        miExp5 = new JMenuItem("5 variables formula", KeyEvent.VK_5);
        miExp5.addActionListener(this);
        menuExamples.add(miExp5);
        miExp10 = new JMenuItem("10 variables formula", KeyEvent.VK_1);
        miExp10.addActionListener(this);
        menuExamples.add(miExp10);
        miExp20 = new JMenuItem("20 variables formula", KeyEvent.VK_0);
        miExp20.addActionListener(this);
        menuExamples.add(miExp20);
        menuExamples.addSeparator();
        miExp21 = new JMenuItem("21 variables formula");
        miExp21.addActionListener(this);
        menuExamples.add(miExp21);
        miExp22 = new JMenuItem("22 variables formula");
        miExp22.addActionListener(this);
        menuExamples.add(miExp22);
        miExp23 = new JMenuItem("23 variables formula");
        miExp23.addActionListener(this);
        menuExamples.add(miExp23);
        miExp24 = new JMenuItem("24 variables formula");
        miExp24.addActionListener(this);
        menuExamples.add(miExp24);

        JMenu menuHelp = new JMenu("Help");
        menuHelp.setMnemonic(KeyEvent.VK_H);
        miHelp = new JMenuItem("Contents", KeyEvent.VK_C);
        miHelp.addActionListener(this);
        miAbout = new JMenuItem("About", KeyEvent.VK_A);
        miAbout.addActionListener(this);
        menuHelp.add(miHelp);
        menuHelp.add(miAbout);

        JMenuBar theMenuBar = new JMenuBar();
        theMenuBar.add(menuCalculator);
        theMenuBar.add(menuExamples);
        theMenuBar.add(menuHelp);
        return theMenuBar;
    }

    private JPanel createInputPanel() {
        txtInput = new JTextArea("", 2, 2);
        txtInput.setFont(new Font("", Font.BOLD, 16));
        txtInput.setEditable(false);
        JScrollPane scrollInput = new JScrollPane(txtInput);
        scrollInput.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        btnGo = new JButton(" \u21B5 ");
        btnGo.addActionListener(this);
        btnGo.setToolTipText("Go!");

        ddbGo = new DropDownButton(" \u21B5 ", this);
        btnBackspace = new JButton(" \u2190 ");
        btnBackspace.addActionListener(this);
        btnBackspace.setToolTipText("Backspace");

        btnClear = new JButton(" CE ");
        btnClear.addActionListener(this);
        btnClear.setToolTipText("Clear");

        JPanel pnlSub = new JPanel();
        pnlSub.add(btnBackspace);
        pnlSub.add(btnClear);

        JPanel pnlGo = new JPanel();
        pnlGo.add(btnGo);
        btnGo.setVisible(false);
        pnlGo.add(ddbGo);
        pnlGo.add(btnBackspace);
        pnlGo.add(btnClear);

        rbLogic = new JRadioButton("<html>Logic Formula Evaluator<html>");
        rbLogic.addActionListener(this);
        rbLogic.setSelected(true);
        rbLogic.setMnemonic(KeyEvent.VK_B);

        rbDeduction = new JRadioButton("<html>Logical Entailment<html>");
        rbDeduction.setHorizontalAlignment(JLabel.CENTER);
        rbDeduction.addActionListener(this);
        rbDeduction.setMnemonic(KeyEvent.VK_D);

        rbNormalForm = new JRadioButton("<html>FNC/FND Converter<html>");
        rbNormalForm.addActionListener(this);
        rbNormalForm.setMnemonic(KeyEvent.VK_F);

        ButtonGroup rbGroup = new ButtonGroup();
        rbGroup.add(rbLogic);
        rbGroup.add(rbDeduction);
        rbGroup.add(rbNormalForm);

        JPanel pnlMode = new JPanel(new BorderLayout(10, 10));
        TitledBorder borde = BorderFactory.createTitledBorder(" ");
        borde.setTitleJustification(TitledBorder.CENTER);
        borde.setTitlePosition(TitledBorder.DEFAULT_POSITION);
        pnlMode.setBorder(borde);
        pnlMode.add(rbLogic, "West");
        pnlMode.add(rbDeduction);
        pnlMode.add(rbNormalForm, "East");

        JPanel thePanel = new JPanel(new BorderLayout());
        thePanel.add(scrollInput);
        thePanel.add(pnlGo, BorderLayout.EAST);
        thePanel.add(pnlMode, BorderLayout.SOUTH);

        return thePanel;
    }

    private JTabbedPane createOutputPanel() {
        txtOutput = Constants.TXT_OUTPUT;
        JScrollPane scrollOutput = new JScrollPane(txtOutput);
        
        txtDebug = Constants.TXT_DEBUG;
        JScrollPane scrollDebug = new JScrollPane(txtDebug);
        
        JTabbedPane theTabbedPane = new JTabbedPane();
        theTabbedPane.addTab("Result", scrollOutput);
        theTabbedPane.addTab("Debug", scrollDebug);
        return theTabbedPane;
    }

    private void clearAll() {
        txtInput.setText("");
        this.clearOutput();
    }

    private void clearOutput() {
        txtOutput.setText("");
        txtDebug.setText("");
        progressBar.setValue(0);
        lblStatus.setText("");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnGo) {
            this.go(true);
        } else if (e.getSource() == miHelp) {
            new HelpDialog(this).setVisible(true);
        } else if (e.getSource() == miAbout) {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String today = formatter.format(new Date());
            JOptionPane.showMessageDialog(this, "<html><strong>Logic Calculator</strong> version <strong>1.0</strong><br /><br />"
                    + "<pre style='font-size: small'>Requires JRE 5 or above</pre><br />"
                    + "Last compilation: <strong>" + today + "</strong><br /><br />"
                    + "Contact: <pre style='color: blue'>chavez@programador.com</pre>"
                    + "<pre  style='color: blue'>maripozos@gmail.com</pre></html>",
                    "About Logic Calculator",
                    JOptionPane.INFORMATION_MESSAGE);
        } else if (e.getSource() == miExp2) {
            this.clearAll();
            txtInput.setText("a → b ");
        } else if (e.getSource() == miExp5) {
            this.clearAll();
            txtInput.setText("a → b ∧ c ∨ d → e ");
        } else if (e.getSource() == miExp10) {
            this.clearAll();
            txtInput.setText("a → b ∧ c ∨ d → e → ( f ∧ g → h ∧ ( i ∨ j ) ) ");
        } else if (e.getSource() == miExp20) {
            this.clearAll();
            txtInput.setText("a → b ∧ c ∨ d → e → ( f ∧ g → h ∧ ( i ∨ j ) ) → k ∧ l ∨ ¬m ∨ n ∧ o → p ∨ q → r ∧ s → t ");
        } else if (e.getSource() == miExp21) {
            this.clearAll();
            txtInput.setText("a → b ∧ c ∨ d → e → ( f ∧ g → h ∧ ( i ∨ j ) ) → k ∧ l ∨ ¬m ∨ n ∧ o → p ∨ q → r ∧ s → ( t ∧ w ) ");
        } else if (e.getSource() == miExp22) {
            this.clearAll();
            txtInput.setText("a → b ∧ c ∨ d → e → ( f ∧ g → h ∧ ( i ∨ j ) ) → k ∧ l ∨ ¬m ∨ n ∧ o → p ∨ q → r ∧ s → ( t ∧ w ∧ x ) ");
        } else if (e.getSource() == miExp23) {
            this.clearAll();
            txtInput.setText("a → b ∧ c ∨ d → e → ( f ∧ g → h ∧ ( i ∨ j ) ) → k ∧ l ∨ ¬m ∨ n ∧ o → p ∨ q → r ∧ s → ( t ∧ w ∧ x → y ) ");
        } else if (e.getSource() == miExp24) {
            this.clearAll();
            txtInput.setText("a → b ∧ c ∨ d → e → ( f ∧ g → h ∧ ( i ∨ j ) ) → k ∧ l ∨ ¬m ∨ n ∧ o → p ∨ q → r ∧ s → ( t ∧ w ∧ x → y ∧ ¬z ) ");
        } else if (e.getSource() == miExit) {
            System.exit(0);
        } else if (e.getSource() == rbLogic) {
            this.changeToLogicMode();
        } else if (e.getSource() == rbDeduction) {
            this.changeToDeductionMode();
        } else if (e.getSource() == rbNormalForm) {
            this.changeToNormalFormMode();
        } else if (e.getSource() == btnBackspace) {
            this.backspace();
        } else if (e.getSource() == btnClear) {
            this.clearAll();
        } else if (e.getSource() == btnSEPARATOR) {
            txtInput.append(Constants.VISUAL_SEPARATOR);
        } else if (e.getSource() == btnNOT) {
            txtInput.append(Constants.VISUAL_NOT);
        } else if (e.getSource() == btnAND) {
            txtInput.append(Constants.VISUAL_AND + " ");
        } else if (e.getSource() == btnOR) {
            txtInput.append(Constants.VISUAL_OR + " ");
        } else if (e.getSource() == btnIMPLIES) {
            txtInput.append(Constants.VISUAL_IMPLIES + " ");
        } else if (e.getSource() == btnBIMPLIES) {
            txtInput.append(Constants.VISUAL_BIMPLIES + " ");
        } else if (e.getSource() == btnDEDUCTION) {
            txtInput.append(Constants.VISUAL_DEDUCTION + " ");
        } else if (e.getSource() == btnLeftPar) {
            txtInput.append(Constants.VISUAL_LEFT_PAR + " ");
        } else if (e.getSource() == btnRightPar) {
            txtInput.append(Constants.VISUAL_RIGHT_PAR + " ");;
        } else if (e.getSource() == btnP) {
            txtInput.append("p ");
        } else if (e.getSource() == btnQ) {
            txtInput.append("q ");
        } else if (e.getSource() == btnR) {
            txtInput.append("r ");
        } else if (e.getSource() == btnS) {
            txtInput.append("s ");
        } else if (e.getSource() == btnT) {
            txtInput.append("t ");
        } else if (e.getSource() == btnU) {
            txtInput.append("u ");
        } else if (e.getSource() == btnW) {
            txtInput.append("w ");
        } else if (e.getSource() == btnX) {
            txtInput.append("x ");
        } else if (e.getSource() == btnY) {
            txtInput.append("y ");
        } else if (e.getSource() == btnZ) {
            txtInput.append("z ");
        } else if (e.getSource() == btnA) {
            txtInput.append("a ");
        } else if (e.getSource() == btnB) {
            txtInput.append("b ");
        } else if (e.getSource() == btnC) {
            txtInput.append("c ");
        } else if (e.getSource() == btnD) {
            txtInput.append("d ");
        } else if (e.getSource() == btnE) {
            txtInput.append("e ");
        } else if (e.getSource() == btnF) {
            txtInput.append("f ");
        } else if (e.getSource() == btnG) {
            txtInput.append("g ");
        } else if (e.getSource() == btnH) {
            txtInput.append("h ");
        } else if (e.getSource() == btnI) {
            txtInput.append("i ");
        } else if (e.getSource() == btnJ) {
            txtInput.append("j ");
        } else if (e.getSource() == btnK) {
            txtInput.append("k ");
        } else if (e.getSource() == btnL) {
            txtInput.append("l ");
        } else if (e.getSource() == btnM) {
            txtInput.append("m ");
        } else if (e.getSource() == btnN) {
            txtInput.append("n ");
        } else if (e.getSource() == btnO) {
            txtInput.append("o ");
        }
    }
    
    public void go(boolean printTruthTable) {
        if (this.getTheExpression().length() == 0) {
            return;
        }
        this.clearOutput();
        threadWorker = new ThreadWorker(this, printTruthTable);
        threadWorker.addPropertyChangeListener(this);
        threadWorker.execute();
        this.setSize(this.getWidth(), 600);
    }

    public void goWithRank(int init, int end) {
        if (init > end) {
            JOptionPane.showMessageDialog(this, "Wrong final value", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.clearOutput();
        threadWorker = new ThreadWorker(this, init, end);
        threadWorker.addPropertyChangeListener(this);
        threadWorker.execute();
        this.setSize(this.getWidth(), 600);
    }

    public void goOneInterpretation(Processor p, boolean[][] truthTable) {
        this.clearOutput();
        this.progressBar.setValue(0);
        p.processLogic(truthTable, true);
        this.setSize(this.getWidth(), 550);
        this.progressBar.setValue(this.progressBar.getMaximum());
        this.progressBar.setStringPainted(true);
    }

    public void goWithModels(int init, int end) {
        this.clearOutput();
        threadWorker = new ThreadWorker(this, init, end);
        threadWorker.addPropertyChangeListener(this);
        threadWorker.execute();
    }

    @Override
    public void propertyChange(PropertyChangeEvent pce) {
    }

    private void backspace() {
        if (txtInput.getText().length() == 0) {
            return;
        }
        String tempInput = txtInput.getText().substring(0, txtInput.getText().length() - 2).trim();
        txtInput.setText(tempInput + " ");
    }

    private void cleanInput(String wrongSymbol) {
        int pos = txtInput.getText().indexOf(wrongSymbol);
        if (pos > 0) {
            txtInput.setText(txtInput.getText().substring(0, pos));
        }
    }

    private void changeToLogicMode() {
        btnBIMPLIES.setEnabled(true);
        btnDEDUCTION.setEnabled(false);
        btnSEPARATOR.setEnabled(false);
        ddbGo.activeDropdownAll(true);
        this.mode = Constants.LOGIC_MODE;
        /*
         * if <theExpression> has a <Constants.SEPARATOR> or <Constants.DEDUCTION>
         * then clean <theExpression> to the non-valid token
         */
        this.cleanInput(Constants.VISUAL_SEPARATOR);
        this.cleanInput(Constants.VISUAL_DEDUCTION);
        this.clearOutput();
    }

    private void changeToNormalFormMode() {
        btnBIMPLIES.setEnabled(false);
        btnDEDUCTION.setEnabled(false);
        btnSEPARATOR.setEnabled(false);
        ddbGo.activeDropdownAll(false);
        this.mode = Constants.NORMAL_FORM_MODE;
        /*
         * if <theExpression> has a <Constants.BIMPLIES> or <Constants.SEPARATOR> or <Constants.DEDUCTION>
         * then clean <theExpression> to the non-valid token
         */
        this.cleanInput(Constants.VISUAL_BIMPLIES);
        this.cleanInput(Constants.VISUAL_SEPARATOR);
        this.cleanInput(Constants.VISUAL_DEDUCTION);
        this.clearOutput();
    }

    private void changeToDeductionMode() {
        btnBIMPLIES.setEnabled(true);
        btnDEDUCTION.setEnabled(true);
        btnSEPARATOR.setEnabled(true);
        ddbGo.activeDropdownAll(true);
        ddbGo.activeDropdown2(false);
        this.mode = Constants.LOGICAL_ENTAILMENT_MODE;
        this.clearOutput();
    }

    public String getTheExpression() {
        return txtInput.getText().replaceAll("\\s", "");
    }

    public int getMode() {
        return mode;
    }
}
