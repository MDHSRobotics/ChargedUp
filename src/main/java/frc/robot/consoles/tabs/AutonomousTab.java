package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.brains.SwerveDriverBrain;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;
import frc.robot.BotCommands;
import frc.robot.BotSubsystems;
import frc.robot.commands.auto.*;

public class AutonomousTab {

        // Tab & Layouts
        private ShuffleboardTab m_tab;

        // Layouts
        private ShuffleboardLayout m_commandLayout;
        private ShuffleboardLayout m_individualCommandLayout;
        private ShuffleboardLayout m_layoutChargeStationSpeedPID;
    
        // Widgets
        private ComplexWidget m_placeCubeInner, m_placeCubeLeft, m_ejectCubeInner, m_ejectCubeLeft, m_ejectCubeRight, m_placeCubeRight, m_autoCommandDefault;
        private ComplexWidget m_balanceCommand, m_moveForwardCommand, m_ejectCube, m_liftForklift, m_placeCube;
        private SimpleWidget m_forwardTime;

        private SimpleWidget m_widgetChargeStationSpeedP;
        private SimpleWidget m_widgetChargeStationSpeedI;
        private SimpleWidget m_widgetChargeStationSpeedD;
    
        // Constructor
        public AutonomousTab() {
            ShuffleLogger.logTrivial("Constructing AutonomousTab...");
    
            m_tab = Shuffleboard.getTab("Autonomous");
    
            m_commandLayout = Shuffler.constructLayout(m_tab, "Auto Commands", 0, 0, 4, 4, 1, 4, "LEFT");
            m_individualCommandLayout = Shuffler.constructLayout(m_tab, "Individual Auto Commands", 4, 0, 4, 4, 1, 2, "LEFT");

            m_layoutChargeStationSpeedPID = Shuffler.constructLayout(m_tab, "Charge Station PID", 0, 4, 3, 4, 1, 3, "TOP");
        }
    
        // Create Brain Widgets
        public void preInitialize() {
            // Charge Station Speed P
            m_widgetChargeStationSpeedP = m_layoutChargeStationSpeedPID.add("Charge Station Speed P", SwerveDriverBrain.defaultChargeStationP);
            SwerveDriverBrain.entryChargeStationSpeedP = m_widgetChargeStationSpeedP.getEntry();
            m_widgetChargeStationSpeedP.withWidget(BuiltInWidgets.kTextView);

            // Charge Station Speed I
            m_widgetChargeStationSpeedI = m_layoutChargeStationSpeedPID.add("Charge Station Speed I", SwerveDriverBrain.defaultChargeStationI);
            SwerveDriverBrain.entryChargeStationSpeedI = m_widgetChargeStationSpeedI.getEntry();
            m_widgetChargeStationSpeedI.withWidget(BuiltInWidgets.kTextView);

            // Charge Station Speed D
            m_widgetChargeStationSpeedD = m_layoutChargeStationSpeedPID.add("Charge Station Speed D", SwerveDriverBrain.defaultChargeStationD);
            SwerveDriverBrain.entryChargeStationSpeedD = m_widgetChargeStationSpeedD.getEntry();
            m_widgetChargeStationSpeedD.withWidget(BuiltInWidgets.kTextView);
        }
    
        // Create all other Widgets
        public void initialize() {
            m_placeCubeInner = m_commandLayout.add("Place Cube Inner", BotCommands.placeCubeInner);
            m_placeCubeLeft = m_commandLayout.add("Place Cube Left", BotCommands.placeCubeLeft);
            m_placeCubeRight = m_commandLayout.add("Place Cube Right", BotCommands.placeCubeRight);
            m_ejectCubeInner = m_commandLayout.add("Eject Cube Inner", BotCommands.ejectCubeInner);
            m_ejectCubeLeft = m_commandLayout.add("Eject Cube Left", BotCommands.ejectCubeLeft);
            m_ejectCubeRight = m_commandLayout.add("Eject Cube Right", BotCommands.ejectCubeRight);
            m_autoCommandDefault = m_commandLayout.add("Default", BotCommands.defaultAutoCommand);

            m_balanceCommand = m_individualCommandLayout.add("Balance Charge Station", new BalanceChargeStation(BotSubsystems.swerveDriver, false));
            m_ejectCube = m_individualCommandLayout.add("Eject Cube", new EjectCube(BotSubsystems.intake, 1));
            m_placeCube = m_individualCommandLayout.add("Place Cube", new PlaceCube());
        }
    
        // Configure all Widgets
        public void configure() {
        }
    
        // This will be called in the robotPeriodic
        public void update() {
        }
    
}
