
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;
import frc.robot.brains.*;

public class CloseClampForklift extends CommandBase {

    private Forklift m_forklift;

    public CloseClampForklift(Forklift forklift) {
        Logger.setup("Constructing Command: CloseClamp...");

        // Add given subsystem requirements
        m_forklift = forklift;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: CloseClamp...");
    }

    @Override
    public void execute() {
        m_forklift.moveClamp(-ForkliftBrain.getClawPower());
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: CloseClamp...");
        } else {
            Logger.ending("Ending Command: CloseClamp...");
        }

    }

}
