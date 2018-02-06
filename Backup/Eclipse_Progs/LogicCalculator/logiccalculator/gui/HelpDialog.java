package logiccalculator.gui;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JEditorPane;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author chavez
 */
public class HelpDialog extends JDialog implements TreeSelectionListener {
    private final JEditorPane txtInfo;
    private final JTree treeHelp;
    
    public HelpDialog(MainGUI mainGUI) {
        super(mainGUI, "Logic Calculator help", false);
        DefaultMutableTreeNode nodeRoot = new DefaultMutableTreeNode("Help");
        createNodes(nodeRoot);
        
        treeHelp = new JTree(nodeRoot);
        treeHelp.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        treeHelp.setShowsRootHandles(true);
        treeHelp.addTreeSelectionListener(this);
        
        JScrollPane scrollHelp = new JScrollPane(treeHelp);
        scrollHelp.getViewport().setBackground(null);
        scrollHelp.getViewport().setOpaque(false);
        
        txtInfo = new JEditorPane();
        txtInfo.setEditable(false);
        JScrollPane scrollInfo = new JScrollPane(txtInfo);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setOneTouchExpandable(true);
        splitPane.setLeftComponent(scrollHelp);
        splitPane.setRightComponent(scrollInfo);
        splitPane.setDividerLocation(180);
        splitPane.setPreferredSize(new Dimension(500, 300));
        
        this.getContentPane().add(splitPane, BorderLayout.CENTER);      
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.pack();
	this.setBounds (300, 200, 650, 425);
      	
      this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      this.assignFile(txtInfo, "help/main.html");
    }
    
    private void createNodes(DefaultMutableTreeNode nodeRoot) {
        DefaultMutableTreeNode topic = new DefaultMutableTreeNode(new Topic("Main window", "help/main.html"), false);
        nodeRoot.add(topic);
        topic = new DefaultMutableTreeNode(new Topic("Logic mode", "help/logic.html"), false);
        nodeRoot.add(topic);
        topic = new DefaultMutableTreeNode(new Topic("Deduction mode", "help/deduction.html"), false);
        nodeRoot.add(topic);
        topic = new DefaultMutableTreeNode(new Topic("Normal form converter", "help/conversion.html"), false);
        nodeRoot.add(topic);
    }
    
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)treeHelp.getLastSelectedPathComponent();
        
        if (node == null) {
            return;
        }
                
        Object objNodoInfo = node.getUserObject();
        if (node.isLeaf()) {
            Topic topic = (Topic)objNodoInfo;
            assignFile(txtInfo, topic.fileName);
        }
    }
    
    private class Topic {
        private final String title;
        private final String fileName;
        
        /**
         * Creates an Topic instance where:
         * @args title Unique name for a given topic.
         * <br>
         * @args fileName HTML file with the info for the topic  */
        Topic(String title, String fileName) {
            this.title  = title;
            this.fileName = fileName;
        }
        
        /** Returns topic name */
        @Override
        public String toString() {
            return title;
        }
    }
    
    private void assignFile(JEditorPane txt, String file) {
        URL url = null;
        try {
            url = this.getClass().getResource(file);
            if (url == null) {
                url = new URL("file:///" + System.getProperty("user.dir") + "/" + file);
            }
            txt.setPage(url);
        } catch (MalformedURLException e) {
            System.err.println("Error al crear URL para : " + file + "\n" + e);
        } catch (IOException e) {
            System.err.println("Error al cargar URL : " + url + "\n" + e);
        }
    }
    
}
