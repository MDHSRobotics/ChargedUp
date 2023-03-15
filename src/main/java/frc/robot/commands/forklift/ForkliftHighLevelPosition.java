
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.subsystems.Forklift;

public class ForkliftHighLevelPosition extends SequentialCommandGroup {

    public ForkliftHighLevelPosition(Forklift forklift) {

        addCommands(

            new MoveElevatorToPosition(BotSubsystems.forklift, -68),

            new MoveExtenderToPosition(BotSubsystems.forklift, -90)

        );

    }

}