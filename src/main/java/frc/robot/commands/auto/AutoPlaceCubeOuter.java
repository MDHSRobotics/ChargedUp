package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/* Autonomous pathing 2

Goal: Place the cube on the top row and drive outside the community.
*/
public class AutoPlaceCubeOuter extends SequentialCommandGroup {

    public AutoPlaceCubeOuter() {
    
        addCommands(

            //Lift the forklift before moving forward
            new PlaceCube(),

            //Drive backward to go outside the community
            new Move(3,-1)
        );

    }

}