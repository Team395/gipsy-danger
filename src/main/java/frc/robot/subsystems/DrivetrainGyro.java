package frc.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.RobotMap;

public class DrivetrainGyro {
	
	public static final int GYRO_TALON = 5;
	private static final int YAW_INDEX = 0;
	private static final int PITCH_INDEX = 1;
	private static final int ROLL_INDEX = 2;
	
	private final PigeonIMU pigeon = new PigeonIMU(RobotMap.pigeonIMUID);
	
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
	
	public double getRoll() {
		double[] returnArray = new double[3];
		pigeon.getYawPitchRoll(returnArray);
		return returnArray[ROLL_INDEX];
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
	
	
	public PIDSource getRollSource() {
		return new PIDSource(){
			
			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				throw new UnsupportedOperationException(); 
			}
			
			@Override
			public double pidGet() {
				return getRoll();
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
