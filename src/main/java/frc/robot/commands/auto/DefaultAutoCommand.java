package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

public class DefaultAutoCommand extends SequentialCommandGroup {

    public DefaultAutoCommand() {
    
        addCommands(
            new EjectCube(BotSubsystems.intake, 1)
        );

    }

}