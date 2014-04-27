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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import org.apache.commons.io.IOUtils;
import pl.edu.agh.model.MaskCellRenderer;
import pl.edu.agh.model.Entity;
import pl.edu.agh.model.PermissionType;

/**
 *
 * @author lukasz
 */
public class FileInfo {
    
    private final AclOperator aclOperator;
    
    public FileInfo() {
        this.aclOperator = new AclOperator();
    }

    public void getInfoFromFS(List<Entity> entities, JTable aclList, JLabel jPath, JLabel jType,
            JLabel jMask, JLabel jFlags, JCheckBox jReadMask, JCheckBox jWriteMask, JCheckBox jExecuteMask, String currentPath) {
        if (isReadeable(currentPath)) {
            String type = getType(currentPath);
            String mask = getMask(currentPath);
            String flags = getFlags(currentPath);

            entities.clear();
            entities.addAll(aclOperator.getAclList(currentPath));

            if (currentPath.length() > 50) {
                jPath.setText("..." + currentPath.substring(currentPath.lastIndexOf("/")));
            } else {
                jPath.setText(currentPath);
            }
            if (type.length() > 60) {
                jType.setText(type.substring(0, 56) + "...");
            } else {
                jType.setText(type);
            }
            jMask.setText(mask);
            jFlags.setText(flags);

            if (mask.equals("")) {
                jReadMask.setSelected(false);
                jReadMask.setEnabled(false);
                jWriteMask.setSelected(false);
                jWriteMask.setEnabled(false);
                jExecuteMask.setSelected(false);
                jExecuteMask.setEnabled(false);
            } else {
                jWriteMask.setEnabled(true);
                jReadMask.setEnabled(true);
                jExecuteMask.setEnabled(true);
                if (mask.contains("r")) {
                    jReadMask.setSelected(true);
                } else {
                    columnBacklight(PermissionType.READ, true, aclList, entities);
                }
                if (mask.contains("w")) {
                    jWriteMask.setSelected(true);
                } else {
                    columnBacklight(PermissionType.WRITE, true, aclList, entities);
                }
                if (mask.contains("x")) {
                    jExecuteMask.setSelected(true);
                }else {
                    columnBacklight(PermissionType.EXECUTE, true, aclList, entities);
                }
            }
        }
    }

    public void columnBacklight(PermissionType permissionType, Boolean deselected, JTable aclList, List<Entity> entities) {
        int column = 0;

        switch (permissionType) {
            case READ:
                column = 2;
                break;
            case WRITE:
                column = 3;
                break;
            case EXECUTE:
                column = 4;
                break;
        }

        if (column != 0) {
            for (Entity entity : entities) {
                aclList.getColumnModel().getColumn(column).setCellRenderer(new MaskCellRenderer(deselected));
            }
            aclList.updateUI();
        }
    }

    private String getType(String path) {
        String output = new String();

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"file", "-b", path});
            int retVal = p.waitFor();
            if (retVal == 0) {
                List<String> result = IOUtils.readLines(p.getInputStream());
                output = result.get(0);
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    public String getMask(String path) {
        String output = new String();

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"getfacl", path});
            int retVal = p.waitFor();
            if (retVal == 0) {
                if (retVal == 0) {
                    List<String> result = IOUtils.readLines(p.getInputStream());
                    for (String line : result) {
                        Pattern pattern = Pattern.compile("mask*");
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            output = line.split(":")[2];
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }

    private String getFlags(String path) {
        String output = new String();

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"getfacl", path});
            int retVal = p.waitFor();
            if (retVal == 0) {
                if (retVal == 0) {
                    List<String> result = IOUtils.readLines(p.getInputStream());
                    for (String line : result) {
                        Pattern pattern = Pattern.compile("flags*");
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            output = line.split(": ")[1];
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return output;
    }
    
    public String getOwner(String path) {
        String owner = "";
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"getfacl", path});
            int retVal = p.waitFor();
            if (retVal == 0) {
                
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return owner;
    }

    private boolean isWritable(String path) {
        return new File(path).canWrite();
    }

    private boolean isReadeable(String path) {
        return new File(path).canRead();
    }
}
