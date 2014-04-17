/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author lukasz
 */
public class EntityTableModel extends AbstractTableModel {

    private List<Entity> entities;
    private final String[] columnNames = {"Name", "Type", "Read", "Execute", "Write"
    };

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
