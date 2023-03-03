package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/* Autonomous pathing 3

Goal: Spit the cube into the bottom row and drive outside the community
*/
public class AutoEjectCubeOuter extends SequentialCommandGroup {

    public AutoEjectCubeOuter() {
    
        addCommands(
            
            //Drive backward to go outside the community
            new Move(3,-1)
        );

    }

}