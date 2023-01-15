
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.BotSubsystems;
import frc.robot.brains.SwerveDriverBrain;
import frc.robot.consoles.ShuffleLogger;

import java.util.Map;

// The Shuffleboard Drive tab.
public class DriveTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_layoutModuleFL;
    private ShuffleboardLayout m_layoutCommands;

    // Widgets
    private ComplexWidget m_widgetSwerveDrive;

    
    private SimpleWidget m_widgetDrivePowerFL;
    private SimpleWidget m_widgetTurningPowerFL;
    private SimpleWidget m_widgetDriveEncoderTicksFL;
    private SimpleWidget m_widgetDriveEncoderMpsFL;
    private SimpleWidget m_widgetTurningEncoderTicksFL;
    private SimpleWidget m_widgetTurningEncoderMpsFL;
    

    // Create Brain Widgets
    public DriveTab() {
        ShuffleLogger.logCritical("Constructing DriveTab...");

        m_tab = Shuffleboard.getTab("Drive");
        m_layoutModuleFL = m_tab.getLayout("Top Left Module", BuiltInLayouts.kList);
        m_layoutCommands = m_tab.getLayout("Commands", BuiltInLayouts.kList);
    }

    // Create Brain Widgets
    public void preInitialize() {
        // Top Left Module Layout

        
        // Drive Power
        m_widgetDrivePowerFL = m_layoutModuleFL.add("Drive Power", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryDrivePowerFL = m_widgetDrivePowerFL.getEntry();
        m_widgetDrivePowerFL.withWidget(BuiltInWidgets.kTextView);
        // Turning Power
        m_widgetTurningPowerFL = m_layoutModuleFL.add("Turning Power", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryTurningPowerFL = m_widgetTurningPowerFL.getEntry();
        m_widgetTurningPowerFL.withWidget(BuiltInWidgets.kTextView);
        // Drive Encoder (ticks)
        m_widgetDriveEncoderTicksFL = m_layoutModuleFL.add("Encoders/Drive Ticks", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryDriveEncoderTicksFL = m_widgetDriveEncoderTicksFL.getEntry();
        m_widgetDriveEncoderTicksFL.withWidget(BuiltInWidgets.kTextView);
        // Drive Encoder (Meters per Second)
        m_widgetDriveEncoderMpsFL = m_layoutModuleFL.add("Encoders/Drive Meters", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryDriveEncoderMpsFL = m_widgetDriveEncoderMpsFL.getEntry();
        m_widgetDriveEncoderMpsFL.withWidget(BuiltInWidgets.kTextView);
        // Turning Encoder (ticks)
        m_widgetTurningEncoderTicksFL = m_layoutModuleFL.add("Encoders/Turning Ticks", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryTurningEncoderTicksFL = m_widgetTurningEncoderTicksFL.getEntry();
        m_widgetTurningEncoderTicksFL.withWidget(BuiltInWidgets.kTextView);
        // Turning Encoder (Meters per Second)
        m_widgetTurningEncoderMpsFL = m_layoutModuleFL.add("Encoders/Turning Degrees", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryTurningEncoderMpsFL = m_widgetTurningEncoderMpsFL.getEntry();
        m_widgetTurningEncoderMpsFL.withWidget(BuiltInWidgets.kTextView);
        
    }

    // Create all other Widgets
    public void initialize() {
        
        m_widgetSwerveDrive = m_tab.add("Swerve Drive", BotSubsystems.swerveDriver);
    
    }

    // Configure all Widgets
    public void configure() {
        m_layoutModuleFL.withPosition(3, 0);
        m_layoutModuleFL.withSize(2, 4);
        m_layoutModuleFL.withProperties(Map.of("Number of columns", 2));
        m_layoutModuleFL.withProperties(Map.of("Number of rows", 4));
        m_layoutModuleFL.withProperties(Map.of("Label position", "TOP"));

        m_layoutCommands.withPosition(0, 0);
        m_layoutCommands.withSize(2, 2);
        m_layoutCommands.withProperties(Map.of("Number of columns", 2));
        m_layoutCommands.withProperties(Map.of("Number of rows", 2));
        m_layoutCommands.withProperties(Map.of("Label position", "TOP"));

        /*
        m_widgetSwerveDrive.withPosition(3, 3);
        m_widgetSwerveDrive.withSize(3, 1);
        */
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}