package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.brains.ForkliftBrain;
import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.*;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.revrobotics.CANSparkMax.IdleMode;

public class Forklift extends SubsystemBase {

    public static final double MIN_MOTOR_POWER = 0.1;

    public Forklift() {
        Logger.setup("Constructing Subsystem: Forklift...");

        sparkMaxForkliftElevatorTwo.restoreFactoryDefaults();
        sparkMaxForkliftElevator.restoreFactoryDefaults();
        sparkMaxForkliftExtender.restoreFactoryDefaults();
        
        sparkMaxForkliftElevatorTwo.setSmartCurrentLimit(15);
        sparkMaxForkliftElevator.setSmartCurrentLimit(15);
        sparkMaxForkliftExtender.setSmartCurrentLimit(15);

        sparkMaxForkliftElevatorTwo.setIdleMode(IdleMode.kBrake);
        sparkMaxForkliftElevator.setIdleMode(IdleMode.kBrake);
        sparkMaxForkliftExtender.setIdleMode(IdleMode.kBrake);

        sparkMaxForkliftElevatorTwo.follow(sparkMaxForkliftElevator);
    } 

    @Override
    public void periodic() {
        ForkliftBrain.setElevatorEncoder(sparkMaxForkliftElevator.getEncoder().getPosition());
        ForkliftBrain.setExtenderEncoder(sparkMaxForkliftExtender.getEncoder().getPosition());
    }

    //extend arm
    public void moveArmExtender(double power) {
        if(Math.abs(power) < MIN_MOTOR_POWER){
            power = 0.0;
        }
        //Logger.debug("Extender Power: " + power);
        sparkMaxForkliftExtender.set(-power);
    }

    // move arm vertical
    public void moveArmElevator(double power) {
        if(Math.abs(power) < MIN_MOTOR_POWER){
            power = 0.0;
        }
        //Logger.debug("Vertical Power: " + power);
        sparkMaxForkliftElevator.set(power);
    }

    // move claw using pneumatic pistons
    public void moveClampPneumatic(boolean state) {
        Logger.debug("Toggling Piston to: " + state);
        clawSolenoid.set(state);
    }

    //Open the clamp
    public CommandBase openClampCommand() {
        // implicitly require `this`
        Logger.info("Opening Clamp");
        return this.runOnce(() -> moveClampPneumatic(true));
    }

    //Close the clamp
    public CommandBase closeClampCommand() {
        // implicitly require `this`
        Logger.info("Closing Clamp");
        return this.runOnce(() -> moveClampPneumatic(false));
    }

    
}