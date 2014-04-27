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
package pl.edu.agh.utils.lib;

import pl.edu.agh.model.OsType;

/**
 *
 * @author lukasz
 */
public class OsUtils {

    private static String osName = null;
    private static OsType osType = null;

    public static String getOsName() {
        if (osName == null) {
            osName = System.getProperty("os.name");
        }
        return osName;
    }

    public static OsType getOsType() {
        if (osType == null) {
            String osName = System.getProperty("os.name", "generic").toLowerCase();
            if ((osName.indexOf("mac") >= 0) || (osName.indexOf("darwin") >= 0)) {
                osType = OsType.MacOSX;
            } else if (osName.indexOf("win") >= 0) {
                osType = OsType.Windows;
            } else if (osName.indexOf("nux") >= 0) {
                osType = OsType.Linux;
            } else {
                osType = OsType.Other;
            }
        }
        return osType;
    }
}
