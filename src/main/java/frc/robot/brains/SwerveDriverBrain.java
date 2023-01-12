
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Swerve Drive Subsystem,
// their default values, and methods for retrieving their current values.
public class SwerveDriverBrain {

    //----------------//
    // Default Values //
    //----------------//

    public final static double defaultDrivePower = 0.;
    public final static double defaultTicks = 0.;
    public final static double defaultVelocity = 0.;

    //---------------------//
    // NetworkTableEntries //
    //---------------------//

    public static GenericEntry entryDrivePowerFL;
    public static GenericEntry entryDrivePowerFR;
    public static GenericEntry entryDrivePowerRL;
    public static GenericEntry entryDrivePowerRR;

    public static GenericEntry entryTurningPowerFL;
    public static GenericEntry entryTurningPowerFR;
    public static GenericEntry entryTurningPowerRL;
    public static GenericEntry entryTurningPowerRR;  

    public static GenericEntry entryDriveEncoderTicksFL;
    public static GenericEntry entryDriveEncoderTicksFR;
    public static GenericEntry entryDriveEncoderTicksRL;
    public static GenericEntry entryDriveEncoderTicksRR;

    public static GenericEntry entryDriveEncoderMpsFL;
    public static GenericEntry entryDriveEncoderMpsFR;
    public static GenericEntry entryDriveEncoderMpsRL;
    public static GenericEntry entryDriveEncoderMpsRR;

    public static GenericEntry entryTurningEncoderTicksFL;
    public static GenericEntry entryTurningEncoderTicksFR;
    public static GenericEntry entryTurningEncoderTicksRL;
    public static GenericEntry entryTurningEncoderTicksRR;

    public static GenericEntry entryTurningEncoderMpsFL;
    public static GenericEntry entryTurningEncoderMpsFR;
    public static GenericEntry entryTurningEncoderMpsRL;
    public static GenericEntry entryTurningEncoderMpsRR;

    //---------//
    // Setters //
    //---------//

    public static void setModuleDrivePower(String moduleName, double value) {

        switch (moduleName) {
            case "Front Left":
                entryDrivePowerFL.setDouble(value);
                break;
            case "Front Right":
                entryDrivePowerFR.setDouble(value);
                break;
            case "Rear Left":
                entryDrivePowerRL.setDouble(value);
                break;
            case "Rear Right":
                entryDrivePowerRR.setDouble(value);
                break;

            default:
                throw new java.lang.Error(String.format("Unknown module name %s", moduleName));
        }
    }

    public static void setModuleTurningPower(String moduleName, double value) {

        switch (moduleName) {
            case "Front Left":
                entryTurningPowerFL.setDouble(value);
                break;
            case "Front Right":
                entryTurningPowerFR.setDouble(value);
                break;
            case "Rear Left":
                entryTurningPowerRL.setDouble(value);
                break;
            case "Rear Right":
                entryTurningPowerRR.setDouble(value);
                break;

            default:
                throw new java.lang.Error(String.format("Unknown module name %s", moduleName));
        }   
    }

    public static void setModuleEncoderReadings(String moduleName, double[] encoderSettings) {

        switch (moduleName) {
            case "Front Left":
                entryDriveEncoderTicksFL.setDouble(encoderSettings[0]);
                entryDriveEncoderMpsFL.setDouble(encoderSettings[1]);
                entryTurningEncoderTicksFL.setDouble(encoderSettings[2]);
                entryTurningEncoderMpsFL.setDouble(encoderSettings[3]);
                break;
            case "Front Right":
                entryDriveEncoderTicksFR.setDouble(encoderSettings[0]);
                entryDriveEncoderMpsFR.setDouble(encoderSettings[1]);
                entryTurningEncoderTicksFR.setDouble(encoderSettings[2]);
                entryTurningEncoderMpsFR.setDouble(encoderSettings[3]);
                break;
            case "Rear Left":
                entryDriveEncoderTicksRL.setDouble(encoderSettings[0]);
                entryDriveEncoderMpsRL.setDouble(encoderSettings[1]);
                entryTurningEncoderTicksFR.setDouble(encoderSettings[2]);
                entryTurningEncoderMpsFR.setDouble(encoderSettings[3]);
                break;
            case "Rear Right":
                entryDriveEncoderTicksRR.setDouble(encoderSettings[0]);
                entryDriveEncoderMpsRR.setDouble(encoderSettings[1]);
                entryTurningEncoderTicksRR.setDouble(encoderSettings[2]);
                entryTurningEncoderMpsRR.setDouble(encoderSettings[3]);
                break;

            default:
                throw new java.lang.Error(String.format("Unknown module name %s", moduleName));
        }
    }

}
