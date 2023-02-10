package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class DefaultAutoCommand extends SequentialCommandGroup {

    public DefaultAutoCommand() {
    
        addCommands(
            new MoveForward(3)
        );

    }

}