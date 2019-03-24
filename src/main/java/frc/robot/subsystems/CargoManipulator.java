/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.manipulator.IntakeJoystick;
import frc.robot.enums.IntakeMode;

public class CargoManipulator extends Subsystem {
	
	WPI_TalonSRX intakeController = Robot.speedControllerMap.getTalonByID(RobotMap.intakeRollerTalonID);
	
	//Controls the roller
	public void setRollerSpeed(double speed) {
		intakeController.set(ControlMode.PercentOutput,Math.min(speed, 0.75));
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeJoystick());
	}
}
