package frc.robot.subsystems;

import java.util.Map;

public class Intake extends GenericSubsystem {

    public Intake() {
        super(Map.of("sparkMaxIntake", 18, "sparkMaxIntakeTwo", 19));

        super.setFollow("sparkMaxIntake", "sparkMaxIntakeTwo", true);
    }  
}