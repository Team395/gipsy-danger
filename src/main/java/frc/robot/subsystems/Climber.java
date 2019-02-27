/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.ClimberDrive;
import frc.robot.utils.SparkMAX;

/**
 * Add your docs here.
 */
public class Climber extends Subsystem {

  WPI_TalonSRX leadScrew = Robot.speedControllerMap.getTalonByID(RobotMap.leadScrewTalon);
  WPI_TalonSRX wheelPod = Robot.speedControllerMap.getTalonByID(RobotMap.wheelPodTalon);

  double fixedSpeedForTesting = 0;

  public Climber() {
    Preferences.getInstance().putDouble("climberMaxSpeed", 0.0);
    Preferences.getInstance().putDouble("climberScalingFactor", 1.0);
    Preferences.getInstance().putDouble("wheelPodMaxSpeed", 0.0);
    Preferences.getInstance().putDouble("wheelPodScalingFactor", 1.0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ClimberDrive());
  }

  public void leadScrewDrive(double speed) {
    double maxSpeed = Preferences.getInstance().getDouble("climberMaxSpeed", 1.0);
    double scalingFactor = Preferences.getInstance().getDouble("climberScalingFactor", 1.0);
    double actualSpeed = speed * scalingFactor;
    if (Math.abs(actualSpeed) > maxSpeed) {
      actualSpeed = maxSpeed * Math.signum(speed);
    }
    leadScrew.set(actualSpeed);
  }

  public void wheelPodDrive(double speed) {
    double maxSpeed = Preferences.getInstance().getDouble("wheelPodMaxSpeed", 1.0);
    double scalingFactor = Preferences.getInstance().getDouble("wheelPodScalingFactor", 1.0);
    double actualSpeed = speed * scalingFactor;
    if (Math.abs(actualSpeed) > maxSpeed) {
      actualSpeed = maxSpeed * Math.signum(speed);
    }
    wheelPod.set(actualSpeed);
  }

}
