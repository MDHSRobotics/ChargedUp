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

    private ShuffleboardLayout m_positionsLayout;

    // Widgets
    private ComplexWidget m_widgetOpenClamp, m_widgetCloseClamp;
    private ComplexWidget m_widgetEnableSoftStop, m_widgetDisableSoftStop, m_widgetResetEncoders;

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

        m_positionsLayout = Shuffler.constructLayout(m_tab, "Positions", 10, 0, 9, 8, 0, 0, "TOP");
    }

    // Create Brain Widgets
    public void preInitialize() {

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
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
    }
}
