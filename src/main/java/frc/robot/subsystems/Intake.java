package frc.robot.subsystems;

import java.util.Map;
import com.revrobotics.CANSparkMax.IdleMode;

public class Intake extends GenericSubsystem {

    public Intake() {
        super(Map.of("sparkMaxIntake", 36, "sparkMaxIntakeTwo", 37));

        super.setCANSparkMaxBrakeMode("Intake", IdleMode.kBrake);
        super.setCANSparkMaxBrakeMode("IntakeTwo", IdleMode.kBrake);

        super.follow("Intake", "IntakeTwo", true);
    }  
}