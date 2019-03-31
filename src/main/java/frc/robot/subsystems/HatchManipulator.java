package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.RobotMap;

public class HatchManipulator extends Subsystem {

	DoubleSolenoid hatchSolenoid = new DoubleSolenoid(
		RobotMap.hatchSolenoidForwardChannelID,
		RobotMap.hatchSolenoidReverseChannelID
	);

	public void setHatchSolenoid(Value value) {
		hatchSolenoid.set(value);
	}

	@Override
	public void initDefaultCommand() {

	}
}
