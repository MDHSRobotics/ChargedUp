package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.SwerveDriver;

public class TimedSwerve extends CommandBase {

    private SwerveDriver m_swerveDriver;
    private double m_xSpeed;
    private double m_ySpeed;
    private double m_turningSpeed;
    private double m_targetTime;
    private Timer m_timer;


    public TimedSwerve(SwerveDriver swerveDriver, double xSpeed, double ySpeed, double turningSpeed,  double timeInSeconds) {
        Logger.setup("Constructing Command: TimedSwerve...");

        // Add given subsystem requirements
        m_swerveDriver = swerveDriver;
        m_targetTime = timeInSeconds;
        m_xSpeed = xSpeed;
        m_ySpeed = ySpeed;
        m_turningSpeed = turningSpeed;
        m_timer = new Timer();
        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: TimedSwerve...");
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        m_swerveDriver.setChassisSpeed(m_xSpeed, m_ySpeed, m_turningSpeed);

    }

    @Override
    public boolean isFinished() {
        double currentTime = m_timer.get();

        if (currentTime > m_targetTime) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        double currentTime = m_timer.get();
        if (interrupted) {
            Logger.ending(String.format("Interrupting Command: TimedSwerve... Current Time: %.2f", currentTime));
        } else {
            Logger.ending(String.format("Ending Command: TimedSwerve... Current Time: %.2f", currentTime));
        }
        m_swerveDriver.stopModules();
    }

}
