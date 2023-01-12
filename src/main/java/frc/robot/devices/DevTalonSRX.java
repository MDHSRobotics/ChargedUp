package frc.robot.devices;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import static frc.robot.RobotManager.isReal;
import static frc.robot.RobotManager.isSim;

import java.util.Random;

// This class is a wrapper around TalonSRX in order to handle cases where the
// Talon controller and associated motor are not physically connected.  This
// can be the case when running the simulator but it can also happen when
// executing code on the RoboRio without a fully assembled robot with all of
// necessary motors and controllers.

// If the Talon is connected then this class just forwards any calls directly
// to the TalonSRX class.

// If the Talon is not connected, only a subset of the TalonSRX interface is
// supported, mainly by tracing and other monitoring.

public class DevTalonSRX extends WPI_TalonSRX {

    private String m_devName;
    private String m_devDescription;
    private Monitor m_monitor;
    public boolean isConnected = true;

    // Random generator to be used to return dynamic numbers (e.g. in getVelocity)
    private Random m_random;

    public DevTalonSRX(String devName, int deviceNumber) {
        super(deviceNumber);

        m_devName = devName;
        m_devDescription = String.format("TalonSRX #%d", deviceNumber);

        isConnected = isConnected();
        if (!isConnected) {
            m_monitor = new Monitor(m_devName, m_devDescription);
            if (isSim) m_random = new Random();
        }

        if (isConnected) {
            configFactoryDefault();
        }
    }

    // Determines if this is connected
    private boolean isConnected() {
        if (isSim) return false;

        int firmVer = this.getFirmwareVersion();
        return (firmVer != -1);
    }

    public void set(double power){
        if (isConnected) {
            super.set(power);
            return;
        }

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        String arg = String.format("%.3f", power);
        m_monitor.log(methodName, arg);
    }

    public void set(ControlMode mode, double value) {
        if (isConnected) {
            super.set(mode, value);
            return;
        }

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        String arg = String.format("%s, %.3f", mode, value);
        m_monitor.log(methodName, arg);
    }

    public void stopMotor() {
        if (isConnected) {
            super.stopMotor();
            return;
        }

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        m_monitor.log(methodName);
    }

    public double getSelectedSensorVelocity() {
        if (isConnected) {
            return super.getSelectedSensorVelocity();
        }

        if (isReal) return 0;

        // Generate dynamic velocity numbers in range of 9000 and 9999
        int rand_int = m_random.nextInt(1000);
        int velocity = 9000 + rand_int;
        return velocity;
    }

}
