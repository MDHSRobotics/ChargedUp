package frc.robot;

import frc.robot.consoles.Logger;
import frc.robot.commands.auto.*;
import frc.robot.commands.forklift.*;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.commands.swervedrive.*;

public class BotCommands {

    // Auto Command options
    public static AutoPlaceCubeInner placeCubeInner;
    public static AutoPlaceCubeOuter placeCubeOuter;
    public static AutoEjectCubeInner ejectCubeInner;
    public static AutoEjectCubeOuter ejectCubeOuterLeft;
    public static AutoEjectCubeOuter ejectCubeOuterRight;
    public static DefaultAutoCommand defaultAutoCommand;

    // SwerveDriver
    public static SwerveDrive swerveDrive;
    public static ToggleDriverOrientation toggleDriverOrientation;

    public static ChangeDriveSpeed speedUpDrive;
    public static ChangeDriveSpeed slowDownDrive;

    // Forklift
    public static MoveForklift moveForklift;
    public static ForkliftPickUpPosition forkliftPickUpPosition;
    public static CommandBase openClamp;
    public static CommandBase closeClamp;
    public static CommandBase enableSoftStop;
    public static CommandBase disableSoftStop;
    public static CommandBase resetEncoders;

    //Intake
    public static MoveIntake moveIntake;

    // Initialize all robot commands
    public static void initializeCommands() {

        Logger.setup("Initializing BotCommands...");

        // SwerveDriver
        swerveDrive = new SwerveDrive(BotSubsystems.swerveDriver, BotControllers.xbox1); 
        toggleDriverOrientation = new ToggleDriverOrientation(BotSubsystems.swerveDriver);

        speedUpDrive = new ChangeDriveSpeed(1);
        slowDownDrive = new ChangeDriveSpeed(0);
        
        // Forklift
        moveForklift = new MoveForklift(BotSubsystems.forklift); 
        forkliftPickUpPosition = new ForkliftPickUpPosition(BotSubsystems.forklift); 

        enableSoftStop = BotSubsystems.forklift.enableSoftStop();
        disableSoftStop = BotSubsystems.forklift.disableSoftStop();
        resetEncoders = BotSubsystems.forklift.resetEncoders();
        

        //Intake
        moveIntake = new MoveIntake(BotSubsystems.intake);
        
        // Pneumatics
        openClamp = BotSubsystems.forklift.openClampCommand();
        closeClamp = BotSubsystems.forklift.closeClampCommand();

        // Auto Commands 
        placeCubeInner = new AutoPlaceCubeInner();
        placeCubeOuter = new AutoPlaceCubeOuter();
        ejectCubeInner = new AutoEjectCubeInner();
        ejectCubeOuterLeft = new AutoEjectCubeOuter(-1);
        ejectCubeOuterRight = new AutoEjectCubeOuter(1);
        defaultAutoCommand = new DefaultAutoCommand();
    }
}
