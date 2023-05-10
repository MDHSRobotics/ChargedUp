package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANSparkMax;

import frc.robot.devices.DevSparkMax;
import frc.robot.devices.DevTalonFX;
import frc.robot.devices.DevTalonSRX;
import edu.wpi.first.wpilibj.Solenoid;
import com.revrobotics.SparkMaxPIDController;

import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.PneumaticsModuleType;

import java.util.Map;
import java.util.HashMap;

import com.revrobotics.CANSparkMax.IdleMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;

public class GenericSubsystem extends SubsystemBase {

    private static Map<String, SparkMaxPIDController> m_sparkMaxPIDMap = new HashMap<>();
    private static Map<String, DevSparkMax> m_sparkMaxMap = new HashMap<>();
    private static Map<String, DevTalonSRX> m_talonSrxMap = new HashMap<>();
    private static Map<String, DevTalonFX> m_talonFxMap = new HashMap<>();
    private static Map<String, Solenoid> m_solenoidMap = new HashMap<>();

    /**
     * A GenericSubsystem class for initializing and controlling various types of
     * devices.
     * This constructor takes in a Map where the key is a String representing the
     * name of the device and the value is an Integer representing the device ID.
     * Depending on the prefix of the device name, this constructor will initialize
     * the device as the appropriate type and add it to the corresponding map.
     * The types of devices supported by this constructor are DevSparkMax,
     * DevTalonFX, DevTalonSRX and Solenoid.
     * 
     * @param devices A Map where the key is a String representing the name of the
     *                device and the value is an Integer representing the device ID.
     * @author TJ Vasquez
     * @contact manof300.io@gmail.com
     * @date 3/4/2023
     */
    public GenericSubsystem(Map<String, Integer> devices) {

        for (Map.Entry<String, Integer> entry : devices.entrySet()) {

            String deviceName = entry.getKey();
            int deviceID = entry.getValue();

            // Initialize CANSparkMax Device
            if (deviceName.substring(0, 8).equals("sparkMax")) {

                String sparkMaxName = deviceName.substring(8);

                DevSparkMax sparkMax = new DevSparkMax(deviceName, deviceID, MotorType.kBrushless);
                SparkMaxPIDController sparkMaxPID = sparkMax.getPIDController();

                sparkMax.restoreFactoryDefaults();
                sparkMax.setSmartCurrentLimit(15);

                m_sparkMaxMap.put(sparkMaxName, sparkMax);
                m_sparkMaxPIDMap.put(sparkMaxName, sparkMaxPID);

                // Initialize talonFx Device
            } else if (deviceName.substring(0, 7).equals("talonFx")) {

                String talonFxName = deviceName.substring(7);

                DevTalonFX talonFX = new DevTalonFX(deviceName, deviceID);

                talonFX.configFactoryDefault();
                talonFX.setCurrentLimit(15);

                m_talonFxMap.put(talonFxName, talonFX);

                // Initialize Solenoid Devices
            } else if (deviceName.substring(0, 8).equals("talonSrx")) {

                String talonSrxName = deviceName.substring(8);

                DevTalonSRX talonSrx = new DevTalonSRX(deviceName, deviceID);

                talonSrx.configFactoryDefault();

                m_talonSrxMap.put(talonSrxName, talonSrx);
            
            } else if (deviceName.substring(0, 8).equals("solenoid")) {

                String solenoidName = deviceName.substring(8);

                Solenoid Solenoid = new Solenoid(PneumaticsModuleType.CTREPCM, deviceID);

                m_solenoidMap.put(solenoidName, Solenoid);
            }
        }
    }

    // Motor Methods

    /**
     * Sets the brake mode for a motor controlled by a CANSparkMax.
     * 
     * @param motor The name of the motor to set the brake mode for.
     * @param mode  The IdleMode to set the motor to.
     */
    public void setCANSparkMaxBrakeMode(String motor, IdleMode mode) {
        m_sparkMaxMap.get(motor).setIdleMode(mode);
    }

