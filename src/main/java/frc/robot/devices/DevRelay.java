package frc.robot.devices;

import edu.wpi.first.wpilibj.Relay;

import static frc.robot.RobotManager.isSim;

// This class is a wrapper around Relay in order to handle cases where the
// Relay is not physically connected.  This
// can be the case when running the simulator but it can also happen when
// executing code on the RoboRio without a fully assembled robot with all of
// necessary motors and controllers.

// If the Relay is connected then this class just forwards any calls directly
// to the Relay class.

// If the Relay is not connected, only a subset of the Relay interface is
// supported, mainly by tracing and other monitoring.

public class DevRelay extends Relay {

    private String m_devName;
    private String m_devDescription;
    private Monitor m_monitor;
    public boolean isConnected = true;
    public boolean initialized;

    public DevRelay(String devName, int channel) {
        super(channel);

        m_devName = devName;
        m_devDescription = String.format("Relay #%d", channel);

        isConnected = isConnected();
        if (!isConnected) {
            m_monitor = new Monitor(m_devName, m_devDescription);
        }
        initialized = true;
    }

    // Determines if this is connected
    private boolean isConnected() {
        if (isSim)
            return false;
        return true;
    }

    public void set(Value value) {
        if (isConnected || !initialized) {
            super.set(value);
            return;
        }

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        String arg = value.toString();
        m_monitor.log(methodName, arg);
    }

}