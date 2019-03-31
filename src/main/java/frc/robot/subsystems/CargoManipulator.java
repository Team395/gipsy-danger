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
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.manipulator.IntakeJoystick;

public class CargoManipulator extends Subsystem {
	final static double maxRollerSpeed = 0.5;

	static double boundToMaxSpeed(double speed) {
		return Math.max(Math.min(speed, maxRollerSpeed), -maxRollerSpeed);
	}

	WPI_TalonSRX intakeRollerTopController
		= Robot.speedControllerMap.getTalonByID(RobotMap.intakeRollerTopTalonID);
	WPI_TalonSRX intakeRollerBottomController
		= Robot.speedControllerMap.getTalonByID(RobotMap.intakeRollerBottomTalonId);

	public CargoManipulator() {
		intakeRollerTopController.setInverted(false);
		intakeRollerBottomController.setInverted(true);
	}

	//Controls the roller
	public void setRollerSpeed(double speed) {
		intakeRollerTopController.set(ControlMode.PercentOutput,
			boundToMaxSpeed(speed));
		intakeRollerBottomController.set(ControlMode.PercentOutput,
			boundToMaxSpeed(speed));
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeJoystick());
	}
}
