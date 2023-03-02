package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// Autonomous pathing 2
public class AutoCommand2 extends SequentialCommandGroup {

    public AutoCommand2() {
    
        addCommands(

            //Lift the forklift before moving forward
            new LiftForklift(),

            //Move forward to be over the pole
            new Move(1,1),

            //Open the claw

            //Drive backward to go outside the community
            new Move(3,-1)
        );

    }

}