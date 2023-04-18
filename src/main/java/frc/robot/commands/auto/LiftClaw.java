package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.consoles.Logger;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.subsystems.Forklift;

public class LiftClaw extends CommandBase{

    private Forklift m_forklift;
    private double m_targetTime;
    private Timer m_timer;

    public LiftClaw(Forklift forklift,  double timeInSeconds) {
        Logger.setup("Constructing Command: LiftClaw...");

        // Add given subsystem requirements
        m_forklift = forklift;
        m_targetTime = timeInSeconds;
        m_timer = new Timer();
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action(String.format("Initializing Command: LiftClaw"));
        m_timer.reset();
        m_timer.start();

    }

    @Override
    public void execute() {
        m_forklift.move("sparkMaxClaw", 0.5);
    }

    @Override
    public boolean isFinished() {
        double currentTime = m_timer.get();

        return (currentTime > m_targetTime);
    }

    @Override
    public void end(boolean interrupted) {
        double currentTime = m_timer.get();
        if (interrupted) {
            Logger.ending(String.format("Interrupting Command: LiftClaw... Current Time: %.2f", currentTime));
        } else {
            Logger.ending(String.format("Ending Command: LiftClaw... Current Time: %.2f", currentTime));
        }
        m_forklift.stopAllMotors();
    }
}
