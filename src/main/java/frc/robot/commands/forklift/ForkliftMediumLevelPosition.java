
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.subsystems.Forklift;

public class ForkliftMediumLevelPosition extends SequentialCommandGroup {

    public ForkliftMediumLevelPosition(Forklift forklift) {

        addCommands(

            new MoveElevatorToPosition(BotSubsystems.forklift, -68),

            new MoveExtenderToPosition(BotSubsystems.forklift, -90)
        );
    }

}