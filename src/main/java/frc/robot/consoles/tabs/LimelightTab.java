
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.BotCommands;
import frc.robot.sensors.Limelight;
import frc.robot.brains.SwerveDriverBrain;
import frc.robot.commands.limelight.AlignLimelight;
import frc.robot.commands.swervedrive.SwerveDrive;

import java.util.Map;

// The Shuffleboard Main tab.
public class LimelightTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;

    // Layouts
    private ShuffleboardLayout m_pidLayout;

    // Widgets
    private SimpleWidget m_kPxOffset;
    private SimpleWidget m_kIxOffset;
    private SimpleWidget m_kDxOffset;

    private SimpleWidget m_kPDistance;
    private SimpleWidget m_kIDistance;
    private SimpleWidget m_kDDistance;

    private ComplexWidget m_AlignLimelight;

    // Constructor
    public LimelightTab() {
        ShuffleLogger.logTrivial("Constructing LimelightTab");

        m_tab = Shuffleboard.getTab("Limelight");

        m_pidLayout = m_tab.getLayout("Limelight Values", BuiltInLayouts.kList);
        m_pidLayout.withPosition(0, 0);
        m_pidLayout.withSize(2, 8);
        m_pidLayout.withProperties(Map.of("Number of columns", 2));
        m_pidLayout.withProperties(Map.of("Number of rows", 3));
        m_pidLayout.withProperties(Map.of("Label position", "LEFT"));
    }

    // Create Brain Widgets
    public void preInitialize() {

        // PID Values for x offset pid controller

        m_kPxOffset = m_pidLayout.add("kP xOffset", SwerveDriverBrain.defaultkPxOffset);
        SwerveDriverBrain.entryAlignLimelightkPxOffset = m_kPxOffset.getEntry();
        m_kPxOffset.withWidget(BuiltInWidgets.kTextView);

        m_kIxOffset = m_pidLayout.add("kI xOffset", SwerveDriverBrain.defaultkIxOffset);
        SwerveDriverBrain.entryAlignLimelightkIxOffset = m_kIxOffset.getEntry();
        m_kIxOffset.withWidget(BuiltInWidgets.kTextView);

        m_kDxOffset = m_pidLayout.add("kD xOffset", SwerveDriverBrain.defaultkDxOffset);
        SwerveDriverBrain.entryAlignLimelightkDxOffset = m_kDxOffset.getEntry();
        m_kDxOffset.withWidget(BuiltInWidgets.kTextView);


        // PID Values for distance pid controller

        m_kPDistance = m_pidLayout.add("kP Distance", SwerveDriverBrain.defaultkPDistance);
        SwerveDriverBrain.entryAlignLimelightkPDistance = m_kPDistance.getEntry();
        m_kPDistance.withWidget(BuiltInWidgets.kTextView);

        m_kIDistance = m_pidLayout.add("kI Distance", SwerveDriverBrain.defaultkIDistance);
        SwerveDriverBrain.entryAlignLimelightkIDistance = m_kIDistance.getEntry();
        m_kIDistance.withWidget(BuiltInWidgets.kTextView);

        m_kDDistance = m_pidLayout.add("kD Distance", SwerveDriverBrain.defaultkDDistance);
        SwerveDriverBrain.entryAlignLimelightkDDistance = m_kDDistance.getEntry();
        m_kDDistance.withWidget(BuiltInWidgets.kTextView);
    }

    // Create all other Widgets
    public void initialize() {
        m_AlignLimelight = m_pidLayout.add("AlignLimelight", BotCommands.alignLimelight);
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
    }

}
