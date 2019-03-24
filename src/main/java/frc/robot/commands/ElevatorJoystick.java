/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorJoystick extends Command {
  double positionAtCommandEntry;

  public ElevatorJoystick() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.setEndEffectorHeight(Robot.elevator.getEndEffectorHeight());
  }


  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.elevator.setEndEffectorHeight(
      7 * Robot.oi.getElevatorThrottle() + 
      Robot.elevator.getEndEffectorHeight()
    );
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !Robot.oi.elevatorTrigger.get();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.elevator.setEndEffectorHeight(Robot.elevator.getEndEffectorHeight());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
