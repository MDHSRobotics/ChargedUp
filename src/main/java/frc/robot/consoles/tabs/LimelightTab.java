
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.BotCommands;
import frc.robot.sensors.Limelight;
import frc.robot.brains.SwerveDriverBrain;

import java.util.Map;

// The Shuffleboard Main tab.
public class LimelightTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;

    // Layouts
    private ShuffleboardLayout m_commandLayout;

    // Widgets
    private SimpleWidget m_kPxOffset;
    private SimpleWidget m_kIxOffset;
    private SimpleWidget m_kDxOffset;

    private SimpleWidget m_kPDistance;
    private SimpleWidget m_kIDistance;
    private SimpleWidget m_kDDistance;

    // Constructor
    public LimelightTab() {
        ShuffleLogger.logTrivial("Constructing LimelightTab");

        m_tab = Shuffleboard.getTab("Limelight");

        m_commandLayout = m_tab.getLayout("Limelight Values", BuiltInLayouts.kList);
        m_commandLayout.withPosition(0, 0);
        m_commandLayout.withSize(2, 6);
        m_commandLayout.withProperties(Map.of("Number of columns", 2));
        m_commandLayout.withProperties(Map.of("Number of rows", 3));
        m_commandLayout.withProperties(Map.of("Label position", "LEFT"));
    }

    // Create Brain Widgets
    public void preInitialize() {

        // PID Values for x offset pid controller

        m_kPxOffset = m_commandLayout.add("kP xOffset", SwerveDriverBrain.entryAlignLimelightkPxOffset);
        SwerveDriverBrain.entryAlignLimelightkPxOffset = m_kPxOffset.getEntry();
        m_kPxOffset.withWidget(BuiltInWidgets.kTextView);

        m_kIxOffset = m_commandLayout.add("kI xOffset", SwerveDriverBrain.entryAlignLimelightkIxOffset);
        SwerveDriverBrain.entryAlignLimelightkIxOffset = m_kIxOffset.getEntry();
        m_kIxOffset.withWidget(BuiltInWidgets.kTextView);

        m_kDxOffset = m_commandLayout.add("kD xOffset", SwerveDriverBrain.entryAlignLimelightkDxOffset);
        SwerveDriverBrain.entryAlignLimelightkDxOffset = m_kDxOffset.getEntry();
        m_kDxOffset.withWidget(BuiltInWidgets.kTextView);


        // PID Values for distance pid controller

        m_kPDistance = m_commandLayout.add("kP Distance", SwerveDriverBrain.entryAlignLimelightkPDistance);
        SwerveDriverBrain.entryAlignLimelightkPDistance = m_kPDistance.getEntry();
        m_kPDistance.withWidget(BuiltInWidgets.kTextView);

        m_kIDistance = m_commandLayout.add("kI Distance", SwerveDriverBrain.entryAlignLimelightkIDistance);
        SwerveDriverBrain.entryAlignLimelightkIDistance = m_kIDistance.getEntry();
        m_kIDistance.withWidget(BuiltInWidgets.kTextView);

        m_kDDistance = m_commandLayout.add("kD Distance", SwerveDriverBrain.entryAlignLimelightkDDistance);
        SwerveDriverBrain.entryAlignLimelightkDDistance = m_kDDistance.getEntry();
        m_kDDistance.withWidget(BuiltInWidgets.kTextView);
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
