/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LevelRobot extends Command {

  final static double p = 1.0/20.0;
  final static double d = 1.0/200.0;

  //The forward/backward tilt axis is roll rather than pitch
  PIDController pidController = new PIDController(p, 0, d, Robot.gyro.getRollSource(), Robot.elevator.levelElevator());

  public LevelRobot() {
    requires(Robot.elevator);
    setInterruptible(false);
  }

  @Override
  protected void initialize() {
    pidController.enable();
    pidController.setSetpoint(Robot.gyro.getRoll());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pidController.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

}
