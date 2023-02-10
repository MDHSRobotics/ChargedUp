package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.brains.ForkliftBrain;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.BotCommands;

import java.util.Map;

public class ForkliftTab {

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
    public ForkliftTab() {

        ShuffleLogger.logTrivial("Constructing Forklift...");

        m_tab = Shuffleboard.getTab("Forklift");

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
