
package frc.robot.consoles;

import java.time.LocalTime;

// This is a helper class for colorful custom console logging.
// To see the colors in the rio log, you need to replace a javascript file that is installed with WPILib.

// First, rename the existing installed file, so you have a backup (check to see if there is a newer install than this path):
//      C:\Users\Public\wpilib\2020\vscode\data\extensions\wpilibsuite.vscode-wpilib-2020.1.2\resources\dist\riologpage.js.bak
// There is also a backup committed to the TeamWiki git project, just in case (matches the version above):
//      \MDHSRobotics\TeamWiki\code\riologpage_2020_01_02.js

// To replace it, copy this file from the TeamWiki git project:
//      \MDHSRobotics\TeamWiki\code\riologpage.js
// Paste over the installed file on your machine (as above, check to see if there is a newer install than this):
//      C:\Users\Public\wpilib\2020\vscode\data\extensions\wpilibsuite.vscode-wpilib-2020.1.2\resources\dist\riologpage.js

// If you do not replace this file, the Logger will still print the text correctly. It just won't be colored.
public class Logger {

    public Logger() {
    }

    // Grey text for constructor logging, etc.
    public static void setup(Object message) {
        System.out.println("\u001b[38;5;249m" + "setup --> " + LocalTime.now() + " :: " + message);
    }

    // Pink text for waiting
    public static void waiting(Object message) {
        System.out.println("\u001b[38;5;201m" + "waiting --> " + LocalTime.now() + " :: " + message);
    }

    // Green text for taking an action, like starting a command
    public static void action(Object message) {
        System.out.println("\u001B[32m" + "ACTION --> " + LocalTime.now() + " :: " + message);
    }

    // Orange text for info, values, etc.
    public static void info(Object message) {
        System.out.println("\u001B[0m" + "INFO --> " + LocalTime.now() + " :: " + message);
    }

    // Blue text for ending or interrupting, etc.
    public static void ending(Object message) {
        System.out.println("\u001B[34m" + "ending --> " + LocalTime.now() + " :: " + message);
    }

    // Yellow text for warnings
    public static void warning(Object message) {
        System.out.println("\u001B[33m"	+ "WARNING --> " + LocalTime.now() + " :: " + message);
    }

    // Red text for problems
    public static void problem(Object message) {
        System.out.println("\u001B[31m" + "PROBLEM --> " + LocalTime.now() + " :: " + message);
    }

    // White text for debugging
    public static void debug(Object message) {
        System.out.println("\u001B[0m" + "DEBUG --> " + LocalTime.now() + " :: " + message);
    }

}
