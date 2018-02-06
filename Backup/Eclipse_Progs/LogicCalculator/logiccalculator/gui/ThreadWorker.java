/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logiccalculator.gui;

import java.awt.Cursor;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EmptyStackException;
import java.util.Locale;
import java.util.TimeZone;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import logiccalculator.core.Constants;
import logiccalculator.core.Processor;
import normalform.FormalConverter;

/**
 *
 * @author chavez
 */
public class ThreadWorker extends SwingWorker<Void, Void> {

    private final logiccalculator.gui.MainGUI mainGUI;
    private long initTime;
    private long finalTime;
    private boolean error;
    private final int initRank;
    private final int endRank;
    private final boolean printTruthTable;

    /*public ThreadWorker(MainGUI mainGUI) {
     this.mainGUI = mainGUI;
     this.initRank = -1;
     this.endRank  = -1;
     this.printTruthTable = true;
     }*/
    public ThreadWorker(MainGUI mainGUI, boolean printTruthTable) {
        this.mainGUI = mainGUI;
        initRank = -1;
        endRank = -1;
        this.printTruthTable = printTruthTable;
    }

    ThreadWorker(MainGUI mainGUI, int initRank, int endRank) {
        this.mainGUI = mainGUI;
        this.initRank = initRank;
        this.endRank = endRank;
        this.printTruthTable = true;
    }

    @Override
    protected Void doInBackground() {
        try {
            if (mainGUI.getMode() == Constants.NORMAL_FORM_MODE) {
                this.initComponents();
                mainGUI.progressBar.setIndeterminate(true);
                mainGUI.progressBar.setStringPainted(false);
                FormalConverter formalConverter = new FormalConverter(mainGUI.getTheExpression());
                formalConverter.convert();
            } else {
                this.initComponents();
                Processor p = new Processor(mainGUI.getTheExpression(), mainGUI);
                /*boolean printTruthTable = mainGUI.miPrintTruthTable.isSelected();*/
                /*if (p.getNumAtoms() > 20) {
                 printTruthTable = false;
                 JOptionPane.showMessageDialog(mainGUI, "Printing the truth table for more than 20 variables can run out-of-memory.\nOnly the number of models will be printed.");
                 } else if (p.getNumAtoms() > 10) {
                 int n = JOptionPane.showConfirmDialog(mainGUI, "Thruth table of more than 10 variables can be very time-consuming\nDo you want to continue?", "Warning", JOptionPane.WARNING_MESSAGE);
                 printTruthTable = (n == JOptionPane.YES_OPTION);
                 }*/
                Constants.debugln(p.getNumAtoms() + " atoms: " + p.getAtomsList());
                Constants.debugln();
                Constants.debugln("Interpretations: 2^" + p.getNumAtoms() + " = " + p.getNumInterpretations());
                Constants.debugln();
                if (initRank > 0) {
                    p.process(printTruthTable, initRank, endRank);
                } else {
                    p.process(printTruthTable);
                }
            }
            this.error = false;
        } catch (OutOfMemoryError e) {
            this.error = true;
            finalTime = System.currentTimeMillis();
            Constants.println();

            Constants.println(e.toString());
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainGUI, e);
        } catch (StringIndexOutOfBoundsException e) {
            this.error = true;
            finalTime = System.currentTimeMillis();
            Constants.println();
            Constants.println();
            Constants.println(e.toString());
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainGUI, e);
        } catch (EmptyStackException e) {
            this.error = true;
            finalTime = System.currentTimeMillis();
            Constants.println();
            Constants.println(e.toString());
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainGUI, e);
        } catch (ArrayIndexOutOfBoundsException e) {
            this.error = true;
            finalTime = System.currentTimeMillis();
            Constants.println();
            Constants.println(e.toString());
            e.printStackTrace();
            Constants.println(e.toString());
            JOptionPane.showMessageDialog(mainGUI, e);
        } catch (Exception e) {
            this.error = true;
            finalTime = System.currentTimeMillis();
            Constants.println(e.toString());
            e.printStackTrace();
            JOptionPane.showMessageDialog(mainGUI, e);
        }
        return null;
    }

    private void initComponents() {
        mainGUI.lblStatus.setText("Computing...");
        mainGUI.progressBar.setValue(0);
        mainGUI.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        initTime = System.currentTimeMillis();
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        String time = sdf.format(new Date(initTime - TimeZone.getDefault().getRawOffset()));
        Constants.debugln("Init time: " + time);
        Constants.debugln();
        mainGUI.ddbGo.setEnabled(false);
    }

    @Override
    public void done() {
        Toolkit.getDefaultToolkit().beep();
        mainGUI.ddbGo.setEnabled(true);
        mainGUI.setCursor(null);
        mainGUI.progressBar.setIndeterminate(false);
        mainGUI.progressBar.setValue(mainGUI.progressBar.getMaximum());
        mainGUI.progressBar.setStringPainted(true);
        if (!this.error) {
            finalTime = System.currentTimeMillis();
        }
        long totalTime = finalTime - initTime;
        String text = ("Completed in ");
        if (totalTime < 100) {
            text += totalTime + " milliseconds";
        } else if (totalTime < 1000) {
            text += "0." + totalTime + " seconds";
        } else {
            text += (float) totalTime / 1000 + " seconds";
        }
        mainGUI.lblStatus.setText(text);
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        String time = sdf.format(new Date(finalTime - TimeZone.getDefault().getRawOffset()));
        Constants.debugln("End time: " + time);
    }
}
