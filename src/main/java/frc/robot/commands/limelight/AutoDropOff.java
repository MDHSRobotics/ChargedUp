package frc.robot.commands.limelight;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.commands.forklift.ForkliftToPosition;
import frc.robot.commands.swervedrive.AlignGyro;
import frc.robot.commands.swervedrive.TimedSwerve;
import frc.robot.commands.auto.AutoConstants;

public class AutoDropOff extends SequentialCommandGroup{

    public AutoDropOff(int x) {

        addCommands(new AlignGyro(BotSubsystems.swerveDriver, 0),
        new EnterZone(BotSubsystems.swerveDriver),
        //new ForkliftToPosition(BotSubsystems.forklift, AutoConstants.Levels.HIGH),
        new AlignLimelight(BotSubsystems.swerveDriver, x));

        if(x % 3 == 0){
            addCommands(new TimedSwerve(BotSubsystems.swerveDriver, 0, 0.3, 0, 0.5));
        }else if(x % 3 == 2){
            addCommands(new TimedSwerve(BotSubsystems.swerveDriver, 0, -0.3, 0, 0.5));
        }

        addCommands(BotSubsystems.forklift.openClampCommand());

        

    }
}
