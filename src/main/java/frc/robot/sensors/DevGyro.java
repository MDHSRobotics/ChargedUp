
package frc.robot.sensors;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.SPI;

import static frc.robot.RobotManager.isReal;
import frc.robot.consoles.Logger;

// This class contains methods for obtaining useful gyro readings.
public class DevGyro {

    private AHRS m_ahrsGyro;

    public DevGyro() {

        m_ahrsGyro = new AHRS(SPI.Port.kMXP);
    }

    public void initialize() {

        if (isReal) {
            Logger.setup("Initializing gyro ...");
            boolean gyroIsConnected = m_ahrsGyro.isConnected();
            if (!gyroIsConnected) {
                Logger.problem("Gyro not connected!");
            }
        } else {
            Logger.setup("Skipping initializion of gyro in Simulation mode...");
        }
    }

    public void reset() {
        if (isReal) {
            m_ahrsGyro.reset();
        }
{}    }

    public double getPitch() {
        double value;
        if (isReal) {
            value = m_ahrsGyro.getPitch();
        } else {
            value = 10.;
        }
        return value;
    }

    public double getRoll() {
        double value;
        if (isReal) {
            value = m_ahrsGyro.getRoll();
        } else {
            value = 20.;
        }
        return value;
    }

    public double getYaw() {
        double value;
        if (isReal) {
            value = m_ahrsGyro.getYaw();
        } else {
            value = 30.;
        }
        return value;
    }

    public double getAngle() {
        double value;
        if (isReal) {
            value = m_ahrsGyro.getAngle();
        } else {
            value = 40.;
        }
        return value;
    }

    public Rotation2d getRotation2d() {
        Rotation2d value;
        if (isReal) {
            value = m_ahrsGyro.getRotation2d();
        } else {
            value = new Rotation2d(50.);
        }
        return value;
    }
}
