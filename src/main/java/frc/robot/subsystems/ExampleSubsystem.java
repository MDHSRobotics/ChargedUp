package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.consoles.Logger;

public class ExampleSubsystem extends SubsystemBase {

    public ExampleSubsystem() {
        Logger.setup("Constructing Subsystem: ExampleSubsystem...");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run during simulation
    }

    public void doSomething() {
        // Not much to do since this is an example
    }
    
}
