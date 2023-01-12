
package frc.robot.subsystems.constants;

/**
 * The Constants class provides a convenient place for teams to hold subsystem-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class TalonConstants {

    // Number of miliseconds that the talon can stay at peak current.
    public static final int PEAK_AMPERAGE_DURATION = 100;

    // Max amps that the talon can supply during short intervals.
    public static final int PEAK_AMPERAGE = 2;

    // Max amps that the talon can supply continuously.
    public static final int PEAK_CONTINUOUS_AMPERAGE = 2;

    // Minimum speed the talon can move forwards.
    public static final int NOMINAL_OUTPUT_FORWARD = 0;

    // Minimum speed the talon can move backwards.
    public static final int NOMINAL_OUTPUT_REVERSE = 0;

    // Minimum speed the talon can move forwards.
    public static final double MAXIMUM_OUTPUT_FORWARD = 0.6;

    // Minimum speed the talon can move backwards.
    public static final double MAXIMUM_OUTPUT_REVERSE = -0.6;

    // Output deadband percentage of the motor
    public final static double NEUTRAL_DEADBAND = 0.001;

    // Set to zero to skip waiting for confirmation,
    // set to nonzero to wait and report to DS if action fails.
    public static final int TIMEOUT_MS = 20;

}
