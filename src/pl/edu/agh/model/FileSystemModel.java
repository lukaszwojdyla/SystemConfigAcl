/* 
 * Copyright (C) 2014 lukasz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.edu.agh.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
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
    private final ArrayList<TreeModelListener> listeners;

    public FileSystemModel(File rootDirectory) {
        root = rootDirectory;
        listeners = new ArrayList<>();
    }

    @Override
    public Object getRoot() {
        return root;
    }

    @Override
    public Object getChild(Object parent, int index) {
        File directory = (File) parent;
        return new File(directory, getSortedChildren(directory).get(index).getName());
    }

    @Override
    public int getChildCount(Object parent) {
        File fileSystemMember = (File) parent;
        if (fileSystemMember.isDirectory()) {
            File[] directoryMembers = fileSystemMember.listFiles();
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

        List<File> children = getSortedChildren(directory);

        return children.indexOf(directoryMember);
    }

    @Override
    public boolean isLeaf(Object node) {
        /*
         * return ((File) node).isFile();
         * returns char device as directory
         */
        return !((File) node).isDirectory();
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        if (l != null && !listeners.contains(l)) {
            listeners.add(l);
        }
    }

    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        if (l != null) {
            listeners.remove(l);
        }
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // Does Nothing!
    }

    private List<File> getSortedChildren(File node) {
        File[] children = node.listFiles();
        Arrays.parallelSort(children, (File o1, File o2) -> {
            if (o1.isDirectory())
                return o2.isDirectory() ? o1.compareTo(o2) : -1;
            else if (o2.isDirectory())
                return 1;
            
            return o1.compareTo(o2);
        });
        return Arrays.asList(children);
    }

    public void fireTreeNodesInserted(TreeModelEvent e) {
        Enumeration<TreeModelListener> listenerCount = Collections.enumeration(listeners);
        while (listenerCount.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listenerCount
                    .nextElement();
            listener.treeNodesInserted(e);
        }
    }

    public void fireTreeNodesRemoved(TreeModelEvent e) {
        Enumeration<TreeModelListener> listenerCount = Collections.enumeration(listeners);
        while (listenerCount.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listenerCount
                    .nextElement();
            listener.treeNodesRemoved(e);
        }
    }

    public void fireTreeNodesChanged(TreeModelEvent e) {
        Enumeration<TreeModelListener> listenerCount = Collections.enumeration(listeners);
        while (listenerCount.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listenerCount
                    .nextElement();
            listener.treeNodesChanged(e);
        }
    }

    public void fireTreeStructureChanged(TreeModelEvent e) {
        Enumeration<TreeModelListener> listenerCount = Collections.enumeration(listeners);
        while (listenerCount.hasMoreElements()) {
            TreeModelListener listener = (TreeModelListener) listenerCount
                    .nextElement();
            listener.treeStructureChanged(e);
        }
    }
}
