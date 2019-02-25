package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.Gear;
import frc.robot.utils.limelight.Contour;
import frc.robot.utils.limelight.Corners;
import frc.robot.utils.limelight.Limelight;
public class ApproachTarget extends Command {
    
    enum Side {
        kLeft,
        kRight
    };

    static final double CAMERA_HEIGHT = 16.125;
    //Angle from the horizontal in radians
    static final double CAMERA_ANGLE = Math.PI/6;
    static final double MAX_OFFSET = 15;
    static final double MAX_OFFSET_DISTANCE = 8;
    static final double TARGET_CENTER_HEIGHT = 31.25 + 0.5 * 5.826;
    static final double MAX_TIME_WITHOUT_CONTOUR = 0.1;

    Contour contour;
    Side side;
    static final double p = 0.5;
    static final double i = 0;
    static final double d = 1;
    static final double proportionalHeading = 0.013;
    
    Drivetrain.LinearOutput linearOutput = Robot.drivetrain.getLinearOutput();
    PIDController pidController = new PIDController(p, i, d, Robot.encoders, linearOutput);
    
    double lastContourSeenTime = 0;

    public ApproachTarget() {
        requires(Robot.drivetrain);
        setInterruptible(false);
    }
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        pidController.setOutputRange(-0.5, 0.5);
        Robot.drivetrain.shift(Gear.kLow);
        contour = Limelight.getBestContour();
        lastContourSeenTime = Timer.getFPGATimestamp();
        Robot.encoders.zeroEncoders();
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {    
        //Get the target
        contour = Limelight.getBestContour();
        if(contour != null) {
            lastContourSeenTime = Timer.getFPGATimestamp();

            double xOffset = contour.tx;
            double yOffset = contour.ty;
            
            // Calculate distance
            double distance = (TARGET_CENTER_HEIGHT-CAMERA_HEIGHT) / 
                        Math.tan(CAMERA_ANGLE+ Math.toRadians(yOffset)) / 12;

            //Set the setpoint for distance
            pidController.setSetpoint(Robot.encoders.getAveragedEncoderFeet() + distance);
            
            //If the left side is higher than the right side, the target is to our left
            //Otherwise the target is to our right
            Corners corners = Limelight.getContourCorners();
            if(corners.validCorners)
                side = corners.topLeft.y > corners.topRight.y ? Side.kLeft : Side.kRight;
            
            if(side != null){
                double headingCorrection = MAX_OFFSET
                    * Math.min(1, distance / MAX_OFFSET_DISTANCE);
                linearOutput.setHeadingCorrection(-proportionalHeading * 
                    (xOffset + 
                        ( 
                            side == Side.kLeft
                                ? -headingCorrection
                                : headingCorrection
                        )
                    )
                );
                SmartDashboard.putNumber("Heading setpoint", -proportionalHeading * 
                (xOffset + 
                    (side == Side.kLeft
                    ? -headingCorrection
                    : headingCorrection)
                ));
                
            } else {
                linearOutput.setHeadingCorrection(-proportionalHeading * xOffset);
                SmartDashboard.putNumber("Heading setpoint", xOffset);
            }

            if(!pidController.isEnabled())
                pidController.enable();
        } else {
            linearOutput.setHeadingCorrection(0);
        }
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Timer.getFPGATimestamp() - lastContourSeenTime > MAX_TIME_WITHOUT_CONTOUR;
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
