package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.*;

public class Forklift extends SubsystemBase {
    
    private static boolean m_isPistonToggled = false;

    public static final double MIN_MOTOR_POWER = 0.1;

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
        if(Math.abs(power) < MIN_MOTOR_POWER){
            power = 0.0;
        }
        //Logger.debug("Extender Power: " + power);
        sparkMaxForkliftExtender.set(power);
    }

    // move arm vertical
    public void moveArmVertical(double power) {
        if(Math.abs(power) < MIN_MOTOR_POWER){
            power = 0.0;
        }
        //Logger.debug("Vertical Power: " + power);
        sparkMaxForkliftVertical.set(power);
    }

    public void stopForklift() {
        sparkMaxForkliftClamp.stopMotor();
    }

    // move claw using motor
    public void moveClampMotor(double power) {
        sparkMaxForkliftClamp.set(power);
    }

    // move claw using pneumatic pistons
    public void moveClampPneumatic() {
        m_isPistonToggled = !m_isPistonToggled;
        Logger.debug("Toggling Piston to: " + m_isPistonToggled);
        clawSolenoid.set(m_isPistonToggled);
    }

    /** Grabs the hatch. */
    public CommandBase toggleClampCommand() {
        // implicitly require `this`
        return this.runOnce(() -> moveClampPneumatic());
    }
}