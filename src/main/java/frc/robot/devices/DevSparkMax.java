
package frc.robot.devices;

import com.revrobotics.CANSparkMax;

import static frc.robot.RobotManager.isSim;
import static frc.robot.RobotManager.isReal;

// This class is a wrapper around SparkMax in order to handle cases where the
// Talon controller and associated motor are not physically connected.  This
// can be the case when running the simulator but it can also happen when
// executing code on the RoboRio without a fully assembled robot with all of
// necessary motors and controllers.

// If the SparkMax is connected then this class just forwards any calls directly
// to the SparkMax class.

// If the SparkMax is not connected, only a subset of the TalonFX interface is
// supported, mainly by tracing and other monitoring.

public class DevSparkMax extends CANSparkMax {

    private String m_devName;
    private String m_devDescription;
    private Monitor m_monitor;
    public boolean isConnected = true;

    public DevSparkMax(String devName, int deviceNumber, MotorType motorType) {
        super(deviceNumber, motorType);

        m_devName = devName;
        m_devDescription = String.format("SparkMax #%d", deviceNumber);

        isConnected = isConnected();
        if (!isConnected) {
            m_monitor = new Monitor(m_devName, m_devDescription);
        }

        if (isConnected) {
            //configFactoryDefault();
        }
    }

    // Determines if this is connected
    private boolean isConnected() {
        if (isSim) return false;

        return true;
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

    public double getEncoderPosition() {
        if (isConnected) {
            return super.getEncoder().getPosition();
        }

        if (isReal) return 0.;

        // Generate fictitious position numbers
        double position = 4096.;
        return position;
    }
    

}
