package frc.robot.commands.tank;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

public class WriteN extends SequentialCommandGroup{
    public WriteN(){
        addCommands(new TimeTank(BotSubsystems.tank, 0, .5, 2));
        addCommands(new TimeTank(BotSubsystems.tank, .5, 0, 1));
        addCommands(new TimeTank(BotSubsystems.tank, 0, .5, 3));
        addCommands(new TimeTank(BotSubsystems.tank, -.5, 0, 1));
        addCommands(new TimeTank(BotSubsystems.tank, 0, .5, 2));
    }
}