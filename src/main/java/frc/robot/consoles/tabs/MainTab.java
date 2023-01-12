
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.BotCommands;

import java.util.Map;

// The Shuffleboard Main tab.
public class MainTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;

    // Layouts
    private ShuffleboardLayout m_commandLayout;

    // Widgets
    private ComplexWidget m_commandWidget1, m_commandWidget2, m_commandWidget3;

    // Constructor
    public MainTab() {
        ShuffleLogger.logTrivial("Constructing MainTab...");

        m_tab = Shuffleboard.getTab("Main");

        m_commandLayout = m_tab.getLayout("Auto Commands", BuiltInLayouts.kList);
        m_commandLayout.withPosition(0, 0);
        m_commandLayout.withSize(2, 2);
        m_commandLayout.withProperties(Map.of("Number of columns", 2));
        m_commandLayout.withProperties(Map.of("Number of rows", 3));
        m_commandLayout.withProperties(Map.of("Label position", "LEFT"));
    }

    // Create Brain Widgets
    public void preInitialize() {
    }

    // Create all other Widgets
    public void initialize() {
        m_commandWidget1 = m_commandLayout.add("Auto 1", BotCommands.autoCommand1);
        m_commandWidget2 = m_commandLayout.add("Auto 2", BotCommands.autoCommand2);
        m_commandWidget3 = m_commandLayout.add("Auto 3", BotCommands.autoCommand3);
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
