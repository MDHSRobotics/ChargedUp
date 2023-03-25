package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.BotSubsystems;
import frc.robot.commands.swervedrive.TimedSwerve;

/* This class factory will create a command group based on the starting location
    and target placement of the cube (eject on bottom or place on top shelf)
*/
public class AutoFactory extends SequentialCommandGroup {

    private final double SIDEWAYS_TIME = 0.5;
    private final double DRIVE_OUT_TIME = 4.0;

    public AutoFactory(String location, String cubePlacement) {

        Boolean isSideways;

        // STEP 1: Place the cube

        switch (cubePlacement) {
            case "Bottom":

                //Eject Cube in the center
                addCommands(new EjectCube(BotSubsystems.intake, AutoConstants.DEFAULT_EJECT_CUBE_TIME));
                isSideways = true;       
                break;

            case "Top":
                //Place the cube on the top row
                addCommands(new PlaceCube());
                isSideways = false;
                break;

            default:
                throw new java.lang.Error("Unkown Cube placement; should be {Bottom, Top}");
        }

        // STEP 2: Balance or move out of community
        
        switch (location){
            case "Center":
                // We're located in the center so balance on the charge station
                addCommands(new BalanceChargeStation(BotSubsystems.swerveDriver, isSideways));
                break;
        
            //This is relative to the driver facing the field
            case "Left":
            case "Right":
                // We're located on the right or left so back out of community area

                double backingSpeed = AutoConstants.DEFAULT_DRIVE_SPEED;
                // The side to side direction is opposite for Left and Right placements
                double sideToSideSpeed = (location == "Left") ? -AutoConstants.DEFAULT_DRIVE_SPEED : AutoConstants.DEFAULT_DRIVE_SPEED;

                double x1Speed, y1Speed, time1;
                double x2Speed, y2Speed, time2;
                if (isSideways) {
                    // The robot is initially positioned sideways, pointing toward the right
                    x1Speed = sideToSideSpeed;
                    y1Speed = 0.;
                    time1 = SIDEWAYS_TIME;
                    x2Speed = 0.;
                    y2Speed = backingSpeed;
                    time2 = DRIVE_OUT_TIME;
                
                }   else {
                    // The robot is initially positioned toward the wall
                    x1Speed = 0.;
                    y1Speed = sideToSideSpeed;
                    time1 = SIDEWAYS_TIME;
                    x2Speed = -backingSpeed;
                    y2Speed = 0.;
                    time2 = DRIVE_OUT_TIME;
                }

                //Move to the side of the field to clear the charge station
                addCommands(new TimedSwerve(BotSubsystems.swerveDriver, x1Speed, y1Speed, 0., time1));
                
                //Lift the claw
                addCommands(new LiftClaw(BotSubsystems.forklift, 0.2));

                //Drive outside the community
                addCommands(new TimedSwerve(BotSubsystems.swerveDriver, x2Speed, y2Speed, 0., time2));

                break;
            
            default:
                throw new java.lang.Error("Unkown Location; should be {Left, Center, Right}");           
        }

    }

}