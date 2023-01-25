
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;

public class ToggleClamp extends CommandBase {

    private Forklift m_forklift;

    public ToggleClamp(Forklift forklift) {
        Logger.setup("Constructing Command: ToggleClamp...");

        // Add given subsystem requirements
        m_forklift = forklift;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: ToggleClamp...");
        m_forklift.moveClampPneumatic();
    }

    @Override
    public void execute() {
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: ToggleClamp...");
        } else {
            Logger.ending("Ending Command: ToggleClamp...");
        }
        m_forklift.stopForklift();
    }

}
