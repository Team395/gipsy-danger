package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.utils.limelight.Contour;
import frc.robot.utils.limelight.Limelight;

public class AimAtTarget extends Command {
	
	 //Full power at 15 degrees
	double p = 1.0/50;
	//We want to overshoot/not settle this turn
	double d = p * 10; 
	double maxOutput = 0.3;
	//We only need this to roughly align, this gives time to either stop early or accelerate past
	double acceptableErrorDegrees = 5;

	//Initalize a PIDController with a 10 ms period
	PIDController pidController = new PIDController(p, 0, d, Robot.gyro.getYawSource(), Robot.drivetrain.getTurnOutput(), 0.01);
	
	Contour contour;

	public AimAtTarget() {
		requires(Robot.drivetrain);
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		pidController.setOutputRange(-maxOutput, maxOutput);
		contour = Limelight.getBestContour();
		double xOffset = 0;
		if(contour != null) {
			xOffset = contour.xOffset;
		} 

		pidController.setSetpoint(Robot.gyro.getYaw() - xOffset);		
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