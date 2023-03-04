package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.commands.swervedrive.TimedSwerve;

/* Autonomous pathing 3

Goal: Spit the cube into the bottom row and drive outside the community
*/
public class AutoEjectCubeOuter extends SequentialCommandGroup {

    public AutoEjectCubeOuter(double sidetoSideSpeed) {
    
        addCommands(

            //Eject cube in the outer grids
            new EjectCube(BotSubsystems.intake, 1),

            //Move to the side of the field to clear the charge station
            new TimedSwerve(BotSubsystems.swerveDriver, sidetoSideSpeed, 0, 0, 0.5),
            
            //Drive outside the community
            new TimedSwerve(BotSubsystems.swerveDriver, -1, 0, 0, 3)
        );

    }

}