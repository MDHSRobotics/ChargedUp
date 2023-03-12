package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.brains.SwerveDriverBrain;
import frc.robot.consoles.Logger;

public class ChangeDriveSpeed extends CommandBase{

    private double m_speed;

    public ChangeDriveSpeed(double speed){
        Logger.setup("Constructing Command: ChangeDriveSpeed...");

        m_speed = speed;
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: ChangeDriveSpeed...");
        
    }

    @Override
    public void execute(){
        if(m_speed == 1){
            SwerveDriverBrain.setDriveSpeed(2.2, 1.2,2.0);
        }else{
            SwerveDriverBrain.setDriveSpeed(0.5, 0.4, 0.95);
        }
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: ChangeDriveSpeed...");
        } else {
            Logger.ending("Ending Command: ChangeDriveSpeed...");
        }
    }
}
