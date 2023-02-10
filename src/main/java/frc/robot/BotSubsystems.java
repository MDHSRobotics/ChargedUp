package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.*;

public class BotSubsystems {

    public static ExampleSubsystem exampleSubsystem;
    public static SwerveDriver swerveDriver;
    public static Forklift forklift;

    public static void initializeSubsystems() {
        Logger.setup("Initializing BotSubsystems...");
    
        exampleSubsystem = new ExampleSubsystem();
        swerveDriver = new SwerveDriver();
        forklift = new Forklift();
    }

    public static void setTeleopDefaultCommands() {

        // SwerveDriver
        Logger.setup("SwerveDriver Teleop Default Command -> SwerveDrive...");
        swerveDriver.setDefaultCommand(BotCommands.swerveDrive);
        // Forklift
        Logger.setup("SwerveDriver Teleop Default Command -> Forklift...");
        forklift.setDefaultCommand(BotCommands.moveForklift);
        
    }
}
 