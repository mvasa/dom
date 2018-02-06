package logiccalculator.gui;

/**
 *
 * @author chavez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo lfInfo : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(lfInfo.getName())) {
                    javax.swing.UIManager.setLookAndFeel(lfInfo.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        } catch (InstantiationException ex) {
            System.out.println(ex);
        } catch (IllegalAccessException ex) {
            System.out.println(ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println(ex);
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {  
            @Override
            public void run() {
                MainGUI mainGUI = new MainGUI();
                mainGUI.createGUI();
                mainGUI.setVisible(true);
            }  
        });
    }
}
