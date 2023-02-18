package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.BotSensors;
import frc.robot.consoles.Logger;
import frc.robot.subsystems.SwerveDriver;

public class AlignGyro extends CommandBase {

    private SwerveDriver m_swerveDriver;

    private double m_targetYaw;
    private boolean m_isAligned;

    public AlignGyro(SwerveDriver swerveDriver, double targetYaw) {

        Logger.setup("Constructing Command: AlignGyro...");

        // Add given subsystem requirements
        m_swerveDriver = swerveDriver;
        m_targetYaw = targetYaw;
        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: AlignGyro ...");
    }

    @Override
    public void execute() {

        // Get the correction yaw needed to align the Robot with the target yaw
        double yaw = BotSensors.gyro.getYaw();
        double correction = m_targetYaw - yaw;
        double angleTolerance = 10;

        if (correction > 180) correction = correction - 360;
        if (correction < -180) correction = correction + 360;
        Logger.info("SwerveDriver -> Gyro -> Target Yaw: " + m_targetYaw + "; Current Yaw: " + yaw + "; Correction: " + correction);

        // Get the rotation speed to align the Robot with the target gyro yaw
        double zRotation = (correction / 180) * Math.PI;
        boolean isCloseEnough = Math.abs(correction) < angleTolerance;
        
        if (!isCloseEnough) {
            if (0 < zRotation && zRotation < 0.25) zRotation = 0.25;
            if (0 > zRotation && zRotation > -0.25) zRotation = -0.25;
            m_isAligned = false;
        }else{
            m_isAligned = true;
        }

        m_swerveDriver.setChassisSpeed(0, 0, zRotation);
    }

    @Override
    public boolean isFinished() {
        return m_isAligned;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: AlignGyro ...");
        } else {
            Logger.ending("Ending Command: AlignGyro ...");
        }
    }
    
}
