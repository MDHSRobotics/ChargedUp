package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.BotSensors;

public class ExampleCommand extends CommandBase {

    private final String m_commandName;
    private int m_tries;
    private final int m_maxTries;

    public ExampleCommand(String commandName, int maxTries) {
        m_commandName = commandName;
        m_maxTries = maxTries;
        m_tries = 0;

        Logger.setup("Constructing Command: Example (" + m_commandName + ") ...");

    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: Example (" + m_commandName + ") ...");
        m_tries = 0;
    }

    @Override
    public void execute() {
        Logger.info("Gyro Yaw: " + BotSensors.gyro.getYaw());
        Logger.info("Gyro Roll: " + BotSensors.gyro.getRoll());
        Logger.info("Gyro Pitch: " + BotSensors.gyro.getPitch());
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
