package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

public class DefaultAutoCommand extends SequentialCommandGroup {

    private final double EJECT_TIME = 1.0;

    public DefaultAutoCommand() {
    
        addCommands(
            new EjectCube(BotSubsystems.intake, EJECT_TIME)
        );

    }

}