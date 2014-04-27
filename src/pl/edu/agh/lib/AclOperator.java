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
package pl.edu.agh.lib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;
import pl.edu.agh.model.DefaultUserType;
import pl.edu.agh.model.Entity;
import pl.edu.agh.model.EntityType;

/**
 *
 * @author lukasz
 */
public class AclOperator {

    public void saveAcls(List<Entity> entities, String mask, String currentPath) {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"setfacl", "-b", currentPath});
        } catch (IOException ex) {
            Logger.getLogger(AclOperator.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(AclOperator.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                try {
                    Process p = Runtime.getRuntime().exec("setfacl -m m:" + mask + " " + currentPath);
                } catch (IOException ex) {
                    Logger.getLogger(AclOperator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     *
     * @param path the value of path
     * @return 
     */
    public List getAclList(String path) {
        List<Entity> entities = new ArrayList<>();
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"getfacl", path});
            int retVal = p.waitFor();
            if (retVal == 0) {
                List<String> result = IOUtils.readLines(p.getInputStream());
                result.stream().forEach((String line) -> {
                    Entity entity = new Entity();
                    if (!line.toString().startsWith("#") && line.toString().length() > 0) {
                        if (line.split(":")[0].equals("default")) {
                            if (line.split(":")[2].length() > 0) {
                                String type = line.split(":")[1];
                                String user = line.split(":")[2];
                                String permissions = line.split(":")[3];
                                entity.setName(user);
                                if (type.equals("user")) {
                                    entity.setType(EntityType.D_USER);
                                }
                                if (type.equals("group")) {
                                    entity.setType(EntityType.D_GROUP);
                                }
                                if (type.equals("other")) {
                                    entity.setType(EntityType.D_OTHER);
                                }
                                if (permissions.contains("r")) {
                                    entity.setRead(true);
                                }
                                if (permissions.contains("w")) {
                                    entity.setWrite(true);
                                }
                                if (permissions.contains("x")) {
                                    entity.setExecute(true);
                                }
                                entities.add(entity);
                            }
                        } else {
                            if (line.split(":")[1].length() > 0) {
                                String type = line.split(":")[0];
                                String user = line.split(":")[1];
                                String permissions = line.split(":")[2];
                                entity.setName(user);
                                if (type.equals("user")) {
                                    entity.setType(EntityType.USER);
                                }
                                if (type.equals("group")) {
                                    entity.setType(EntityType.GROUP);
                                }
                                if (type.equals("other")) {
                                    entity.setType(EntityType.OTHER);
                                }
                                if (permissions.contains("r")) {
                                    entity.setRead(true);
                                }
                                if (permissions.contains("w")) {
                                    entity.setWrite(true);
                                }
                                if (permissions.contains("x")) {
                                    entity.setExecute(true);
                                }
                                entities.add(entity);
                            }
                        }
                    }
                });
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entities;
    }
}
