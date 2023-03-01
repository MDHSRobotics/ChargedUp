
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.BotCommands;
import frc.robot.BotSensors;
import frc.robot.brains.LighterBrain;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;

public class LightsTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_lightsLayout;

    // Light Widgets
    private SimpleWidget m_numLightsWidget;
    private SimpleWidget m_redValueWidget;
    private SimpleWidget m_greenValueWidget;
    private SimpleWidget m_blueValueWidget;

    private ComplexWidget m_setColor;

    // Constructor
    public LightsTab() {
        ShuffleLogger.logTrivial("Constructing LightsTab...");

        m_tab = Shuffleboard.getTab("Lights");

        m_lightsLayout = Shuffler.constructLayout(m_tab, "Lights", 0, 0, 3, 3, 1, 3, "LEFT");;

    }

    // Create Brain Widgets
    public void preInitialize() {

        m_numLightsWidget = m_lightsLayout.add("Number of LEDs", LighterBrain.getNumLights());
        LighterBrain.numLightsEntry = m_numLightsWidget.getEntry();
        m_numLightsWidget.withWidget(BuiltInWidgets.kTextView);

        m_redValueWidget = m_lightsLayout.add("Red Value", LighterBrain.getRedValue());
        LighterBrain.redValueEntry = m_redValueWidget.getEntry();
        m_redValueWidget.withWidget(BuiltInWidgets.kTextView);


        m_greenValueWidget = m_lightsLayout.add("Green Value", LighterBrain.getGreenValue());
        LighterBrain.greenValueEntry = m_greenValueWidget.getEntry();
        m_greenValueWidget.withWidget(BuiltInWidgets.kTextView);

        m_blueValueWidget = m_lightsLayout.add("Blue Value", LighterBrain.getBlueValue());
        LighterBrain.blueValueEntry = m_blueValueWidget.getEntry();
        m_blueValueWidget.withWidget(BuiltInWidgets.kTextView);

    }

    // Create all other Widgets
    public void initialize() {
        m_setColor = m_lightsLayout.add("Set Color", BotCommands.setDefaultColor);
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {

    }
    
}
