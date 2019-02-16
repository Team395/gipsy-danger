/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.Drivetrain.Gear;
import frc.robot.utils.SparkMAX;

/**
* Add your docs here.
*/
public class DrivetrainEncoders implements PIDSource{
	Gear currentGearing = Robot.drivetrain.getShifterState();
	
	final double highGearRatio = 1.0/9.2;
	final double lowGearRatio = 1.0/20.8;
	
	final double wheelDiameter = 0.5;
	
	SparkMAX leftLeader    = Robot.speedControllerMap.getSparkByID(RobotMap.leftLeaderSpark);
	SparkMAX leftFollower  = Robot.speedControllerMap.getSparkByID(RobotMap.leftFollowerSpark);
	SparkMAX rightLeader   = Robot.speedControllerMap.getSparkByID(RobotMap.rightLeaderSpark);
	SparkMAX rightFollower = Robot.speedControllerMap.getSparkByID(RobotMap.rightFollowerSpark);
	
	public DrivetrainEncoders() {
		
	}
	
	//Left Side Is Negated
	public double getLeftEncoderFeet() {
		if(currentGearing == Gear.kLow) {
			return (leftLeader.getPosition() + leftFollower.getPosition()) / 2 * 
					lowGearRatio * Math.PI * wheelDiameter;
		}
		else {
			return (leftLeader.getPosition() + leftFollower.getPosition()) / 2 * 
					highGearRatio * Math.PI * wheelDiameter;
		}
	}
	
	public double getRightEncoderFeet() {
		if(currentGearing == Gear.kLow) {
			return (rightLeader.getPosition() + rightFollower.getPosition()) / 2 * 
					lowGearRatio * Math.PI * wheelDiameter;
		else {
			return (rightLeader.getPosition() + rightFollower.getPosition()) / 2 * 
					highGearRatio * Math.PI * wheelDiameter;
		}
	}
	
	public void zeroEncoders() {
		leftLeader.zeroPosition();
		leftFollower.zeroPosition();
		rightLeader.zeroPosition();
		rightFollower.zeroPosition();
	}
	
	public double getAveragedEncoderFeet() {
		return (getLeftEncoderFeet()+getRightEncoderFeet())/2;
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}
	
	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public double pidGet(){
		return getAveragedEncoderFeet();
	}
	
	public void setGearing(Gear gear) {
		if(this.currentGearing != gear){
			this.currentGearing = gear;
			zeroEncoders();
		}
	}
	
	public double getLeftVelocity() {
		double averagedRPM = (leftLeader.getVelocity() + 
							  leftFollower.getVelocity()) / 2;
		if(currentGearing == Gear.kHigh) {
			return wheelDiameter * Math.PI * averagedRPM * highGearRatio / 60;
		} else {
			return wheelDiameter * Math.PI * averagedRPM * lowGearRatio / 60;
		}
	}
	
	public double getRightVelocity() {
		double averagedRPM = (rightLeader.getVelocity() + 
							  rightFollower.getVelocity()) / 2;
		if(currentGearing == Gear.kHigh) {
			return wheelDiameter * Math.PI * averagedRPM * highGearRatio / 60;
		} else {
			return wheelDiameter * Math.PI * averagedRPM * lowGearRatio / 60;
		}
	}
	
	public double getCurrentVelocity() {
		return (getLeftVelocity() + getRightVelocity()) / 2;
	}
}
