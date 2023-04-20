package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.GenericSubsystem;
import frc.robot.consoles.Logger;
import frc.robot.BotControllers;

public class TankDrive extends CommandBase{
    
    private GenericSubsystem m_tankDrive;

    public TankDrive(GenericSubsystem tankDrive) {
        Logger.setup("Constructing Command: TankDrive...");

        // Add given subsystem requirements
        m_tankDrive = tankDrive;
        addRequirements(m_tankDrive);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: TankDrive...");
        
    }

    @Override
    public void execute() {
        double forward = BotControllers.xbox3.xbox.getRightY();
        double sideways = BotControllers.xbox3.xbox.getRightX();

        double left = (Math.abs(sideways) > 0) ? sideways : forward;
        double right = (Math.abs(sideways) < 0) ? -sideways : forward;

        m_tankDrive.move("talonFxLeft", left);
        m_tankDrive.move("talonFxLeftTwo", left);
        m_tankDrive.move("talonFxRight", right);
        m_tankDrive.move("talonFxRightTwo", right);
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: TankDrive...");
        } else {
            Logger.ending("Ending Command: TankDrive...");
        }
        m_tankDrive.stopAllMotors();
    }
}
