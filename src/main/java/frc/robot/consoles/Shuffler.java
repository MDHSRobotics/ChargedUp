package frc.robot.consoles;

import frc.robot.consoles.tabs.*;

// Class that wraps all of the interaction with the Shuffleboard

// All decisions about content and layout of the Shuffleboard are consolidated in this file
// to make it easier to change things rather than having to look throughout all of the
// classes for subsystems, commands, etc.

// The Shuffler class knows about the subsystems, commands, etc. but generally not vice versa.
public class Shuffler {
    // Tabs
    private DriveTab m_driveTab;
    private InputsTab m_inputsTab;
    private ForkliftTab m_forkliftTab;
    private AutonomousTab m_autonomousTab;
    private SensorTab m_sensorTab;

    public Shuffler() {
        ShuffleLogger.logTrivial("Constructing Shuffler...");
        m_forkliftTab = new ForkliftTab();
        m_driveTab = new DriveTab();
        m_inputsTab = new InputsTab();
        m_autonomousTab = new AutonomousTab();
        m_jstickTab = new JoystickTab();
        m_sensorTab = new SensorTab();
    }

    public void preInitialize() {
        ShuffleLogger.logTrivial("Pre-Initializing Shuffler...");
        m_forkliftTab.preInitialize();
        m_driveTab.preInitialize();
        m_inputsTab.preInitialize();
        m_autonomousTab.preInitialize();
        m_jstickTab.preInitialize();
        m_sensorTab.preInitialize();
    }

    public void initialize() {
        ShuffleLogger.logTrivial("Initializing Shuffler...");
        m_forkliftTab.initialize();
        m_driveTab.initialize();
        m_inputsTab.initialize();
        m_autonomousTab.initialize();
        m_jstickTab.initialize();
        m_sensorTab.initialize();
    }

    public void configure() {
        ShuffleLogger.logTrivial("Configuring Shuffler...");
        m_forkliftTab.configure();
        m_driveTab.configure();
        m_inputsTab.configure();
        m_autonomousTab.configure();
        m_jstickTab.configure();
        m_sensorTab.configure();

        setupSmartdashboard();
    }

    public void update() {
        m_forkliftTab.update();
        m_mainTab.update();
        m_driveTab.update();
        m_inputsTab.update();
        m_autonomousTab.update();
        m_driveTab.update();
        m_jstickTab.update();
        m_sensorTab.update();
    }

    // This is for stuff that can't be displayed easily in custom Shuffleboard tabs.
    // It will end up on the SmartDashboard tab.
    private void setupSmartdashboard() {
    }

}

