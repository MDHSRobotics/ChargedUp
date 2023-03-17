package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.brains.ForkliftBrain;
import frc.robot.consoles.ShuffleLogger;
import frc.robot.consoles.Shuffler;
import frc.robot.BotCommands;

import edu.wpi.first.cscore.HttpCamera;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.cscore.HttpCamera.HttpCameraKind;

//A main tab, which will be shown during competition
public class MainTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;

    private ShuffleboardLayout m_forkliftEncodersLayout;

    //Widgets
    private SimpleWidget m_widgetExtenderEncoder;
    private SimpleWidget m_widgetElevatorEncoder;

    private ComplexWidget m_cameraFeed;

    public static VideoSource limelightCamera = new HttpCamera("limelight-mdrobot", "http://10.41.41.11:5801/", HttpCameraKind.kMJPGStreamer);

    // Constructor
    public MainTab() {
        ShuffleLogger.logTrivial("Constructing MainTab...");

        m_tab = Shuffleboard.getTab("Main");

        m_forkliftEncodersLayout = Shuffler.constructLayout(m_tab, "Forklift Encoders", 8, 0, 4, 2, 1, 2, "LEFT");

    }

    // Create Brain Widgets
    public void preInitialize() {

        m_widgetExtenderEncoder = m_forkliftEncodersLayout.add("Extender Encoder", ForkliftBrain.defaultExtenderEncoder);
        ForkliftBrain.entryExtenderEncoder = m_widgetExtenderEncoder.getEntry();

        m_widgetElevatorEncoder = m_forkliftEncodersLayout.add("Elevator Encoder", ForkliftBrain.defaultElevatorEncoder);
        ForkliftBrain.entryElevatorEncoder = m_widgetElevatorEncoder.getEntry();

        m_cameraFeed = m_tab.add("Camera Feed", limelightCamera);
        m_cameraFeed.withPosition(0, 0);
        m_cameraFeed.withSize(8, 8);
        m_cameraFeed.withWidget(BuiltInWidgets.kCameraStream);
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

