
package frc.robot.subsystems.utils;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;

import frc.robot.devices.*;

// Utility methods for talon configuration (with or without encoders.)
public class TalonUtils {

    // Which PID slot to pull gains from.
    // Starting 2018, you can choose from 0, 1, 2 or 3.
    // Only the first two (0,1) are visible in web-based configuration.
    private static final int PID_SLOT_0 = 0;

    // Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops.
    // For now we just want the primary one.
    private static final int PID_LOOP_PRIMARY = 0;

    // Number of miliseconds that the talon can stay at peak current.
    private static final int PEAK_AMPERAGE_DURATION = 100;

    // Max amps that the talon can supply during short intervals.
    private static final int PEAK_AMPERAGE = 2;
    
    // Max amps that the talon can supply continuously.
    private static final int PEAK_CONTINUOUS_AMPERAGE = 2;
    
    // Set to zero to skip waiting for confirmation,
    // set to nonzero to wait and report to DS if action fails.
    private static final int TIMEOUT_MS = 20;

    // Config the given TalonSRX
    public static void configureBaseTalon(DevTalonSRX talon, boolean motorInvert) {
        if (!talon.isConnected) return;
        talon.configFactoryDefault();

        talon.setInverted(motorInvert);

        SupplyCurrentLimitConfiguration currentLimitConfig = new SupplyCurrentLimitConfiguration(true, PEAK_CONTINUOUS_AMPERAGE, PEAK_AMPERAGE, PEAK_AMPERAGE_DURATION);
        talon.configSupplyCurrentLimit(currentLimitConfig, TIMEOUT_MS);

        talon.configVoltageCompSaturation(12);
        talon.enableVoltageCompensation(true);

        talon.configNominalOutputForward(0);
        talon.configNominalOutputReverse(0);
        talon.configPeakOutputForward(1);
        talon.configPeakOutputReverse(-1);
    }

    // Config the given TalonFX
    public static void configureBaseTalon(DevTalonFX talon, boolean motorInvert) {
        if (!talon.isConnected) return;
        talon.configFactoryDefault();

        talon.setInverted(motorInvert);
        talon.setNeutralMode(NeutralMode.Brake);

        SupplyCurrentLimitConfiguration currentLimitConfig = new SupplyCurrentLimitConfiguration(true, PEAK_CONTINUOUS_AMPERAGE, PEAK_AMPERAGE, PEAK_AMPERAGE_DURATION);
        talon.configSupplyCurrentLimit(currentLimitConfig, TIMEOUT_MS);

        talon.configVoltageCompSaturation(12);
        talon.enableVoltageCompensation(true);

        talon.configNominalOutputForward(0);
        talon.configNominalOutputReverse(0);
        talon.configPeakOutputForward(0.17);
        talon.configPeakOutputReverse(-0.17);
    }

    // Configure the given TalonSRX
    public static void configureTalonWithEncoder(DevTalonSRX talon, boolean sensorPhase, boolean motorInvert, PIDValues pid) {
        if (!talon.isConnected) return;

        configureBaseTalon(talon, motorInvert);

        talon.setSensorPhase(sensorPhase);

        talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, PID_LOOP_PRIMARY, TIMEOUT_MS);
        talon.configAllowableClosedloopError(PID_SLOT_0, 0, TIMEOUT_MS);

        talon.config_kF(PID_SLOT_0, pid.kF, TIMEOUT_MS);
        talon.config_kP(PID_SLOT_0, pid.kP, TIMEOUT_MS);
        talon.config_kI(PID_SLOT_0, pid.kI, TIMEOUT_MS);
        talon.config_kD(PID_SLOT_0, pid.kD, TIMEOUT_MS);

        talon.configMotionAcceleration(3000, TIMEOUT_MS);
        talon.configMotionCruiseVelocity(8000, TIMEOUT_MS);

        zeroOutEncoder(talon);
    }

    // Configure the given TalonFX
    public static void configureTalonWithEncoder(DevTalonFX talon, boolean sensorPhase, boolean motorInvert, PIDValues pid) {
        if (!talon.isConnected) return;

        configureBaseTalon(talon, motorInvert);

        talon.setSensorPhase(sensorPhase);

        talon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, PID_LOOP_PRIMARY, TIMEOUT_MS);
        talon.configAllowableClosedloopError(PID_SLOT_0, 0, TIMEOUT_MS);

        talon.config_kF(PID_SLOT_0, pid.kF, TIMEOUT_MS);
        talon.config_kP(PID_SLOT_0, pid.kP, TIMEOUT_MS);
        talon.config_kI(PID_SLOT_0, pid.kI, TIMEOUT_MS);
        talon.config_kD(PID_SLOT_0, pid.kD, TIMEOUT_MS);

        talon.configMotionAcceleration(3000, TIMEOUT_MS);
        talon.configMotionCruiseVelocity(8000, TIMEOUT_MS);

        zeroOutEncoder(talon);
    }

    // Config the given TalonSRX master and follower
    public static void configureBaseTalonMasterFollower(DevTalonSRX talonM, DevTalonSRX talonF, boolean motorInvertM, boolean motorInvertF) {
        if (!talonM.isConnected || !talonF.isConnected) return;

        configureBaseTalon(talonF, motorInvertF);
        configureBaseTalon(talonM, motorInvertM);
        talonF.follow(talonM);
    }

    // Config the given TalonFX master and follower
    public static void configureBaseTalonMasterFollower(DevTalonFX talonM, DevTalonFX talonF, boolean motorInvertM, boolean motorInvertF) {
        if (!talonM.isConnected || !talonF.isConnected) return;

        configureBaseTalon(talonF, motorInvertF);
        configureBaseTalon(talonM, motorInvertM);
        talonF.follow(talonM);
    }

    // Initialize current encoder position as zero
    public static void zeroOutEncoder(DevTalonSRX talon) {
        if (!talon.isConnected) return;
        SensorCollection sensorCol = talon.getSensorCollection();
        sensorCol.setQuadraturePosition(0, TIMEOUT_MS);
    }

    // Initialize current encoder position as zero
    public static void zeroOutEncoder(DevTalonFX talon) {
        if (!talon.isConnected) return;
        TalonFXSensorCollection sensorCol = talon.getSensorCollection();
        sensorCol.setIntegratedSensorPosition(0, TIMEOUT_MS);
    }

}
