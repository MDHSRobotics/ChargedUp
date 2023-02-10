package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.commands.auto.*;
import frc.robot.commands.forklift.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.swervedrive.*;

public class BotCommands {

    // Example Command to be used as a placeholder until real commands implemented
    public static ExampleCommand exampleCommand;

    // Auto Command options
    public static AutoCommand1 autoCommand1;
    public static AutoCommand2 autoCommand2;
    public static AutoCommand3 autoCommand3;
    public static DefaultAutoCommand defaultAutoCommand;

    // SwerveDriver
    public static SwerveDrive swerveDrive;
    public static ToggleDriverOrientation toggleDriverOrientation;

    // Forklift
    public static MoveForklift moveForklift;
    public static CommandBase toggleClamp;

    // Initialize all robot commands
    public static void initializeCommands() {

        Logger.setup("Initializing BotCommands...");

        // Re-usable Example Command
        exampleCommand = new ExampleCommand("Default", 10);
        
        // Auto Commands 
        autoCommand1 = new AutoCommand1();
        autoCommand2 = new AutoCommand2();
        autoCommand3 = new AutoCommand3();
        defaultAutoCommand = new DefaultAutoCommand();

        // SwerveDriver
        swerveDrive = new SwerveDrive(BotSubsystems.swerveDriver, BotControllers.xbox1); 
        toggleDriverOrientation = new ToggleDriverOrientation(BotSubsystems.swerveDriver);
 
        // Forklift
        moveForklift = new MoveForklift(BotSubsystems.forklift); 
        
        // Pneumatics
        toggleClamp = BotSubsystems.forklift.toggleClampCommand();
    }
}
