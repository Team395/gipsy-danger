/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeJoystick;

public class RollerIntake extends Subsystem {

  WPI_TalonSRX intakeController = new WPI_TalonSRX(RobotMap.intakeRollerTalonID);

  public void setRollerSpeed(double speed) {
    intakeController.set(ControlMode.PercentOutput,speed);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeJoystick());
  }
}
