package frc.robot.commands.auto;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.brains.SwerveDriverBrain;
import frc.robot.consoles.Logger;
import frc.robot.subsystems.SwerveDriver;
import frc.robot.subsystems.constants.AutoConstants;
import frc.robot.subsystems.constants.SwerveConstants;

import static frc.robot.BotSensors.gyro;

public class BalanceChargeStation extends CommandBase {

    private SwerveDriver m_swerveDriver;
    private PIDController m_pidController;

    private boolean m_isOnChargeStation;
    private boolean m_isBalanced;
    private boolean m_isSideways;

    public BalanceChargeStation(SwerveDriver swerveDriver, boolean isSideways) {
        Logger.setup("Constructing Command: BalanceChargeStation...");

        // Add given subsystem requirements
        m_swerveDriver = swerveDriver;
        m_isSideways = isSideways;
        m_pidController = new PIDController(0,0,0);
        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: BalanceChargeStation...");

        m_isOnChargeStation = false;
        m_isBalanced = false;

        m_pidController.setSetpoint(0);
        m_pidController.setTolerance(2.5);

        m_pidController.setP(SwerveDriverBrain.getChargeStationP());
        m_pidController.setI(SwerveDriverBrain.getChargeStationI());
        m_pidController.setD(SwerveDriverBrain.getChargeStationD());
    }

    @Override
    public void execute() {

        double currentAngle;
        double xSpeed = 0.;
        double ySpeed = 0.;
        if(m_isSideways){
            currentAngle = gyro.getRoll();
            ySpeed = 1.;
        }else{
            currentAngle = gyro.getPitch();
            xSpeed = 1.;
        }

        Logger.info("Is on Charge Station: " + m_isOnChargeStation + " Balanced: " + m_isBalanced + " Angle: " + currentAngle);
        // sets a default speed if the robot is not on the charge station yet
        if (!m_isOnChargeStation) {
            // checks if robot is on charge station
            if (Math.abs(currentAngle) > 13.0) {
                m_isOnChargeStation = true;
            } else {
                m_swerveDriver.setChassisSpeed(SwerveConstants.kMaxChargeStationBalancingPower * -xSpeed, SwerveConstants.kMaxChargeStationBalancingPower * ySpeed , 0);
            }
        } 

        // sets appropriate speed based on position on ramp if robot is on charge station
        /*if (m_isOnChargeStation) {
            double outputScaleFactor = m_pidController.calculate(currentAngle);

            // checks if charge station is balanced
            if (m_pidController.atSetpoint()) {
                m_isBalanced = true;
                m_swerveDriver.stopModules();
            } else {
                double robotSpeed = outputScaleFactor * SwerveConstants.kMaxChargeStationBalancingPower;
            
                // sets power output to 0.1 if current speed is too slow
                if (Math.abs(robotSpeed) < SwerveConstants.kMinChargeStationBalancingPower) {
                    robotSpeed = SwerveConstants.kMinChargeStationBalancingPower * Math.signum(robotSpeed);
                }
    
                // drives forward or backward based on location until robot is balanced on charge station 
                m_swerveDriver.setChassisSpeed(robotSpeed * xSpeed, robotSpeed * ySpeed , 0);
            }
        }
        */
        if(m_isOnChargeStation){
            if(-currentAngle > 11){
                m_swerveDriver.setChassisSpeed(SwerveConstants.kMaxChargeStationBalancingPower * -xSpeed, SwerveConstants.kMaxChargeStationBalancingPower * ySpeed, 0);
            }else{
                m_swerveDriver.setChassisSpeed(SwerveConstants.kMinChargeStationBalancingPower * xSpeed * (-currentAngle), SwerveConstants.kMinChargeStationBalancingPower * ySpeed * (currentAngle), 0);
            }
        }
    } 

    @Override
    public boolean isFinished() {
        return m_isBalanced;
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
