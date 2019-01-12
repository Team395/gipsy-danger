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
import frc.robot.subsystems.Drivetrain;

public class DriveFeet extends Command {
  //Inches per Foot * Ticks per Rotation/(Wheel Diameter * pi)
  public static final double FEET_TO_TICKS = 12 * 4096 / Math.PI / 6;
  public static final double p = 1/8;
  public static final double i = 0;
  public static final double d = 0;
  public static final double proportionalHeading = 0.05;
  
  private final boolean holdHeading;
  private final double initialHeading;

  private final Drivetrain.LinearOutput linearOutput = Robot.drivetrain.getLinearOutput();
  private final PIDController pidController = new PIDController(p, i, d, Robot.encoders, linearOutput);

  public DriveFeet(double distance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this(distance, true);
  }
  
  public DriveFeet(double distance, boolean holdHeading){
    requires(Robot.drivetrain);
    this.holdHeading = holdHeading;

    //Set up PIDController and sensors
    Robot.encoders.zeroEncoders();
    this.initialHeading = Robot.gyro.getYaw();

    //Set setpoint and enable
    pidController.setSetpoint(distance * FEET_TO_TICKS);
    pidController.enable();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(holdHeading){
      linearOutput.setHeadingCorrection(proportionalHeading * (initialHeading - Robot.gyro.getYaw()));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pidController.onTarget();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pidController.disable();
    Robot.drivetrain.tankDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
