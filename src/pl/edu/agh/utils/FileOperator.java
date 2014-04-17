/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.utils;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import pl.edu.agh.model.Entity;
import pl.edu.agh.model.EntityTableModel;
import pl.edu.agh.model.EntityType;

/**
 *
 * @author lukasz
 */
public class FileOperator {

    public void saveAcls(JTable aclList, String currentPath) {
        EntityTableModel etm = (EntityTableModel) aclList.getModel();
        List<Entity> entieies = etm.getEntities();
        
        try {
            Process p = Runtime.getRuntime().exec(new String[] { "setfacl", "-b", currentPath });
        } catch (IOException ex) {
            Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for (Entity entity : entieies) {
            if (!entity.getType().equals(EntityType.NEW) && !entity.getName().equals("-----")) {
                String permissions = "";
                if (entity.isRead()) permissions = permissions.concat("r");
                if (entity.isWrite()) permissions = permissions.concat("w");
                if (entity.isExecute()) permissions = permissions.concat("x");
                if (!permissions.equals("")) {
                    try {
                        Process p = Runtime.getRuntime().exec("setfacl -m u:" + entity.getName() + ":" + permissions + " " + currentPath);
                    } catch (IOException ex) {
                        Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }    
}
