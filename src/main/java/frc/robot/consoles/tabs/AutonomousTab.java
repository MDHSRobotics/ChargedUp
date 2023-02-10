package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.brains.SwerveDriverBrain;
import frc.robot.consoles.ShuffleLogger;

import frc.robot.BotCommands;
import frc.robot.BotSubsystems;
import frc.robot.commands.auto.*;

import java.util.Map;

public class AutonomousTab {

        // Tab & Layouts
        private ShuffleboardTab m_tab;

        // Layouts
        private ShuffleboardLayout m_commandLayout;
        private ShuffleboardLayout m_individualCommandLayout;
    
        // Widgets
        private ComplexWidget m_autoCommand1, m_autoCommand2, m_autoCommand3, m_autoCommandDefault;
        private ComplexWidget m_balanceCommand, m_moveForwardCommand;
    
        // Constructor
        public AutonomousTab() {
            ShuffleLogger.logTrivial("Constructing AutonomousTab...");
    
            m_tab = Shuffleboard.getTab("Autonomous");
    
            m_commandLayout = m_tab.getLayout("Auto Commands", BuiltInLayouts.kList);
            m_commandLayout.withPosition(0, 0);
            m_commandLayout.withSize(2, 2);
            m_commandLayout.withProperties(Map.of("Number of columns", 2));
            m_commandLayout.withProperties(Map.of("Number of rows", 4));
            m_commandLayout.withProperties(Map.of("Label position", "LEFT"));

            m_individualCommandLayout = m_tab.getLayout("Individual Auto Commands", BuiltInLayouts.kList);
            m_individualCommandLayout.withPosition(2, 0);
            m_individualCommandLayout.withSize(2, 3);
            m_individualCommandLayout.withProperties(Map.of("Number of columns", 2));
            m_individualCommandLayout.withProperties(Map.of("Number of rows", 2));
            m_individualCommandLayout.withProperties(Map.of("Label position", "LEFT"));
        }
    
        // Create Brain Widgets
        public void preInitialize() {
        }
    
        // Create all other Widgets
        public void initialize() {
            m_autoCommand1 = m_commandLayout.add("Auto 1", BotCommands.autoCommand1);
            m_autoCommand2 = m_commandLayout.add("Auto 2", BotCommands.autoCommand2);
            m_autoCommand3 = m_commandLayout.add("Auto 3", BotCommands.autoCommand3);
            m_autoCommandDefault = m_commandLayout.add("Default", BotCommands.defaultAutoCommand);

            m_balanceCommand = m_individualCommandLayout.add("Balance Charge Station", new BalanceChargeStation(BotSubsystems.swerveDriver));
            m_moveForwardCommand = m_individualCommandLayout.add("Move Forward", new MoveForward(2));
        }
    
        // Configure all Widgets
        public void configure() {
        }
    
        // This will be called in the robotPeriodic
        public void update() {
        }
    
}
