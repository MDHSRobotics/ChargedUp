package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.BotCommands;

// Autonomous pathing 1
public class AutoCommand1 extends SequentialCommandGroup {

    public AutoCommand1() {
    
        addCommands(

            //Lift the forklift before moving forward
            new LiftForklift(),

            //Move forward to be over the pole
            new Move(1,1),

            //Open the claw

            //Balance on the charge station
            new BalanceChargeStation(BotSubsystems.swerveDriver)
            
        );

    }

}