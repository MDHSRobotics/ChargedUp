
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.BotSensors;
import frc.robot.brains.SensorBrain;
import frc.robot.consoles.Logger;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;

// The Shuffleboard Inputs tab.
public class SensorTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_gyroLayout;

    // Gyro Widgets
    private SimpleWidget m_rollWidget;
    private SimpleWidget m_pitchWidget;
    private SimpleWidget m_yawWidget;
    private SimpleWidget m_angleWidget;

    // State flag for displaying gyro data
    private boolean m_updateGyroData;

    // Constructor
    public SensorTab() {
        ShuffleLogger.logTrivial("Constructing SensorsTab...");

        m_tab = Shuffleboard.getTab("Sensors");

        m_gyroLayout = Shuffler.constructLayout(m_tab, "Gyroscope", 0, 0, 3, 3, 1, 3, "LEFT");

        m_updateGyroData = true;

    }

    // Create Brain Widgets
    public void preInitialize() {

        // Gyro data
        m_pitchWidget = m_gyroLayout.add("Pitch", SensorBrain.defaultGyroPitch);
        SensorBrain.entryGyroPitch = m_pitchWidget.getEntry();

        m_rollWidget = m_gyroLayout.add("Roll", SensorBrain.defaultGyroRoll);
        SensorBrain.entryGyroRoll = m_rollWidget.getEntry();
        
        m_yawWidget = m_gyroLayout.add("Yaw", SensorBrain.defaultGyroYaw);
        SensorBrain.entryGyroYaw = m_yawWidget.getEntry();

    }

    // Create all other Widgets
    public void initialize() {
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {

        SensorBrain.setGyroPitch(BotSensors.gyro.getPitch());
        SensorBrain.setGyroRoll(BotSensors.gyro.getRoll());
        SensorBrain.setGyroYaw(BotSensors.gyro.getYaw());

    }

}
