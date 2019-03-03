/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
		
	final double wheelDiameterFeet = 0.5;
	final double ticksToFeet = wheelDiameterFeet / 4096.0; 	
	
	WPI_TalonSRX leftLeader    = Robot.speedControllerMap.getTalonByID(RobotMap.driveLeftLeaderSparkID);
	WPI_TalonSRX leftFollower  = Robot.speedControllerMap.getTalonByID(RobotMap.driveLeftFollowerSparkID);
	WPI_TalonSRX rightLeader   = Robot.speedControllerMap.getTalonByID(RobotMap.driveRightLeaderSparkID);
	WPI_TalonSRX rightFollower = Robot.speedControllerMap.getTalonByID(RobotMap.driveRightFollowerSparkID);
	
	public DrivetrainEncoders() {
		
	}
	
	//Left Side Is Negated
	public double getLeftEncoderFeet() {
		return leftLeader.getSelectedSensorPosition() * ticksToFeet;
	}
	
	public double getRightEncoderFeet() {
		return rightLeader.getSelectedSensorPosition() * ticksToFeet;
	}
	
	public void zeroEncoders() {
		leftLeader.setSelectedSensorPosition(0);
		rightLeader.setSelectedSensorPosition(0);
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
	
	public double getLeftVelocity() {
		return 0;
	}
	
	public double getRightVelocity() {
		return 0;
	}
	
	public double getCurrentVelocity() {
		return (getLeftVelocity() + getRightVelocity()) / 2;
	}
}
