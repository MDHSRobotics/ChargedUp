
package frc.robot.devices;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import static frc.robot.RobotManager.isSim;
import static frc.robot.RobotManager.isReal;

// This class is a wrapper around TalonFX in order to handle cases where the
// Talon controller and associated motor are not physically connected.  This
// can be the case when running the simulator but it can also happen when
// executing code on the RoboRio without a fully assembled robot with all of
// necessary motors and controllers.

// If the Talon is connected then this class just forwards any calls directly
// to the TalonFX class.

// If the Talon is not connected, only a subset of the TalonFX interface is
// supported, mainly by tracing and other monitoring.

public class DevTalonFX extends WPI_TalonFX {

    private String m_devName;
    private String m_devDescription;
    private Monitor m_monitor;
    public boolean isConnected = true;

    public DevTalonFX(String devName, int deviceNumber) {
        super(deviceNumber);

        m_devName = devName;
        m_devDescription = String.format("TalonFX #%d", deviceNumber);

        isConnected = isConnected();
        if (!isConnected) {
            m_monitor = new Monitor(m_devName, m_devDescription);
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

    public void stopMotor() {
        if (isConnected) {
            super.stopMotor();
            return;
        }

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        m_monitor.log(methodName);
    }

    public double getSelectedSensorPosition() {
        if (isConnected) {
            return super.getSelectedSensorPosition();
        }

        if (isReal) return 0.;

        // Generate fictitious position numbers
        double position = 4096.;
        return position;
    }

    public void setCurrentLimit(double currentLimit) {
        configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(true, currentLimit,0,0), 0);
    }

}
