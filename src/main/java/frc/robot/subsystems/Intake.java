package frc.robot.subsystems;

import java.util.Map;
import com.revrobotics.CANSparkMax.IdleMode;

public class Intake extends GenericSubsystem {

    public Intake() {
        super(Map.of("sparkMaxIntake", 18, "sparkMaxIntakeTwo", 19));

        super.setCANSparkMaxBrakeMode("sparkMaxIntake", IdleMode.kBrake);
        super.setCANSparkMaxBrakeMode("sparkMaxIntakeTwo", IdleMode.kBrake);

        super.setFollow("sparkMaxIntake", "sparkMaxIntakeTwo", true);
    }  
}