/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;

import frc.robot.Robot;

public class TurnDegrees extends Command {
  public static final double p = 1/60;
  public static final double i = 0;
  public static final double d = 0;

  private final PIDController pidController = new PIDController(p, i, d, Robot.gyro, Robot.drivetrain.getTurnOutput());
  private final Timer onTargetTimer = new Timer();
  private final double degrees;
  public TurnDegrees(double degrees) {
    requires(Robot.drivetrain);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.degrees = degrees;
    pidController.setAbsoluteTolerance(1);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    pidController.setSetpoint(Robot.gyro.getYaw() + degrees);
    pidController.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(pidController.onTarget()) {
      onTargetTimer.start();
    } else {
      onTargetTimer.stop();
      onTargetTimer.reset();
    }
    if(onTargetTimer.hasPeriodPassed(1)){
      return true;
    };
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pidController.disable();
    Robot.drivetrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
