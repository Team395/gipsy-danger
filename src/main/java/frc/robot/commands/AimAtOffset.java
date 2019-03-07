/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.commands.DriveToTarget.Side;
import frc.robot.utils.limelight.Contour;
import frc.robot.Robot;
import frc.robot.utils.limelight.*;

public class AimAtOffset extends Command {
  static final double aggressiveSkewThreshold = 100000; //TODO: Tune
  static final double maxOffsetAggressive = 20; //TODO: Tune
  static final double maxOffsetNormal = 15;
  static final double maxOffsetDistance = 6.0;

  Contour contour;
  Side side;
  final double targetHeight;
  double initialSkewAbs;
  double xOffset;
  double distance;
  
  static final double cameraHeight = 8.375;
  static final double cameraAngle = 30;

  static final double lowTargetHeight = 28.337;
  static final double highTargetHeight = 35.962;
  //Full power at 15 degrees
  double p = 1.0/50;
  //We want to overshoot/not settle this turn
  double d = p * 10; 
  double maxOutput = 0.3;
  //We only need this to roughly align, this gives time to either stop early or accelerate past
  double acceptableErrorDegrees = 5;

  //Initalize a PIDController with a 10 ms period
  PIDController pidController = new PIDController(p, 0, d, Robot.gyro.getYawSource(), Robot.drivetrain.getTurnOutput(), 0.01);
    
  public AimAtOffset() {
     requires(Robot.drivetrain);
     targetHeight = lowTargetHeight;
  }
   
   // Called just before this Command runs the first time
   @Override
   protected void initialize() {
    contour = Limelight.getBestContour();
    if(contour != null) {
      Corners corners = Limelight.getContourCorners();
      if(corners.validCorners) {
          side = corners.topRight.y > corners.topLeft.y ? Side.kRight : Side.kLeft;            
      }

      double yOffset = contour.ty;
      xOffset = contour.tx;

      distance = (targetHeight - cameraHeight) / 
      Math.tan(Math.toRadians(cameraAngle + yOffset)) / 12;

      double maxOffset = initialSkewAbs > aggressiveSkewThreshold ? maxOffsetAggressive : maxOffsetNormal;
      double additionalOffsetSign = side == Side.kLeft ? -1 : 1;
      double distanceScaling = Math.min(1, (distance * distance) / (maxOffsetDistance * maxOffsetDistance));
        
      double additionalOffset = maxOffset * distanceScaling * additionalOffsetSign;
      
      pidController.setOutputRange(-maxOutput, maxOutput);
  
      pidController.setSetpoint(Robot.gyro.getYaw() - (xOffset + additionalOffset));		
      pidController.enable();
    } 
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
