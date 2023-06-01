package frc.robot.commands.swervedrive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.BotSensors;
import frc.robot.consoles.Logger;

public class CalibrateGyro extends CommandBase{

    public CalibrateGyro() {

        Logger.setup("Constructing Command: CalibrateGyro...");
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        BotSensors.gyro.calibrate();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void end(boolean interrupted) {
    }
}
