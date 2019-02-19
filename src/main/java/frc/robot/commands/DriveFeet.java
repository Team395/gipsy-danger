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
import frc.robot.utils.LinearOutput;

public class DriveFeet extends Command {
	public static final double p = 0.5;
	public static final double i = 0;
	public static final double d = 1;
	public static final double proportionalHeading = 0.01;
	
	private final boolean holdHeading;
	private double initialHeading;
	private final double distance;
	
	private final LinearOutput linearOutput = Robot.drivetrain.getLinearOutput();
	private final PIDController pidController = new PIDController(p, i, d, Robot.encoders, linearOutput);
	
	public DriveFeet(double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		this(distance, true);
	}
	
	public DriveFeet(double distance, boolean holdHeading){
		requires(Robot.drivetrain);
		this.holdHeading = holdHeading;
		this.distance = distance;
		//Set up PIDController and sensors
		pidController.setAbsoluteTolerance(1);
		pidController.setOutputRange(-0.75, 0.75);
		
		setInterruptible(false);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		this.initialHeading = Robot.gyro.getYaw();
		//Set setpoint and enable
		pidController.setSetpoint(distance + Robot.encoders.getAveragedEncoderFeet());
		pidController.enable();
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
		end();
	}
}
