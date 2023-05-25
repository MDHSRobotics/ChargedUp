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

    private final Color kCubeTarget = new Color(85/255, 36/255, 183/255);
    private final Color kConeTarget = new Color(229/255, 214/255, 14/255);

    public DevRevV3ColorSensor() {
        super(I2C.Port.kMXP);
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

        m_colorMatcher.addColorMatch(kCubeTarget);
        m_colorMatcher.addColorMatch(kConeTarget); 
        
    }

    public String getMatchedColor(){

        String colorString;
        Color color = getHexColor();

        if (color == kCubeTarget) {
            colorString = "Cube";
        } else if (color == kConeTarget) {
            colorString = "Cone";
        } else {
            colorString = "Unknown";
        }
        return colorString;
    }

    public Color getHexColor(){
        Color detectedColor = getColor();
        
        ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);
        return match.color;
    }

}