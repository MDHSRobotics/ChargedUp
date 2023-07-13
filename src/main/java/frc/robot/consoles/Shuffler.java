package frc.robot.consoles;

import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.shuffleboard.*;
import java.util.Map;
import frc.robot.sensors.Limelight;

import frc.robot.BotCommands;
import frc.robot.commands.auto.*;
import frc.robot.BotSubsystems;

import frc.robot.BotSensors;
import edu.wpi.first.networktables.GenericEntry;

import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.cscore.HttpCamera.HttpCameraKind;

// Class that wraps all of the interaction with the Shuffleboard

// All decisions about content and layout of the Shuffleboard are consolidated in this file
// to make it easier to change things rather than having to look throughout all of the
// classes for subsystems, commands, etc.

// The Shuffler class knows about the subsystems, commands, etc. but generally not vice versa.
public class Shuffler {

    public static VideoSource limelightCamera = new HttpCamera("limelight-mdrobot", "http://10.41.41.11:5801/", HttpCameraKind.kMJPGStreamer);

    // Tabs
    public static ShuffleboardTab m_driveTab;
    public static ShuffleboardTab m_forkliftTab;
    public static ShuffleboardTab m_autonomousTab;
    public static ShuffleboardTab m_sensorTab;
    public static ShuffleboardTab m_mainTab;
    public static ShuffleboardTab m_limelightTab;

    //Layouts
    private ShuffleboardLayout m_forklftCommandLayout;
    private ShuffleboardLayout m_gyroLayout;
    private ShuffleboardLayout m_autoCommandLayout;
    private ShuffleboardLayout m_autoIndividualCommandLayout;
    private ShuffleboardLayout m_limelightLayout;
    private ShuffleboardLayout m_limelightCommandsLayout;

    //Entries
    private GenericEntry entryRoll;
    private GenericEntry entryPitch;
    private GenericEntry entryYaw;

    private GenericEntry entryXAccel;
    private GenericEntry entryYAccel;
    private GenericEntry entryZAccel;

    private GenericEntry entryFieldOriented;

    private GenericEntry entryX;
    private GenericEntry entryY;
    private GenericEntry entryZ;
    private GenericEntry targetDetectedEntry;
    private GenericEntry entryMagX;

    //Initialize Tabs and Some Layouts
    public Shuffler() {
        ShuffleLogger.logTrivial("Constructing Shuffler...");

        //Forklift Tab
        m_forkliftTab = Shuffleboard.getTab("Forklift");
        m_forklftCommandLayout = constructLayout(m_forkliftTab, "Commands", 0, 0, 4, 3, 1, 2, "LEFT");
        
        m_driveTab = Shuffleboard.getTab("Drive");

        //Autonomous Tab
        m_autonomousTab = Shuffleboard.getTab("Autonomous");
        m_autoCommandLayout = Shuffler.constructLayout(m_autonomousTab, "Auto Commands", 0, 0, 4, 5, 1, 5, "LEFT");
        m_autoIndividualCommandLayout = Shuffler.constructLayout(m_autonomousTab, "Individual Auto Commands", 4, 0, 4, 4, 1, 2, "LEFT");

        //Sensor Tab
        m_sensorTab = Shuffleboard.getTab("Sensors");
        m_gyroLayout = Shuffler.constructLayout(m_sensorTab, "Gyroscope", 0, 0, 3, 6, 1, 3, "LEFT");
        entryRoll = m_gyroLayout
            .add("Roll", 0.0)
            .withWidget(BuiltInWidgets.kDial)
            .withProperties(Map.of("min", -180.0, "max", 180.0))
            .getEntry();
        entryPitch = m_gyroLayout
            .add("Pitch", 0.0)
            .withWidget(BuiltInWidgets.kDial)
            .withProperties(Map.of("min", -180.0, "max", 180.0))
            .getEntry();
        entryYaw = m_gyroLayout
            .add("Yaw", 0.0)
            .withWidget(BuiltInWidgets.kDial)
            .withProperties(Map.of("min", -180.0, "max", 180.0))
            .getEntry();
        entryXAccel = m_gyroLayout
            .add("X Accel", 0.0)
            .getEntry();
        entryYAccel = m_gyroLayout
            .add("Y Accel", 0.0)
            .getEntry();
        entryZAccel = m_gyroLayout
            .add("Z Accel", 0.0)
            .getEntry();
        entryMagX = m_gyroLayout
            .add("Mag X", 0.0)
            .getEntry();
        
        //Limelight Tab
        m_limelightTab = Shuffleboard.getTab("Limelight");
        m_limelightLayout = Shuffler.constructLayout(m_limelightTab, "Limelight Info", 0, 0, 3, 3, 1, 3, "LEFT");
        m_limelightCommandsLayout = Shuffler.constructLayout(m_limelightTab, "Limelight Commands", 5, 0, 3, 3, 1, 3, "LEFT");
        entryX = m_limelightLayout
            .add("X", 0.0)
            .getEntry();
        entryY = m_limelightLayout
            .add("Y", 0.0)
            .getEntry();
        entryZ = m_limelightLayout
            .add("Z", 0.0)
            .getEntry();
        
        targetDetectedEntry = m_limelightTab.add("Target Detected", false)
            .withPosition(3, 0)
            .getEntry();
        
        //Main Tab
        m_mainTab = Shuffleboard.getTab("Main");

        entryFieldOriented = m_mainTab.add("Field Oriented", false)
            .withPosition(8, 0)
            .getEntry();

        m_mainTab.add("Camera Feed", limelightCamera)
            .withPosition(0, 0)
            .withSize(8, 8)
            .withWidget(BuiltInWidgets.kCameraStream);
    }

