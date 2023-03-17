package frc.robot.sensors;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import static frc.robot.RobotManager.isReal;
import frc.robot.consoles.Logger;

public class DevRevV3ColorSensor extends ColorSensorV3  {

    public DevRevV3ColorSensor() {
        super(I2C.Port.kOnboard);
    }

    public void initialize() {

        if (isReal) {
            Logger.setup("Initializing color sensor ...");
            boolean colorSensorIsConnected = isConnected();
            if (!colorSensorIsConnected) {
                Logger.problem("Color sensor not connected!");
            }
        } else {
            Logger.setup("Skipping initializion of color sensor in Simulation mode...");
        }
    }

}