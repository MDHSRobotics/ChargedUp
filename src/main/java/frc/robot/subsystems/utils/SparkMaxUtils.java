package frc.robot.subsystems.utils;

import com.revrobotics.CANSparkMax;

import com.revrobotics.SparkMaxPIDController;

public class SparkMaxUtils {
    
    public static void configureSparkMaxPID(CANSparkMax sparkMax, double kP, double kI, double kD, double kF){
        
        SparkMaxPIDController m_pidController = sparkMax.getPIDController();

        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setFF(kF);

    }
}
