package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.SwerveDriver;

public class StopSwerveDrive extends CommandBase {

    private SwerveDriver m_swerveDriver;

    public StopSwerveDrive(SwerveDriver swerveDriver) {
        Logger.setup("Constructing Command: StopSwerveDrive...");

        // Add given subsystem requirements
        m_swerveDriver = swerveDriver;
        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: StopSwerveDrive...");
    }

    @Override
    public void execute() {
        m_swerveDriver.stopModules();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: StopSwerveDrive...");
        } else {
            Logger.ending("Ending Command: StopSwerveDrive...");
        }
        m_swerveDriver.stopModules();
    }

}
