/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.utils;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.commons.io.IOUtils;
import pl.edu.agh.model.Entity;
import pl.edu.agh.model.EntityType;

/**
 *
 * @author lukasz
 */
public class FileInfo {

    public void genFileInfo(JPanel panel, String path) {
        if (isReadeable(path)) {
            JLabel currentPath = (JLabel) panel.getComponent(6);
            JLabel type = (JLabel) panel.getComponent(4);
            JLabel mask = (JLabel) panel.getComponent(5);
            JLabel flags = (JLabel) panel.getComponent(7);
            JCheckBox readMask = (JCheckBox) panel.getComponent(9);
            JCheckBox writeMask = (JCheckBox) panel.getComponent(10);          
            JCheckBox executeMask = (JCheckBox) panel.getComponent(11);

            if (path.length() > 50) {
                currentPath.setText("..." + path.substring(path.lastIndexOf("/")));
            } else {
                currentPath.setText(path);
            }
            type.setText(getType(path));
            mask.setText(getMask(path));
            flags.setText(getFlags(path));
            if (mask.getText().equals("")) {
                readMask.setSelected(false);
                readMask.setEnabled(false);
                writeMask.setSelected(false);
                writeMask.setEnabled(false);                
                executeMask.setSelected(false);
                executeMask.setEnabled(false);
            } else {
                writeMask.setEnabled(true);
                readMask.setEnabled(true);
                executeMask.setEnabled(true);
                if (getMask(path).contains("r")) {
                    readMask.setSelected(true);
                }
                if (getMask(path).contains("w")) {
                    writeMask.setSelected(true);
                }
                if (getMask(path).contains("x")) {
                    executeMask.setSelected(true);
                }
            }

            currentPath.paintImmediately(currentPath.getVisibleRect());
            panel.paintImmediately(panel.getVisibleRect());
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

    public List<Entity> getAclList(String path) {
        List<Entity> entities = new ArrayList<>();

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"getfacl", path});
            int retVal = p.waitFor();
            if (retVal == 0) {
                if (retVal == 0) {
                    List<String> result = IOUtils.readLines(p.getInputStream());
                    result.stream().forEach((line) -> {
                        Pattern pattern = Pattern.compile("user*");
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            if (line.split(":")[1].length() > 0) {
                                Entity entity = new Entity();
                                String permissions = line.split(":")[2];
                                String user = line.split(":")[1];

                                entity.setName(user);
                                entity.setType(EntityType.USER);

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
                    });
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return entities;
    }

    public List<String> getSystemUsers() {
        List<String> users = new ArrayList<>();

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"cat", "/etc/passwd"});
            int retVal = p.waitFor();
            if (retVal == 0) {
                if (retVal == 0) {
                    List<String> result = IOUtils.readLines(p.getInputStream());
                    for (String line : result) {
                        if (Integer.parseInt(line.split(":")[3]) >= 500) {
                            users.add(line.split(":")[0]);
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return users;
    }

    public List<String> getSystemGroups() {
        List<String> groups = new ArrayList<>();

        try {
            Process p = Runtime.getRuntime().exec(new String[]{"cat", "/etc/group"});
            int retVal = p.waitFor();
            if (retVal == 0) {
                if (retVal == 0) {
                    List<String> result = IOUtils.readLines(p.getInputStream());
                    for (String line : result) {
                        if (Integer.parseInt(line.split(":")[2]) >= 500) {
                            groups.add(line.split(":")[0]);
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(FileInfo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return groups;
    }

    private boolean isWritable(String path) {
        return new File(path).canWrite();
    }

    private boolean isReadeable(String path) {
        return new File(path).canRead();
    }
}
