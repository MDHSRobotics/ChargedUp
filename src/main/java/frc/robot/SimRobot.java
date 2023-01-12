package frc.robot;

// This is a specialization of the Robot class specifically for running in the Simulator.
public class SimRobot extends Robot {

    // Note that the RoboRio uses a duration of .02 seconds which is too
    // short of a time slice when running in simulation mode (i.e. it results
    // in watchdog overruns)
    private static double kSimulationPeriod = .2;

    public SimRobot() {
        super(kSimulationPeriod);
    }

}
