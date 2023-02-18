
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;
import frc.robot.BotCommands;
import frc.robot.sensors.Limelight;
import frc.robot.brains.LimelightBrain;
import frc.robot.brains.SwerveDriverBrain;

// The Shuffleboard Main tab.
public class LimelightTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;

    // Layouts
    private ShuffleboardLayout m_pidLayout;
    private ShuffleboardLayout m_limelightLayout;

    // Widgets
    private SimpleWidget m_kPxOffset;
    private SimpleWidget m_kIxOffset;
    private SimpleWidget m_kDxOffset;

    private SimpleWidget m_kPDistance;
    private SimpleWidget m_kIDistance;
    private SimpleWidget m_kDDistance;

    private SimpleWidget m_xOffset;
    private SimpleWidget m_yOffset;
    private SimpleWidget m_validTarget;
    private SimpleWidget m_distance;

    private ComplexWidget m_positionLimelight;

    // Constructor
    public LimelightTab() {
        ShuffleLogger.logTrivial("Constructing LimelightTab");

        m_tab = Shuffleboard.getTab("Limelight");

        m_pidLayout = Shuffler.constructLayout(m_tab, "Limelight PID Values", 0, 0, 2, 3, 1, 3, "LEFT");
        m_limelightLayout = Shuffler.constructLayout(m_tab, "Limelight Values", 2, 0, 2, 2, 1, 4, "LEFT");

    }

    // Create Brain Widgets
    public void preInitialize() {

        // PID Values for x offset pid controller

        m_kPxOffset = m_pidLayout.add("kP xOffset", SwerveDriverBrain.defaultkPxOffset);
        SwerveDriverBrain.entryPositionLimelightkPxOffset = m_kPxOffset.getEntry();
        m_kPxOffset.withWidget(BuiltInWidgets.kTextView);

        m_kIxOffset = m_pidLayout.add("kI xOffset", SwerveDriverBrain.defaultkIxOffset);
        SwerveDriverBrain.entryPositionLimelightkIxOffset = m_kIxOffset.getEntry();
        m_kIxOffset.withWidget(BuiltInWidgets.kTextView);

        m_kDxOffset = m_pidLayout.add("kD xOffset", SwerveDriverBrain.defaultkDxOffset);
        SwerveDriverBrain.entryPositionLimelightkDxOffset = m_kDxOffset.getEntry();
        m_kDxOffset.withWidget(BuiltInWidgets.kTextView);


        // PID Values for distance pid controller

        m_kPDistance = m_pidLayout.add("kP Distance", SwerveDriverBrain.defaultkPDistance);
        SwerveDriverBrain.entryPositionLimelightkPDistance = m_kPDistance.getEntry();
        m_kPDistance.withWidget(BuiltInWidgets.kTextView);

        m_kIDistance = m_pidLayout.add("kI Distance", SwerveDriverBrain.defaultkIDistance);
        SwerveDriverBrain.entryPositionLimelightkIDistance = m_kIDistance.getEntry();
        m_kIDistance.withWidget(BuiltInWidgets.kTextView);

        m_kDDistance = m_pidLayout.add("kD Distance", SwerveDriverBrain.defaultkDDistance);
        SwerveDriverBrain.entryPositionLimelightkDDistance = m_kDDistance.getEntry();
        m_kDDistance.withWidget(BuiltInWidgets.kTextView);

        // Limelight Values
        LimelightBrain.xOffsetEntry = m_limelightLayout.add("X Offset", LimelightBrain.xOffsetEntryDefault).getEntry();
        LimelightBrain.yOffsetEntry = m_limelightLayout.add("Y Offset", LimelightBrain.yOffsetEntryDefault).getEntry();
        LimelightBrain.distanceEntry = m_limelightLayout.add("Distance", LimelightBrain.distanceEntryDefault).getEntry();
        LimelightBrain.validTargetEntry = m_limelightLayout.add("Valid Target", LimelightBrain.validTargetEntryDefault).getEntry();
        //m_yOffset = m_limelightLayout.add("Y Offset", 0);
        //LimelightBrain.yOffsetEntry = m_yOffset.getEntry();
        // m_distance = m_limelightLayout.add("Distance", 0);
        // LimelightBrain.distanceEntry = m_distance.getEntry();
        // m_validTarget = m_limelightLayout.add("Valid Target", 0);
        // LimelightBrain.validTargetEntry = m_validTarget.getEntry();

    }

    // Create all other Widgets
    public void initialize() {
        m_positionLimelight = m_pidLayout.add("PositionLimelight", BotCommands.positionLimelight);
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
        LimelightBrain.xOffsetEntry.setDouble(Limelight.getXOffset());
        LimelightBrain.yOffsetEntry.setDouble(Limelight.getYOffset());
        LimelightBrain.distanceEntry.setDouble(Limelight.calculateDistanceToTarget());
        LimelightBrain.validTargetEntry.setBoolean(Limelight.getDetectionState());
    }

}
