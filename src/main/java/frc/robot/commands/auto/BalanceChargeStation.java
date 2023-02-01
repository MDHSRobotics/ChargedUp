package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.SwerveDriver;
import frc.robot.subsystems.constants.AutoConstants;
import static frc.robot.BotSensors.gyro;

public class BalanceChargeStation extends CommandBase {

    private SwerveDriver m_swerveDriver;

    private boolean m_isOnChargeStation = false;
    private boolean m_isBalanced = false;

    public BalanceChargeStation(SwerveDriver swerveDriver) {
        Logger.setup("Constructing Command: BalanceChargeStation...");

        // Add given subsystem requirements
        m_swerveDriver = swerveDriver;
        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: BalanceChargeStation...");
    }

    @Override
    public void execute() {

        double currentRollAngle = gyro.getRoll();
        double rollTolerance = 2;     

        // drives forward or backward based on location until robot is balanced on charge station 
        if (currentRollAngle >= 0) {
            m_swerveDriver.setChassisSpeed(AutoConstants.kDrivePower, 0, 0);
        } else if (currentRollAngle <= -1) { 
            m_swerveDriver.setChassisSpeed(-AutoConstants.kDrivePower, 0 , 0);
        }

        // determines if robot has driven onto charge station ramp
        if (Math.abs(currentRollAngle) > Math.abs((AutoConstants.kChargeStationRampAngle) - rollTolerance)) {
            m_isOnChargeStation = true;
        }

        // determines if robot has been balanced
        if (m_isOnChargeStation) {
            if (currentRollAngle <= 1) {
                m_isBalanced = true;
            }
        }
    } 

    @Override
    public boolean isFinished() {
        if (m_isBalanced) {  
            return true;
        }
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: BalanceChargeStation...");
        } else {
            Logger.ending("Ending Command: BalanceChargeStation...");
        }
        m_swerveDriver.stopModules();
    }

}
