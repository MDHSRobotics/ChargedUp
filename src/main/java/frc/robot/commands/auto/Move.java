package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.BotSubsystems;
import frc.robot.commands.swervedrive.TimedSwerve;
import frc.robot.subsystems.constants.AutoConstants; 

public class Move extends SequentialCommandGroup {

    public Move(double seconds, double xSpeed) {

        addCommands(
                new TimedSwerve(BotSubsystems.swerveDriver, xSpeed * AutoConstants.kDrivePower, 0.0, 0.0, seconds)
        );
        
    }

}