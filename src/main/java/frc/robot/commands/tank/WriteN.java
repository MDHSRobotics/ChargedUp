package frc.robot.commands.tank;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

public class WriteN extends SequentialCommandGroup{
    public WriteN(){
        addCommands(new TimeTank(BotSubsystems.tank, 0, .5, 1),
        new TimeTank(BotSubsystems.tank, 0.5, 0.5, 1.25),
        new TimeTank(BotSubsystems.tank, 0, 0.5, 1),
        new TimeTank(BotSubsystems.tank, -0.5, 0.5, 0.75),
        new TimeTank(BotSubsystems.tank, 0, .5, 1));
    }
}