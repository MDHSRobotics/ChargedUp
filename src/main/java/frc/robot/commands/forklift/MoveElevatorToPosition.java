
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;
import frc.robot.commands.auto.AutoConstants;

public class MoveElevatorToPosition extends CommandBase {

    private final double MOTOR_POSITION_TOLERANCE = 3.0;

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
                targetPosition = -0;
                break;
            case MEDIUM:
                targetPosition = -51;
                break;
            case HIGH:
                targetPosition = -75;
                break;
            default:
                throw new java.lang.Error("Unkown Elevator Position; should be {PICKUP, MEDIUM, HIGH}");
        }
        m_forklift.moveSparkMaxPosition("Elevator", targetPosition);

    }

    @Override
    public void execute() {
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished(){
        return m_forklift.isSparkMaxAtPosition("Elevator", -targetPosition, MOTOR_POSITION_TOLERANCE);
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: MoveElevatorToPosition...");
        } else {
            Logger.ending("Ending Command: MoveElevatorToPosition...");
        }
        m_forklift.stopAllMotors();
    }

}