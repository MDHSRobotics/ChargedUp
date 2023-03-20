package frc.robot.commands.auto;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;
import frc.robot.subsystems.SwerveDriver;

import static frc.robot.BotSensors.gyro;

public class BalanceChargeStation extends CommandBase {

    //private final double CORRECTION_TOLERANCE = 1.0;
    //private final double CORRECTION_SPEED = 0.1;
    private final double CHARGE_STATION_DRIVE_SPEED = AutoConstants.DEFAULT_DRIVE_SPEED;
    private final double CHARGE_STATION_BALANCING_POWER = 0.008;

    private SwerveDriver m_swerveDriver;

    private boolean m_isOnChargeStation;
    private boolean m_isBalanced;
    private boolean m_isSideways;
    //private double m_startingHeading;

    public BalanceChargeStation(SwerveDriver swerveDriver, boolean isSideways) {
        Logger.setup("Constructing Command: BalanceChargeStation...");

        // Add given subsystem requirements
        m_swerveDriver = swerveDriver;
        m_isSideways = isSideways;
        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: BalanceChargeStation...");

        m_isOnChargeStation = false;
        m_isBalanced = false;
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

        /*double yawDifference = m_startingHeading - gyro.getYaw();
        double newTurningSpeed = 0;
        if(yawDifference < -CORRECTION_TOLERANCE){
            newTurningSpeed -= CORRECTION_SPEED;
        }else if(yawDifference > CORRECTION_TOLERANCE){
            newTurningSpeed += CORRECTION_SPEED;
        }*/

        //Logger.info("Is on Charge Station: " + m_isOnChargeStation + " Balanced: " + m_isBalanced + " Angle: " + currentAngle);
        // sets a default speed if the robot is not on the charge station yet
        if (!m_isOnChargeStation) {
            Logger.info("Driving to Charge station, Angle: " + currentAngle);
            // checks if robot is on charge station
            if (Math.abs(currentAngle) > 13.0) {
                m_isOnChargeStation = true;
            } else {
                m_swerveDriver.setChassisSpeed(CHARGE_STATION_DRIVE_SPEED * -xSpeed, CHARGE_STATION_DRIVE_SPEED * ySpeed, 0);
            }
        } 
        
        if(m_isOnChargeStation){
            if(-currentAngle > 11){
                Logger.info("Driving up the charge station, Angle: " + currentAngle);
                m_swerveDriver.setChassisSpeed(CHARGE_STATION_DRIVE_SPEED * -xSpeed, CHARGE_STATION_DRIVE_SPEED * -ySpeed, 0);
            }else{
                Logger.info("Balancing on the charge station, Angle: " + currentAngle);
                m_swerveDriver.setChassisSpeed(CHARGE_STATION_BALANCING_POWER * xSpeed * (-currentAngle), CHARGE_STATION_BALANCING_POWER * ySpeed * (currentAngle), 0);
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
