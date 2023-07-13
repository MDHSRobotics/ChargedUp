package frc.robot.commands.limelight;

import frc.robot.sensors.Limelight;
import frc.robot.subsystems.SwerveDriver;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.consoles.Logger;

import static frc.robot.BotSensors.gyro;

//We align the bot using limelight in the community using april tags, x0 y0 being top left
public class AlignLimelight extends CommandBase{

    private SwerveDriver m_swerveDriver;
    private int m_x;

    private boolean targetAligned = false;
    private boolean targetSeen = false;
    private int targetID = 0;

    public AlignLimelight(SwerveDriver swerveDriver, int x){
        Logger.setup("Constructing Command: AlignLimelight...");
        m_swerveDriver = swerveDriver;
        m_x = x;
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: AlignLimelight ...");

        Limelight.setPipeline(0);

        if(Limelight.getTagID() == 6){
            if(m_x <= 2){
                targetID = 8;
            }else if(m_x <= 5){
                targetID = 7;
            }else{
                targetID = 6;
            }
        }else if(Limelight.getTagID() == 3){
            if(m_x <= 2){
                targetID = 3;
            }else if(m_x <= 5){
                targetID = 2;
            }else{
                targetID = 1;
            }
        }
    }

    @Override
    public void execute() {
        double xOffset = Limelight.getXOffset();

        double yawDifference = 0 - gyro.getYaw();
        double newTurningSpeed = 0;
        if(yawDifference < -1){
            newTurningSpeed -= 0.1;
        }else if(yawDifference > 1){
            newTurningSpeed += 0.1;
        }

        if(targetID > 6 && !targetSeen){
            m_swerveDriver.setChassisSpeed(0, 0.5, newTurningSpeed);
            if(targetID == Limelight.getTagID()){
                targetSeen = true;
            }
        }
        if (targetSeen && !targetAligned){
            if(xOffset <= -9){
                //Logger.info("moving left");
                m_swerveDriver.setChassisSpeed(0, 0.2, newTurningSpeed);
            }else if (xOffset >= -6){
                //Logger.info("moving right");
                m_swerveDriver.setChassisSpeed(0, -0.2, newTurningSpeed);
            }else{
                targetAligned = true;
            }
        }
    }

    @Override
    public boolean isFinished() {
        return targetAligned || m_x > 5;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: AlignLimelight ...");
        } else {
            Logger.ending("Ending Command: AlignLimelight ...");
        }
    }
}
