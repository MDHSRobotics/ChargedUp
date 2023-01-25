package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.subsystems.constants.AutoConstants;

// Autonomous pathing 1
public class AutoCommand1 extends SequentialCommandGroup {

    public AutoCommand1() {
    
        addCommands(
            new MoveForward(3)
        );

    }

}