package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.*;

public class Forklift extends SubsystemBase {

    public Forklift() {
        Logger.setup("Constructing Subsystem: Forklift...");

        sparkMaxForkliftClamp.restoreFactoryDefaults();
        sparkMaxForkliftExtender.restoreFactoryDefaults();
        sparkMaxForkliftVertical.restoreFactoryDefaults();

        sparkMaxForkliftClamp.setSmartCurrentLimit(15);
        sparkMaxForkliftExtender.setSmartCurrentLimit(15);
        sparkMaxForkliftVertical.setSmartCurrentLimit(15);
    } 

    //extend arm
    public void moveArmExtender(double power) {
        sparkMaxForkliftExtender.set(power);
    }

    // move arm vertical
    public void moveArmVertical(double power) {
        sparkMaxForkliftVertical.set(power);
    }

    // move claw
    public void moveClamp(double power) {
        sparkMaxForkliftClamp.set(power);
    }

}