package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.ExampleSubsystem;

public class ExampleCommand extends CommandBase {

    private final ExampleSubsystem m_subsystem;

    private final String m_commandName;
    private int m_tries;
    private final int m_maxTries;

    public ExampleCommand(ExampleSubsystem subsystem, String commandName, int maxTries) {
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
        m_subsystem.doSomething();
        m_tries += 1;
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
