package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.brains.ForkliftBrain;
import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.*;

import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

public class Forklift extends SubsystemBase {

    public static final double MIN_MOTOR_POWER = 0.1;

    public static final double ELEVATOR_MIN = 7;
    public static final double ELEVATOR_MAX = -16;
    public static final double EXTENDER_MIN = -15;
    public static final double EXTENDER_MAX = 120;

    public Forklift() {
        Logger.setup("Constructing Subsystem: Forklift...");

        sparkMaxForkliftElevatorTwo.restoreFactoryDefaults();
        sparkMaxForkliftElevator.restoreFactoryDefaults();
        sparkMaxForkliftExtender.restoreFactoryDefaults();
        sparkMaxClawLift.restoreFactoryDefaults();
        
        sparkMaxForkliftElevatorTwo.setSmartCurrentLimit(15);
        sparkMaxForkliftElevator.setSmartCurrentLimit(15);
        sparkMaxForkliftExtender.setSmartCurrentLimit(15);
        sparkMaxClawLift.setSmartCurrentLimit(15);

        sparkMaxForkliftElevatorTwo.setIdleMode(IdleMode.kBrake);
        sparkMaxForkliftElevator.setIdleMode(IdleMode.kBrake);
        sparkMaxForkliftExtender.setIdleMode(IdleMode.kBrake);
        sparkMaxClawLift.setIdleMode(IdleMode.kBrake);

        sparkMaxForkliftElevatorTwo.follow(sparkMaxForkliftElevator);
    } 

    @Override
    public void periodic() {
        ForkliftBrain.setElevatorEncoder(sparkMaxForkliftElevator.getEncoder().getPosition());
        ForkliftBrain.setExtenderEncoder(sparkMaxForkliftExtender.getEncoder().getPosition());
    }

    //extend arm
    public void moveArmExtender(double power) {
        //Apply Deadband
        double extenderPower = Math.abs(power) >= MIN_MOTOR_POWER ? power : 0;

        //Apply Motor Softstop
        double extenderPower2 = (sparkMaxForkliftExtender.getEncoder().getPosition() > EXTENDER_MIN && extenderPower < 0) || 
                                (sparkMaxForkliftExtender.getEncoder().getPosition() < EXTENDER_MAX && extenderPower > 0) ? extenderPower : 0;

        //Logger.debug("Extender Power: " + power);
        sparkMaxForkliftExtender.set(-extenderPower);
    }

    // move arm vertical
    public void moveArmElevator(double power) {
        //Apply Deadband
        double elevatorPower = Math.abs(power) >= MIN_MOTOR_POWER ? power : 0;

        //Apply Motor Softstop
        double elevatorPower2 = (sparkMaxForkliftElevator.getEncoder().getPosition() < ELEVATOR_MIN && elevatorPower < 0) || 
                                (sparkMaxForkliftElevator.getEncoder().getPosition() > ELEVATOR_MAX && elevatorPower > 0) ? elevatorPower : 0;

        //Logger.debug("Vertical Power: " + power);
        sparkMaxForkliftElevator.set(elevatorPower);
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

    //Move the claw up and down
    public void moveClaw(double power){
        sparkMaxClawLift.set(power);
    }
    
}