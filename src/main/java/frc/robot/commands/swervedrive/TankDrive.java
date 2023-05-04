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
    public void execute() {/* 
        double forward = BotControllers.xbox3.xbox.getLeftY();
        double sideways = BotControllers.xbox3.xbox.getRightY();

        double left = (Math.abs(sideways) > 0) ? sideways : forward;
        double right = (Math.abs(sideways) < 0) ? -sideways : forward;
        */
        double x = BotControllers.xbox3.xbox.getLeftX();
        double y = -BotControllers.xbox3.xbox.getLeftY();

        double v = (100-Math.abs(x))*(y/100)+y;
        double w = (100-Math.abs(y))*(x/100)+x;

        double r = (v+w)/2;
        double l = (v-w)/2;

        m_tankDrive.move("talonSrxLeft", l);
        m_tankDrive.move("talonSrxLeftTwo", l);
        m_tankDrive.move("talonSrxRight", -r);
        m_tankDrive.move("talonSrxRightTwo", -r);
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
