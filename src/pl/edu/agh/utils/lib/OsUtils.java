/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
