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
package pl.edu.agh.mainold;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import pl.edu.agh.utils.FileOperator;

/**
 *
 * @author lukasz
 */
public class SystemConfigAcl extends JFrame {

    public SystemConfigAcl() {
        initComponents();
        fileInfo.genFileInfo(properties, root);
        setLocationRelativeTo(null);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        sp = new javax.swing.JScrollPane();
        tree = new javax.swing.JTree(fileSystemModel);
        properties = new javax.swing.JPanel();
        pathLabel = new javax.swing.JLabel();
        path = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        maskLabel = new javax.swing.JLabel();
        mask = new javax.swing.JLabel();
        flagsLabel = new javax.swing.JLabel();
        flags = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        acl = new javax.swing.JPanel();
        browser = new javax.swing.JScrollPane();
        aclList = new javax.swing.JTable();
        entities = new ArrayList<>();
        List<String> users = fileInfo.getSystemUsers();

        for (EntityType type : EntityType.values()) {
            typesCombox.addItem(type);
        }
        namesCombox.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent evt) {
                namesComboxPopupVisible(evt);
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent evt) {
                namesComboxPopupInvisible(evt);
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent evt) {
            }
        });

        typesCombox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evt) {
                typesComboxItemStateChanged(evt);
            }
        });

        entities = fileInfo.getAclList(currentPath);
        model = new EntityTableModel(entities);
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

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

        pathLabel.setText("Path");

        path.setText("path");

        typeLabel.setText("Type");

        type.setText("type");

        maskLabel.setText("Mask");

        mask.setText("mask");

        flagsLabel.setText("Flags");

        flags.setText("flags");

        jLabel1.setText("Set mask:");

        jCheckBox1.setText("write");
        jCheckBox1.setMaximumSize(new java.awt.Dimension(63, 16));
        jCheckBox1.setMinimumSize(new java.awt.Dimension(63, 16));
        jCheckBox1.setPreferredSize(new java.awt.Dimension(63, 16));
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("read");
        jCheckBox2.setMaximumSize(new java.awt.Dimension(63, 16));
        jCheckBox2.setMinimumSize(new java.awt.Dimension(63, 16));
        jCheckBox2.setPreferredSize(new java.awt.Dimension(63, 16));

        jCheckBox3.setText("execute");
        jCheckBox3.setMaximumSize(new java.awt.Dimension(81, 16));
        jCheckBox3.setMinimumSize(new java.awt.Dimension(81, 16));
        jCheckBox3.setPreferredSize(new java.awt.Dimension(81, 16));

        javax.swing.GroupLayout propertiesLayout = new javax.swing.GroupLayout(properties);
        properties.setLayout(propertiesLayout);
        propertiesLayout.setHorizontalGroup(
            propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(propertiesLayout.createSequentialGroup()
                        .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pathLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(flagsLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(maskLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(typeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(type, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mask, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(path, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(flags, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(propertiesLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        propertiesLayout.setVerticalGroup(
            propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(propertiesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathLabel)
                    .addComponent(path))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(typeLabel)
                    .addComponent(type))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(maskLabel)
                    .addComponent(mask))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(flagsLabel)
                    .addComponent(flags))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(propertiesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        aclList.setModel(model);
        aclList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumn tc1 = aclList.getColumnModel().getColumn(0);
        DefaultCellEditor editor1 = new DefaultCellEditor(namesCombox);
        editor1.setClickCountToStart(2);
        tc1.setCellEditor(editor1);

        TableColumn tc2 = aclList.getColumnModel().getColumn(1);
        DefaultCellEditor editor2 = new DefaultCellEditor(typesCombox);
        editor2.setClickCountToStart(2);
        tc2.setCellEditor(editor2);
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
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
        });

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
                .addComponent(browser, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
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
            .addComponent(sp)
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
        list.addAll(fileInfo.getAclList(currentPath));
        fileInfo.genFileInfo(properties, currentPath);
        namesCombox.setSelectedIndex(-1);
        typesCombox.setSelectedIndex(-1);
        entities.clear();
        entities.addAll(list);
        aclList.updateUI();
        if (list.size() > 0) {
            int lastRow = aclList.convertRowIndexToView(aclList.getRowCount() - 1);
            aclList.setRowSelectionInterval(lastRow, lastRow);
        }
    }//GEN-LAST:event_treeMouseClicked

    /* 
        
     */
    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        Entity entity = new Entity("-----", EntityType.NEW);
        entities.add(entity);
        aclList.updateUI();
        aclList.scrollRectToVisible(aclList.getCellRect(aclList.getRowCount() - 1, aclList.getColumnCount(), true));
        int lastRow = aclList.convertRowIndexToView(aclList.getRowCount() - 1);
        aclList.setRowSelectionInterval(lastRow, lastRow);
    }//GEN-LAST:event_addButtonMouseClicked

    private void removeButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeButtonMouseClicked
        int row = aclList.getSelectedRow();
        if (row >= 0 && row < aclList.getRowCount()) {
            entities.remove(row);
            aclList.updateUI();
            if (aclList.getRowCount() > 0) {
                int lastRow = aclList.convertRowIndexToView(aclList.getRowCount() - 1);
                aclList.setRowSelectionInterval(lastRow, lastRow);
            }
        }
    }//GEN-LAST:event_removeButtonMouseClicked

    private void treeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_treeKeyReleased
        if (tree.getSelectionPath() != null) {
            currentPath = tree.getSelectionPath().getLastPathComponent().toString();
        }
        fileInfo.genFileInfo(properties, currentPath);
    }//GEN-LAST:event_treeKeyReleased

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked
        fileOperator.saveAcls(aclList);
    }//GEN-LAST:event_updateButtonMouseClicked

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void namesComboxPopupVisible(PopupMenuEvent evt) {
        int row = aclList.convertRowIndexToView(aclList.getSelectedRow());
        EntityType type = (EntityType) aclList.getValueAt(row, 1);
        List<String> types = new ArrayList<>();

        switch (type) {
            case GROUP:
            case D_GROUP:
                types.addAll(fileInfo.getSystemGroups());
                break;
            case USER:
            case D_USER:
                types.addAll(fileInfo.getSystemUsers());
                break;
            case NEW:
                types.add("-----");
                break;
        }
        namesCombox.setModel(new DefaultComboBoxModel(types.toArray()));
    }
    
    public void namesComboxPopupInvisible(PopupMenuEvent evt) {
    }

    private void typesComboxItemStateChanged(ItemEvent evt) {
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            int row = aclList.getSelectedRow();
            aclList.getModel().setValueAt("-----", row, 0);
        }
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
    private List<Entity> entities;
    private EntityTableModel model;
    private javax.swing.JButton addButton;
    private javax.swing.JScrollPane browser;
    private javax.swing.JLabel flags;
    private javax.swing.JLabel flagsLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel mask;
    private javax.swing.JLabel maskLabel;
    private javax.swing.JLabel path;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JPanel properties;
    private javax.swing.JButton removeButton;
    private javax.swing.JScrollPane sp;
    private javax.swing.JTree tree;
    private final String root = "/";
    private FileSystemModel fileSystemModel = new FileSystemModel(new File(root));
    private javax.swing.JLabel type;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
    private String currentPath = root;
    private final FileOperator fileOperator =  new FileOperator();
    private final FileInfo fileInfo = new FileInfo();
}
