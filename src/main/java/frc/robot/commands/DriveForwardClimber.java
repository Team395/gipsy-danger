/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

public class DriveForwardClimber extends TimedCommand {
	
	static double driveForwardTime = 5;
	
	public DriveForwardClimber() {
		super(driveForwardTime);
		requires(Robot.climber);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.climber.wheelPodDrive(1);
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.climber.wheelPodDrive(0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
