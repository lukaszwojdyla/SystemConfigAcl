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

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lukasz
 */
public class EntityTableModel extends AbstractTableModel {

    private List<Entity> entities;
    private final String[] columnNames = {"Name", "Type", "Read", "Write", "Execute"};
    private final List<Integer> disabledColumns = new ArrayList<>();

    public EntityTableModel(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public int getRowCount() {
        return entities.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return getValueAt(0, columnIndex).getClass();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Entity entity = entities.get(rowIndex);
        Object[] array = entity.getFields();
        return array[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (disabledColumns.contains(col)) {
            return false;
        }
        return true;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        Entity entity = entities.get(rowIndex);
        entity.updateField(value, columnIndex);
        entities.set(rowIndex, entity);
        fireTableCellUpdated(rowIndex, columnIndex);
    }
    
    public void setEntities(List<Entity> entities) {
        this.entities = entities;
        fireTableDataChanged();
    }
    
    public List<Entity> getEntities() {
        return entities;
    }
}
