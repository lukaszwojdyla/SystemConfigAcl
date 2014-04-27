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
