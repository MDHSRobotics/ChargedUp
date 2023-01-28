package frc.robot.commands.limelight;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.sensors.Limelight;
import frc.robot.subsystems.ExampleSubsystem;


public class ChangePipeline extends CommandBase {

    private final ExampleSubsystem m_subsystem;

    private final String m_commandName;
    private int m_tries;
    private final int m_maxTries;

    public ChangePipeline(ExampleSubsystem subsystem, String commandName, int maxTries, Limelight limelight) {
        m_commandName = commandName;
        m_maxTries = maxTries;
        m_tries = 0;

        Logger.setup("Constructing Command: Example (" + m_commandName + ") ...");

        // Add given subsystem requirements
        m_subsystem = subsystem;
        addRequirements(m_subsystem);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: Example (" + m_commandName + ") ...");
        m_tries = 0;
    }

    @Override
    public void execute() {
        //m_limelight.setPipeline(0);
    }

    @Override
    public boolean isFinished() {
        boolean allDone = m_tries >= m_maxTries;
        return allDone;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: Example (" + m_commandName + ") ...");
        } else {
            Logger.ending("Ending Command: Example (" + m_commandName + ") ...");
        }
    }

}
