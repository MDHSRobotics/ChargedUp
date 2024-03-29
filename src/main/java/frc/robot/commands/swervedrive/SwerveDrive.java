package frc.robot.commands.swervedrive;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.brains.SwerveDriverBrain;
import frc.robot.consoles.Logger;

import frc.robot.oi.controllers.JoystickPositionAccessible;
import frc.robot.oi.controllers.XboxPositionAccessible;
import frc.robot.oi.movements.SwerveMovement;

import frc.robot.subsystems.SwerveDriver;

public class SwerveDrive extends CommandBase {

    private final SwerveDriver m_swerveDriver;
    private final JoystickPositionAccessible m_jstickController;
    private final XboxPositionAccessible m_xboxController;
    private final SlewRateLimiter m_forwardBackwardLimiter, m_sideToSideLimiter, m_rotationLimiter;
    private static String m_chosenController; //jstick or xbox

    private double brainDeadband;
    private double brainForwardBackwardSpeed;
    private double brainLeftRightSpeed;
    private double brainRotationSpeed;

    // Constants
    private final double MAX_ACCLERATION = 1.0;
    private final double MAX_ANGULAR_ACCELERATION = 1.5;

    public SwerveDrive (SwerveDriver swerveDriver, JoystickPositionAccessible controller) {
        Logger.setup("Constructing Command: SwerveDrive...");

        m_chosenController = "jstick";
        m_swerveDriver = swerveDriver;
        m_jstickController = controller;
        m_xboxController = null;
        m_forwardBackwardLimiter = new SlewRateLimiter(MAX_ACCLERATION);
        m_sideToSideLimiter = new SlewRateLimiter(MAX_ACCLERATION);
        m_rotationLimiter = new SlewRateLimiter(MAX_ANGULAR_ACCELERATION);

        addRequirements(m_swerveDriver);
    }

    public SwerveDrive (SwerveDriver swerveDriver, XboxPositionAccessible controller) {
        Logger.setup("Constructing Command: SwerveDrive...");

        m_chosenController = "xbox";
        m_swerveDriver = swerveDriver;
        m_jstickController = null;
        m_xboxController = controller;
        m_forwardBackwardLimiter = new SlewRateLimiter(MAX_ACCLERATION);
        m_sideToSideLimiter = new SlewRateLimiter(MAX_ACCLERATION);
        m_rotationLimiter = new SlewRateLimiter(MAX_ANGULAR_ACCELERATION);

        addRequirements(m_swerveDriver);
    }

    @Override
    public void initialize() {
        Logger.action("Initializing Command: SwerveDrive...");
        
        brainDeadband = SwerveDriverBrain.getDeadband();
    }

    @Override
    public void execute() {

        brainForwardBackwardSpeed = SwerveDriverBrain.getForwardBackwardSpeed();
        brainLeftRightSpeed = SwerveDriverBrain.getLeftRightSpeed();
        brainRotationSpeed = SwerveDriverBrain.getRotationSpeed();

        // 1. Get real-time joystick inputs
        SwerveMovement joystickMovement;

        if (m_chosenController.equals("jstick")) {
            joystickMovement = SwerveMovement.getMovement(m_jstickController, false);
        } else {
            joystickMovement = SwerveMovement.getMovement(m_xboxController, false);
        }

        double forwardBackwardSpeed = joystickMovement.forwardBackwardSpeed;
        double sideToSideSpeed = joystickMovement.sideToSideSpeed;
        double rotationSpeed = joystickMovement.rotationSideToSideSpeed;

        //SmartDashboard.putString("10: Joystick input", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed, sideToSideSpeed, rotationSpeed));

        // 2. Apply deadband
        double forwardBackwardSpeed2 = Math.abs(forwardBackwardSpeed) > brainDeadband ? forwardBackwardSpeed : 0.0;
        double sideToSideSpeed2 = Math.abs(sideToSideSpeed) > brainDeadband ? sideToSideSpeed : 0.0;
        double rotationSpeed2 = Math.abs(rotationSpeed) > brainDeadband ? rotationSpeed : 0.0;

        //SmartDashboard.putString("09: Apply deadpan", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed2, sideToSideSpeed2, rotationSpeed2));

        // 3. Make the driving smoother
        double forwardBackwardSpeed3 = m_forwardBackwardLimiter.calculate(forwardBackwardSpeed2) * brainForwardBackwardSpeed;
        double sideToSideSpeed3 = m_sideToSideLimiter.calculate(sideToSideSpeed2) * brainLeftRightSpeed;
        double rotationSpeed3 = m_rotationLimiter.calculate(rotationSpeed2) * brainRotationSpeed;

        SmartDashboard.putString("08: Chassis velocity", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed3, sideToSideSpeed3, rotationSpeed3));

        // 4. Output each module states to wheels
        m_swerveDriver.setChassisSpeed(forwardBackwardSpeed3, -sideToSideSpeed3, rotationSpeed3);
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
