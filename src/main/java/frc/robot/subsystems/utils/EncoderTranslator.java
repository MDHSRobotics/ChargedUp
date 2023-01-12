
package frc.robot.subsystems.utils;

import java.lang.Math;

// Utility methods to convert to/from encoder units

public class EncoderTranslator {

    // The amount of native ticks per revolution (TPR)
    final private int m_TPR;
    // The diameter of the wheel
    // NOTE: the units of the wheel diameter determine the units of any length-based
    //       calculations, such as distance, velocity, etc.
    final private double m_wheelDiameter;
    // The gear ratio defined as motor rotations per wheel rotation
    // For example, a gear ratio of 10.0 means that it takes 10 rotations of the
    //              motor to result in one rotation of the wheel
    final private double m_gearRatio;

    public EncoderTranslator(String encoderType, double wheelDiameter, double gearRatio) {

        m_wheelDiameter = wheelDiameter;
        m_gearRatio = gearRatio;

        if (encoderType.equals("TalonFX")) {
            // TalonFX motors have build-in encoders
            m_TPR = 2048;
        }
        else if (encoderType.equals("TalonSRX")) {
            // TalonFX motors have build-in encoders
            m_TPR = 4096;
        }
        else {
            throw new java.lang.Error(String.format("EncoderTranslator was given unknown encoder type %s", encoderType));
        }
    }

    /** Computes an encoder tick count based on the desired motor rotation in degrees for a given gearbox ratio (MS : GS)
     * @param angleDegrees Desired angle to compute to ticks
     * @return Returns the angle computed to ticks
    */
    public int degrees_to_ticks(double angleDegrees) {
        double rotationCountGS = angleDegrees / 360.0; // Amount of rotations on the gearbox shaft
        double rotationCountMS = rotationCountGS * m_gearRatio; // Amount of rotations on the motor shaft
        double rotationTicks = rotationCountMS * m_TPR; // Amount of ticks to rotate
        return (int) (rotationTicks);
    }

    /** Computes an encoder tick count based on the desired distance in inches for a given wheel diameter and gearbox ratio (MS : GS)
     * @param distance The desired distance
     * @return Returns the amount of ticks to move a certain distance
    */
    public int distance_to_ticks(double distance) {
        double wheelCircumference = Math.PI * m_wheelDiameter;
        double rotationCountGS = distance / wheelCircumference; // Amount of rotations on the gearbox shaft
        double rotationCountMS = rotationCountGS * m_gearRatio; // Amount of rotations on the motor shaft
        double rotationTicks = rotationCountMS * m_TPR; // Amount of ticks to rotate
        return (int) rotationTicks;
    }

    /** Converts ticks to inches ased on wheel diameter
     * @param rotationTicks The amount of ticks to rotate
     * @return Returns the distance (in same units as wheel diameter)
    */
    public double ticks_to_distance(double rotationTicks){
        double wheelCircumference = Math.PI * m_wheelDiameter;
        double distance = (rotationTicks/m_TPR)*wheelCircumference / m_gearRatio;
        return distance;
    }

    /**
     * Converts velocity (units per second) to TPHMS (ticks per hundred millisecondse)
     * @param velocity Velocity in feet/second, meters/second, etc.
     * @return
     */
    public double velocity_to_ticksPerDecisecond(double velocity) {

        // Take the velocity and divide it by the cirfcumference to find the ratio.
        // After finding the ratio,  multiply by the ticks per rotation to get ticks per second.
        // Convert to per hundred milliseconds
        double wheelCircumference = m_wheelDiameter * Math.PI;
        double tphms = ((velocity / wheelCircumference) * m_TPR * m_gearRatio) / 10;
        return tphms;
    }

    /**
     * Converts TPHMS (ticks per hundred milliseconds) to velocity (units per second)
     * @param tphms Ticks per hundrd milliseconds
     * @return Velocity in units determined by the wheel diameter (m/s, ft/s, etc.)
     */
    public double ticksPerDecisecond_to_velocity(double tphms) {
        double wheelCircumference = m_wheelDiameter * Math.PI;
        double velocity = ((tphms * wheelCircumference) / m_TPR / m_gearRatio) * 10;
        return velocity;
    }

    /** Computes an encoder velocity tick count based on the desired rotations per second and the gearbox ratio (MS : GS)
     * @param rps Amount of rps on the gearbox shaft
     * @return Returns ticksPerDecisecond as an int
    */
    public int rotationsPerSecond_to_ticksPerDecisecond(double rps) {
        double rotationsPerDecisecondGS = rps / 10.0; // Amount of rpds on the gearbox shaft
        double rotationsPerDecisecondMS = rotationsPerDecisecondGS * m_gearRatio; // Amount of rpds on the motor shaft
        double ticksPerDecisecond = rotationsPerDecisecondMS * m_TPR; // Amount of ticks per decisecond to rotate
        return (int) ticksPerDecisecond;
    }

    /**Computes ticks to radians
     * @param ticks tick integer that is computed to radian value
     * @return Returns the desired radian value
    */
    public double ticks_to_radians(double ticks) {
        double radians = ticks / m_TPR * 2 * Math.PI / m_gearRatio;
        return radians;
    }

    /**Computes radians to ticks
     * @param radians radian value that is computed to a tick integer
     * @return Returns the desired tick integer
    */
    public int radians_to_ticks(double radians) {
        int ticks = (int) (radians * m_TPR / 2 / Math.PI * m_gearRatio);
        return ticks;
    }

    /**Computes ticks per hundred miliseconds to radians per second
     * @param ticksPDS tick per hundred milisecond
     * @return radiansPerSecond Returns the desired radians per second
    */
    public double ticksPerDecisecond_to_RadiansPerSecond(double ticksPDS) {
        double radiansPerSecond = (ticksPDS * 1000 * 2 * Math.PI) / (m_TPR * 100) / m_gearRatio;
        return radiansPerSecond;
    }

}
