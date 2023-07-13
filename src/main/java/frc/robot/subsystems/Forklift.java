package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.consoles.Logger;
import com.revrobotics.CANSparkMax.IdleMode;
import java.util.Map;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

public class Forklift extends GenericSubsystem {

    public static Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);

    public Forklift() {
        super(Map.of("sparkMaxElevator", 32, "sparkMaxElevatorTwo", 33, "sparkMaxExtender", 34, "sparkMaxClawLift", 35, "solenoidClaw", 7));
        Logger.setup("Constructing Subsystem: Forklift...");

        super.setCANSparkMaxBrakeMode("Elevator", IdleMode.kBrake);
        super.setCANSparkMaxBrakeMode("ElevatorTwo", IdleMode.kBrake);
        super.setCANSparkMaxBrakeMode("Extender", IdleMode.kBrake);
        super.setCANSparkMaxBrakeMode("ClawLift", IdleMode.kBrake);

        super.invert("Extender");

        super.follow("Elevator", "ElevatorTwo", false);

        super.configureSparkMaxPID("Elevator", 0.03, 0.0000001, 0.0001);
        super.configureSparkMaxPID("Extender", 0.03, 0.0000001, 0.0001);
    }

    @Override
    public void periodic() {
    }

    //Reset the encoders
    private void resetMotorEncoders(){
        Logger.info("Resetting Motor Encoders");
        super.resetEncoder("Elevator");
        super.resetEncoder("Extender");
    }

    /* One Line Commands */

    //Open the clamp
    public CommandBase openClampCommand() {
        Logger.info("Opening Clamp");
        return this.runOnce(() -> super.setSolenoid("Claw", true));
    }

    //Close the clamp
    public CommandBase closeClampCommand() {
        Logger.info("Closing Clamp");
        return this.runOnce(() -> super.setSolenoid("Claw", false));
    }

    //Reset the motor encoders
    public CommandBase resetEncoders() {
        Logger.info("Resetting Soft Stop");
        return this.runOnce(() -> resetMotorEncoders());
    }
    
}