package frc.robot.commands.limelight;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.brains.SwerveDriverBrain;
import frc.robot.consoles.Logger;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.SwerveDriver;


public class PositionLimelight extends CommandBase {

    private SwerveDriver m_swerveDriver;

    private PIDController m_xOffsetPidController;
    private PIDController m_distancePidController;

    public PositionLimelight(SwerveDriver swerveDriver) {

        Logger.setup("Constructing Command: PositionLimelight...");

        // Add given subsystem requirements
        m_swerveDriver = swerveDriver;
        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: PositionLimelight ...");

        double kPxOffset = SwerveDriverBrain.getPositionLimelightkPxOffset();
        double kIxOffset = SwerveDriverBrain.getPositionLimelightkIxOffset();
        double kDxOffset = SwerveDriverBrain.getPositionLimelightkDxOffset();

        double kPDistance = SwerveDriverBrain.getPositionLimelightkPDistance();
        double kIDistance = SwerveDriverBrain.getPositionLimelightkIDistance();
        double kDDistance = SwerveDriverBrain.getPositionLimelightkDDistance();

        double xOffsetSetPoint = 0.; 
        double xOffsetTolerance = 0.05;

        double distanceSetPoint = 0.5;
        double distanceTolerance = 0.05;  

        m_xOffsetPidController = new PIDController(kPxOffset, kIxOffset, kDxOffset);
        m_distancePidController = new PIDController(kPDistance, kIDistance, kDDistance); 

        m_xOffsetPidController.setSetpoint(xOffsetSetPoint); // Target x Offset
        m_xOffsetPidController.setTolerance(xOffsetTolerance); // x Offset tolerance

        m_distancePidController.setSetpoint(distanceSetPoint); // Target 0.5 ft away from limelight target
        m_distancePidController.setTolerance(distanceTolerance); // Distance tolerance

    }

    @Override
    public void execute() {
        
        double distance = Limelight.calculateDistanceToTarget();
        double xOffset = Limelight.getXOffset();

        double strafeSpeed = m_xOffsetPidController.calculate(xOffset);
        double forwardSpeed = m_distancePidController.calculate(distance);

        Logger.info("SwerveDriver -> Limelight -> Distance: " + distance + "; xOffset: " + xOffset);
        m_swerveDriver.setChassisSpeed(strafeSpeed, forwardSpeed, 0);
    }

    @Override
    public boolean isFinished() {
        boolean atTarget;

        if (m_xOffsetPidController.atSetpoint() && m_distancePidController.atSetpoint()){
            m_swerveDriver.stopModules();
            atTarget = true;
        } else {
            atTarget = false;
        }
        return atTarget;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: PositionLimelight ...");
        } else {
            Logger.ending("Ending Command: PositionLimelight ...");
        }
    }

}
