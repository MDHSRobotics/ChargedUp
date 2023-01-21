
package frc.robot.consoles.tabs;

import edu.wpi.first.wpilibj.shuffleboard.*;

import frc.robot.brains.XboxBrain;
import frc.robot.consoles.ShuffleLogger;

import java.util.Map;

// The Shuffleboard Inputs tab.
public class InputsTab {

    // Tab & Layouts
    private ShuffleboardTab m_tab;
    private ShuffleboardLayout m_controllersLayout;

    private ShuffleboardLayout m_xboxLeftLayout;
    private ShuffleboardLayout m_xboxRightLayout;


    // Thumbstick Widget - Left
    private SimpleWidget m_yLeftDeadZoneWidget;
    private SimpleWidget m_xLeftDeadZoneWidget;
    private SimpleWidget m_yLeftSensitivityWidget;
    private SimpleWidget m_xLeftSensitivityWidget;

    // Thumbstick Widget - Right
    private SimpleWidget m_yRightDeadZoneWidget;
    private SimpleWidget m_xRightDeadZoneWidget;
    private SimpleWidget m_yRightSensitivityWidget;
    private SimpleWidget m_xRightSensitivityWidget;

    // Constructor
    public InputsTab() {
        ShuffleLogger.logTrivial("Constructing InputsTab...");

        m_tab = Shuffleboard.getTab("Inputs");

        m_controllersLayout = m_tab.getLayout("Controllers", BuiltInLayouts.kGrid);
        m_controllersLayout.withPosition(0, 0);
        m_controllersLayout.withSize(3, 1);
        m_controllersLayout.withProperties(Map.of("Number of columns", 1));
        m_controllersLayout.withProperties(Map.of("Number of rows", 2));
        m_controllersLayout.withProperties(Map.of("Label position", "LEFT"));


        m_xboxLeftLayout = m_tab.getLayout("XBOX Left Thumbstick", BuiltInLayouts.kGrid);
        m_xboxLeftLayout.withPosition(0, 1);
        m_xboxLeftLayout.withSize(3, 1);
        m_xboxLeftLayout.withProperties(Map.of("Number of columns", 2));
        m_xboxLeftLayout.withProperties(Map.of("Number of rows", 2));
        m_xboxLeftLayout.withProperties(Map.of("Label position", "LEFT"));

        m_xboxRightLayout = m_tab.getLayout("XBOX Right Thumbstick", BuiltInLayouts.kGrid);
        m_xboxRightLayout.withPosition(0, 2);
        m_xboxRightLayout.withSize(3, 1);
        m_xboxRightLayout.withProperties(Map.of("Number of columns", 2));
        m_xboxRightLayout.withProperties(Map.of("Number of rows", 2));
        m_xboxRightLayout.withProperties(Map.of("Label position", "LEFT"));
    }

    // Create Brain Widgets
    public void preInitialize() {

        // Thumbstick - Left
        m_yLeftDeadZoneWidget = m_xboxLeftLayout.add("Y Left Dead Zone", XboxBrain.yLeftDeadZoneDefault);
        XboxBrain.yLeftDeadZoneEntry = m_yLeftDeadZoneWidget.getEntry();
        m_yLeftDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_xLeftDeadZoneWidget = m_xboxLeftLayout.add("X Left Dead Zone", XboxBrain.xLeftDeadZoneDefault);
        XboxBrain.xLeftDeadZoneEntry = m_xLeftDeadZoneWidget.getEntry();
        m_xLeftDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_yLeftSensitivityWidget = m_xboxLeftLayout.add("Y Left Sensitivity", XboxBrain.yLeftSensitivityDefault);
        XboxBrain.yLeftSensitivityEntry = m_yLeftSensitivityWidget.getEntry();
        m_yLeftSensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        m_xLeftSensitivityWidget = m_xboxLeftLayout.add("X Left Sensitivity", XboxBrain.xLeftSensitivityDefault);
        XboxBrain.xLeftSensitivityEntry = m_xLeftSensitivityWidget.getEntry();
        m_xLeftSensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        // Thumbstick - Right
        m_yRightDeadZoneWidget = m_xboxRightLayout.add("Y Right Dead Zone", XboxBrain.yRightDeadZoneDefault);
        XboxBrain.yRightDeadZoneEntry = m_yRightDeadZoneWidget.getEntry();
        m_yRightDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_xRightDeadZoneWidget = m_xboxRightLayout.add("X Right Dead Zone", XboxBrain.xRightDeadZoneDefault);
        XboxBrain.xRightDeadZoneEntry = m_xRightDeadZoneWidget.getEntry();
        m_xRightDeadZoneWidget.withWidget(BuiltInWidgets.kTextView);

        m_yRightSensitivityWidget = m_xboxRightLayout.add("Y Right Sensitivity", XboxBrain.yRightSensitivityDefault);
        XboxBrain.yRightSensitivityEntry = m_yRightSensitivityWidget.getEntry();
        m_yRightSensitivityWidget.withWidget(BuiltInWidgets.kTextView);

        m_xRightSensitivityWidget = m_xboxRightLayout.add("X Right Sensitivity", XboxBrain.xRightSensitivityDefault);
        XboxBrain.xRightSensitivityEntry = m_xRightSensitivityWidget.getEntry();
        m_xRightSensitivityWidget.withWidget(BuiltInWidgets.kTextView);
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
