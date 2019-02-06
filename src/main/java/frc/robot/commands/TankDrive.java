/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.Gear;

public class TankDrive extends Command {

  public final static double lowShiftSpeed = 3;
  public final static double highShiftSpeed = 9;
  double lastShiftTime = Timer.getFPGATimestamp();
  final static double shiftDelay = 1;

  public TankDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.drivetrain.tankDrive(Robot.oi.getLeftY(), Robot.oi.getRightY());
    if(Timer.getFPGATimestamp() - lastShiftTime > shiftDelay) {
      if(Math.abs(Robot.encoders.getCurrentVelocity()) < highShiftSpeed) {
        Robot.drivetrain.shift(Gear.kLow);
        lastShiftTime = Timer.getFPGATimestamp();
      }
      else if(Math.abs(Robot.encoders.getCurrentVelocity()) > lowShiftSpeed) {
        Robot.drivetrain.shift(Gear.kHigh);
        lastShiftTime = Timer.getFPGATimestamp();
      }
    }
    // if(Robot.oi.getShiftHigh())
    //   Robot.drivetrain.shift(Gear.kHigh);

    // else if(Robot.oi.getShiftLow())
    //   Robot.drivetrain.shift(Gear.kLow);
    SmartDashboard.putNumber("velocity", Robot.encoders.getCurrentVelocity());
  }
  
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.tankDrive(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
