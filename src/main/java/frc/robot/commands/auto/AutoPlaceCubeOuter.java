package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.commands.swervedrive.TimedSwerve;
import frc.robot.subsystems.constants.AutoConstants;

/* Autonomous pathing 2

Goal: Place the cube on the top row and drive outside the community.
*/
public class AutoPlaceCubeOuter extends SequentialCommandGroup {

    public AutoPlaceCubeOuter() {
    
        addCommands(

            //Lift the forklift before moving forward
            new PlaceCube(),

            //Drive backward to go outside the community
            new TimedSwerve(BotSubsystems.swerveDriver, -AutoConstants.kDrivePower, 0, 0, 4.0)
        );

    }

}