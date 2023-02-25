package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.consoles.Logger;

import edu.wpi.first.wpilibj.SPI;

public class Lighter extends SubsystemBase {

    private int m_numLights;
    private SPI m_spiPort;    

    public Lighter(int numLights) {
        Logger.setup("Constructing Subsystem: Lighter...");

        m_numLights = numLights;
        m_spiPort = new SPI(SPI.Port.kOnboardCS0);

    }

    public void setColor(int r, int g, int b) {

        int byteCount = m_numLights * 3;
        byte[] pixels = new byte[byteCount];

        for (int i = 0; i < m_numLights; i++) {
            int currentBit = i * 3; 
            pixels[0 + currentBit] =  (byte) r;
            pixels[1 + currentBit] =  (byte) g;
            pixels[2 + currentBit] =  (byte) b;
        }

        m_spiPort.write(pixels, byteCount);
    }

    public CommandBase setDefaultColorCommand() {
        Logger.info("Setting Default Color");
        return this.runOnce(() -> setColor(0x77, 0x77, 0x77));
    }

}
 