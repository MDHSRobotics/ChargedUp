package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.subsystems.constants.MotorIDConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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

    //Forklift
    public static CANSparkMax sparkMaxForkliftExtender = new CANSparkMax(MotorIDConstants.motorIDElevator, MotorType.kBrushless);
    public static DevTalonFX talonFxForkliftExtenderTwo = new DevTalonFX("talonFxForkliftExtenderTwo",MotorIDConstants.motorIdExtenderTwo);
    public static CANSparkMax sparkMaxForkliftElevator = new CANSparkMax(MotorIDConstants.motorIDElevator, MotorType.kBrushless);
    public static CANSparkMax sparkMaxForkliftElevatorTwo = new CANSparkMax(MotorIDConstants.motorIdElevatorTwo, MotorType.kBrushless);
    public static CANSparkMax sparkMaxForkliftClamp = new CANSparkMax(MotorIDConstants.motorIdClaw, MotorType.kBrushless);
    
    //Pneumatics
    public static Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    public static Solenoid clawSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 7);
} 