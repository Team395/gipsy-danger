/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberDrive;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  WPI_TalonSRX leadScrew = Robot.speedControllerMap.getTalonByID(RobotMap.climberLeadScrewTalonID);
  WPI_TalonSRX wheelPod = Robot.speedControllerMap.getTalonByID(RobotMap.climberWheelPodTalonID);

  double fixedSpeedForTesting = 0;

  public Climber() {
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }

  public void leadScrewDrive(double speed) {
    leadScrew.set(speed);
  }

  public void wheelPodDrive(double speed) {
    wheelPod.set(speed);
  }
}
