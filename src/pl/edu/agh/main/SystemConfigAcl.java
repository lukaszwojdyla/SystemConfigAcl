/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in 
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
the editor.
 */
package pl.edu.agh.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
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
public class SystemConfigAcl extends JFrame {

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
        List<String> users = FileInfo.getSystemUsers();

        namesCombox.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent evt) {
                namesComboxPopupVisible(evt);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent evt) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent evt) {
            }
        });

        typesCombox.addItem(EntityType.USER);
        typesCombox.addItem(EntityType.GROUP);
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("System Config ACL");
        setResizable(false);

        tree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });
        tree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                treeKeyReleased(evt);
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
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        entities = FileInfo.getAclList(currentPath);
        model = new EntityTableModel(entities);
        aclList.setModel(model);
        aclList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        TableColumn tc1 = aclList.getColumnModel().getColumn(0);
        tc1.setCellEditor(new DefaultCellEditor(namesCombox));
        TableColumn tc2 = aclList.getColumnModel().getColumn(1);
        tc2.setCellEditor(new DefaultCellEditor(typesCombox));
        browser.setViewportView(aclList);

        addButton.setText("Add");
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
        });

        removeButton.setText("Remove");
        removeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                removeButtonMouseClicked(evt);
            }
        });

        updateButton.setText("Update");

        javax.swing.GroupLayout aclLayout = new javax.swing.GroupLayout(acl);
        acl.setLayout(aclLayout);
        aclLayout.setHorizontalGroup(
            aclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aclLayout.createSequentialGroup()
                .addGroup(aclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(browser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(aclLayout.createSequentialGroup()
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(removeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(updateButton)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        aclLayout.setVerticalGroup(
            aclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aclLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(browser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(aclLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(removeButton)
                    .addComponent(updateButton)))
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
        List<Entity> list = new ArrayList<>();
        if (tree.getSelectionPath() != null) {
            currentPath = tree.getSelectionPath().getLastPathComponent().toString();
        }
        list = FileInfo.getAclList(currentPath);
        FileInfo.genFileInfo(properties, currentPath);
        namesCombox.setSelectedIndex(-1);
        typesCombox.setSelectedIndex(-1);
        entities.clear();
        entities.addAll(list);
        aclList.updateUI();
        if (list.size() > 0) {
            int lastRow = aclList.convertRowIndexToView(aclList.getRowCount() - 1);
            aclList.setRowSelectionInterval(lastRow, lastRow);
            aclList.setColumnSelectionInterval(0, 0);   
        }
    }//GEN-LAST:event_treeMouseClicked

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        Entity entity = new Entity("NEW", EntityType.NEW);
        entities.add(entity);
        aclList.updateUI();
        aclList.scrollRectToVisible(aclList.getCellRect(aclList.getRowCount() - 1, aclList.getColumnCount(), true));
        int lastRow = aclList.convertRowIndexToView(aclList.getRowCount() - 1);
        aclList.setRowSelectionInterval(lastRow, lastRow);
        aclList.setColumnSelectionInterval(0, 0);
    }//GEN-LAST:event_addButtonMouseClicked

    private void removeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeButtonMouseClicked
        int row = aclList.getSelectedRow();
        if (row >= 0 && row < aclList.getRowCount()) {
            entities.remove(row);
            aclList.updateUI();
            if (aclList.getRowCount() > 0 ) {
                int lastRow = aclList.convertRowIndexToView(aclList.getRowCount() - 1);
                aclList.setRowSelectionInterval(lastRow, lastRow);
                aclList.setColumnSelectionInterval(0, 0);
            }
        }
    }//GEN-LAST:event_removeButtonMouseClicked

    private void treeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_treeKeyReleased
        if (tree.getSelectionPath() != null) {
            currentPath = tree.getSelectionPath().getLastPathComponent().toString();
        }
        FileInfo.genFileInfo(properties, currentPath);
    }//GEN-LAST:event_treeKeyReleased

    private void namesComboxPopupVisible(PopupMenuEvent evt) {
        System.out.println("TEST2");
        
        int column = aclList.getSelectedColumn();
        int row = aclList.getSelectedRow();
        EntityType type = (EntityType) aclList.getValueAt(row, 1);
        List<String> types = new ArrayList<>();
        System.out.println(type);
        
        switch (type) {
            case GROUP:
                System.out.println("group");
                types.addAll(FileInfo.getSystemGroups());
                break;
            case USER:
                System.out.println("user");
                types.addAll(FileInfo.getSystemUsers());
                break;
            case NEW:
                types.add("-----");
                break;
        }

        namesCombox.setModel(new DefaultComboBoxModel(types.toArray()));
    }

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
    private JComboBox namesCombox = new JComboBox();
    private JComboBox typesCombox = new JComboBox();
    private int i = 0;
    private List<Entity> entities;
    private EntityTableModel model;
    private javax.swing.JButton addButton;
    private javax.swing.JScrollPane browser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel properties;
    private javax.swing.JButton removeButton;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTree tree;
    private final String root = "/";
    private FileSystemModel fileSystemModel = new FileSystemModel(new File(root));
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    private String currentPath = root;
}
