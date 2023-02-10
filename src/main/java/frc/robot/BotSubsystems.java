package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.*;

public class BotSubsystems {

    public static ExampleSubsystem exampleSubsystem;
    public static Forklift forklift;

    public static void initializeSubsystems() {
        Logger.setup("Initializing BotSubsystems...");
    
        exampleSubsystem = new ExampleSubsystem();
        forklift = new Forklift();
    }

    public static void setTeleopDefaultCommands() {

        // Forklift
        Logger.setup("SwerveDriver Teleop Default Command -> Forklift...");
        forklift.setDefaultCommand(BotCommands.moveForklift);
        
    }
}
 