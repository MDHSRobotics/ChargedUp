
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.brains.SwerveDriverBrain;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;

// The Shuffleboard Drive tab.
public class DriveTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_layoutModuleFL;
    private ShuffleboardLayout m_layoutModuleFR;
    private ShuffleboardLayout m_layoutModuleRL;
    private ShuffleboardLayout m_layoutModuleRR;

    private ShuffleboardLayout m_layoutCommands;

    private ShuffleboardLayout m_telemetryLayout;
    private ShuffleboardLayout m_encoderOffsetLayout;

    // Widgets
    private SimpleWidget m_widgetDrivePowerFL;
    private SimpleWidget m_widgetTurningPowerFL;
    private SimpleWidget m_widgetDrivePowerFR;
    private SimpleWidget m_widgetTurningPowerFR;
    private SimpleWidget m_widgetDrivePowerRL;
    private SimpleWidget m_widgetTurningPowerRL;
    private SimpleWidget m_widgetDrivePowerRR;
    private SimpleWidget m_widgetTurningPowerRR;

    private SimpleWidget m_widgetDriveEncoderTicksFL;
    private SimpleWidget m_widgetDriveEncoderMpsFL;
    private SimpleWidget m_widgetTurningEncoderTicksFL;
    private SimpleWidget m_widgetTurningEncoderMpsFL;
    private SimpleWidget m_widgetDriveEncoderTicksFR;
    private SimpleWidget m_widgetDriveEncoderMpsFR;
    private SimpleWidget m_widgetTurningEncoderTicksFR;
    private SimpleWidget m_widgetTurningEncoderMpsFR;
    private SimpleWidget m_widgetDriveEncoderTicksRL;
    private SimpleWidget m_widgetDriveEncoderMpsRL;
    private SimpleWidget m_widgetTurningEncoderTicksRL;
    private SimpleWidget m_widgetTurningEncoderMpsRL;
    private SimpleWidget m_widgetDriveEncoderTicksRR;
    private SimpleWidget m_widgetDriveEncoderMpsRR;
    private SimpleWidget m_widgetTurningEncoderTicksRR;
    private SimpleWidget m_widgetTurningEncoderMpsRR;

    private SimpleWidget m_positionWidget;
    private SimpleWidget m_rotationWidget;

    private SimpleWidget m_FLEncoderOffsetWidget;
    private SimpleWidget m_FREncoderOffsetWidget;
    private SimpleWidget m_RLEncoderOffsetWidget;
    private SimpleWidget m_RREncoderOffsetWidget;

    // Create Brain Widgets
    public DriveTab() {
        ShuffleLogger.logCritical("Constructing DriveTab...");

        m_tab = Shuffleboard.getTab("Drive");

        m_layoutModuleFL = Shuffler.constructLayout(m_tab, "Top Left Module", 2, 0, 2, 2, 2, 4, "LEFT");
        m_layoutModuleFR = Shuffler.constructLayout(m_tab, "Top Right Module", 4, 0, 2, 2, 2, 4, "LEFT");
        m_layoutModuleRL = Shuffler.constructLayout(m_tab, "Rear Left Module", 2, 2, 2, 2, 2, 4, "LEFT");
        m_layoutModuleRR = Shuffler.constructLayout(m_tab, "Rear Right Moudle", 4, 2, 2, 2, 2, 4, "LEFT");

        m_telemetryLayout = Shuffler.constructLayout(m_tab, "Telemetry", 0, 0, 2, 2, 1, 2, "LEFT");
        m_encoderOffsetLayout = Shuffler.constructLayout(m_tab, "Telemetry", 0, 2, 2, 2, 1, 4, "LEFT");
    }

    // Create Brain Widgets
    public void preInitialize() {

        // Drive Power
        m_widgetDrivePowerFL = m_layoutModuleFL.add("Drive Power FL", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryDrivePowerFL = m_widgetDrivePowerFL.getEntry();
        m_widgetDrivePowerFL.withWidget(BuiltInWidgets.kTextView);

        // Turning Power
        m_widgetTurningPowerFL = m_layoutModuleFL.add("Turning Power FL", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryTurningPowerFL = m_widgetTurningPowerFL.getEntry();
        m_widgetTurningPowerFL.withWidget(BuiltInWidgets.kTextView);

        // Drive Encoder (ticks)
        m_widgetDriveEncoderTicksFL = m_layoutModuleFL.add("Encoders/Drive Ticks FL", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryDriveEncoderTicksFL = m_widgetDriveEncoderTicksFL.getEntry();
        m_widgetDriveEncoderTicksFL.withWidget(BuiltInWidgets.kTextView);

        // Drive Encoder (Meters per Second)
        m_widgetDriveEncoderMpsFL = m_layoutModuleFL.add("Encoders/Drive Meters FL", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryDriveEncoderMpsFL = m_widgetDriveEncoderMpsFL.getEntry();
        m_widgetDriveEncoderMpsFL.withWidget(BuiltInWidgets.kTextView);

        // Turning Encoder (ticks)
        m_widgetTurningEncoderTicksFL = m_layoutModuleFL.add("Encoders/Turning Ticks FL", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryTurningEncoderTicksFL = m_widgetTurningEncoderTicksFL.getEntry();
        m_widgetTurningEncoderTicksFL.withWidget(BuiltInWidgets.kTextView);

        // Turning Encoder (Meters per Second)
        m_widgetTurningEncoderMpsFL = m_layoutModuleFL.add("Encoders/Turning Degrees FL", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryTurningEncoderMpsFL = m_widgetTurningEncoderMpsFL.getEntry();
        m_widgetTurningEncoderMpsFL.withWidget(BuiltInWidgets.kTextView);

        // Drive Power
        m_widgetDrivePowerFR = m_layoutModuleFR.add("Drive Power FR", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryDrivePowerFR = m_widgetDrivePowerFR.getEntry();
        m_widgetDrivePowerFR.withWidget(BuiltInWidgets.kTextView);

        // Turning Power
        m_widgetTurningPowerFR = m_layoutModuleFR.add("Turning Power FR", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryTurningPowerFR = m_widgetTurningPowerFR.getEntry();
        m_widgetTurningPowerFR.withWidget(BuiltInWidgets.kTextView);

        // Drive Encoder (ticks) 
        m_widgetDriveEncoderTicksFR = m_layoutModuleFR.add("Encoders/Drive Ticks FR", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryDriveEncoderTicksFR = m_widgetDriveEncoderTicksFR.getEntry();
        m_widgetDriveEncoderTicksFR.withWidget(BuiltInWidgets.kTextView);

        // Drive Encoder (Meters per Second)
        m_widgetDriveEncoderMpsFR = m_layoutModuleFR.add("Encoders/Drive Meters FR", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryDriveEncoderMpsFR = m_widgetDriveEncoderMpsFR.getEntry();
        m_widgetDriveEncoderMpsFR.withWidget(BuiltInWidgets.kTextView);

        // Turning Encoder (ticks)
        m_widgetTurningEncoderTicksFR = m_layoutModuleFR.add("Encoders/Turning Ticks FR", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryTurningEncoderTicksFR = m_widgetTurningEncoderTicksFR.getEntry();
        m_widgetTurningEncoderTicksFR.withWidget(BuiltInWidgets.kTextView);

        // Turning Encoder (Meters per Second)
        m_widgetTurningEncoderMpsFR = m_layoutModuleFR.add("Encoders/Turning Degrees FR", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryTurningEncoderMpsFR = m_widgetTurningEncoderMpsFR.getEntry();
        m_widgetTurningEncoderMpsFR.withWidget(BuiltInWidgets.kTextView);

        // Drive Power
        m_widgetDrivePowerRL = m_layoutModuleRL.add("Drive Power RL", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryDrivePowerRL = m_widgetDrivePowerRL.getEntry();
        m_widgetDrivePowerRL.withWidget(BuiltInWidgets.kTextView);

        // Turning Power
        m_widgetTurningPowerRL = m_layoutModuleRL.add("Turning Power RL", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryTurningPowerRL = m_widgetTurningPowerRL.getEntry();
        m_widgetTurningPowerRL.withWidget(BuiltInWidgets.kTextView);

        // Drive Encoder (ticks)
        m_widgetDriveEncoderTicksRL = m_layoutModuleRL.add("Encoders/Drive Ticks RL", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryDriveEncoderTicksRL = m_widgetDriveEncoderTicksRL.getEntry();
        m_widgetDriveEncoderTicksRL.withWidget(BuiltInWidgets.kTextView);

        // Drive Encoder (Meters per Second)
        m_widgetDriveEncoderMpsRL = m_layoutModuleRL.add("Encoders/Drive Meters RL", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryDriveEncoderMpsRL = m_widgetDriveEncoderMpsRL.getEntry();
        m_widgetDriveEncoderMpsRL.withWidget(BuiltInWidgets.kTextView);

        // Turning Encoder (ticks)
        m_widgetTurningEncoderTicksRL = m_layoutModuleRL.add("Encoders/Turning Ticks RL", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryTurningEncoderTicksRL = m_widgetTurningEncoderTicksRL.getEntry();
        m_widgetTurningEncoderTicksRL.withWidget(BuiltInWidgets.kTextView);

        // Turning Encoder (Meters per Second)
        m_widgetTurningEncoderMpsRL = m_layoutModuleRL.add("Encoders/Turning Degrees RL", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryTurningEncoderMpsRL = m_widgetTurningEncoderMpsRL.getEntry();
        m_widgetTurningEncoderMpsRL.withWidget(BuiltInWidgets.kTextView);

        // Drive Power
        m_widgetDrivePowerRR = m_layoutModuleRR.add("Drive Power RR", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryDrivePowerRR = m_widgetDrivePowerRR.getEntry();
        m_widgetDrivePowerRR.withWidget(BuiltInWidgets.kTextView);

        // Turning Power
        m_widgetTurningPowerRR = m_layoutModuleRR.add("Turning Power RR", SwerveDriverBrain.defaultDrivePower);
        SwerveDriverBrain.entryTurningPowerRR = m_widgetTurningPowerRR.getEntry();
        m_widgetTurningPowerRR.withWidget(BuiltInWidgets.kTextView);

        // Drive Encoder (ticks)
        m_widgetDriveEncoderTicksRR = m_layoutModuleRR.add("Encoders/Drive Ticks RR", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryDriveEncoderTicksRR = m_widgetDriveEncoderTicksRR.getEntry();
        m_widgetDriveEncoderTicksRR.withWidget(BuiltInWidgets.kTextView);

        // Drive Encoder (Meters per Second)
        m_widgetDriveEncoderMpsRR = m_layoutModuleRR.add("Encoders/Drive Meters RR", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryDriveEncoderMpsRR = m_widgetDriveEncoderMpsRR.getEntry();
        m_widgetDriveEncoderMpsRR.withWidget(BuiltInWidgets.kTextView);

        // Turning Encoder (ticks)
        m_widgetTurningEncoderTicksRR = m_layoutModuleRR.add("Encoders/Turning Ticks RR", SwerveDriverBrain.defaultTicks);
        SwerveDriverBrain.entryTurningEncoderTicksRR = m_widgetTurningEncoderTicksRR.getEntry();
        m_widgetTurningEncoderTicksRR.withWidget(BuiltInWidgets.kTextView);

        // Turning Encoder (Meters per Second)
        m_widgetTurningEncoderMpsRR = m_layoutModuleRR.add("Encoders/Turning Degrees RR", SwerveDriverBrain.defaultVelocity);
        SwerveDriverBrain.entryTurningEncoderMpsRR = m_widgetTurningEncoderMpsRR.getEntry();
        m_widgetTurningEncoderMpsRR.withWidget(BuiltInWidgets.kTextView);

        // Current Position
        m_positionWidget = m_telemetryLayout.add("Current Position", SwerveDriverBrain.currentPositionDefault);
        SwerveDriverBrain.entryCurrentPosition = m_positionWidget.getEntry();
        m_positionWidget.withWidget(BuiltInWidgets.kTextView);

        m_rotationWidget = m_telemetryLayout.add("Current Rotation", SwerveDriverBrain.currentRotationDefault);
        SwerveDriverBrain.entryCurrentRotation = m_rotationWidget.getEntry();
        m_rotationWidget.withWidget(BuiltInWidgets.kTextView);

        m_FLEncoderOffsetWidget = m_encoderOffsetLayout.add("FL Encoder Offset", SwerveDriverBrain.defaultFLEncoderOffset);
        SwerveDriverBrain.entryFLEncoderOffset = m_FLEncoderOffsetWidget.getEntry();

        m_FREncoderOffsetWidget = m_encoderOffsetLayout.add("FR Encoder Offset", SwerveDriverBrain.defaultFREncoderOffset);
        SwerveDriverBrain.entryFREncoderOffset = m_FREncoderOffsetWidget.getEntry();

        m_RLEncoderOffsetWidget = m_encoderOffsetLayout.add("RL Encoder Offset", SwerveDriverBrain.defaultRLEncoderOffset);
        SwerveDriverBrain.entryRLEncoderOffset = m_RLEncoderOffsetWidget.getEntry();

        m_RREncoderOffsetWidget = m_encoderOffsetLayout.add("RR Encoder Offset", SwerveDriverBrain.defaultRREncoderOffset);
        SwerveDriverBrain.entryRREncoderOffset = m_RREncoderOffsetWidget.getEntry();

    }

    // Create all other Widgets
    public void initialize() {
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
