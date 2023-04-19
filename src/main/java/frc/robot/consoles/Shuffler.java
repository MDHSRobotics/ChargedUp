package frc.robot.consoles;

import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.consoles.tabs.*;
import java.util.Map;

import frc.robot.BotCommands;

// Class that wraps all of the interaction with the Shuffleboard

// All decisions about content and layout of the Shuffleboard are consolidated in this file
// to make it easier to change things rather than having to look throughout all of the
// classes for subsystems, commands, etc.

// The Shuffler class knows about the subsystems, commands, etc. but generally not vice versa.
public class Shuffler {
    // Tabs
    public static ShuffleboardTab m_driveTab;
    public static ShuffleboardTab m_inputsTab;
    public static ShuffleboardTab m_forkliftTab;
    public static ShuffleboardTab m_autonomousTab;
    public static ShuffleboardTab m_sensorTab;
    public static ShuffleboardTab m_mainTab;

    private ShuffleboardLayout m_forklftCommandLayout = constructLayout(m_forkliftTab, "Commands", 0, 0, 4, 2, 1, 2, "LEFT");

    public Shuffler() {
        ShuffleLogger.logTrivial("Constructing Shuffler...");
        m_forkliftTab = Shuffleboard.getTab("Forklift");
        m_driveTab = Shuffleboard.getTab("Drive");
        m_inputsTab = Shuffleboard.getTab("Inputs");
        m_autonomousTab = Shuffleboard.getTab("Autonomous");
        m_sensorTab = Shuffleboard.getTab("Sensors");
        m_mainTab = Shuffleboard.getTab("Main");
    }

    /*public void preInitialize() {
        ShuffleLogger.logTrivial("Pre-Initializing Shuffler...");
        m_forkliftTab.preInitialize();
        m_driveTab.preInitialize();
        m_inputsTab.preInitialize();
        m_autonomousTab.preInitialize();
        m_sensorTab.preInitialize();
        m_mainTab.preInitialize();
    }
    */
    public void initialize() {
        ShuffleLogger.logTrivial("Initializing Shuffler...");

        m_forklftCommandLayout.add("Open Clamp", BotCommands.openClamp);
        m_forklftCommandLayout.add("Close Clamp", BotCommands.closeClamp);
        m_forklftCommandLayout.add("Reset Encoders", BotCommands.resetEncoders);
        /*m_forkliftTab.initialize();
        m_driveTab.initialize();
        m_inputsTab.initialize();
        m_autonomousTab.initialize();
        m_sensorTab.initialize();
        m_mainTab.initialize();*/
    }
    /*
    public void configure() {
        ShuffleLogger.logTrivial("Configuring Shuffler...");
        m_forkliftTab.configure();
        m_driveTab.configure();
        m_inputsTab.configure();
        m_autonomousTab.configure();
        m_sensorTab.configure();
        m_mainTab.configure();

        setupSmartdashboard();
    }

    public void update() {
        m_forkliftTab.update();
        m_driveTab.update();
        m_inputsTab.update();
        m_autonomousTab.update();
        m_driveTab.update();
        m_sensorTab.update();
        m_mainTab.update();
    }*/

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

