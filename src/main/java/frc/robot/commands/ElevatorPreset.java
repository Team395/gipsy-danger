/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorPreset extends Command {
  
  public static enum PresetHeight {
    kCargoHigh(83.5),
    kCargoMedium(55.5),
    kCargoLow(27.5),
    kCargoShip(0),
    kCargoLoading(0),
    kHatchHigh(75.0),
    kHatchMedium(47.0),
    kHatchLow(19.0),
    kHatchShip(19.0),
    kHatchLoading(19.0),
    kZero(1.0),
    kMaxHeight(80);

    private final double height;

    public double getHeight() {
      return height;
    }

    private PresetHeight(double height) {
      this.height = height;
    }
  }
  
  PresetHeight setpoint;

  public ElevatorPreset(PresetHeight setpoint) {
    requires(Robot.elevator);

    this.setpoint = setpoint;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.setEndEffectorHeight(setpoint.getHeight());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.elevator.onTarget();
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
