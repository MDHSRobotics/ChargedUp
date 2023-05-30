package frc.robot.commands.limelight;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.consoles.Logger;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.SwerveDriver;


public class PositionLimelight extends CommandBase {

    private SwerveDriver m_swerveDriver;

    private PIDController m_xOffsetPidController;
    private PIDController m_distancePidController;

    private boolean yCorrect = false;
    private boolean xCorrect = false;

    public PositionLimelight(SwerveDriver swerveDriver) {

        Logger.setup("Constructing Command: PositionLimelight...");

        // Add given subsystem requirements
        m_swerveDriver = swerveDriver;
        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: PositionLimelight ...");

        Limelight.setPipeline(0);

        double kPxOffset = 0.01;
        double kIxOffset = 0;
        double kDxOffset = 0;

        double kPDistance = 0.01;
        double kIDistance = 0;
        double kDDistance = 0;

        double xOffsetSetPoint = -5.0; 
        double xOffsetTolerance = 0.1;

        double distanceSetPoint = 5;
        double distanceTolerance = 0.1;  

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

        /*double strafeSpeed = m_xOffsetPidController.calculate(xOffset);
        double forwardSpeed = m_distancePidController.calculate(distance);

        Logger.info("Distance: " + distance + "; xOffset: " + xOffset + "; strafeSpeed: " + strafeSpeed);
        m_swerveDriver.setChassisSpeed(strafeSpeed, -forwardSpeed, 0);*/

        if(xOffset <= -9){
            Logger.info("moving left");
            m_swerveDriver.setChassisSpeed(0, 0.2, 0);
        }else if (xOffset >= -6){
            Logger.info("moving right");
            m_swerveDriver.setChassisSpeed(0, -0.2, 0);
        }else{
            yCorrect = true;
        }
        if(yCorrect){
            if(distance >= 5){
                Logger.info("moving forward");
                m_swerveDriver.setChassisSpeed(0.2, 0, 0);
            }else if (distance <= 3){
                Logger.info("moving backward");
                m_swerveDriver.setChassisSpeed(-0.2, 0, 0);
            }else{
                xCorrect = true;
            }
        }
    }

    @Override
    public boolean isFinished() {
        boolean atTarget;

        if (xCorrect && yCorrect){
            m_swerveDriver.stopModules();
            atTarget = true;
            Limelight.setPipeline(1);
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
