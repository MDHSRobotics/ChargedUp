package frc.robot.commands.swervedrive;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.consoles.Logger;

import frc.robot.oi.controllers.JoystickPositionAccessible;
import frc.robot.oi.controllers.XboxPositionAccessible;
import frc.robot.oi.movements.SwerveMovement;

import frc.robot.subsystems.SwerveDriver;
import frc.robot.subsystems.constants.SwerveConstants;
import frc.robot.subsystems.constants.SwerveConstants.OIConstants;


public class SwerveDrive extends CommandBase {

    private final SwerveDriver m_swerveDriver;
    private final JoystickPositionAccessible m_jstickController;
    private final XboxPositionAccessible m_xboxController;
    private final SlewRateLimiter m_forwardBackwardLimiter, m_sideToSideLimiter, m_rotationForwardBackLimiter, m_rotationSideToSideLimiter;
    private static String m_chosenController; //jstick or xbox

    public SwerveDrive (SwerveDriver swerveDriver, JoystickPositionAccessible controller) {
        Logger.setup("Constructing Command: SwerveDrive...");

        m_chosenController = "jstick";
        m_swerveDriver = swerveDriver;
        m_jstickController = controller;
        m_xboxController = null;
        m_forwardBackwardLimiter = new SlewRateLimiter(SwerveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        m_sideToSideLimiter = new SlewRateLimiter(SwerveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        m_rotationForwardBackLimiter = new SlewRateLimiter(SwerveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        m_rotationSideToSideLimiter = new SlewRateLimiter(SwerveConstants.kTeleDriveMaxAngularAccelerationUnitsPerSecond);

        addRequirements(m_swerveDriver);
    }

    public SwerveDrive (SwerveDriver swerveDriver, XboxPositionAccessible controller) {
        Logger.setup("Constructing Command: SwerveDrive...");

        m_chosenController = "xbox";
        m_swerveDriver = swerveDriver;
        m_jstickController = null;
        m_xboxController = controller;
        m_forwardBackwardLimiter = new SlewRateLimiter(SwerveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        m_sideToSideLimiter = new SlewRateLimiter(SwerveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        m_rotationForwardBackLimiter = new SlewRateLimiter(SwerveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
        m_rotationSideToSideLimiter = new SlewRateLimiter(SwerveConstants.kTeleDriveMaxAngularAccelerationUnitsPerSecond);

        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: SwerveDrive...");
    }

    @Override
    public void execute() {
        // 1. Get real-time joystick inputs
        SwerveMovement joystickMovement;

        if (m_chosenController.equals("jstick")) {
            joystickMovement = SwerveMovement.getMovement(m_jstickController, false);
        } else {
            joystickMovement = SwerveMovement.getMovement(m_xboxController, false);
        }

        double forwardBackwardSpeed = joystickMovement.forwardBackwardSpeed;
        double sideToSideSpeed = joystickMovement.sideToSideSpeed;
        double rotationForwardBackwardSpeed = joystickMovement.rotationForwardBackwardSpeed;
        double rotationSideToSideSpeed = joystickMovement.rotationSideToSideSpeed;

        SmartDashboard.putString("10: Joystick input", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed, sideToSideSpeed, rotationForwardBackwardSpeed, rotationSideToSideSpeed));

        // 2. Apply deadband
        double forwardBackwardSpeed2 = Math.abs(forwardBackwardSpeed) > OIConstants.kDeadband ? forwardBackwardSpeed : 0.0;
        double sideToSideSpeed2 = Math.abs(sideToSideSpeed) > OIConstants.kDeadband ? sideToSideSpeed : 0.0;
        double rotationForwardBackwardSpeed2 = Math.abs(rotationForwardBackwardSpeed) > OIConstants.kDeadband ? rotationForwardBackwardSpeed : 0.0;
        double rotationSideToSideSpeed2 = Math.abs(rotationSideToSideSpeed) > OIConstants.kDeadband ? rotationSideToSideSpeed : 0.0;

        SmartDashboard.putString("09: Apply deadpan", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed2, sideToSideSpeed2, rotationSideToSideSpeed2));

        // 3. Make the driving smoother
        double forwardBackwardSpeed3 = m_forwardBackwardLimiter.calculate(forwardBackwardSpeed2) * SwerveConstants.kTeleDriveMaxSpeedMetersPerSecond;
        double sideToSideSpeed3 = m_sideToSideLimiter.calculate(sideToSideSpeed2) * SwerveConstants.kTeleDriveMaxSpeedMetersPerSecond;
        double rotationForwardBackwardSpeed3 = m_rotationForwardBackLimiter.calculate(rotationForwardBackwardSpeed2) * SwerveConstants.kTeleDriveMaxSpeedMetersPerSecond;
        double rotationSideToSideSpeed3 = m_rotationSideToSideLimiter.calculate(rotationSideToSideSpeed2) * SwerveConstants.kTeleDriveMaxAngularSpeedRadiansPerSecond;

        SmartDashboard.putString("08: Chassis velocity", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed3, sideToSideSpeed3, rotationSideToSideSpeed3));

        // 5. Output each module states to wheels
        m_swerveDriver.setChassisSpeed(forwardBackwardSpeed3, -sideToSideSpeed3, rotationSideToSideSpeed3);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            Logger.ending("Interrupting Command: SwerveDrive...");
        } else {
            Logger.ending("Ending Command: SwerveDrive...");
        }
        m_swerveDriver.stopModules();
    }

}
