package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

/* Autonomous pathing 3

Goal: Spit the cube into the bottom row and drive outside the community
*/
public class AutoEjectCubeInner extends SequentialCommandGroup {

    public AutoEjectCubeInner() {
    
        addCommands(

            //Balance on the charge station
            new BalanceChargeStation(BotSubsystems.swerveDriver)
            
        );

    }

}