    /**
     * Sets the neutral mode for a Talon motor controller.
     * 
     * @param motor The name of the motor to set the neutral mode for.
     * @param mode  The NeutralMode to set the motor to.
     */
    public void setTalonBrakeMode(String motor, NeutralMode mode) {
        if(m_talonFxMap.containsKey(motor)){
            m_talonFxMap.get(motor).setNeutralMode(mode);
        }else if(m_talonSrxMap.containsKey(motor)){
            m_talonSrxMap.get(motor).setNeutralMode(mode);
        }else{
            throw new java.lang.Error(String.format("Set Brake Mode: Invalid Motor %s", motor));
        }
    }

    /**
     * Inverts a motor.
     * 
     * @param motor The name of the motor to invert.
     */
    public void invert(String motor) {
        if (m_sparkMaxMap.containsKey(motor)) {
            m_sparkMaxMap.get(motor).setInverted(true);
        } else if (m_talonFxMap.containsKey(motor)) {
            m_talonFxMap.get(motor).setInverted(true);
        } else if (m_talonSrxMap.containsKey(motor)){
            m_talonSrxMap.get(motor).setInverted(true);
        }else{
            throw new java.lang.Error(String.format("Invert: Invalid Motor %s", motor));
        }
    }

    /**
     * Sets a motor to follow another motor.
     * 
     * @param leader   The name of the motor to follow.
     * @param follower The name of the motor to set as the follower.
     * @param inverted If the follower should follow the leader in reverse. Only for
     *                 CANSparkMax
     */
    public void follow(String leader, String follower, boolean inverted) {
        if (m_sparkMaxMap.containsKey(leader)) {
            m_sparkMaxMap.get(follower).follow(m_sparkMaxMap.get(leader), inverted);
        } else if (m_talonFxMap.containsKey(leader)) {
            m_talonFxMap.get(follower).follow(m_talonFxMap.get(leader));
        } else if (m_talonSrxMap.containsKey(leader)){
            m_talonSrxMap.get(follower).follow(m_talonSrxMap.get(leader));
        }else{
            throw new java.lang.Error(String.format("Follow: Invalid Motor %s", leader));
        }
    }

    /**
     * Moves a motor using percentage power.
     * 
     * @param motor The name of the motor to move.
     * @param power The power to move the motor at.
     */
    public void move(String motor, double power) {
        if (m_sparkMaxMap.containsKey(motor)) {
            m_sparkMaxMap.get(motor).set(power);
        } else if (m_talonFxMap.containsKey(motor)) {
            m_talonFxMap.get(motor).set(power);
        } else if (m_talonSrxMap.containsKey(motor)){
            m_talonSrxMap.get(motor).set(power);
        }else{
            throw new java.lang.Error(String.format("Move: Invalid Motor %s", motor));
        }
    }

    /**
     * Moves a motor with a minimum power threshold.
     * 
     * @param motor    The name of the motor to move.
     * @param power    The power to move the motor at.
     * @param minPower The minimum power threshold.
     */
    public void move(String motor, double power, double minPower) {
        double speed = Math.abs(power) >= minPower ? power : 0;

        if (m_sparkMaxMap.containsKey(motor)) {
            m_sparkMaxMap.get(motor).set(speed);
        } else if (m_talonFxMap.containsKey(motor)) {
            m_talonFxMap.get(motor).set(speed);
        } else if (m_talonSrxMap.containsKey(motor)){
            m_talonSrxMap.get(motor).set(speed);
        }else{
            throw new java.lang.Error(String.format("Move: Invalid Motor %s", motor));
        }

    }

    /**
     * Moves a motor with a minimum power threshold and a soft stop.
     * 
     * @param motor       The name of the motor to move.
     * @param power       The power to move the motor at.
     * @param minPower    The minimum power threshold.
     * @param softStopMin The minimum encoder position for the soft stop.
     * @param softStopMax The maximum encoder position for the soft stop.
     */
    public void move(String motor, double power, double minPower, double softStopMin, double softStopMax) {
        double speed = Math.abs(power) >= minPower ? power : 0;

        double speed2 = (getEncoderPosition(motor) > softStopMin && speed > 0) ||
                (getEncoderPosition(motor) < softStopMax && speed < 0) ? speed : 0;

        if (m_sparkMaxMap.containsKey(motor)) {
            m_sparkMaxMap.get(motor).set(speed2);
        } else if (m_talonFxMap.containsKey(motor)) {
            m_talonFxMap.get(motor).set(speed2);
        } else if (m_talonSrxMap.containsKey(motor)){
            m_talonSrxMap.get(motor).set(speed2);
        }else{
            throw new java.lang.Error(String.format("Move: Invalid Motor %s", motor));
        }
    }

