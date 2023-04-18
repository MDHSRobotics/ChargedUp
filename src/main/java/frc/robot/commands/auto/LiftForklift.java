package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;

public class LiftForklift extends CommandBase {

    private Forklift m_forklift;

    private double m_elevatorEncoderPosition = 0;
    private double m_extenderEncoderPosition = 0;

    private double m_targetElevatorPosition;
    private double m_targetExtenderPosition;

    private boolean m_isElevatorAtTarget = false;
    private boolean m_isExtenderAtTarget = false;

    public LiftForklift(Forklift forklift, double targetExtenderPosition, double targetElevatorPosition) {
        Logger.setup("Constructing Command: Lift Forklift...");

        // Add given subsystem requirements
        m_forklift = forklift;
        m_targetElevatorPosition = targetElevatorPosition;
        m_targetExtenderPosition = targetExtenderPosition;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: Lift Forklift...");
    }

    @Override
    public void execute() {
        //Get encoder positions in the command
        m_extenderEncoderPosition = m_forklift.getEncoderPosition("sparkMaxExtender");
        m_elevatorEncoderPosition = m_forklift.getEncoderPosition("sparkMaxElevator");
        //move the elevator up first to the target position
        if(!m_isElevatorAtTarget){
            if(m_elevatorEncoderPosition >= m_targetElevatorPosition){
                m_isElevatorAtTarget = true;
                m_forklift.stopAllMotors();
            }else{
                m_forklift.move("sparkMaxElevator", 0.5);
            }
        }else{
            //move the extender up second to the target position
            if(m_extenderEncoderPosition >= m_targetExtenderPosition){
                m_isExtenderAtTarget = true;
                m_forklift.stopAllMotors();
            }else{
                m_forklift.move("sparkMaxExtender", 0.5);
            }
        }

    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return (m_isExtenderAtTarget && m_isElevatorAtTarget);
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: Lift Forklift... ");
        } else {
            Logger.ending("Ending Command: Lift Forklift... ");
        }
        m_forklift.stopAllMotors();
    }

}
