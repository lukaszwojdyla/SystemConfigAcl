/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    public static void genFileInfo(JPanel panel, String path) {
        if (isReadeable(path)) {
            JLabel currentPath = (JLabel) panel.getComponent(7);
            JLabel type = (JLabel) panel.getComponent(4);
            JLabel mask = (JLabel) panel.getComponent(5);
            JLabel flags = (JLabel) panel.getComponent(6);
            if (path.length() > 50) {
                currentPath.setText("..." + path.substring(path.lastIndexOf("/")));
            } else {
                currentPath.setText(path);
            }
            type.setText(getType(path));
            mask.setText(getMask(path));
            flags.setText(getFlags(path));

            currentPath.paintImmediately(currentPath.getVisibleRect());
            panel.paintImmediately(panel.getVisibleRect());
        }
    }

    private static String getType(String path) {
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

    private static String getMask(String path) {
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

    private static String getFlags(String path) {
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

    public static List<Entity> getAclList(String path) {
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
                                
                                if (permissions.contains("r")) entity.setRead(true);
                                if (permissions.contains("w")) entity.setWrite(true);
                                if (permissions.contains("x")) entity.setExecute(true);
                                
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
    
    public static List<String> getSystemUsers() {
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

    private static boolean isWritable(String path) {
        return new File(path).canWrite();
    }

    private static boolean isReadeable(String path) {
        return new File(path).canRead();
    }
}
