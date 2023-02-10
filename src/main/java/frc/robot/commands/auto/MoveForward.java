package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

import frc.robot.BotSubsystems;
import frc.robot.commands.swervedrive.TimedSwerve;
import frc.robot.subsystems.constants.AutoConstants; 

public class MoveForward extends SequentialCommandGroup {

    public MoveForward(double seconds) {

        addCommands(
                new TimedSwerve(BotSubsystems.swerveDriver, AutoConstants.kDrivePower, 0.0, 0.0, seconds)
        );
        
    }

}