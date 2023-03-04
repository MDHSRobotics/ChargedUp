
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;

public class MoveElevatorToPosition extends CommandBase {

    private Forklift m_forklift; 
    private double m_targetPosition;

    public MoveElevatorToPosition(Forklift forklift, double targetPosition) {
        Logger.setup("Constructing Command: MoveElevatorToPosition...");

        // Add given subsystem requirements
        m_forklift = forklift;
        m_targetPosition = targetPosition;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: MoveElevatorToPosition...");
        //m_forklift.moveElevatorToPosition(0);
        m_forklift.moveExtenderToPosition(m_targetPosition);
        
    }

    @Override
    public void execute() {
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return m_forklift.isElevatorAtPosition(m_targetPosition);
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: ForkliftPickUpPosition...");
        } else {
            Logger.ending("Ending Command: ForkliftPickUpPosition...");
        }
        m_forklift.stopMotors();
    }

}