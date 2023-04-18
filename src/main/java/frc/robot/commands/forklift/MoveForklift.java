
package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.BotControllers;
import frc.robot.brains.ForkliftBrain;
import frc.robot.consoles.Logger;
import frc.robot.subsystems.Forklift;

public class MoveForklift extends CommandBase {

    private Forklift m_forklift; 

    private double brainExtenderSpeed;
    private double brainElevatorSpeed;

    public MoveForklift(Forklift forklift) {
        Logger.setup("Constructing Command: MoveForklift...");

        // Add given subsystem requirements
        m_forklift = forklift;
        addRequirements(m_forklift);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: MoveForklift...");
        
        brainExtenderSpeed = ForkliftBrain.getExtenderSpeed();
        brainElevatorSpeed = ForkliftBrain.getElevatorSpeed();
    }

    @Override
    public void execute() {
        double extenderPower = BotControllers.xbox2.xbox.getRightY();
        double elevatorPower = BotControllers.xbox2.xbox.getLeftY();

        m_forklift.move("sparkMaxExtender", extenderPower * brainExtenderSpeed);
        m_forklift.move("sparkMaxElevator", elevatorPower * brainElevatorSpeed);

        double clawPower = BotControllers.xbox2.xbox.getLeftTriggerAxis() - BotControllers.xbox2.xbox.getRightTriggerAxis();
        m_forklift.move("sparkMaxClawLift", clawPower);

        //Logger.info("Claw power: " + clampLiftPower + " Extender Power: " + extenderPower + " Elevator Power: " + elevatorPower + " Clamp: " + toggleClamp);
        
    }

    // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: MoveForklift...");
        } else {
            Logger.ending("Ending Command: MoveForklift...");
        }
        m_forklift.stopAllMotors();
    }

}