    /**
     * Stops the motor.
     * 
     * @param motor The name of the motor to stop.
     */
    public void stop(String motor) {
        if (m_sparkMaxMap.containsKey(motor)) {
            m_sparkMaxMap.get(motor).stopMotor();
        } else if (m_talonFxMap.containsKey(motor)) {
            m_talonFxMap.get(motor).stopMotor();
        } else if (m_talonSrxMap.containsKey(motor)){
            m_talonSrxMap.get(motor).stopMotor();
        }else{
            throw new java.lang.Error(String.format("Stop: Invalid Motor %s", motor));
        }
    }

    /**
     * Stops all sparkMax and TalonFX motors.
     */
    public void stopAllMotors(){
        for (Map.Entry<String, DevSparkMax> motor : m_sparkMaxMap.entrySet()) {
            motor.getValue().stopMotor();
        }

        for (Map.Entry<String, DevTalonFX>  motor : m_talonFxMap.entrySet()) {
            motor.getValue().stopMotor();
        }

        for (Map.Entry<String, DevTalonSRX>  motor : m_talonSrxMap.entrySet()) {
            motor.getValue().stopMotor();
        }

    }

    /**
     * Gets the encoder position of a CANSparkMax.
     * 
     * @param motor The name of the motor to get the encoder position of.
     * @return The encoder position of the motor.
     */
    public double getEncoderPosition(String motor) {
        double position = 0;
        if (motor.substring(0, 8).equals("sparkMax")) {
            position = m_sparkMaxMap.get(motor).getEncoder().getPosition();
        } else if (motor.substring(0, 7).equals("talonFx")) {
            position = m_talonFxMap.get(motor).getSelectedSensorPosition();
        }
        return position;
    }

    /**
     * Resets the motor's encoder to 0.
     * 
     * @param motor The name of the motor to reset the encoder for.
     */
    public void resetEncoder(String motor) {
        if (motor.substring(0, 8).equals("sparkMax")) {
            m_sparkMaxMap.get(motor).getEncoder().setPosition(0);
        } else if (motor.substring(0, 7).equals("talonFx")) {
            m_talonFxMap.get(motor).setSelectedSensorPosition(0);
        }
    }

    /**
     * Configures a PID for a CANSparkMax.
     * 
     * @param motor The name of the motor corresponding to the pid.
     * @param kP    The P value for the PID
     * @param kI    The I value for the PID
     * @param kD    The D value for the PID
     */
    public void configureSparkMaxPID(String motor, double kP, double kI, double kD) {
        m_sparkMaxPIDMap.get(motor).setP(kP);
        m_sparkMaxPIDMap.get(motor).setI(kI);
        m_sparkMaxPIDMap.get(motor).setD(kD);
    }

    /**
     * Moves a CANSparkMax motor to a set position using it's PID controller.
     * 
     * @param motor    The name of the motor to move.
     * @param position The target position of the motor.
     */
    public void moveSparkMaxPosition(String motor, double position) {
        m_sparkMaxPIDMap.get(motor).setReference(position, CANSparkMax.ControlType.kPosition);
    }

    /**
     * Checks if a CANSparkMax is at a given target position.
     * 
     * @param motor     The name of the motor to check.
     * @param position  The target position of the motor.
     * @param tolerance How much off the position can be from the target
     * @return If the motor is at the given position within the tolerance.
     */
    public boolean isSparkMaxAtPosition(String motor, double position, double tolerance) {
        CANSparkMax sparkMax = m_sparkMaxMap.get(motor);
        return (Math.abs(sparkMax.getEncoder().getPosition() - position) < tolerance);
    }

    // Solenoid Methods

    /**
     * Sets a solenoid to the desired state
     * 
     * @param solenoid
     * @param state
     */
    public void setSolenoid(String solenoid, boolean state) {
        m_solenoidMap.get(solenoid).set(state);
    }

    /**
     * Toggles a solenoid from it's current state
     * 
     * @param solenoid
     */
    public void toggleSolenoid(String solenoid) {
        boolean state = m_solenoidMap.get(solenoid).get();
        m_solenoidMap.get(solenoid).set(!state);
    }
}
