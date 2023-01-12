package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.*;

public class BotSubsystems {

    public static ExampleSubsystem exampleSubsystem;

    public static void initializeSubsystems() {
        Logger.setup("Initializing BotSubsystems...");
    
        exampleSubsystem = new ExampleSubsystem();
    }

    public static void setTeleopDefaultCommands() {

        // SwerveDriver
        //Logger.setup("SwerveDriver Teleop Default Command -> SwerveDrive...");
        //swerveDriver.setDefaultCommand(BotCommands.swerveDrive);
        
    }
}
 