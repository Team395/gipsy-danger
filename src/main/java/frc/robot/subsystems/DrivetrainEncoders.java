/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import frc.robot.Robot;
/**
 * Add your docs here.
 */
public class DrivetrainEncoders implements PIDSource{
  public static final int LEFT_ENCODER_TALON = 1;
  public static final int RIGHT_ENCODER_TALON = 3;

  private final TalonSRX leftEncoderTalon = Robot.controllerMap.getTalonByID(LEFT_ENCODER_TALON);
  private final TalonSRX rightEncoderTalon = Robot.controllerMap.getTalonByID(RIGHT_ENCODER_TALON);

  public double getLeftEncoder() {
    return leftEncoderTalon.getSelectedSensorPosition();
  }

  public double getRightEncoder() {
    return rightEncoderTalon.getSelectedSensorPosition();
  }
  
  public void zeroEncoders() {
    leftEncoderTalon.setSelectedSensorPosition(0);
    rightEncoderTalon.setSelectedSensorPosition(0);
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
    return (getLeftEncoder()+getRightEncoder())/2;
  }


}
