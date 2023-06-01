package frc.robot.commands.limelight;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotCommands;

public class AlignLimelight extends SequentialCommandGroup{
    public AlignLimelight() {
        addCommands(BotCommands.alignGyro,
        BotCommands.positionLimelight);
    }
}
