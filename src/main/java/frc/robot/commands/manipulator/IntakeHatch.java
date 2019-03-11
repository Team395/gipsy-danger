/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class IntakeHatch extends Command {
  
  static final double intakeTimeout = 2;
  static final double crawlSpeed = 0.25;

  Timer timer = new Timer();
  boolean gamePieceAquired = false; //TODO: Implement vacuum pump current reading
  
  public IntakeHatch() {
    requires(Robot.manipulator);
    requires(Robot.drivetrain);
    setInterruptible(false);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      Robot.manipulator.setVacuum(1);
      Robot.drivetrain.tankDrive(crawlSpeed, crawlSpeed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.hasPeriodPassed(intakeTimeout) || gamePieceAquired;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.manipulator.actuateFloor(Value.kReverse);
    Robot.manipulator.actuatePopout(Value.kReverse);
    Robot.drivetrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
