/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

public class ElevatorJoystick extends InstantCommand {
//   final static double speedGain = 0.5;
  
//   @Override
//   public void execute() {
//     Robot.elevator.operatorControl(speedGain * Robot.oi.getElevatorThrottle());
//   }

//   @Override
//   public boolean isFinished() {
//     return Robot.oi.elevatorTrigger.get();
//   }

//   @Override
//   public void end() {
//     Robot.elevator.setEndEffectorHeight(Robot.elevator.getEndEffectorHeight());
//   }

//   @Override
//   public void interrupted() {
//     end();
//   }
// }

  public ElevatorJoystick() {
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.elevator.setEndEffectorHeight(Robot.elevator.getEndEffectorHeight() + Robot.oi.getElevatorThrottle());
  } 
}
