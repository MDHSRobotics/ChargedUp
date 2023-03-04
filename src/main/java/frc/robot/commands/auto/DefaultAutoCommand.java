package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.commands.swervedrive.TimedSwerve;

public class DefaultAutoCommand extends SequentialCommandGroup {

    public DefaultAutoCommand() {
    
        addCommands(
            new TimedSwerve(BotSubsystems.swerveDriver, 1, 0, 0, 3)
        );

    }

}