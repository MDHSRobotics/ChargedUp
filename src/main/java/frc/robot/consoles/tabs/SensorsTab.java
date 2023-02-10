
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.brains.XboxBrain;
import frc.robot.consoles.ShuffleLogger;

import java.util.Map;

// The Shuffleboard Inputs tab.
public class SensorsTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_gyroLayout;

    // Gyro Widgets
    private SimpleWidget m_rollWidget;
    private SimpleWidget m_pitchWidget;
    private SimpleWidget m_yawWidget;

    // Constructor
    public SensorsTab() {
        ShuffleLogger.logTrivial("Constructing SensorsTab...");

        m_tab = Shuffleboard.getTab("Sensors");

        m_gyroLayout = m_tab.getLayout("Gyro", BuiltInLayouts.kGrid);
        m_gyroLayout.withPosition(0, 0);
        m_gyroLayout.withSize(2, 3);
        m_gyroLayout.withProperties(Map.of("Number of columns", 2));
        m_gyroLayout.withProperties(Map.of("Number of rows", 3));
        m_gyroLayout.withProperties(Map.of("Label position", "LEFT"));

    // Create Brain Widgets
    public void preInitialize() {

        // Thumbstick - Left
        m_pitchWidget = m_gyroLayout.add("Pitch", XboxBrain.yLeftDeadZoneDefault);
        XboxBrain.yLeftDeadZoneEntry = m_pitchWidget.getEntry();
        m_pitchWidget.withWidget(BuiltInWidgets.kTextView);

        m_xLeftDeadZoneWidget = m_xboxLeftLayout.add("X Left Dead Zone", XboxBrain.xLeftDeadZoneDefault);
        XboxBrain.xLeftDeadZoneEntry = m_xLeftDeadZoneWidget.getEntry();
        m_xLeftDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_yLeftSensitivityWidget = m_xboxLeftLayout.add("Y Left Sensitivity", XboxBrain.yLeftSensitivityDefault);
        XboxBrain.yLeftSensitivityEntry = m_yLeftSensitivityWidget.getEntry();
        m_yLeftSensitivityWidget.withWidget(BuiltInWidgets.kTextView);
      
    }

    // Create all other Widgets
    public void initialize() {
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
