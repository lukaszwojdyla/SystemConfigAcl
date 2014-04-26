/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.model;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author lukasz
 */
public class TypeCellRenderer extends JCheckBox implements TableCellRenderer {

    private final boolean owner;

    public TypeCellRenderer(Boolean owner) {
        super();
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        this.owner = owner;
    }

    @Override
    /**
     * TODO TODO TODO TODO
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (owner) {
            System.out.println("trololo");
        }
        setSelected((value != null && ((Boolean) value).booleanValue()));
        return this;
    }
}
