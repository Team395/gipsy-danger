package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.Gear;
import frc.robot.utils.Targets.TargetType;
import frc.robot.utils.limelight.Contour;
import frc.robot.utils.limelight.Corners;
import frc.robot.utils.limelight.Limelight;
import org.opencv.core.Point;
public class ApproachTarget extends Command {
    
    private enum Side {
        kLeft,
        kRight
    };

    private static final double CAMERA_HEIGHT = 16.125;
    //Angle from the horizontal in radians
    private static final double CAMERA_ANGLE = Math.PI/6;
    private static final double MAX_OFFSET = 45;
    private static final double MAX_OFFSET_DISTANCE = 10;
    private static final double TARGET_CENTER_HEIGHT = 31.25 + 0.5 * 5.826;

    Contour contour;
    Side side;
    public static final double p = 0.5;
    public static final double i = 0;
    public static final double d = 0;
    public static final double proportionalHeading = 0.01;
    
    private final Drivetrain.LinearOutput linearOutput = Robot.drivetrain.getLinearOutput();
    private final PIDController pidController = new PIDController(p, i, d, Robot.encoders, linearOutput);
    
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
        Robot.encoders.zeroEncoders();
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {    
        //Get the target
        contour = Limelight.getBestContour();

        if(contour != null) {
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
                    * distance / MAX_OFFSET_DISTANCE
                    * Math.max(Math.min(1, contour.ts/15), 0.25);
                linearOutput.setHeadingCorrection(-proportionalHeading * 
                    (xOffset + 
                        (
                            side == Side.kLeft
                                ? -headingCorrection
                                : headingCorrection
                        )
                    )
                );
            } else {
                linearOutput.setHeadingCorrection(-proportionalHeading * xOffset);
            }

            if(!pidController.isEnabled())
                pidController.enable();
        }
    }
    
    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return contour == null ;
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
