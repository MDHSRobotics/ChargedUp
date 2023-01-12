// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

/**
 * Do NOT add any static variables to this class, or any initialization at all. Unless you know what
 * you are doing, do not modify this file except to change the parameter class to the startRobot
 * call.
 */
public final class Main {
  private Main() {}

  /**
   * Main initialization function. Do not perform any initialization here.
   *
   * <p>If you change your main robot class, change the parameter type.
   */
  public static void main(String... args) {

    if (RobotBase.isSimulation()) {
      // Simulation mode - not connected to RoboRio.
      // Among potentially other things, the SimRobot has a longer loop cycle
      // to minimize watchdog overruns
      RobotBase.startRobot(SimRobot::new);
    }

    else {
        // Normal execution connected to RoboRio
        RobotBase.startRobot(Robot::new);
    }

  }

}  
