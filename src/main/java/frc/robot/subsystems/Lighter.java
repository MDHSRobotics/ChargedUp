package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.consoles.Logger;
import edu.wpi.first.wpilibj.SPI;

public class Lighter extends SubsystemBase {

    public SPI spi;
    private Byte pixels[];

    int numLights;

    Lighter(int numLights) {
        Logger.setup("Constructing Subsystem: Lighter...");

        this.numLights = numLights;
        spi = new SPI(SPI.Port.kOnboardCS0);
        pixels = new Byte[numLights];
    }

}
