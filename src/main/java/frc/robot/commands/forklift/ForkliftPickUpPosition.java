
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.subsystems.Forklift;

public class ForkliftPickUpPosition extends SequentialCommandGroup {

    public ForkliftPickUpPosition(Forklift forklift) {

        addCommands(

            new MoveExtenderToPosition(BotSubsystems.forklift, 0),

            new MoveElevatorToPosition(BotSubsystems.forklift, 0)
        );
    }

}