    public void initialize() {
        ShuffleLogger.logTrivial("Initializing Shuffler...");

        
        m_forklftCommandLayout.add("Open Clamp", BotCommands.openClamp);
        m_forklftCommandLayout.add("Close Clamp", BotCommands.closeClamp);
        m_forklftCommandLayout.add("Reset Encoders", BotCommands.resetEncoders);

        m_autoCommandLayout.add("Place Cube Inner", BotCommands.placeCubeInner);
        m_autoCommandLayout.add("Place Cube Left", BotCommands.placeCubeLeft);
        m_autoCommandLayout.add("Place Cube Right", BotCommands.placeCubeRight);
        m_autoCommandLayout.add("Eject Cube Inner", BotCommands.ejectCubeInner);
        m_autoCommandLayout.add("Eject Cube Left", BotCommands.ejectCubeLeft);
        m_autoCommandLayout.add("Eject Cube Right", BotCommands.ejectCubeRight);
        m_autoCommandLayout.add("Default", BotCommands.defaultAutoCommand);

        m_autoIndividualCommandLayout.add("Balance Charge Station", new BalanceChargeStation(BotSubsystems.swerveDriver, true));
        m_autoIndividualCommandLayout.add("Eject Cube", new EjectCube(BotSubsystems.intake, 1));
        m_autoIndividualCommandLayout.add("Place Cube", new PlaceCube());

        m_limelightCommandsLayout.add("Enter Zone", BotCommands.enterZone);
        m_limelightCommandsLayout.add("Align Gyro", BotCommands.alignGyro);
        m_limelightCommandsLayout.add("Auto Drop Off", BotCommands.autoDropOff);
        m_limelightCommandsLayout.add("Align Limelight", BotCommands.alignLimelight);

        m_sensorTab.add("Calibrate Gyro", BotCommands.calibrateGyro);
    }

    public void update(){
        entryRoll.setDouble(BotSensors.gyro.getPitch());
        entryPitch.setDouble(BotSensors.gyro.getPitch());
        entryYaw.setDouble(BotSensors.gyro.getYaw());

        entryXAccel.setDouble(BotSensors.gyro.getXAcceleration());
        entryYAccel.setDouble(BotSensors.gyro.getYAcceleration());
        entryZAccel.setDouble(BotSensors.gyro.getZAcceleration());

        entryFieldOriented.setBoolean(BotSubsystems.swerveDriver.fieldRelative);

        entryX.setDouble(Limelight.getXOffset());
        entryY.setDouble(Limelight.getYOffset());
        entryZ.setDouble(Limelight.calculateDistanceToTarget());
        targetDetectedEntry.setBoolean(Limelight.getDetectionState());

        entryMagX.setFloat(BotSensors.gyro.getMagX());
    }

    //Method for easier layout construction
    public static ShuffleboardLayout constructLayout(ShuffleboardTab tab, String title, int posX, int posY, int width, int height, int columns, int rows, String labelPosition){
        ShuffleboardLayout layout = tab.getLayout(title, BuiltInLayouts.kList);
        layout = tab.getLayout(title, BuiltInLayouts.kList);
        layout.withPosition(posX, posY);
        layout.withSize(width, height);
        layout.withProperties(Map.of("Number of columns", columns));
        layout.withProperties(Map.of("Number of rows", rows));
        layout.withProperties(Map.of("Label position", labelPosition));
        return layout;
    }
}

