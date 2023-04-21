package frc.robot.subsystems.utils;

import java.lang.Math;

// Utility methods for encoder values.
public class EncoderUtils {

    private static final int ENCODER_TPR = 4096;
    
    // Computes an encoder tick count based on the desired motor rotation in degrees for a given gearbox ratio (MS : GS)
    public static double translateAngleToTicks(double angle, double gearRatio) {
        double rotationCountGS = angle / 360.0; // Amount of rotations on the gearbox shaft
        double rotationCountMS = rotationCountGS * gearRatio; // Amount of rotations on the motor shaft
        double rotationTicks = rotationCountMS * ENCODER_TPR; // Amount of ticks to rotate
        return rotationTicks;
    }

    // Computes an encoder tick count based on the desired distance in inches for a given wheel diameter and gearbox ratio (MS : GS)
    public static double translateDistanceToTicks(double distance, double wheelDiameter, double gearRatio) {
        double wheelCircumference = Math.PI * (wheelDiameter / 12.0);
        double rotationCountGS = distance / wheelCircumference; // Amount of rotations on the gearbox shaft
        double rotationCountMS = rotationCountGS * gearRatio; // Amount of rotations on the motor shaft
        double rotationTicks = rotationCountMS * ENCODER_TPR; // Amount of ticks to rotate
        return rotationTicks;
    }

    // Computes an encoder velocity tick count based on the desired velocity in feet per second for a given wheel diameter and gearbox ratio (MS : GS)
    public static double translateFPSToTicksPerDecisecond(double fps, double wheelDiameter, double gearRatio) {
        double wheelCircumference = Math.PI * (wheelDiameter / 12.0);
        double rotationsPerSecondGS = fps / wheelCircumference; // Amount of rotations per second on the gearbox shaft
        double rotationsPerSecondMS = rotationsPerSecondGS * gearRatio; // Amount of rotations per second on the motor shaft
        double ticksPerDecisecond = rotationsPerSecondMS * ENCODER_TPR / 10.0; // Amount of ticks per decisecond on the motor shaft
        return ticksPerDecisecond;
    }

    public static double translateTicksPerDecisecondToFPS(double ticksPerDecisecond, double wheelDiameter, double gearRatio){
        double rotationsPerSecondMS = ticksPerDecisecond / ENCODER_TPR * 10.0;
        double rotationsPerSecondGS = rotationsPerSecondMS / gearRatio;
        double wheelCircumference = Math.PI * (wheelDiameter / 12.0);
        double fps = rotationsPerSecondGS * wheelCircumference;
        return fps;
    }

    // Computes an encoder velocity tick count based on the desired rotations per second and the gearbox ratio (MS : GS)
    public static double translateRPSToTicksPerDecisecond(double rps, double gearRatio) {
        double rotationsPerDecisecondGS = rps / 10.0; // Amount of rpds on the gearbox shaft
        double rotationsPerDecisecondMS = rotationsPerDecisecondGS * gearRatio; // Amount of rpds on the motor shaft
        double ticksPerDecisecond = rotationsPerDecisecondMS * ENCODER_TPR; // Amount of ticks per decisecond to rotate
        return ticksPerDecisecond;
    }

}