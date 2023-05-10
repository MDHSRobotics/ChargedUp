
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;
import frc.robot.commands.auto.AutoConstants;

public class MoveExtenderToPosition extends CommandBase {

    private final double MOTOR_POSITION_TOLERANCE = 3.0;

    private Forklift m_forklift;
    private AutoConstants.Levels m_level;

    private double targetPosition;

    public MoveExtenderToPosition(Forklift forklift, AutoConstants.Levels level) {
        Logger.setup("Constructing Command: MoveExtenderToPosition...");

        // Add given subsystem requirements
        m_forklift = forklift;
        m_level = level;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: MoveExtenderToPosition...");
        switch(m_level){
            case PICKUP:
                targetPosition = -0;
                break;
            case MEDIUM:
                targetPosition = -60;
                break;
            case HIGH:
                targetPosition = -100;
                break;
            default:
                throw new java.lang.Error("Unkown Extender Position; should be {PICKUP, MEDIUM, HIGH}");
        }
        m_forklift.moveSparkMaxPosition("Extender", targetPosition);

    }

    @Override
    public void execute() {
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return m_forklift.isSparkMaxAtPosition("Extender", -targetPosition, MOTOR_POSITION_TOLERANCE);
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: MoveExtenderToPosition...");
        } else {
            Logger.ending("Ending Command: MoveExtenderToPosition...");
        }
        m_forklift.stopAllMotors();
    }

}