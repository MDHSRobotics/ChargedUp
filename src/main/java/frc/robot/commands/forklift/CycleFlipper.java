
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.GenericSubsystem;

public class CycleFlipper extends CommandBase {

    private GenericSubsystem m_flipper;
    private double m_targetTime = 1.2;
    private Timer m_timer;

    public CycleFlipper(GenericSubsystem flipper) {
        Logger.setup("Constructing Command: CycleFlipper...");

        // Add given subsystem requirements
        m_flipper = flipper;
        m_timer = new Timer();
        addRequirements(m_flipper);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: CycleFlipper...");
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        Logger.info(m_timer.get());
        if(m_timer.get() < m_targetTime / 2){
            m_flipper.move("Flipper", -1.);
        }else{
            m_flipper.move("Flipper", 1.);
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
            Logger.ending("Interrupting Command: CycleFlipper...");
        } else {
            Logger.ending("Ending Command: CycleFlipper...");
        }
        m_flipper.stop("Flipper");
    }

}
