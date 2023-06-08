package frc.robot.commands.tank;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

public class WriteJ extends SequentialCommandGroup{
    public WriteJ(){
        addCommands(new TimeTank(BotSubsystems.tank, .5, .3, 2));
        addCommands(new TimeTank(BotSubsystems.tank, 0, .5, 1));
        addCommands(new TimeTank(BotSubsystems.tank, .5, 0.1, .7));
        addCommands(new TimeTank(BotSubsystems.tank, 0, .5, .5));
        addCommands(new TimeTank(BotSubsystems.tank, 0, -.5, 1));
    }
}