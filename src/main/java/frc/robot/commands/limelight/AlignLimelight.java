package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.SwerveDriver;


public class AlignLimelight extends CommandBase {

    private SwerveDriver m_SwerveDriver;

    public AlignLimelight(SwerveDriver SwerveDriver) {

        Logger.setup("Constructing Command: AlignLimelight...");

        // Add given subsystem requirements
        m_SwerveDriver = SwerveDriver;
        addRequirements(m_SwerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: AlignLimelight ...");
    }

    @Override
    public void execute() {
        if (!m_SwerveDriver.getAlignment()){
          m_SwerveDriver.driveAlign(0);  
        } else {
          m_SwerveDriver.driveLimelight();  
        } 
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: AlignLimelight ...");
        } else {
            Logger.ending("Ending Command: AlignLimelight ...");
        }
    }

}
