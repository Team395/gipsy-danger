/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DrivetrainGyro {
	
	public static final int GYRO_TALON = 5;
	private static final int YAW_INDEX = 0;
	private static final int PITCH_INDEX = 1;
	
	private final PigeonIMU pigeon = new PigeonIMU(0);
	
	public double getYaw(){
		double[] returnArray = new double[3];
		pigeon.getYawPitchRoll(returnArray);
		return returnArray[YAW_INDEX];
	}
	
	public double getPitch(){
		double[] returnArray = new double[3];
		pigeon.getYawPitchRoll(returnArray);
		return returnArray[PITCH_INDEX];
	}
	
	public PIDSource getPitchSource() {
		return new PIDSource(){
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				throw new UnsupportedOperationException(); 
			}
			
			@Override
			public double pidGet() {
				return getPitch();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		};
	}
	
	public PIDSource getYawSource() {
		return new PIDSource(){
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				throw new UnsupportedOperationException(); 
			}
			
			@Override
			public double pidGet() {
				return getYaw();
			}
			
			@Override
			public PIDSourceType getPIDSourceType() {
				return PIDSourceType.kDisplacement;
			}
		};
	}
}
