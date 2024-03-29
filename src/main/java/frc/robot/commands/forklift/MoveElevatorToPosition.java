
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;
import frc.robot.brains.ForkliftBrain;
import frc.robot.commands.auto.AutoConstants;

public class MoveElevatorToPosition extends CommandBase {

    private Forklift m_forklift;
    private AutoConstants.Levels m_level;

    private double targetPosition;

    public MoveElevatorToPosition(Forklift forklift, AutoConstants.Levels level) {
        Logger.setup("Constructing Command: MoveElevatorToPosition...");

        // Add given subsystem requirements
        m_forklift = forklift;
        m_level = level;
        addRequirements(m_forklift);

    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: MoveElevatorToPosition...");
        switch(m_level){
            case PICKUP:
                targetPosition = -ForkliftBrain.getElevatorPickupPosition();
                break;
            case MEDIUM:
                targetPosition = -ForkliftBrain.getElevatorMediumPosition();
                break;
            case HIGH:
                targetPosition = -ForkliftBrain.getElevatorHighPosition();
                break;
            default:
                throw new java.lang.Error("Unkown Elevator Position; should be {PICKUP, MEDIUM, HIGH}");
        }
        m_forklift.moveElevatorToPosition(targetPosition);

    }

    @Override
    public void execute() {
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return m_forklift.isElevatorAtPosition(-targetPosition);
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: MoveElevatorToPosition...");
        } else {
            Logger.ending("Ending Command: MoveElevatorToPosition...");
        }
        m_forklift.stopMotors();
    }

}