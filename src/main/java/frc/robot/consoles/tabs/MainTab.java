package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;
import frc.robot.BotSubsystems;
import frc.robot.brains.*;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;

import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.cscore.HttpCamera.HttpCameraKind;

//A main tab, which will be shown during competition
public class MainTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;

    private ShuffleboardLayout m_forkliftEncodersLayout;
    private ShuffleboardLayout m_objectsInRangeLayout;

    //Widgets
    private SimpleWidget m_widgetExtenderEncoder;
    private SimpleWidget m_widgetElevatorEncoder;

    private SimpleWidget m_cubeInRangeWidget;
    private SimpleWidget m_coneInRangeWidget;

    private SimpleWidget m_fieldOrientedWidget;

    private ComplexWidget m_cameraFeed;

    public static VideoSource limelightCamera = new HttpCamera("limelight-mdrobot", "http://10.41.41.11:5801/", HttpCameraKind.kMJPGStreamer);

    // Constructor
    public MainTab() {
        ShuffleLogger.logTrivial("Constructing MainTab...");

        m_tab = Shuffleboard.getTab("Main");

        m_forkliftEncodersLayout = Shuffler.constructLayout(m_tab, "Forklift Encoders", 8, 0, 4, 2, 1, 2, "LEFT");
        m_objectsInRangeLayout = Shuffler.constructLayout(m_tab, "Objects In Range", 8, 2, 4, 4, 2, 1, "LEFT");

    }

    // Create Brain Widgets
    public void preInitialize() {

        m_cameraFeed = m_tab.add("Camera Feed", limelightCamera);
        m_cameraFeed.withPosition(0, 0);
        m_cameraFeed.withSize(8, 8);
        m_cameraFeed.withWidget(BuiltInWidgets.kCameraStream);

        m_widgetExtenderEncoder = m_forkliftEncodersLayout.add("Extender Encoder", ForkliftBrain.defaultExtenderEncoder);
        //ForkliftBrain.entryExtenderEncoder = m_widgetExtenderEncoder.getEntry();

        m_widgetElevatorEncoder = m_forkliftEncodersLayout.add("Elevator Encoder", ForkliftBrain.defaultElevatorEncoder);
        //ForkliftBrain.entryElevatorEncoder = m_widgetElevatorEncoder.getEntry();

    }

    // Create all other Widgets
    public void initialize() {
        //Objects in range
        m_cubeInRangeWidget = m_objectsInRangeLayout.add("Cube Detected", false);
        SensorBrain.entryObjectInRange = m_cubeInRangeWidget.getEntry(); 

        m_coneInRangeWidget = m_objectsInRangeLayout.add("Cone Detected", false);
        SensorBrain.entryColor = m_coneInRangeWidget.getEntry();

        m_fieldOrientedWidget = m_tab.add("Field Oriented", false);
        SwerveDriverBrain.entryFieldOrientedEntry = m_fieldOrientedWidget.getEntry();
        m_fieldOrientedWidget.withPosition(12, 0);
    }

    // Configure all Widgets
    public void configure() {
    }

    // This will be called in the robotPeriodic
    public void update() {
        SwerveDriverBrain.setFieldOriented(BotSubsystems.swerveDriver.fieldRelative);
    }
}

