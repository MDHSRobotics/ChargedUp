package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

public class PlaceCube extends SequentialCommandGroup{

    public PlaceCube(){

        addCommands(
            //Lift the forklift before moving forward
            new LiftForklift(BotSubsystems.forklift, 100, 20),

            //Move forward to be over the pole
            new Move(1,1),

            //Open the clamp
            BotSubsystems.forklift.openClampCommand()
        );
    }
}
