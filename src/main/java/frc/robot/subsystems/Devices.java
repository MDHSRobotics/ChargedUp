package frc.robot.subsystems;

import frc.robot.subsystems.constants.MotorIDConstants;

import frc.robot.devices.DevTalonFX;
import com.ctre.phoenix.sensors.CANCoder;

// This class contains singleton (static) instances of id mapped subsystem components.
// If a device is not connected at initialization, it should be set to null.
// IMPORTANT: Only ONE subsystem should control any given device.
// Device instances are package-private (neither private nor public) so they can only be used by subsystems.
public class Devices {

    //////////////////////
    // Device Instances //
    ////////////////////// 

    // SwerveDrive
    public static DevTalonFX talonFxSwerveDriveFL = new DevTalonFX("talonFxSwerveDriveWheelFrontLeft", MotorIDConstants.motorIdDriveFL);
    public static DevTalonFX talonFxSwerveDriveFR = new DevTalonFX("talonFxSwerveDriveWheelFrontRight", MotorIDConstants.motorIdDriveFR);
    public static DevTalonFX talonFxSwerveDriveRL = new DevTalonFX("talonFxSwerveDriveWheelRearLeft", MotorIDConstants.motorIdDriveRL);
    public static DevTalonFX talonFxSwerveDriveRR = new DevTalonFX("talonFxSwerveDriveWheelRearRight", MotorIDConstants.motorIdDriveRR);
    public static DevTalonFX talonFxSwerveTurnFL = new DevTalonFX("talonFxSwerveTurnWheelFrontLeft", MotorIDConstants.motorIdTurnFL);
    public static DevTalonFX talonFxSwerveTurnFR = new DevTalonFX("talonFxSwerveTurnWheelFrontRight", MotorIDConstants.motorIdTurnFR);
    public static DevTalonFX talonFxSwerveTurnRL = new DevTalonFX("talonFxSwerveTurnWheelRearLeft", MotorIDConstants.motorIdTurnRL);
    public static DevTalonFX talonFxSwerveTurnRR = new DevTalonFX("talonFxSwerveTurnWheelRearRight", MotorIDConstants.motorIdTurnRR);

    public static CANCoder canCoderFL = new CANCoder(1);
    public static CANCoder canCoderFR = new CANCoder(3);
    public static CANCoder canCoderRL = new CANCoder(2);
    public static CANCoder canCoderRR = new CANCoder(4);
} 