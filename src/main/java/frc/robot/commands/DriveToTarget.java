package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.Gear;
import frc.robot.utils.LinearOutput;
import frc.robot.utils.limelight.Contour;
import frc.robot.utils.limelight.Corners;
import frc.robot.utils.limelight.HeadingOffsetCalculator;
import frc.robot.utils.limelight.Limelight;
import frc.robot.enums.TargetType;

/**
 * A command designed to approach a target perpendicularly which the robot is already pointed at.
 * All units are in feet and degrees unless otherwise notated.
 */
public class DriveToTarget extends Command {
    
    //The amount of time the command will continue to run without seeing a contour in seconds.
    static final double maxTimeWithoutContour = 0.30;
    //A double variable holding the last time a valid contour was seen.
    double contourLastSeenTime;

    //The PID coefficients of the linear drive.
    static final double p = 0.5;
    static final double i = 0;
    static final double d = 0;
    static final double maxOutput = 0.3;
    static final double minOutput = 0.15;
    
    //The proportional constant for the angle adjustment 
    static final double rotationP = -0.005;

    //The PID controller and PIDOutput. We maintain a reference to linearOutput to set the heading correction.
    LinearOutput linearOutput = Robot.drivetrain.getLinearOutput();
    PIDController pidController = new PIDController(p, i, d, Robot.encoders, linearOutput);
    
    TargetType targetType;

    Contour contour;
    
    public DriveToTarget(TargetType targetType) {
        requires(Robot.drivetrain);
        setInterruptible(false);

        this.targetType = targetType;

        pidController.setOutputRange(minOutput, maxOutput);
    }
    


    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        //Drive slow in 
        Robot.drivetrain.shift(Gear.kLow);
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {    
        contour = Limelight.getBestContour();

        if(contour != null) {
            contourLastSeenTime = Timer.getFPGATimestamp();            

            Corners corners = Limelight.getContourCorners();

            //Set the setpoint for distance relative to current position
            pidController.setSetpoint(
                Robot.encoders.getAveragedEncoderFeet() + 
                HeadingOffsetCalculator.calculateDistance(contour, targetType)
            );

            linearOutput.setHeadingCorrection(
                rotationP * HeadingOffsetCalculator.calculateTotalOffset(contour, corners, targetType)
            );
        } else {
            linearOutput.setHeadingCorrection(0);
        }
    
        if(!pidController.isEnabled()){
            pidController.enable();
        }
        
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - contourLastSeenTime > maxTimeWithoutContour;
    }
    
    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.drivetrain.tankDrive(0, 0);
        pidController.disable();
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
    
}
