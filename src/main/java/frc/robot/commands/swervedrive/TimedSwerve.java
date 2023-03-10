package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.SwerveDriver;

import static frc.robot.BotSensors.gyro;

public class TimedSwerve extends CommandBase {

    private final double CORRECTION_TOLERANCE = 1;
    private final double CORRECTION_SPEED = 0.1;

    private SwerveDriver m_swerveDriver;
    private double m_xSpeed;
    private double m_ySpeed;
    private double m_turningSpeed;
    private double m_targetTime;
    private Timer m_timer;
    private double m_startingHeading;


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
        Logger.action(String.format("Initializing Command: TimedSwerve (xSpeed=%.2f, ySpeed=%.2f, turningSpeed=%.2f, time=%.1f) ...", m_xSpeed, m_ySpeed, m_turningSpeed, m_targetTime));
        m_timer.reset();
        m_timer.start();

        m_startingHeading = gyro.getYaw();

    }

    @Override
    public void execute() {
        double yawDifference = m_startingHeading - gyro.getYaw();
        double newTurningSpeed = m_turningSpeed;
        if(yawDifference < -CORRECTION_TOLERANCE){
            newTurningSpeed -= CORRECTION_SPEED;
        }else if(yawDifference > CORRECTION_TOLERANCE){
            newTurningSpeed += CORRECTION_SPEED;
        }

        Logger.info("Current Yaw: " + gyro.getYaw() + " Yaw Difference: " + yawDifference + " Initial Yaw: " + m_startingHeading + " Turning Speed: " + newTurningSpeed);

        m_swerveDriver.setChassisSpeed(m_xSpeed, m_ySpeed, newTurningSpeed);

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
