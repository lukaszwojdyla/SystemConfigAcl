/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in 
 */
package pl.edu.agh.main;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.LayoutStyle;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
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

    private void initComponents() {

        sp = new JScrollPane();
        tree = new JTree(fileSystemModel);
        properties = new JPanel();
        pathLabel = new JLabel();
        path = new JLabel();
        typeLabel = new JLabel();
        type = new JLabel();
        maskLabel = new JLabel();
        mask = new JLabel();
        flagsLabel = new JLabel();
        flags = new JLabel();
        acl = new JPanel();
        browser = new JScrollPane();
        aclList = new JTable();
        entities = new ArrayList<>();
        List<String> users = fileInfo.getSystemUsers();

        for (EntityType type : EntityType.values()) {
            typesCombox.addItem(type);
        }

        entities = fileInfo.getAclList(currentPath);
        model = new EntityTableModel(entities);
        addButton = new JButton();
        removeButton = new JButton();
        updateButton = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("System Config ACL");
        setResizable(false);

        sp.setViewportView(tree);

        pathLabel.setText("Path");
        path.setText("path");

        typeLabel.setText("Type");
        type.setText("type");

        maskLabel.setText("Mask");
        mask.setText("mask");

        flagsLabel.setText("Flags");
        flags.setText("flags");

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

        GroupLayout propertiesLayout = new GroupLayout(properties);
        properties.setLayout(propertiesLayout);
        propertiesLayout.setHorizontalGroup(
                propertiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(propertiesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(propertiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(pathLabel, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addComponent(flagsLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                                .addComponent(maskLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(typeLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propertiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(type, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(mask, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(flags, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(path, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(propertiesLayout.createSequentialGroup()
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                ));
        propertiesLayout.setVerticalGroup(
                propertiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(propertiesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(propertiesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(pathLabel)
                                .addComponent(path))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propertiesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(typeLabel)
                                .addComponent(type))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propertiesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(maskLabel)
                                .addComponent(mask))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(propertiesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(flagsLabel)
                                .addComponent(flags))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        GroupLayout aclLayout = new GroupLayout(acl);
        acl.setLayout(aclLayout);
        aclLayout.setHorizontalGroup(
                aclLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(aclLayout.createSequentialGroup()
                        .addGroup(aclLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(browser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGroup(aclLayout.createSequentialGroup()
                                        .addComponent(addButton)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(removeButton)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(updateButton)))
                        .addGap(0, 0, Short.MAX_VALUE))
        );
        aclLayout.setVerticalGroup(
                aclLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(aclLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(browser, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(aclLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(addButton)
                                .addComponent(removeButton)
                                .addComponent(updateButton)))
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(sp, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(properties, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(acl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(sp, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                        .addComponent(properties, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
        );
        
                tree.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                treeMouseClicked(evt);
            }
        });
        tree.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                treeKeyReleased(evt);
            }
        });

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

        pack();
    }

    private void treeMouseClicked(java.awt.event.MouseEvent evt) {
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
    }

    /* 
        
     */
    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {
        Entity entity = new Entity("-----", EntityType.NEW);
        entities.add(entity);
        aclList.updateUI();
        aclList.scrollRectToVisible(aclList.getCellRect(aclList.getRowCount() - 1, aclList.getColumnCount(), true));
        int lastRow = aclList.convertRowIndexToView(aclList.getRowCount() - 1);
        aclList.setRowSelectionInterval(lastRow, lastRow);
    }

    private void removeButtonMouseClicked(java.awt.event.MouseEvent evt) {
        int row = aclList.getSelectedRow();
        if (row >= 0 && row < aclList.getRowCount()) {
            entities.remove(row);
            aclList.updateUI();
            if (aclList.getRowCount() > 0) {
                int lastRow = aclList.convertRowIndexToView(aclList.getRowCount() - 1);
                aclList.setRowSelectionInterval(lastRow, lastRow);
            }
        }
    }

    private void treeKeyReleased(java.awt.event.KeyEvent evt) {
        if (tree.getSelectionPath() != null) {
            currentPath = tree.getSelectionPath().getLastPathComponent().toString();
        }
        fileInfo.genFileInfo(properties, currentPath);
    }

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {
        fileOperator.test();
    }

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

    private JPanel acl;
    private JTable aclList;
    private JComboBox namesCombox = new JComboBox();
    private JComboBox typesCombox = new JComboBox();
    private List<Entity> entities;
    private EntityTableModel model;
    private JButton addButton;
    private JScrollPane browser;
    private JLabel flags;
    private JLabel flagsLabel;
    private JLabel mask;
    private JLabel maskLabel;
    private JLabel path;
    private JLabel pathLabel;
    private JPanel properties;
    private JButton removeButton;
    private JScrollPane sp;
    private JTree tree;
    private final String root = "/";
    private FileSystemModel fileSystemModel = new FileSystemModel(new File(root));
    private JLabel type;
    private JLabel typeLabel;
    private JButton updateButton;
    private String currentPath = root;
    private final FileOperator fileOperator = new FileOperator();
    private final FileInfo fileInfo = new FileInfo();
}
