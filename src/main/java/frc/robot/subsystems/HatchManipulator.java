package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.RobotMap;

public class HatchManipulator extends Subsystem {

	//Reversed holds a hatch panel
	//Forward intakes a hatch panel
	DoubleSolenoid scoreSolenoid = new DoubleSolenoid(
		RobotMap.scoreSolenoidForwardChannelID,
		RobotMap.scoreSolenoidReverseChannelID
	);

	//Forward is up
	//Reversed is out 
	DoubleSolenoid deploySolenoid = new DoubleSolenoid(
		RobotMap.deploySolenoidForwardChannelID,
		RobotMap.deploySolenoidReverseChannelID
	);

	public void setHatchMechanismDeployed(boolean deployed) {
		if(deployed) {
			deploySolenoid.set(Value.kReverse);
		} else {
			deploySolenoid.set(Value.kForward);
		}
	}

	public void setHatchMechanismOpen(boolean open) {
		if(open) {
			scoreSolenoid.set(Value.kForward);
		} else {
			scoreSolenoid.set(Value.kReverse);
		}
	}
 
	public boolean getHatchMechanismOpen() {
		return scoreSolenoid.get() == Value.kForward;
	}

	public boolean getHatchMechanismDeployed() {
		return deploySolenoid.get() == Value.kReverse;
	}

	@Override
	public void initDefaultCommand() {

	}
}
