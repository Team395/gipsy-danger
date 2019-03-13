/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.Robot;
import frc.robot.enums.ScoringPosition;
import frc.robot.utils.limelight.Limelight;

public class AutoScoreChooser extends Command {
  public AutoScoreChooser() {
    requires(Robot.manipulator);
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.oi.setAutoscoreModeLED(true);
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.oi.controlBoard.getHatchMode()) {
      if(Robot.oi.controlBoard.getElevatorHigh()) {
        Scheduler.getInstance().add(new AutoScore(ScoringPosition.kHatchHigh));
      } else if(Robot.oi.controlBoard.getElevatorMedium()) {
        Scheduler.getInstance().add(new AutoScore(ScoringPosition.kHatchMedium));
      } else if(Robot.oi.controlBoard.getElevatorLow()) {
        Scheduler.getInstance().add(new AutoScore(ScoringPosition.kHatchLow));
      } else if(Robot.oi.controlBoard.getElevatorShip()) {
        Scheduler.getInstance().add(new AutoScore(ScoringPosition.kHatchShip));
      }
    } else if(Robot.oi.controlBoard.getCargoMode()) {
      if(Robot.oi.controlBoard.getElevatorHigh()) {
        Scheduler.getInstance().add(new AutoScore(ScoringPosition.kCargoHigh));
      } else if(Robot.oi.controlBoard.getElevatorMedium()) {
        Scheduler.getInstance().add(new AutoScore(ScoringPosition.kCargoMedium));
      } else if(Robot.oi.controlBoard.getElevatorLow()) {
        Scheduler.getInstance().add(new AutoScore(ScoringPosition.kCargoLow));
      } else if(Robot.oi.controlBoard.getElevatorShip()) {
        Scheduler.getInstance().add(new AutoScore(ScoringPosition.kCargoShip));
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.oi.controlBoard.getAutoIntakePressed() || Limelight.getBestContour() == null;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.oi.setAutoscoreModeLED(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.oi.setAutoscoreModeLED(false);
  }
}