/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.utils.limelight.Contour;
import frc.robot.Robot;
import frc.robot.utils.limelight.*;
import frc.robot.enums.TargetType;
import frc.robot.subsystems.Elevator;

public class AimAtOffset extends Command {
	
	TargetType targetType;
	Contour contour;

	//Full power at 15 degrees
	double p = 1.0/50;
	//We want to overshoot/not settle this turn
	double d = p * 10; 
	double maxOutput = 0.3;
	//We only need this to roughly align, this gives time to either stop early or accelerate past
	double acceptableErrorDegrees = 5;
	
	//Initalize a PIDController with a 10 ms period
	PIDController pidController = new PIDController(p, 0, d, Robot.gyro.getYawSource(), Robot.drivetrain.getTurnOutput(), 0.01);
	boolean useOffset;

	public AimAtOffset(TargetType targetType) {
		this(targetType, true);
	}

	public AimAtOffset(TargetType targetType, boolean useOffset) {
		requires(Robot.drivetrain);
		setInterruptible(false);

		this.targetType = targetType;
		this.useOffset = useOffset;
		pidController.setOutputRange(-maxOutput, maxOutput);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		contour = Limelight.getBestContour();
		
		if(contour != null) {
			Corners corners = Limelight.getContourCorners();
			double totalOffset;
			if(useOffset) {
				totalOffset = HeadingOffsetCalculator.calculateTotalOffset(contour
										, corners
										, targetType
										, 0);//Robot.elevator.getEndEffectorHeight());
			} else {
				totalOffset = HeadingOffsetCalculator.calculateXAngle(contour);
			}
			pidController.setSetpoint(Robot.gyro.getYaw() + totalOffset);
			pidController.enable();
		} else {
			System.out.println("Contour is null");
		}
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		contour = Limelight.getBestContour();
		if(contour != null) {
			Corners corners = Limelight.getContourCorners();
			double totalOffset;
			if(useOffset) {
				totalOffset = HeadingOffsetCalculator.calculateTotalOffset(contour
										, corners
										, targetType
										, 0);//Robot.elevator.getEndEffectorHeight());
			} else {
				totalOffset = HeadingOffsetCalculator.calculateXAngle(contour);
			}
			pidController.setSetpoint(Robot.gyro.getYaw() + totalOffset);
		}
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		//Not using onTarget as that may do stability checking, this should end as soon as we hit the range at all
		return Math.abs(pidController.getError()) < acceptableErrorDegrees || contour == null;
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
