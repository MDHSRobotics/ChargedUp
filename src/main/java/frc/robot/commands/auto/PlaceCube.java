package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.commands.swervedrive.TimedSwerve;

public class PlaceCube extends SequentialCommandGroup{

    public PlaceCube(){

        addCommands(
            //Lift the forklift before moving forward
            new LiftForklift(BotSubsystems.forklift, 100, 20),

            //Move forward to be over the pole
            new TimedSwerve(BotSubsystems.swerveDriver, 1, 0, 0, 1),

            //Open the clamp
            BotSubsystems.forklift.openClampCommand()
        );
    }
}
