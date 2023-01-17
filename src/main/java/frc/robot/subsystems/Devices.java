package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.devices.*;
import frc.robot.subsystems.constants.MotorIDConstants;

import com.ctre.phoenix.sensors.CANCoder;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// This class contains singleton (static) instances of id mapped subsystem components.
// If a device is not connected at initialization, it should be set to null.
// IMPORTANT: Only ONE subsystem should control any given device.
// Device instances are package-private (neither private nor public) so they can only be used by subsystems.
public class Devices {

    //////////////////////
    // Device Instances //
    //////////////////////

    //Forklift
    public static CANSparkMax sparkMaxForkliftExtender = new CANSparkMax(MotorIDConstants.motorIdHorizontalExtender, MotorType.kBrushless);
    public static CANSparkMax sparkMaxForkliftVertical = new CANSparkMax(MotorIDConstants.motorIdVerticalExtender, MotorType.kBrushless);
    public static CANSparkMax sparkMaxForkliftClamp = new CANSparkMax(MotorIDConstants.motorIdClaw, MotorType.kBrushless);
} 