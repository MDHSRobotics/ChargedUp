
package frc.robot.commands.tank;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.GenericSubsystem;

public class TimeTank extends CommandBase {

    private GenericSubsystem m_tank;
    private double m_targetTime = 0;
    private Timer m_timer;
    private double m_x;
    private double m_y;

    private double targetPosition;

    public TimeTank(GenericSubsystem tank, double x, double y, double targetTime) {
        Logger.setup("Constructing Command: WriteN...");

        // Add given subsystem requirements
        m_tank = tank;
        m_timer = new Timer();
        addRequirements(m_tank);
        m_x = x;
        m_y = y;
        m_targetTime = targetTime;
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: WriteN...");
        m_timer.reset();
        m_timer.start();
    }

    @Override
    public void execute() {
        Logger.info(m_timer.get());

        double x = m_x;
        double y = m_y;

        double v = (100-Math.abs(x))*(y/100)+y;
        double w = (100-Math.abs(y))*(x/100)+x;

        double r = (v+w)/2;
        double l = (v-w)/2;

        m_tank.move("talonSrxLeft", l);
        m_tank.move("talonSrxLeftTwo", l);
        m_tank.move("talonSrxRight", -r);
        m_tank.move("talonSrxRightTwo", -r);

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
            Logger.ending("Interrupting Command: WriteN...");
        } else {
            Logger.ending("Ending Command: WriteN...");
        }
        m_tank.stop("sparkMaxTank");
    }

}
