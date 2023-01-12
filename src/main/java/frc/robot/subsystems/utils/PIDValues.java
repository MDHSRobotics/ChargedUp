package frc.robot.subsystems.utils;

public class PIDValues {

    public PIDValues(double kF, double kP, double kI, double kD) {
        this.kF = kF;
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
    }

    public double kF = 0.0;
    public double kP = 0.0;
    public double kI = 0.0;
    public double kD = 0.0;  
}