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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author lukasz
 */
public class SystemInfo {

    /**
     * @author lukasz
     * @return 
     */
    public List getSystemGroups() {
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

    /**
     * @author lukasz
     * @return 
     */
    public List getSystemUsers() {
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
    
}
