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
    private ShuffleboardLayout m_commandLayout;

    // Widgets
    private SimpleWidget extenderPower;
    private SimpleWidget verticalPower;
    private ComplexWidget openClamp, closeClamp;

    // Constructor
    public ForkliftTab() {

        ShuffleLogger.logTrivial("Constructing Forklift...");

        m_tab = Shuffleboard.getTab("Forklift");

        m_motorPowersLayout = Shuffler.constructLayout(m_tab, "Motor Powers", 4, 0, 8, 3, 1, 2, "LEFT");
        m_commandLayout = Shuffler.constructLayout(m_tab, "Commands", 0, 0, 4, 2, 1, 2, "LEFT");
        
    }

    // Create Brain Widgets
    public void preInitialize() {
        //Motor Powers
        extenderPower = m_motorPowersLayout.add("Extender Power", ForkliftBrain.extenderPowerDefault);
        ForkliftBrain.entryExtenderPower = extenderPower.getEntry();
        extenderPower.withWidget(BuiltInWidgets.kNumberSlider);
        extenderPower.withProperties(Map.of("min", 0, "max", 1.0));

        verticalPower = m_motorPowersLayout.add("Vertical Power", ForkliftBrain.elevatorPowerDefault);
        ForkliftBrain.entryElevatorPower = verticalPower.getEntry();
        verticalPower.withWidget(BuiltInWidgets.kNumberSlider);
        verticalPower.withProperties(Map.of("min", 0, "max", 1.0));
        
    }

    // Create all other Widgets
    public void initialize() {
        openClamp = m_commandLayout.add("Open Clamp", BotCommands.openClamp);
        closeClamp = m_commandLayout.add("Close Clamp", BotCommands.closeClamp);
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
    }
}
