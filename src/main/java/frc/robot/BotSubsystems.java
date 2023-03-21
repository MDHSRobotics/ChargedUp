package frc.robot;

import java.util.Map;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.*;

public class BotSubsystems {

    public static SwerveDriver swerveDriver;
    public static Forklift forklift;
    public static Intake intake;
    public static GenericSubsystem flipper;

    public static void initializeSubsystems() {
        Logger.setup("Initializing BotSubsystems...");
    

        swerveDriver = new SwerveDriver();
        forklift = new Forklift();
        intake = new Intake();
        flipper = new GenericSubsystem(Map.of("sparkMaxFlipper", 21));
    }

    public static void setTeleopDefaultCommands() {

        // SwerveDriver
        Logger.setup("SwerveDriver Teleop Default Command -> SwerveDrive...");
        swerveDriver.setDefaultCommand(BotCommands.swerveDrive); 

        // Forklift
        Logger.setup("SwerveDriver Teleop Default Command -> Forklift...");
        forklift.setDefaultCommand(BotCommands.moveForklift);

        // Intake
        Logger.setup("SwerveDriver Teleop Default Command -> Intake...");
        intake.setDefaultCommand(BotCommands.moveIntake);
    }
}
 