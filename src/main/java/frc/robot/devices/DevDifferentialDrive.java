
package frc.robot.devices;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;

import static frc.robot.RobotManager.isSim;

// This class is a wrapper around DifferentialDrive in order to handle cases where the
// motors for the DifferentialDrive are not physically connected.  This
// can be the case when running the simulator but it can also happen when
// executing code on the RoboRio without a fully assembled robot with all of
// necessary motors and controllers.

// If the DifferentialDrive motors are connected then this class just forwards any calls directly
// to the DifferentialDrive class.

// If the DifferentialDrive motors are not connected, only a subset of the DifferentialDrive interface is
// supported, mainly by tracing and other monitoring.

public class DevDifferentialDrive extends DifferentialDrive {

    private String m_devName;
    private String m_devDescription;
    private Monitor m_monitor;
    public boolean isConnected = true;

    public DevDifferentialDrive(String devName, MotorController leftMotor, MotorController rightMotor) {
        super(leftMotor, rightMotor);

        m_devName = devName;
        m_devDescription = String.format("DifferentialDrive");

        isConnected = isConnected();
        if (!isConnected) {
            m_monitor = new Monitor(m_devName, m_devDescription);

            // Turn off motor safety in simation mode
            if (isSim) setSafetyEnabled(false);
        }
    }

    // Determines if this is connected
    private boolean isConnected() {
        if (isSim) return false;
        return true;
    }

    public void stopMotor() {
        if (isConnected) {
            super.stopMotor();
            return;
        }

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        m_monitor.log(methodName);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        if (isConnected) {
            super.tankDrive(leftSpeed, rightSpeed);
            return;
        }

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        String arg1 = String.format("%.2f", leftSpeed);
        String arg2 = String.format("%.2f", rightSpeed);
        m_monitor.log(methodName, arg1, arg2);
    }

    public void arcadeDrive(double xSpeed, double zRotation) {
        if (isConnected) {
            super.arcadeDrive(xSpeed, zRotation);
            return;
        }

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        String arg1 = String.format("%.2f", xSpeed);
        String arg2 = String.format("%.2f", zRotation);
        m_monitor.log(methodName, arg1, arg2);
    }

}
