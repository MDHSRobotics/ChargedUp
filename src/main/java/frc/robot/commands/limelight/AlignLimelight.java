package frc.robot.commands.limelight;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotCommands;
import frc.robot.BotSubsystems;
import frc.robot.commands.forklift.ForkliftToPosition;
import frc.robot.commands.swervedrive.AlignGyro;
import frc.robot.commands.auto.AutoConstants;

public class AlignLimelight extends SequentialCommandGroup{

    public AlignLimelight() {

        addCommands(new AlignGyro(BotSubsystems.swerveDriver, 0),
        new PositionLimelight(BotSubsystems.swerveDriver),
        new ForkliftToPosition(BotSubsystems.forklift, AutoConstants.Levels.HIGH));

    }
}
