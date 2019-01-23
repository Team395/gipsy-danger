/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.utils.PIDTuner;

public class TestPIDCommand extends Command {
  boolean positionMode = true;
  double rpm = 0;
  double position = 0;

  PIDTuner tuner = Robot.test.getTuner();

  public TestPIDCommand() {
    requires(Robot.test);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    SmartDashboard.putBoolean("PositionMode", positionMode);
    SmartDashboard.putNumber("RPM", rpm);
    SmartDashboard.putNumber("Position", position);

    Preferences.getInstance().putDouble(Robot.test.getName() + "_P0", 0.08);
    Preferences.getInstance().putDouble(Robot.test.getName() + "_P1", 1);
    Preferences.getInstance().putDouble(Robot.test.getName() + "_D0", 40);
    Preferences.getInstance().putDouble(Robot.test.getName() + "_D1", 5);
    Preferences.getInstance().putDouble(Robot.test.getName() + "_FF1", 0.0001754);

    tuner.initializePreferences();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    positionMode = SmartDashboard.getBoolean("PositionMode", positionMode);
    rpm = SmartDashboard.getNumber("RPM", rpm);
    position = SmartDashboard.getNumber("Position", position);

    if(positionMode) {
      Robot.test.setPosition(position);
    } else {
      Robot.test.setSpeed(rpm);
    }

    tuner.updateGains();
    Robot.test.printTelemetry();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
