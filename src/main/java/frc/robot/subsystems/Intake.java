package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.consoles.Logger;
import static frc.robot.subsystems.Devices.*;

import com.revrobotics.CANSparkMax.IdleMode;

public class Intake extends SubsystemBase {

    public static final double MIN_MOTOR_POWER = 0.1;

    public Intake() {
        Logger.setup("Constructing Subsystem: Intake...");

        sparkMaxIntake.restoreFactoryDefaults();
        sparkMaxIntakeTwo.restoreFactoryDefaults();
        
        sparkMaxIntake.setSmartCurrentLimit(15);
        sparkMaxIntakeTwo.setSmartCurrentLimit(15);

        sparkMaxIntake.setIdleMode(IdleMode.kBrake);
        sparkMaxIntakeTwo.setIdleMode(IdleMode.kBrake);

        sparkMaxIntakeTwo.follow(sparkMaxIntake, true);
    } 

    @Override
    public void periodic() {
    }

    public void moveIntake(double power){
        double intakePower = Math.abs(power) >= MIN_MOTOR_POWER ? power : 0;
        sparkMaxIntake.set(intakePower);
    }
    
}