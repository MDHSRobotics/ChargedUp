package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.brains.ForkliftBrain;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;
import frc.robot.BotCommands;

import java.util.Map;

public class ForkliftTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;

    private ShuffleboardLayout m_motorPowersLayout;
    private ShuffleboardLayout m_motorSpeedsLayout;
    private ShuffleboardLayout m_motorEncodersLayout;
    private ShuffleboardLayout m_commandLayout;
    private ShuffleboardLayout m_softStopLayout;

    // Widgets
    private ComplexWidget openClamp, closeClamp;
    private ComplexWidget enableSoftStop, disableSoftStop, resetEncoders;

    private SimpleWidget m_widgetExtenderPower;
    private SimpleWidget m_widgetElevatorPower;

    private SimpleWidget m_widgetExtenderSpeed;
    private SimpleWidget m_widgetElevatorSpeed;

    private SimpleWidget m_widgetExtenderEncoder;
    private SimpleWidget m_widgetElevatorEncoder;

    private SimpleWidget m_widgetSoftStopEnabled;

    // Constructor
    public ForkliftTab() {

        ShuffleLogger.logTrivial("Constructing Forklift...");

        m_tab = Shuffleboard.getTab("Forklift");

        m_commandLayout = Shuffler.constructLayout(m_tab, "Commands", 0, 0, 4, 2, 1, 2, "LEFT");
        m_motorPowersLayout = Shuffler.constructLayout(m_tab, "Motor Powers", 4, 0, 8, 3, 1, 2, "LEFT");
        m_motorSpeedsLayout = Shuffler.constructLayout(m_tab, "Motor Speeds", 4, 3, 8, 3, 1, 2, "LEFT");
        m_motorEncodersLayout = Shuffler.constructLayout(m_tab, "Motor Encoders", 0, 2, 4, 2, 1, 2, "LEFT");
        m_softStopLayout = Shuffler.constructLayout(m_tab, "Soft Stop", 0, 4, 4, 4, 1, 2, "LEFT");
    }

    // Create Brain Widgets
    public void preInitialize() {
        //Motor Powers
        m_widgetExtenderPower = m_motorPowersLayout.add("Extender Power", ForkliftBrain.defaultExtenderPower);
        ForkliftBrain.entryExtenderPower = m_widgetExtenderPower.getEntry();
        m_widgetExtenderPower.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetExtenderPower.withProperties(Map.of("min", 0, "max", 1.0));

        m_widgetElevatorPower = m_motorPowersLayout.add("Elevator Power", ForkliftBrain.defaultElevatorPower);
        ForkliftBrain.entryElevatorPower = m_widgetElevatorPower.getEntry();
        m_widgetElevatorPower.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetElevatorPower.withProperties(Map.of("min", 0, "max", 1.0));
        
        //Motor Speeds
        m_widgetExtenderSpeed = m_motorSpeedsLayout.add("Extender Speed", ForkliftBrain.defaultExtenderSpeed);
        ForkliftBrain.entryExtenderSpeed = m_widgetExtenderSpeed.getEntry();
        m_widgetExtenderSpeed.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetExtenderSpeed.withProperties(Map.of("min", 0, "max", 1.0));

        m_widgetElevatorSpeed = m_motorSpeedsLayout.add("Elevator Speed", ForkliftBrain.defaultElevatorSpeed);
        ForkliftBrain.entryElevatorSpeed = m_widgetElevatorSpeed.getEntry();
        m_widgetElevatorSpeed.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetElevatorSpeed.withProperties(Map.of("min", 0, "max", 1.0));

        m_widgetExtenderEncoder = m_motorEncodersLayout.add("Extender Encoder", ForkliftBrain.defaultExtenderEncoder);
        ForkliftBrain.entryExtenderEncoder = m_widgetExtenderEncoder.getEntry();

        m_widgetElevatorEncoder = m_motorEncodersLayout.add("Elevator Encoder", ForkliftBrain.defaultElevatorEncoder);
        ForkliftBrain.entryElevatorEncoder = m_widgetElevatorEncoder.getEntry();

        m_widgetSoftStopEnabled = m_softStopLayout.add("Enabled", ForkliftBrain.defaultSoftStopEnabled);
        ForkliftBrain.entrySoftStopEnabled = m_widgetSoftStopEnabled.getEntry();
    }

    // Create all other Widgets
    public void initialize() {
        openClamp = m_commandLayout.add("Open Clamp", BotCommands.openClamp);
        closeClamp = m_commandLayout.add("Close Clamp", BotCommands.closeClamp);

        enableSoftStop = m_softStopLayout.add("Enable", BotCommands.enableSoftStop);
        disableSoftStop = m_softStopLayout.add("Disable", BotCommands.disableSoftStop);
        resetEncoders = m_softStopLayout.add("Reset Encoders", BotCommands.resetEncoders);
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
    }
}
