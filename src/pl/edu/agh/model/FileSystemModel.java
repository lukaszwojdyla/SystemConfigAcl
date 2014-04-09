/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.model;

import java.io.File;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author lukasz
 */
public class FileSystemModel implements TreeModel {
    private final File root;
    private final Vector<TreeModelListener> listeners;

    public FileSystemModel(File rootDirectory) {
        root = rootDirectory;
        listeners = new Vector<TreeModelListener>();
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        File directory = (File) parent;
        String[] directoryMembers = directory.list();
        return (new File(directory, directoryMembers[index]));
    }

    @Override
    public int getChildCount(Object parent) {
        File fileSystemMember = (File) parent;
        if (fileSystemMember.isDirectory()) {
            String[] directoryMembers = fileSystemMember.list();
            if (directoryMembers == null) {
                return 0;
            }
            return directoryMembers.length;
        } else {
            return 0;
        }
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        File directory = (File) parent;
        File directoryMember = (File) child;
        String[] directoryMemberNames = directory.list();
        int result = -1;

        for (int i = 0; i < directoryMemberNames.length; ++i) {
            if (directoryMember.getName().equals(directoryMemberNames[i])) {
                result = i;
                break;
            }
        }

        return result;
    }

    @Override
    public boolean isLeaf(Object node) {
        return ((File) node).isFile();
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        if (l != null && !listeners.contains(l)) {
            listeners.addElement(l);
        }
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        if (l != null) {
            listeners.removeElement(l);
        }
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // Does Nothing!
    }

    public void fireTreeNodesInserted(TreeModelEvent e) {
        Enumeration<TreeModelListener> listenerCount = listeners.elements();
        while (listenerCount.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listenerCount
                    .nextElement();
            listener.treeNodesInserted(e);
        }
    }

    public void fireTreeNodesRemoved(TreeModelEvent e) {
        Enumeration<TreeModelListener> listenerCount = listeners.elements();
        while (listenerCount.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listenerCount
                    .nextElement();
            listener.treeNodesRemoved(e);
        }

    }

    public void fireTreeNodesChanged(TreeModelEvent e) {
        Enumeration<TreeModelListener> listenerCount = listeners.elements();
        while (listenerCount.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listenerCount
                    .nextElement();
            listener.treeNodesChanged(e);
        }

    }

    public void fireTreeStructureChanged(TreeModelEvent e) {
        Enumeration<TreeModelListener> listenerCount = listeners.elements();
        while (listenerCount.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listenerCount
                    .nextElement();
            listener.treeStructureChanged(e);
        }

    }
}
