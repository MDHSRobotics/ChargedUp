
package frc.robot.consoles;

import edu.wpi.first.wpilibj.shuffleboard.EventImportance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

// Log events to various places: Shuffleboard, stdout, etc.
// Note: Event names should be terse
public class ShuffleLogger {

    //--------------------------------------------------------------------------------------------//
    // Trivial events such as command switching state
    public static void logTrivial(String eventName) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, EventImportance.kTrivial);

        // Print to stdout
        System.out.println(eventName);
    }

    public static void logTrivialVerbose(String eventName, String description) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, description, EventImportance.kTrivial);

        // Print to stdout
        System.out.println(eventName);
    }

    //--------------------------------------------------------------------------------------------//
    // Low events such as grabbing a ball or hatch
    public static void logLow(String eventName) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, EventImportance.kLow);

        // Print to stdout
        System.out.println(eventName);
    }

    public static void logLowVerbose(String eventName, String description) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, description, EventImportance.kLow);

        // Print to stdout
        System.out.println(eventName);
    }

    //--------------------------------------------------------------------------------------------//
    // Normal events such as transition from autonomous to teleop mode
    public static void logNormal(String eventName) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, EventImportance.kNormal);

        // Print to stdout
        System.out.println(eventName);
    }

    public static void logNormalVerbose(String eventName, String description) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, description, EventImportance.kNormal);

        // Print to stdout
        System.out.println(eventName);
    }

    //--------------------------------------------------------------------------------------------//
    //High events such as scoring a game piece
    public static void logHigh(String eventName) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, EventImportance.kHigh);

        // Print to stdout
        System.out.println(eventName);
    }

    public static void logHighVerbose(String eventName, String description) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, description, EventImportance.kHigh);

        // Print to stdout
        System.out.println(eventName);
    }

    //--------------------------------------------------------------------------------------------//
    //Critical events such as a brownout, component failure, or software deadlock.
    public static void logCritical(String eventName) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, EventImportance.kCritical);

        // Print to stdout
        System.out.println(eventName);
    }

    public static void logCriticalVerbose(String eventName, String description) {

        // Print to Shuffleboard
        Shuffleboard.addEventMarker(eventName, description, EventImportance.kCritical);

        // Print to stdout
        System.out.println(eventName);
    }

}
