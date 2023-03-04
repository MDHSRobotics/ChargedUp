package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.brains.ForkliftBrain;
import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.*;

import com.revrobotics.CANSparkMax.IdleMode;

public class Forklift extends SubsystemBase {

    private static final double MIN_MOTOR_POWER = 0.1;

    private static double elevatorMin = 0.;
    private static double elevatorMax = -66.;
    private static double extenderMin = 0.;
    private static double extenderMax = -158.;

    private boolean m_isSoftStopEnabled;

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

        sparkMaxForkliftExtender.setInverted(true);

        sparkMaxForkliftElevatorTwo.follow(sparkMaxForkliftElevator);
    }

    @Override
    public void periodic() {
        ForkliftBrain.setElevatorEncoder(getElevatorEncoder());
        ForkliftBrain.setExtenderEncoder(getExtenderEncoder());

        ForkliftBrain.setSoftStopEnabled(m_isSoftStopEnabled);
    }

    //extend arm
    public void moveArmExtender(double power) {
        //Apply Deadband
        double extenderPower = Math.abs(power) >= MIN_MOTOR_POWER ? power : 0;

        //Apply Motor Softstop
        double extenderPower2 = extenderPower;
        if(m_isSoftStopEnabled){
            extenderPower2 = (Math.abs(getExtenderEncoder()) > extenderMin && extenderPower > 0) || 
                             (Math.abs(getExtenderEncoder()) < extenderMax && extenderPower < 0) ? extenderPower : 0;
        }

        //Logger.debug("Extender Power: " + power);
        sparkMaxForkliftExtender.set(extenderPower2);
    }

    // move arm vertical
    public void moveArmElevator(double power) {
        //Apply Deadband
        double elevatorPower = Math.abs(power) >= MIN_MOTOR_POWER ? power : 0;

        //Apply Motor Softstop
        double elevatorPower2 = elevatorPower;
        if(m_isSoftStopEnabled){
            elevatorPower2 = (Math.abs(getElevatorEncoder()) > elevatorMin && elevatorPower > 0) || 
                             (Math.abs(getElevatorEncoder()) < elevatorMax && elevatorPower < 0) ? elevatorPower : 0;
        }

        //Logger.debug("Vertical Power: " + power);
        sparkMaxForkliftElevator.set(elevatorPower2);
    }

    // move claw using pneumatic pistons
    public void moveClampPneumatic(boolean state) {
        Logger.info("Toggling Piston to: " + state);
        clawSolenoid.set(state);
    }

    //Move the claw up and down
    public void moveClaw(double power){
        sparkMaxClawLift.set(power);
    }

    //Reset the encoders
    private void resetMotorEncoders(){
        Logger.info("Resetting Motor Encoders");
        sparkMaxForkliftElevator.getEncoder().setPosition(0);
        sparkMaxForkliftExtender.getEncoder().setPosition(0);
    }

    //Stop all of the motors
    public void stopMotors(){
        sparkMaxForkliftElevator.stopMotor();
        sparkMaxForkliftElevatorTwo.stopMotor();
        sparkMaxForkliftExtender.stopMotor();
        sparkMaxClawLift.stopMotor();
    }

    //Get the motor encoders
    public double getElevatorEncoder(){
        return sparkMaxForkliftElevator.getEncoder().getPosition();
    }

    public double getExtenderEncoder(){
        return sparkMaxForkliftExtender.getEncoder().getPosition();
    }

    public void setSoftStops(double extender, double elevator){
        Logger.info("Setting SoftStops to: " + extender + " and " + elevator);
        elevatorMax = elevator;
        extenderMax = extender;
    }

    /* One Line Commands */

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

    public CommandBase enableSoftStop() {
        Logger.info("Enabling Soft Stop");
        return this.runOnce(() -> m_isSoftStopEnabled = true);
    }

    public CommandBase disableSoftStop() {
        Logger.info("Disabling Soft Stop");
        return this.runOnce(() -> m_isSoftStopEnabled = false);
    }

    public CommandBase resetEncoders() {
        Logger.info("Resetting Soft Stop");
        return this.runOnce(() -> resetMotorEncoders());
    }
    
}