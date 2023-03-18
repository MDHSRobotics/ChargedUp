
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.commands.auto.AutoConstants;
import frc.robot.subsystems.Forklift;

public class ForkliftToPosition extends SequentialCommandGroup {

    

    public ForkliftToPosition(Forklift forklift, AutoConstants.Levels level) {
        if(level == AutoConstants.Levels.PICKUP){
            addCommands(

                new MoveExtenderToPosition(BotSubsystems.forklift, level),
                new MoveElevatorToPosition(BotSubsystems.forklift, level)
                
            );
        }else{
            addCommands(

                new MoveElevatorToPosition(BotSubsystems.forklift, level),
                new MoveExtenderToPosition(BotSubsystems.forklift, level)
                
            );
        }
    }

}