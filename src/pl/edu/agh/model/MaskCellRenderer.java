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
public class MaskCellRenderer extends JCheckBox implements TableCellRenderer {

    private final boolean deselected;

    public MaskCellRenderer(Boolean deselected) {
        super();
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        this.deselected = deselected;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (deselected) {
            setBackground(Color.YELLOW);
            setForeground(Color.YELLOW);
        } else {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
        }
        setSelected((value != null && ((Boolean) value).booleanValue()));
        return this;
    }
}
