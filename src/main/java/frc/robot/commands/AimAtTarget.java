package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.limelight.Contour;
import frc.robot.utils.limelight.Limelight;

public class AimAtTarget extends Command {
	
	 //Full power at 15 degrees
	double p = 1.0/15.0;
	//We want to overshoot/not settle this turn
	double d = 0; 
	double maxOutput = 0.5;
	//We only need this to roughly align, this gives time to either stop early or accelerate past
	double acceptableErrorDegrees = 5;

	PIDController pidController = new PIDController(p, 0, d, Robot.gyro.getYawSource(), Robot.drivetrain.getTurnOutput());
	
	Contour contour;

	public AimAtTarget() {
		requires(Robot.drivetrain);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		pidController.setOutputRange(-maxOutput, maxOutput);
		
		contour = Limelight.getBestContour();
		double xOffset = contour.tx;
		if(contour != null) {
			pidController.setSetpoint(xOffset + Robot.gyro.getYaw());		
		} 
		pidController.enable();

	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
	}
	
	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		//Not using onTarget as that may do stability checking, this should end as soon as we hit the range at all
		return Math.abs(pidController.getError()) < acceptableErrorDegrees ||
			   contour == null;
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