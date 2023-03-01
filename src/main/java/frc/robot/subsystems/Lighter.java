package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.brains.LighterBrain;
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

        int byteCount = (int) LighterBrain.getNumLights();
        byte[] pixels = new byte[byteCount];
        byte[] dataStore = new byte[byteCount];

        for (int i = 0; i < m_numLights; i++) {
            int currentBit = i * 3; 
            pixels[0 + currentBit] =  (byte) r;
            pixels[1 + currentBit] =  (byte) g;
            pixels[2 + currentBit] =  (byte) b;
        }

        int byteNumWrite = m_spiPort.write(pixels, byteCount);
        Logger.info("Byte Array: " + pixels);
        Logger.info("Number of Bytes Written: " + byteNumWrite);
        Logger.info("Bit 1: " + pixels[0]);
        Logger.info("Bit 2: " + pixels[1]);
        Logger.info("Bit 3: " + pixels[2]);

        int byteNumRead = m_spiPort.read(false, dataStore, byteCount);
        Logger.info("Byte Array: " + dataStore);
        Logger.info("Number of Bytes Written: " + byteNumRead);
        Logger.info("Bit 1: " + dataStore[0]);
        Logger.info("Bit 2: " + dataStore[1]);
        Logger.info("Bit 3: " + dataStore[2]);

    }

    public CommandBase setDefaultColorCommand() {
        Logger.info("Setting Default Color");

        int redValue = (int) LighterBrain.getRedValue();
        int greenValue = (int) LighterBrain.getGreenValue();
        int blueValue = (int) LighterBrain.getBlueValue();

        return this.runOnce(() -> setColor(redValue, greenValue, blueValue));
    }

}
  