package frc.robot.commands.tank;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

public class WriteA extends SequentialCommandGroup{
    public WriteA(){
        addCommands(new TimeTank(BotSubsystems.tank, 0, .5, 2),
        new TimeTank(BotSubsystems.tank, 0.5, 0.5, 1.0),
        new TimeTank(BotSubsystems.tank, 0, 0.5, 2),
        new TimeTank(BotSubsystems.tank, 0, -0.5, 1.75),
        new TimeTank(BotSubsystems.tank, -0.5, -0.5, 1.1),
        new TimeTank(BotSubsystems.tank, 0, -.5, 0.75));
    }
}