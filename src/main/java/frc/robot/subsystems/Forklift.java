package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.BotSensors;
import frc.robot.brains.ForkliftBrain;
import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.*;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;

public class Forklift extends SubsystemBase {

    private static final double MIN_MOTOR_POWER = 0.1;
    private static final double MOTOR_POSITION_TOLERANCE = 3.0;

    private final double GREEN_BIAS = 100;

    private static double elevatorMin = 0.;
    private static double elevatorMax = -66.;
    private static double extenderMin = 0.;
    private static double extenderMax = -158.;

    private SparkMaxPIDController m_elevatorPIDController;
    private SparkMaxPIDController m_extenderPIDController;

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

        m_elevatorPIDController = sparkMaxForkliftElevator.getPIDController();
        m_elevatorPIDController.setP(0.03);
        m_elevatorPIDController.setI(0.0000001);
        m_elevatorPIDController.setD(0.0001);
        m_elevatorPIDController.setSmartMotionMaxVelocity(0.07, 0);

        m_extenderPIDController = sparkMaxForkliftExtender.getPIDController();
        m_extenderPIDController.setP(0.03);
        m_extenderPIDController.setI(0.0000001);
        m_extenderPIDController.setD(0.0001);
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
            extenderPower2 = (getExtenderEncoder() > extenderMin && extenderPower > 0) || 
                             (getExtenderEncoder() < extenderMax && extenderPower < 0) ? extenderPower : 0;
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
            elevatorPower2 = (getElevatorEncoder() > elevatorMin && elevatorPower > 0) || 
                             (getElevatorEncoder() < elevatorMax && elevatorPower < 0) ? elevatorPower : 0;
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
        return -(sparkMaxForkliftElevator.getEncoder().getPosition());
    }

    public double getExtenderEncoder(){
        return -(sparkMaxForkliftExtender.getEncoder().getPosition());
    }

    //set the softstops of the motors
    public void setSoftStops(double extender, double elevator){
        Logger.info("Setting SoftStops to: " + extender + " and " + elevator);
        elevatorMax = elevator;
        extenderMax = extender;
    }

    //Move the elevator to a set position, return if it is within the tolerance of the position
    public void moveElevatorToPosition(double position){
        m_elevatorPIDController.setReference(position, CANSparkMax.ControlType.kPosition);
    }

    //Move the extender to a set position, return if it is within the tolerance of the position
    public void moveExtenderToPosition(double position){
        m_extenderPIDController.setReference(position, CANSparkMax.ControlType.kPosition);
    }

    public boolean isElevatorAtPosition(double target){
        Logger.info(Math.abs(getElevatorEncoder() - target));
        return (Math.abs(getElevatorEncoder() - target) < MOTOR_POSITION_TOLERANCE);
    }

    public boolean isExtenderAtPosition(double target){
        return (Math.abs(getExtenderEncoder() - target) < MOTOR_POSITION_TOLERANCE);
    }

    //returns whether or not the cone/cube is in range
    public boolean isCubeInRange() {

        boolean isCubeInRange =  false;
        int distance = BotSensors.colorSensor.getProximity();
        int blue = BotSensors.colorSensor.getBlue();
        int red = BotSensors.colorSensor.getRed();
        int green = BotSensors.colorSensor.getGreen();

        if (blue > red && distance > 70) {
            isCubeInRange =  true;
        }
        
        /*if(BotSensors.colorSensor.getMatchedColor().equals("Cube")){
            isCubeInRange = true;
        }*/

        return isCubeInRange;
    }

    public boolean isConeInRange() {

        boolean isConeInRange =  false;
        int distance = BotSensors.colorSensor.getProximity();
        int blue = BotSensors.colorSensor.getBlue();
        int red = BotSensors.colorSensor.getRed();
        int green = BotSensors.colorSensor.getGreen();

        if (red > blue && green > blue && distance > 60) {
            isConeInRange =  true;
        }
        /*if(BotSensors.colorSensor.getMatchedColor().equals("Cone")){
            isConeInRange = true;
        }*/

        return isConeInRange;
    }

    public boolean isInRange(){
        int distance = BotSensors.colorSensor.getProximity();
        boolean isInRange = false;
        if(distance > 100){
            isInRange = true;
        }
        return isInRange;
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

    //Enable the SoftStop
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