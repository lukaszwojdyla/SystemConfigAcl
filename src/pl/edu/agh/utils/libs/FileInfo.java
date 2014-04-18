/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.utils.libs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import org.apache.commons.io.IOUtils;
import pl.edu.agh.model.Entity;
import pl.edu.agh.model.EntityType;

/**
 *
 * @author lukasz
 */
public class FileInfo {

    public void getInfoFromFS(List<Entity> entities, JLabel jPath, JLabel jType,
            JLabel jMask, JLabel jFlags, JCheckBox jReadMask, JCheckBox jWriteMask, JCheckBox jExecuteMask, String currentPath) {
        if (isReadeable(currentPath)) {
            String type = getType(currentPath);
            String mask = getMask(currentPath);
            String flags = getFlags(currentPath);

            entities.clear();
            entities.addAll(getAclList(currentPath));

            if (currentPath.length() > 50) {
                jPath.setText("..." + currentPath.substring(currentPath.lastIndexOf("/")));
            } else {
                jPath.setText(currentPath);
            }
            jType.setText(type);
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
                }
                if (mask.contains("w")) {
                    jWriteMask.setSelected(true);
                }
                if (mask.contains("x")) {
                    jExecuteMask.setSelected(true);
                }
            }
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
                List<String> result = IOUtils.readLines(p.getInputStream());
                result.stream().forEach((line) -> {
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
                                if (type.equals(("other"))) {
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
                                if (type.equals(("other"))) {
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
