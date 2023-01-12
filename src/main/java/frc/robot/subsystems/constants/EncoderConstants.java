package frc.robot.subsystems.constants;

/**
 * The Constants class provides a convenient place for teams to hold subsystem-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class EncoderConstants {

    // Which PID slot to pull gains from.
    // Starting 2018, you can choose from 0, 1, 2 or 3.
    // Only the first two (0,1) are visible in web-based configuration.
    public static final int PID_SLOT_0 = 0;

    public static final int PID_SLOT_1 = 1;

    // Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops.
    // For now we just want the primary one.
    public static final int PID_LOOP_PRIMARY = 0;

    public static final int PID_LOOP_AUXILIARY = 1;

    // The amount of native ticks per revolution (Tpr) in the Redline Encoder.
    public static final int ENCODER_TPR = 4096;

    // The amount of time spent per PID frame
    public static final int CLOSED_LOOP_TIME_MS = 1;

}