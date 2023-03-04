package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;

/* Autonomous pathing 1

Goal: Place the cube on the top row and drive up the charge station.
*/
public class AutoPlaceCubeInner extends SequentialCommandGroup {

    public AutoPlaceCubeInner() {
    
        addCommands(

            //Place the cube on the top row
            new PlaceCube(),

            //Balance on the charge station
            new BalanceChargeStation(BotSubsystems.swerveDriver, false)
        );

    }

}