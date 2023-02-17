
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import java.util.Map;

import frc.robot.brains.JoystickBrain;
import frc.robot.consoles.ShuffleLogger;

// The Shuffleboard Joystick tab.
public class JoystickTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;

    private ShuffleboardLayout m_deadZoneLayout;
    private ShuffleboardLayout m_sensitivitLayout;

    // Dead Zones
    private SimpleWidget m_yDeadZone;
    private SimpleWidget m_xDeadZone;
    private SimpleWidget m_zDeadZone;

    // Sensitivity
    private SimpleWidget m_ySensitivity;
    private SimpleWidget m_xSensitivity;
    private SimpleWidget m_zSensitivity;

    // Constructor
    public JoystickTab() {
        ShuffleLogger.logTrivial("Constructing Joystick...");

        m_tab = Shuffleboard.getTab("Joystick");

        m_deadZoneLayout = m_tab.getLayout("Dead Zones", BuiltInLayouts.kGrid);
        m_deadZoneLayout.withPosition(0, 2); 
        m_deadZoneLayout.withSize(3, 1); 
        m_deadZoneLayout.withProperties(Map.of("Number of columns", 2)); 
        m_deadZoneLayout.withProperties(Map.of("Number of rows", 2)); 
        m_deadZoneLayout.withProperties(Map.of("Label position", "LEFT")); 

        m_sensitivitLayout = m_tab.getLayout("Sensitivity", BuiltInLayouts.kGrid);
        m_sensitivitLayout.withPosition(0, 2); 
        m_sensitivitLayout.withSize(3, 1); 
        m_sensitivitLayout.withProperties(Map.of("Number of columns", 2)); 
        m_sensitivitLayout.withProperties(Map.of("Number of rows", 2)); 
        m_sensitivitLayout.withProperties(Map.of("Label position", "LEFT")); 
    }

    // Create Brain Widgets
    public void preInitialize() {
        // Dead Zones
        m_yDeadZone = m_deadZoneLayout.add("yDeadZone", JoystickBrain.defaultYDeadZone);
        JoystickBrain.entryYDeadZone = m_yDeadZone.getEntry();
        m_yDeadZone.withWidget(BuiltInWidgets.kTextView);

        m_xDeadZone = m_deadZoneLayout.add("xDeadZone", JoystickBrain.defaultXDeadZone);
        JoystickBrain.entryXDeadZone = m_xDeadZone.getEntry();
        m_xDeadZone.withWidget(BuiltInWidgets.kTextView);

        m_zDeadZone = m_deadZoneLayout.add("zDeadZone", JoystickBrain.defaultZDeadZone);
        JoystickBrain.entryZDeadZone = m_zDeadZone.getEntry();
        m_zDeadZone.withWidget(BuiltInWidgets.kTextView);

        // Sensitivity
        m_ySensitivity = m_sensitivitLayout.add("ySensitivity", JoystickBrain.defaultYSensitivity);
        JoystickBrain.entryYSensitivity = m_ySensitivity.getEntry();
        m_ySensitivity.withWidget(BuiltInWidgets.kTextView);

        m_xSensitivity = m_sensitivitLayout.add("xSensitivity", JoystickBrain.defaultXSensitivity);
        JoystickBrain.entryXSensitivity = m_xSensitivity.getEntry();
        m_xSensitivity.withWidget(BuiltInWidgets.kTextView);

        m_zSensitivity = m_sensitivitLayout.add("zSensitivity", JoystickBrain.defaultZSensitivity);
        JoystickBrain.entryZSensitivity = m_zSensitivity.getEntry();
        m_zSensitivity.withWidget(BuiltInWidgets.kTextView);
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
