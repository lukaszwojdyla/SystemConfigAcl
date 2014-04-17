/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.utils;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import pl.edu.agh.model.EntityType;

/**
 *
 * @author lukasz
 */
public class FileOperator {

    public void saveAcls(JTable aclList) {
        int rowsCount = aclList.getRowCount();
        int columnsCount = aclList.getColumnCount();

        for (int i = 0; i < rowsCount; i++) {
            if (!aclList.getValueAt(i, 0).equals("-----") && !aclList.getValueAt(i, 1).equals(EntityType.NEW)) {
                List<String> entity = new ArrayList<>();
                for (int j = 0; j < columnsCount; j++) {
                    entity.add((String) aclList.getValueAt(i, j).toString());
                }
                System.out.println(entity.toString());
            }
        }
    }
}
