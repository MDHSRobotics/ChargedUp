package frc.robot.commands.forklift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.Intake;
import frc.robot.BotControllers;

public class MoveIntake extends CommandBase{

    private Intake m_intake;

    public MoveIntake(Intake intake) {
        Logger.setup("Constructing Command: MoveIntake...");

        // Add given subsystem requirements
        m_intake = intake;
        addRequirements(m_intake);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: MoveIntake...");
    }

    @Override
    public void execute() {
        double intakePower = BotControllers.xbox1.regps4.getR2Axis() - BotControllers.xbox1.regps4.getL2Axis();

        m_intake.move("Intake", intakePower, 0.1);
    }

        // This command continues until interrupted
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: MoveIntake...");
        } else {
            Logger.ending("Ending Command: MoveIntake...");
        }
    }
}
