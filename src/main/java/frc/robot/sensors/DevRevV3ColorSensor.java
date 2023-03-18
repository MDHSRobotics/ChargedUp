package frc.robot.sensors;

import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

import edu.wpi.first.wpilibj.util.Color;

import edu.wpi.first.wpilibj.I2C;
import static frc.robot.RobotManager.isReal;
import frc.robot.consoles.Logger;

public class DevRevV3ColorSensor extends ColorSensorV3  {

    private final ColorMatch m_colorMatcher = new ColorMatch();

    private final Color kBlueTarget = new Color(0.143, 0.427, 0.429);
    private final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
    private final Color kRedTarget = new Color(0.561, 0.232, 0.114);
    private final Color kYellowTarget = new Color(0.361, 0.524, 0.113);

    public DevRevV3ColorSensor() {
        super(I2C.Port.kOnboard);
    }

    public void initialize() {

        if (isReal) {
            Logger.setup("Initializing color sensor ...");
            boolean colorSensorIsConnected = isConnected();
            if (!colorSensorIsConnected) {
                Logger.problem("Color sensor not connected!");
            }
        } else {
            Logger.setup("Skipping initializion of color sensor in Simulation mode...");
        }

        m_colorMatcher.addColorMatch(kBlueTarget);
        m_colorMatcher.addColorMatch(kGreenTarget);
        m_colorMatcher.addColorMatch(kRedTarget);
        m_colorMatcher.addColorMatch(kYellowTarget);   
        
    }

    public String getMatchedColor(){
        Color detectedColor = getColor();

        String colorString;
        
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

        if (match.color == kBlueTarget) {
        colorString = "Blue";
        } else if (match.color == kRedTarget) {
        colorString = "Red";
        } else if (match.color == kGreenTarget) {
        colorString = "Green";
        } else if (match.color == kYellowTarget) {
        colorString = "Yellow";
        } else {
        colorString = "Unknown";
        }
        return "";
    }

}