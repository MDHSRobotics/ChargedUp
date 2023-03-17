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

    private ShuffleboardLayout m_motorSoftStopLayout;
    private ShuffleboardLayout m_motorSpeedsLayout;
    private ShuffleboardLayout m_motorEncodersLayout;
    private ShuffleboardLayout m_commandLayout;
    private ShuffleboardLayout m_softStopLayout;
    private ShuffleboardLayout m_positionsLayout;

    // Widgets
    private ComplexWidget openClamp, closeClamp;
    private ComplexWidget enableSoftStop, disableSoftStop, resetEncoders;

    private SimpleWidget m_widgetExtenderSoftStop;
    private SimpleWidget m_widgetElevatorSoftStop;

    private SimpleWidget m_widgetExtenderSpeed;
    private SimpleWidget m_widgetElevatorSpeed;

    private SimpleWidget m_widgetExtenderEncoder;
    private SimpleWidget m_widgetElevatorEncoder;

    private SimpleWidget m_widgetSoftStopEnabled;
    
    private SimpleWidget m_widgetElevatorPickupPosition;
    private SimpleWidget m_widgetElevatorMediumPosition;
    private SimpleWidget m_widgetElevatorHighPosition;

    private SimpleWidget m_widgetExtenderPickupPosition;
    private SimpleWidget m_widgetExtenderMediumPosition;
    private SimpleWidget m_widgetExtenderHighPosition;

    // Constructor
    public ForkliftTab() {

        ShuffleLogger.logTrivial("Constructing ForkliftTab...");

        m_tab = Shuffleboard.getTab("Forklift");

        m_commandLayout = Shuffler.constructLayout(m_tab, "Commands", 0, 0, 4, 2, 1, 2, "LEFT");
        m_motorSoftStopLayout = Shuffler.constructLayout(m_tab, "Motor Soft Stops", 4, 3, 6, 3, 1, 2, "LEFT");
        m_motorSpeedsLayout = Shuffler.constructLayout(m_tab, "Motor Speeds", 4, 0, 6, 3, 1, 2, "LEFT");
        m_motorEncodersLayout = Shuffler.constructLayout(m_tab, "Motor Encoders", 0, 2, 4, 2, 1, 2, "LEFT");
        m_softStopLayout = Shuffler.constructLayout(m_tab, "Soft Stop", 0, 4, 4, 4, 1, 2, "LEFT");
        m_positionsLayout = Shuffler.constructLayout(m_tab, "Positions", 11, 0, 8, 8, 0, 0, "TOP");
    }

    // Create Brain Widgets
    public void preInitialize() {
        //Motor Soft Stops
        m_widgetExtenderSoftStop = m_motorSoftStopLayout.add("Extender Soft Stop", ForkliftBrain.defaultExtenderSoftStop);
        ForkliftBrain.entryExtenderSoftStop = m_widgetExtenderSoftStop.getEntry();
        m_widgetExtenderSoftStop.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetExtenderSoftStop.withProperties(Map.of("min", 0, "max", 200.0));

        m_widgetElevatorSoftStop = m_motorSoftStopLayout.add("Elevator Soft Stop", ForkliftBrain.defaultElevatorSoftStop);
        ForkliftBrain.entryElevatorSoftStop = m_widgetElevatorSoftStop.getEntry();
        m_widgetElevatorSoftStop.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetElevatorSoftStop.withProperties(Map.of("min", 0, "max", 100.0));
        
        //Motor Speeds
        m_widgetExtenderSpeed = m_motorSpeedsLayout.add("Extender Speed", ForkliftBrain.defaultExtenderSpeed);
        ForkliftBrain.entryExtenderSpeed = m_widgetExtenderSpeed.getEntry();
        m_widgetExtenderSpeed.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetExtenderSpeed.withProperties(Map.of("min", 0, "max", 1.0));

        m_widgetElevatorSpeed = m_motorSpeedsLayout.add("Elevator Speed", ForkliftBrain.defaultElevatorSpeed);
        ForkliftBrain.entryElevatorSpeed = m_widgetElevatorSpeed.getEntry();
        m_widgetElevatorSpeed.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetElevatorSpeed.withProperties(Map.of("min", 0, "max", 1.0));

        //Motor Encoders
        m_widgetExtenderEncoder = m_motorEncodersLayout.add("Extender Encoder", ForkliftBrain.defaultExtenderEncoder);
        ForkliftBrain.entryExtenderEncoder = m_widgetExtenderEncoder.getEntry();

        m_widgetElevatorEncoder = m_motorEncodersLayout.add("Elevator Encoder", ForkliftBrain.defaultElevatorEncoder);
        ForkliftBrain.entryElevatorEncoder = m_widgetElevatorEncoder.getEntry();

        m_widgetSoftStopEnabled = m_softStopLayout.add("Enabled", ForkliftBrain.defaultSoftStopEnabled);
        ForkliftBrain.entrySoftStopEnabled = m_widgetSoftStopEnabled.getEntry();

        m_widgetElevatorHighPosition = m_positionsLayout.add("Elevator High Position", ForkliftBrain.defaultElevatorHighPosition);
        ForkliftBrain.elevatorHighPosition = m_widgetElevatorHighPosition.getEntry();
        m_widgetElevatorHighPosition.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetElevatorHighPosition.withProperties(Map.of("min", 0, "max", 120));

        m_widgetElevatorMediumPosition = m_positionsLayout.add("Elevator Medium Position", ForkliftBrain.defaultElevatorMediumPosition);
        ForkliftBrain.elevatorMediumPosition = m_widgetElevatorMediumPosition.getEntry();
        m_widgetElevatorMediumPosition.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetElevatorMediumPosition.withProperties(Map.of("min", 0, "max", 120));

        m_widgetElevatorPickupPosition = m_positionsLayout.add("Elevator Pickup Position", ForkliftBrain.defaultElevatorPickupPosition);
        ForkliftBrain.elevatorPickupPosition = m_widgetElevatorPickupPosition.getEntry();
        m_widgetElevatorPickupPosition.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetElevatorPickupPosition.withProperties(Map.of("min", 0, "max", 120));

        m_widgetExtenderHighPosition = m_positionsLayout.add("Extender High Position", ForkliftBrain.defaultExtenderHighPosition);
        ForkliftBrain.extenderHighPosition = m_widgetExtenderHighPosition.getEntry();
        m_widgetExtenderHighPosition.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetExtenderHighPosition.withProperties(Map.of("min", 0, "max", 120));

        m_widgetExtenderMediumPosition = m_positionsLayout.add("Extender Medium Position", ForkliftBrain.defaultExtenderMediumPosition);
        ForkliftBrain.extenderMediumPosition = m_widgetExtenderMediumPosition.getEntry();
        m_widgetExtenderMediumPosition.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetExtenderMediumPosition.withProperties(Map.of("min", 0, "max", 120));

        m_widgetExtenderPickupPosition = m_positionsLayout.add("Extender Pickup Position", ForkliftBrain.defaultExtenderPickupPosition);
        ForkliftBrain.extenderPickupPosition = m_widgetExtenderPickupPosition.getEntry();
        m_widgetExtenderPickupPosition.withWidget(BuiltInWidgets.kNumberSlider);
        m_widgetExtenderPickupPosition.withProperties(Map.of("min", 0, "max", 120));
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
