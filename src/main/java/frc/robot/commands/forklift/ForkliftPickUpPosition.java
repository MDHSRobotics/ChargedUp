
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;

public class ForkliftPickUpPosition extends CommandBase {

    private Forklift m_forklift; 

    public ForkliftPickUpPosition(Forklift forklift) {
        Logger.setup("Constructing Command: ForkliftPickUpPosition...");

        // Add given subsystem requirements
        m_forklift = forklift;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: ForkliftPickUpPosition...");
        //m_forklift.moveElevatorToPosition(0);
        m_forklift.moveExtenderToPosition(0);
        
    }

    @Override
    public void execute() {
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return m_forklift.areMotorsAtPosition(0, 0);
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