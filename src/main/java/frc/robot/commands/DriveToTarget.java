package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain.Gear;
import frc.robot.utils.LinearOutput;
import frc.robot.utils.limelight.Contour;
import frc.robot.utils.limelight.Corners;
import frc.robot.utils.limelight.Limelight;

public class DriveToTarget extends Command {
    
    public enum TargetType {
        kHighTarget,
        kLowTarget;
    }

    enum Side {
        kLeft,
        kRight
    };
    
 

    //Camera Parameters
    static final double cameraHeight = 16.125;
    static final double cameraAngle = 30;

    /**
     * agressiveSkewThreshold: The threshold in native limelight skew that determines if we take an agressive appraoch.
     * maxOffsetAgressive: The angle offset an agressive approach will target.
     * maxOffsetNormal: The angle offset a normal approach will target.
     * offsetScalingFactor: The maximum distance at which we will use the maximum offset. Below this distance we scale offset down linearly
     */

    static final double aggressiveSkewThreshold = 15; //TODO: Tune
    static final double maxOffsetAggressive = 25; //TODO: Tune
    static final double maxOffsetNormal = 12.5;
    static final double maxOffsetDistance = 6.0;

    static final double lowTargetHeight = 33.893;
    static final double highTargetHeight = 41.787;

    //The amount of time the command will continue to run without seeing a contour in seconds.
    static final double maxTimeWithoutContour = 0.1;

    //The PID coefficients of the linear drive.
    static final double p = 0.5;
    static final double i = 0;
    static final double d = 1;
    static final double maxOutput = 0.5;

    //The proportional constant for the angle adjustment 
    static final double rotationP = 0.013;

    //The PID controller and PIDOutput. We maintain a reference to linearOutput to set the heading correction.
    LinearOutput linearOutput = Robot.drivetrain.getLinearOutput();
    PIDController pidController = new PIDController(p, i, d, Robot.encoders, linearOutput);
    
    Contour contour;
    Side side;
    final double targetHeight;
    double initialSkewAbs;
    double xOffset;
    double distance;

    //A timer which starts when a contour is not seen to determine if we should kill the command.
    Timer contourNotSeen = new Timer();

    public DriveToTarget(TargetType targetType) {
        requires(Robot.drivetrain);
        setInterruptible(false);
        targetHeight = (targetType == TargetType.kHighTarget) ? highTargetHeight : lowTargetHeight;
    }
    

    private double calculateHeadingCorrection() {
        double additionalOffset;

        if(side == null) {
            additionalOffset = 0;
        } else {
            double maxOffset = initialSkewAbs > aggressiveSkewThreshold ? maxOffsetAggressive : maxOffsetNormal;
            double additionalOffsetSign = side == Side.kRight ? 1 : -1;
            double distanceScaling = Math.min(1, distance/maxOffsetDistance);
            
            additionalOffset = maxOffset * distanceScaling * additionalOffsetSign;
        }
        return rotationP * (xOffset + additionalOffset);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        //Drive slow in 
        Robot.drivetrain.shift(Gear.kLow);
        pidController.setOutputRange(-maxOutput, maxOutput);
        
        contour = Limelight.getBestContour();
        if(contour != null) {
            initialSkewAbs = Math.abs(contour.ts);
        }
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {    
        contour = Limelight.getBestContour();

        if(contour == null) {
            contourNotSeen.start();
            linearOutput.setHeadingCorrection(0);
        } else {
            contourNotSeen.stop();
            contourNotSeen.reset();
            
            xOffset = contour.tx;
            double yOffset = contour.ty;
            
            double distanceFeet = (lowTargetHeight - cameraHeight) / 
                        Math.tan(Math.toRadians(cameraAngle + yOffset)) / 12;

            //Set the setpoint for distance relative to current position
            pidController.setSetpoint(Robot.encoders.getAveragedEncoderFeet() + distanceFeet);
            
            //Determine the side by picking which upper corner is higher
            Corners corners = Limelight.getContourCorners();
            if(corners.validCorners) {
                side = corners.topRight.y > corners.topLeft.y ? Side.kRight : Side.kLeft;            
            }

            linearOutput.setHeadingCorrection(calculateHeadingCorrection());

            if(!pidController.isEnabled()){
                pidController.enable();
            }
        }
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return contourNotSeen.hasPeriodPassed(maxTimeWithoutContour);
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
