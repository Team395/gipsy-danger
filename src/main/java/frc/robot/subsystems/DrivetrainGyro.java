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
import frc.robot.Robot;

public class DrivetrainGyro implements PIDSource{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static final int GYRO_TALON = 5;
  private static final int YAW_INDEX = 0;

  private final PigeonIMU pigeon = new PigeonIMU(0);

  public double getYaw(){
    double[] returnArray = new double[3];
    pigeon.getYawPitchRoll(returnArray);
    return returnArray[YAW_INDEX];
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
    return getYaw();
  }
}
