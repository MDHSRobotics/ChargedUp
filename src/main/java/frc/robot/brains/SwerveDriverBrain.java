
package frc.robot.brains;

import edu.wpi.first.networktables.GenericEntry;

// This class contains all the shared NetworkTableEntries for the Swerve Drive,
// their default values, and methods for retrieving their current values
public class SwerveDriverBrain {

    //----------------//
    // Default Values //
    //----------------//

    public final static double defaultDrivePower = 0.;
    public final static double defaultTicks = 0.;
    public final static double defaultVelocity = 0.;

    public final static double defaultChargeStationP = 1.;
    public final static double defaultChargeStationI = 0.;
    public final static double defaultChargeStationD = 0.;

    public final static String defaultCurrentPosition = "()";
    public final static double defaultCurrentRotation = 0;

    public final static double defaultDeadBand = 0.1;
    public final static double defaultForwardBackwardSpeed = 2.4;
    public final static double defaultLeftRightSpeed = 1.2;
    public final static double defaultRotationSpeed = 2.0;

    public static double defaultFLEncoderOffset = -34.893;
    public static double defaultFREncoderOffset = -93.604;
    public static double defaultRLEncoderOffset = -131.045;
    public static double defaultRREncoderOffset = -169.805;

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

    public static GenericEntry entryChargeStationSpeedP;
    public static GenericEntry entryChargeStationSpeedI;
    public static GenericEntry entryChargeStationSpeedD;

    public static GenericEntry entryCurrentPosition;
    public static GenericEntry entryCurrentRotation;

    public static GenericEntry entryDeadBand;
    public static GenericEntry entryForwardBackwardSpeed;
    public static GenericEntry entryLeftRightSpeed;
    public static GenericEntry entryRotationSpeed;

    public static GenericEntry entryFieldOrientedEntry;

    public static GenericEntry entryAbsoluteEncoderOffsetDegreesFL;
    public static GenericEntry entryAbsoluteEncoderOffsetDegreesFR;
    public static GenericEntry entryAbsoluteEncoderOffsetDegreesRL;
    public static GenericEntry entryAbsoluteEncoderOffsetDegreesRR;

    //---------//
    // Getters //
    //---------//

    public static double getChargeStationP() {
        return entryChargeStationSpeedP.getDouble(defaultChargeStationP);
    }

    public static double getChargeStationI() {
        return entryChargeStationSpeedI.getDouble(defaultChargeStationI);
    }

    public static double getChargeStationD() {
        return entryChargeStationSpeedD.getDouble(defaultChargeStationD);
    }

    public static double getDeadband(){
        return entryDeadBand.getDouble(defaultDeadBand);
    }

    public static double getForwardBackwardSpeed(){
        return entryForwardBackwardSpeed.getDouble(defaultForwardBackwardSpeed);
    }
    
    public static double getLeftRightSpeed(){
        return entryLeftRightSpeed.getDouble(defaultLeftRightSpeed);
    }

    public static double getRotationSpeed(){
        return entryRotationSpeed.getDouble(defaultRotationSpeed);
    }

    public static double getAbsoluteEncoderOffset(String moduleName){
        double offset;
        if(moduleName.equals("Front Left")){
            offset = entryAbsoluteEncoderOffsetDegreesFL.getDouble(defaultFLEncoderOffset);
        }else if(moduleName.equals("Front Right")){
            offset = entryAbsoluteEncoderOffsetDegreesFR.getDouble(defaultFREncoderOffset);
        }else if(moduleName.equals("Rear Left")){
            offset = entryAbsoluteEncoderOffsetDegreesRL.getDouble(defaultRLEncoderOffset);
        }else if(moduleName.equals("Rear Right")){
            offset = entryAbsoluteEncoderOffsetDegreesRR.getDouble(defaultRREncoderOffset);
        }else{
            throw new java.lang.Error(String.format("Unkown Module Name %s",moduleName));
        }
        return offset;
        
    }

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

    public static void setDriveSpeed(double forwardBackward, double leftRight, double rotation){

        entryForwardBackwardSpeed.setDouble(forwardBackward);
        entryLeftRightSpeed.setDouble(leftRight);
        entryRotationSpeed.setDouble(rotation);

    }

    public static void setFieldOriented(boolean value){
        entryFieldOrientedEntry.setBoolean(value);
    }

    public static void setAbsoulteEncoderOffsets(double FLOffset, double FROffset, double RLOffset, double RROffset){
        entryAbsoluteEncoderOffsetDegreesFL.setDouble(FLOffset);
        entryAbsoluteEncoderOffsetDegreesFR.setDouble(FROffset);
        entryAbsoluteEncoderOffsetDegreesRL.setDouble(RLOffset);
        entryAbsoluteEncoderOffsetDegreesRR.setDouble(RROffset);
    }


}
