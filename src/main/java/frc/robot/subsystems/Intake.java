package frc.robot.subsystems;

import java.util.Map;
import com.revrobotics.CANSparkMax.IdleMode;

public class Intake extends GenericSubsystem {

    public Intake() {
        super(Map.of("sparkMaxIntake", 36, "sparkMaxIntakeTwo", 37));

        super.setCANSparkMaxBrakeMode("sparkMaxIntake", IdleMode.kBrake);
        super.setCANSparkMaxBrakeMode("sparkMaxIntakeTwo", IdleMode.kBrake);

        super.follow("sparkMaxIntake", "sparkMaxIntakeTwo", true);
    }  
}