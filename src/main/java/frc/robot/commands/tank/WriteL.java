package frc.robot.commands.tank;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

public class WriteL extends SequentialCommandGroup{
    public WriteL(){
        addCommands(new TimeTank(BotSubsystems.tank, 0, .5, 1));
        addCommands(new TimeTank(BotSubsystems.tank, .5, .1, .7));
        addCommands(new TimeTank(BotSubsystems.tank, 0, 0.5, 1.5));
    }
}