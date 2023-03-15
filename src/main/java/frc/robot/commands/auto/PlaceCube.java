package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.commands.forklift.ForkliftHighLevelPosition;
import frc.robot.commands.forklift.ForkliftPickUpPosition;
import frc.robot.commands.swervedrive.TimedSwerve;
import frc.robot.subsystems.constants.AutoConstants;

public class PlaceCube extends SequentialCommandGroup{

    public PlaceCube(){

        addCommands(

            new TimedSwerve(BotSubsystems.swerveDriver, -1, 0, 0, 0.2),
            //Lift the forklift before moving forward
            //new LiftForklift(BotSubsystems.forklift, 100, 20),
            new ForkliftHighLevelPosition(BotSubsystems.forklift),

            //Move forward to be over the pole
            new TimedSwerve(BotSubsystems.swerveDriver, AutoConstants.kDrivePower, 0, 0, 0.8),

            //Open the clamp
            BotSubsystems.forklift.openClampCommand(),

            new TimedSwerve(BotSubsystems.swerveDriver, AutoConstants.kDrivePower, 0, 0, -0.6),

            new ForkliftPickUpPosition(BotSubsystems.forklift)
        );
    }
}
