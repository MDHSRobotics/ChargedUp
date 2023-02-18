package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.*;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

public class Forklift extends SubsystemBase {

    public static final double MIN_MOTOR_POWER = 0.1;

    public Forklift() {
        Logger.setup("Constructing Subsystem: Forklift...");

        sparkMaxForkliftVerticalTwo.restoreFactoryDefaults();
        sparkMaxForkliftVertical.restoreFactoryDefaults();
        sparkMaxForkliftClamp.restoreFactoryDefaults();
        talonFxForkliftExtender.configFactoryDefault();
        talonFxForkliftExtenderTwo.configFactoryDefault();
        
        sparkMaxForkliftVerticalTwo.setSmartCurrentLimit(15);
        sparkMaxForkliftVertical.setSmartCurrentLimit(15);
        sparkMaxForkliftClamp.setSmartCurrentLimit(15);
        talonFxForkliftExtender.setCurrentLimit(15);
        talonFxForkliftExtenderTwo.setCurrentLimit(15);

        talonFxForkliftExtenderTwo.follow(talonFxForkliftExtender);
        sparkMaxForkliftVerticalTwo.follow(sparkMaxForkliftVertical);
    } 

    //extend arm
    public void moveArmExtender(double power) {
        if(Math.abs(power) < MIN_MOTOR_POWER){
            power = 0.0;
        }
        //Logger.debug("Extender Power: " + power);
        talonFxForkliftExtender.set(power);
    }

    // move arm vertical
    public void moveArmElevator(double power) {
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
    public void moveClampPneumatic(boolean state) {
        Logger.debug("Toggling Piston to: " + state);
        clawSolenoid.set(state);
    }

    /** Grabs the hatch. */
    public CommandBase openClampCommand() {
        // implicitly require `this`
        return this.runOnce(() -> moveClampPneumatic(true));
    }

    public CommandBase closeClampCommand() {
        // implicitly require `this`
        return this.runOnce(() -> moveClampPneumatic(false));
    }
}