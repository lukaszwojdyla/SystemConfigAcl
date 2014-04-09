/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.TableColumn;
import pl.edu.agh.model.Entity;
import pl.edu.agh.model.EntityTableModel;
import pl.edu.agh.model.EntityType;
import pl.edu.agh.utils.FileInfo;
import pl.edu.agh.model.FileSystemModel;

/**
 *
 * @author lukasz
 */
public class SystemConfigAcl extends javax.swing.JFrame {

    public SystemConfigAcl() {
        
        initComponents();
        FileInfo.genFileInfo(properties, root);
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree(fileSystemModel);
        properties = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        acl = new javax.swing.JPanel();
        browser = new javax.swing.JScrollPane();
        aclList = new javax.swing.JTable();
        entities = new ArrayList<>();
        nameCombox.addItem("NieTest1");
        nameCombox.addItem("NieTest2");
        nameCombox.addItem("NieTest3");
        nameCombox.addItem("NieTest4");

        EntityTableModel model = new EntityTableModel(entities);
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("System Config ACL");
        setResizable(false);

        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });
        sp.setViewportView(tree);

        jLabel1.setText("Path");

        jLabel2.setText("jLabel2");

        jLabel3.setText("Type");

        jLabel4.setText("jLabel4");

        jLabel5.setText("Mask");

        jLabel6.setText("jLabel6");

        jLabel7.setText("Flags");

        jLabel8.setText("jLabel8");

        javax.swing.GroupLayout propertiesLayout = new javax.swing.GroupLayout(properties);
        properties.setLayout(propertiesLayout);
        propertiesLayout.setHorizontalGroup(
            propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(propertiesLayout.createSequentialGroup()
                        .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        propertiesLayout.setVerticalGroup(
            propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        for (i = 0 ;i < 50 ; i++) {
            entities.add(new Entity("Test" + i, EntityType.USER));
        }
        aclList.setModel(model);
        aclList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableColumn tc = aclList.getColumnModel().getColumn(0);
        tc.setCellEditor(new DefaultCellEditor(nameCombox));
        browser.setViewportView(aclList);

        jButton1.setText("Add");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setText("Remove");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });

        jButton3.setText("Update");

        javax.swing.GroupLayout aclLayout = new javax.swing.GroupLayout(acl);
        acl.setLayout(aclLayout);
        aclLayout.setHorizontalGroup(
            aclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aclLayout.createSequentialGroup()
                .addGroup(aclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(aclLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        aclLayout.setVerticalGroup(
            aclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aclLayout.createSequentialGroup()
                .addComponent(browser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sp, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(properties, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(acl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(properties, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(acl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treeMouseClicked
        if (tree.getSelectionPath() != null) {
            currentPath = tree.getSelectionPath().getLastPathComponent().toString();
        }
        FileInfo.genFileInfo(properties, currentPath);
    }//GEN-LAST:event_treeMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        Entity entity = new Entity("Test" + i++, EntityType.GROUP);
        entities.add(entity);
        aclList.updateUI();
        aclList.scrollRectToVisible(aclList.getCellRect(aclList.getRowCount() - 1, aclList.getColumnCount(), true));
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        int row = aclList.getSelectedRow();
        if (row >= 0 && row < aclList.getRowCount()) {
            entities.remove(row);
        }
        aclList.updateUI();
    }//GEN-LAST:event_jButton2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(SystemConfigAcl.class.getName()).log(Level.SEVERE, null, ex);
        }
        SwingUtilities.invokeLater(() -> {
            new SystemConfigAcl().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel acl;
    private javax.swing.JTable aclList;
    private JComboBox nameCombox = new JComboBox();
    private int i = 0;
    private List<Entity> entities;
    private javax.swing.JScrollPane browser;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel properties;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTree tree;
    private final String root = "/";
    private FileSystemModel fileSystemModel = new FileSystemModel(new File(root));
    // End of variables declaration//GEN-END:variables
    private String currentPath = root;
}
