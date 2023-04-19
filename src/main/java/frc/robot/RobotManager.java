
package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.consoles.Shuffler;
import frc.robot.consoles.Logger;

// This is where the robot state is initialized and persisted.
public class RobotManager {

    //--------------//
    // Robot States //
    //--------------//

    public enum Variant {
        TEST_BOARD, TEST_DRIVE, BUILD_HOME, BUILD_AWAY
    }

    // Variant is used to configure different device mappings for different "robots"
    // TODO: This needs to be added to the Brain and Shuffleboard, so that it is settable on the fly
    public static Variant botVariant = Variant.TEST_BOARD;

    // If the robot is running in the real world or in simulation
    public static final boolean isReal = RobotBase.isReal();
    public static final boolean isSim = !isReal;

    //-------------------------------//
    // Shuffleboard & SmartDashboard //
    //-------------------------------//

    // The robot Shuffler instance
    public static Shuffler botShuffler;

    // The auto command chooser to add to SmartDashboard
    public static SendableChooser<Command> autoCommandChooser;

    //----------------//
    // Initialization //
    //----------------//

    // It is important that the robot be initialized in exactly this order.
    public static void initialize() {
        Logger.setup("Initializing RobotManager...");

        // Pre-intialize the Shuffler
        botShuffler = new Shuffler();
        //botShuffler.preInitialize();

        // Initialize BotSensors, BotSubsystems, BotCommands, and TestCommands
        BotSensors.initializeSensors();
        BotSubsystems.initializeSubsystems();
        BotCommands.initializeCommands();

        // Intialize and configure the Shuffler
        botShuffler.initialize();
        //botShuffler.configure();    

        // Setup SmartDashboard
        setupSmartDashboard();
    }

    // Add the desired commands to the SmartDashboard
    private static void setupSmartDashboard() {
        Logger.setup("Adding AutoModes to SmartDashboard...");

        autoCommandChooser = new SendableChooser<>();

        // Add commands to the autonomous command chooser
        autoCommandChooser.setDefaultOption("Place Cube Left", BotCommands.placeCubeLeft);
        autoCommandChooser.addOption("Place Cube Right", BotCommands.placeCubeRight);
        autoCommandChooser.addOption("Place Cube Inner", BotCommands.placeCubeInner);
        autoCommandChooser.addOption("Eject Cube Left", BotCommands.ejectCubeLeft);
        autoCommandChooser.addOption("Eject Cube Right", BotCommands.ejectCubeRight);
        autoCommandChooser.addOption("Eject Cube Inner", BotCommands.ejectCubeInner);
        autoCommandChooser.addOption("Testing Auto Command", BotCommands.defaultAutoCommand);

        // Put the chooser on the dashboard
        SmartDashboard.putData("AutoMode", autoCommandChooser);
    }

}
