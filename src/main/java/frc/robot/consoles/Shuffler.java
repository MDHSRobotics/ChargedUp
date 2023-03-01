package frc.robot.consoles;

import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.consoles.tabs.*;
import java.util.Map;

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
    private LightsTab m_lightsTab;

    public Shuffler() {
        ShuffleLogger.logTrivial("Constructing Shuffler...");
        m_forkliftTab = new ForkliftTab();
        m_driveTab = new DriveTab();
        m_inputsTab = new InputsTab();
        m_autonomousTab = new AutonomousTab();
        m_sensorTab = new SensorTab();
        m_lightsTab = new LightsTab();
    }

    public void preInitialize() {
        ShuffleLogger.logTrivial("Pre-Initializing Shuffler...");
        m_forkliftTab.preInitialize();
        m_driveTab.preInitialize();
        m_inputsTab.preInitialize();
        m_autonomousTab.preInitialize();
        m_sensorTab.preInitialize();
        m_lightsTab.preInitialize();
    }

    public void initialize() {
        ShuffleLogger.logTrivial("Initializing Shuffler...");
        m_forkliftTab.initialize();
        m_driveTab.initialize();
        m_inputsTab.initialize();
        m_autonomousTab.initialize();
        m_sensorTab.initialize();
        m_lightsTab.initialize();
    }

    public void configure() {
        ShuffleLogger.logTrivial("Configuring Shuffler...");
        m_forkliftTab.configure();
        m_driveTab.configure();
        m_inputsTab.configure();
        m_autonomousTab.configure();
        m_sensorTab.configure();
        m_lightsTab.configure();

        setupSmartdashboard();
    }

    public void update() {
        m_forkliftTab.update();
        m_driveTab.update();
        m_inputsTab.update();
        m_autonomousTab.update();
        m_driveTab.update();
        m_sensorTab.update();
        m_lightsTab.update();
    }

    // This is for stuff that can't be displayed easily in custom Shuffleboard tabs.
    // It will end up on the SmartDashboard tab.
    private void setupSmartdashboard() {
    }

    //Method for easier layout construction
    public static ShuffleboardLayout constructLayout(ShuffleboardTab tab, String title, int posX, int posY, int width, int height, int columns, int rows, String labelPosition){
        ShuffleboardLayout layout = tab.getLayout(title, BuiltInLayouts.kList);
        layout = tab.getLayout(title, BuiltInLayouts.kList);
        layout.withPosition(posX, posY);
        layout.withSize(width, height);
        layout.withProperties(Map.of("Number of columns", columns));
        layout.withProperties(Map.of("Number of rows", rows));
        layout.withProperties(Map.of("Label position", labelPosition));
        return layout;
    }
}

