/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.utils.lib;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pl.edu.agh.model.DefaultUserType;
import pl.edu.agh.model.Entity;
import pl.edu.agh.model.EntityType;

/**
 *
 * @author lukasz
 */
public class FileOperator {

    public void saveAcls(List<Entity> entities, String mask, String currentPath) {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"setfacl", "-b", currentPath});
        } catch (IOException ex) {
            Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(Iterator<Entity> iterator = entities.iterator(); iterator.hasNext();) {
            Entity entity = iterator.next();
            if (entity.getType().equals(EntityType.NEW) || entity.getName().equals(DefaultUserType.DEFAULT.toString())) {
                iterator.remove();
            }
        }
        
        if (!entities.isEmpty()) {
            for (Entity entity : entities) {
                if (!entity.getType().equals(EntityType.NEW) && !entity.getName().equals(DefaultUserType.DEFAULT.toString())) {
                    String permissions = "";
                    String type = "";
                    if (entity.isRead()) {
                        permissions = permissions.concat("r");
                    } else {
                        permissions = permissions.concat("-");
                    }
                    if (entity.isWrite()) {
                        permissions = permissions.concat("w");
                    } else {
                        permissions = permissions.concat("-");
                    }
                    if (entity.isExecute()) {
                        permissions = permissions.concat("x");
                    } else {
                        permissions = permissions.concat("-");
                    }
                    if (entity.getType().equals(EntityType.USER)) {
                        type = "u";
                    }
                    if (entity.getType().equals(EntityType.GROUP)) {
                        type = "g";
                    }
                    try {
                        Process p = Runtime.getRuntime().exec("setfacl -m " + type + ":" + entity.getName() + ":" + permissions + " " + currentPath);
                    } catch (IOException ex) {
                        Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                try {
                    Process p = Runtime.getRuntime().exec("setfacl -m m:" + mask + " " + currentPath);
                } catch (IOException ex) {
                    Logger.getLogger(FileOperator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
