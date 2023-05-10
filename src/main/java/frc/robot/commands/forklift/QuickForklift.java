
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;

public class QuickForklift extends CommandBase {

    private Forklift m_forklift;
    private double m_targetTime = 2.0;
    private double m_dropClawTime = 2.0/3.0;
    private Timer m_timer;

    public QuickForklift(Forklift forklift) {
        Logger.setup("Constructing Command: QuickForklift...");

        // Add given subsystem requirements
        m_timer = new Timer();
        m_forklift = forklift;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: QuickForklift...");
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        Logger.info(m_timer.get());
        if(m_timer.get() < m_dropClawTime){
            m_forklift.move("ClawLift", -0.6);
        }else{
            m_forklift.move("ClawLift", -0.35);
            m_forklift.move("Extender", -0.965);
        }
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        double currentTime = m_timer.get();

        if (currentTime > m_targetTime) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: QuickForklift...");
        } else {
            Logger.ending("Ending Command: QuickForklift...");
        }
        m_forklift.stopAllMotors();
    }

}
