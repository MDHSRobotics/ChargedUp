package frc.robot.commands.swervedrive;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.consoles.Logger;

import frc.robot.oi.controllers.JoystickPositionAccessible;
import frc.robot.oi.controllers.HandHeldPositionAccessible;
import frc.robot.oi.movements.SwerveMovement;

import frc.robot.subsystems.SwerveDriver;

import frc.robot.consoles.Shuffler;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.networktables.GenericEntry;
import java.util.Map;
import frc.robot.BotControllers;

public class SwerveDrive extends CommandBase {

    private final SwerveDriver m_swerveDriver;
    private final JoystickPositionAccessible m_jstickController;
    private final HandHeldPositionAccessible m_xboxController;
    private final SlewRateLimiter m_forwardBackwardLimiter, m_sideToSideLimiter, m_rotationLimiter;
    private final double m_autoAlignPower = 0.5;
    private static String m_chosenController; //jstick or xbox

    private double deadband;

    // Constants
    private final double MAX_ACCLERATION = 1.0;
    private final double MAX_ANGULAR_ACCELERATION = 1.5;

    private ShuffleboardLayout m_telemetryLayout = Shuffler.constructLayout(Shuffler.m_driveTab, "Telemetry", 0, 0, 7, 2, 1, 2, "LEFT");
    private ShuffleboardLayout m_preferencesLayout = Shuffler.constructLayout(Shuffler.m_driveTab, "Driver Preferences", 0, 2, 7, 4, 2, 2, "LEFT");

    // Current Position and Rotation
    private GenericEntry entryPosition = m_telemetryLayout
        .add("Current Position", 0)
        .getEntry();

    private GenericEntry entryRotation = m_telemetryLayout
        .add("Current Rotation", 0)
        .getEntry();

    //Preferences
    private GenericEntry entryForwardBackwardSpeed = m_preferencesLayout
        .addPersistent("Max Forward Backward Speed", 2.4)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 5))
        .getEntry();

    private GenericEntry entryLeftRightSpeed = m_preferencesLayout
        .addPersistent("Max Left Right Speed", 1.2)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 5))
        .getEntry();

    private GenericEntry entryRotationSpeed = m_preferencesLayout
        .addPersistent("Max Rotation Speed", 2.0)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 5))
        .getEntry();
    
    //Deadband
    private GenericEntry entryDeadBand = m_preferencesLayout
        .addPersistent("DeadBand", 0.1)
        .withWidget(BuiltInWidgets.kNumberSlider)
        .withProperties(Map.of("min", 0, "max", 0.5))
        .getEntry();

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

    public SwerveDrive (SwerveDriver swerveDriver, HandHeldPositionAccessible controller) {
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
        
        deadband = entryDeadBand.getDouble(0.1);
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
        double rotationSpeed = joystickMovement.rotationSideToSideSpeed;

        //SmartDashboard.putString("10: Joystick input", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed, sideToSideSpeed, rotationSpeed));

        // 2. Apply deadband
        double forwardBackwardSpeed2 = Math.abs(forwardBackwardSpeed) > deadband ? forwardBackwardSpeed : 0.0;
        double sideToSideSpeed2 = Math.abs(sideToSideSpeed) > deadband ? sideToSideSpeed : 0.0;
        double rotationSpeed2 = Math.abs(rotationSpeed) > deadband ? rotationSpeed : 0.0;

        //SmartDashboard.putString("09: Apply deadpan", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed2, sideToSideSpeed2, rotationSpeed2));

        // 3. Make the driving smoother
        double forwardBackwardSpeed3 = m_forwardBackwardLimiter.calculate(forwardBackwardSpeed2) * entryForwardBackwardSpeed.getDouble(2.4);
        double sideToSideSpeed3 = m_sideToSideLimiter.calculate(sideToSideSpeed2) * entryLeftRightSpeed.getDouble(1.2);
        double rotationSpeed3 = m_rotationLimiter.calculate(rotationSpeed2) * entryRotationSpeed.getDouble(2.0);

        SmartDashboard.putString("08: Chassis velocity", String.format("X = %.2f; Y = %.2f, Turn = %.2f", forwardBackwardSpeed3, sideToSideSpeed3, rotationSpeed3));

        // 4. Output each module states to wheels
        m_swerveDriver.setChassisSpeed(forwardBackwardSpeed3, -sideToSideSpeed3, rotationSpeed3);

        //Retrieve Shuffleboard data from subsystem
        entryPosition.setString(m_swerveDriver.getPosition());
        entryRotation.setDouble(m_swerveDriver.getRotation());

        if(BotControllers.xbox1.regps4.getCrossButtonPressed()){
            entryForwardBackwardSpeed.setDouble(2.4);
            entryLeftRightSpeed.setDouble(1.6);
            entryRotationSpeed.setDouble(2.0);
        }
        if(BotControllers.xbox1.regps4.getCircleButtonPressed()){
            entryForwardBackwardSpeed.setDouble(0.5);
            entryLeftRightSpeed.setDouble(0.5);
            entryRotationSpeed.setDouble(0.95);
        }
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
