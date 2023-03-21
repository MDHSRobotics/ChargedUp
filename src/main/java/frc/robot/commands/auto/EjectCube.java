package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.BotSensors;
import frc.robot.consoles.Logger;
import frc.robot.subsystems.Intake;

public class EjectCube extends CommandBase{

    private Intake m_intake;
    private Timer m_timer;
    private double m_targetTime;

    public EjectCube(Intake intake, double timeInSeconds){
        Logger.setup("Constructing Command: Eject Cube...");

        m_intake = intake;
        m_targetTime = timeInSeconds;
        m_timer = new Timer();
        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: Eject Cube...");
        m_timer.reset();
        m_timer.start();
        BotSensors.gyro.setSideways();
    }

    @Override
    public void execute(){
        m_intake.move("sparkMaxIntake", -1);;
    }

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
        double currentTime = m_timer.get();
        if (interrupted) {
            Logger.ending(String.format("Interrupting Command: Eject Cube... Current Time: %.2f", currentTime));
        } else {
            Logger.ending(String.format("Ending Command: Eject Cube... Current Time: %.2f", currentTime));
        }
        m_intake.stopAllMotors();
    }
}
