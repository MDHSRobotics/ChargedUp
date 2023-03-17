
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.BotSensors;
import frc.robot.brains.SensorBrain;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;

// The Shuffleboard Inputs tab.
public class SensorTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_gyroLayout;
    private ShuffleboardLayout m_colorSensorLayout;
    private ShuffleboardLayout m_cubeDetectedLayout;
    private ShuffleboardLayout m_coneDetectedLayout;

    // Gyro Widgets
    private SimpleWidget m_rollWidget;
    private SimpleWidget m_pitchWidget;
    private SimpleWidget m_yawWidget;

    // Color Sensor Widgets
    private SimpleWidget m_targetDistanceWidget;
    private SimpleWidget m_redValueWidget;
    private SimpleWidget m_greenValueWidget;
    private SimpleWidget m_blueValueWidget;
    private SimpleWidget m_cubeInRangeWidget;
    private SimpleWidget m_coneInRangeWidget;

    // State flag for displaying gyro data
    private boolean m_updateGyroData;

    // Constructor
    public SensorTab() {
        ShuffleLogger.logTrivial("Constructing SensorsTab...");

        m_tab = Shuffleboard.getTab("Sensors");

        m_gyroLayout = Shuffler.constructLayout(m_tab, "Gyroscope", 0, 0, 3, 3, 1, 3, "LEFT");
        m_colorSensorLayout = Shuffler.constructLayout(m_tab, "Color Sensor", 3, 0, 3, 3, 1, 3, "LEFT");

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

        // Color sensor data 
        m_targetDistanceWidget = m_colorSensorLayout.add("Target Distance", SensorBrain.defaultTargetDistance);
        SensorBrain.entryTargetDistance =  m_targetDistanceWidget.getEntry();

        m_redValueWidget =  m_colorSensorLayout.add("Red Value", SensorBrain.defaultRedValue);
        SensorBrain.entryRedValue = m_redValueWidget.getEntry();

        m_greenValueWidget =  m_colorSensorLayout.add("Green Value", SensorBrain.defaultGreenValue);
        SensorBrain.entryGreenValue = m_greenValueWidget.getEntry();

        m_blueValueWidget =  m_colorSensorLayout.add("Blue Value", SensorBrain.defaultBlueValue);
        SensorBrain.entryBlueValue = m_blueValueWidget.getEntry();

    }

    // Create all other Widgets
    public void initialize() {
        //Objects in range
        m_cubeInRangeWidget = m_cubeDetectedLayout.add("Cube Detected", false);
        SensorBrain.entryCubeInRange = m_cubeInRangeWidget.getEntry(); 

        m_coneInRangeWidget = m_coneDetectedLayout.add("Cone Detected", false);
        SensorBrain.entryConeInRange = m_coneInRangeWidget.getEntry(); 
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {

        SensorBrain.setGyroPitch(BotSensors.gyro.getPitch());
        SensorBrain.setGyroRoll(BotSensors.gyro.getRoll());
        SensorBrain.setGyroYaw(BotSensors.gyro.getYaw());

        SensorBrain.setTargetDistance((BotSensors.colorSensor.getProximity()));
        SensorBrain.setRedValue(BotSensors.colorSensor.getRed());
        SensorBrain.setGreenValue(BotSensors.colorSensor.getGreen());
        SensorBrain.setBlueValue(BotSensors.colorSensor.getBlue());

    }

}
