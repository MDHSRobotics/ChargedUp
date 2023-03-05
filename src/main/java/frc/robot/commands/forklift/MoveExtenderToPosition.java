
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;

public class MoveExtenderToPosition extends CommandBase {

    private Forklift m_forklift;
    private double m_targetPosition;

    public MoveExtenderToPosition(Forklift forklift, double targetPosition) {
        Logger.setup("Constructing Command: MoveExtenderToPosition...");

        // Add given subsystem requirements
        m_forklift = forklift;
        m_targetPosition = targetPosition;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: MoveExtenderToPosition...");
        // m_forklift.moveElevatorToPosition(0);
        m_forklift.moveExtenderToPosition(m_targetPosition);

    }

    @Override
    public void execute() {
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return m_forklift.isExtenderAtPosition(m_targetPosition);
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: MoveExtenderToPosition...");
        } else {
            Logger.ending("Ending Command: MoveExtenderToPosition...");
        }
        m_forklift.stopMotors();
    }

}