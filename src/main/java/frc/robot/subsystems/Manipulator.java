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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.manipulator.IntakeJoystick;

public class Manipulator extends Subsystem {
	
	private enum VacuumState {
		kFullPower(1),
		kReducedPower(0.45),
		kOff(0);
		
		private double power;
		
		public double get() {
			return power;
		}
		
		private VacuumState(double power) {
			this.power = power;
		}
	};
	
	WPI_TalonSRX vacuumPumpTalon = Robot.speedControllerMap.getTalonByID(RobotMap.vacuumPumpTalonID);
	WPI_TalonSRX intakeController = Robot.speedControllerMap.getTalonByID(RobotMap.intakeRollerTalonID);
	
	Relay suctionValveRelay = new Relay(RobotMap.suctionValveRelayChannel);
	DoubleSolenoid floorSolenoid = new DoubleSolenoid(RobotMap.deployFloorChannel,
	RobotMap.retractFloorChannel);
	DoubleSolenoid popoutSolenoid = new DoubleSolenoid(RobotMap.deployPopoutChannel,
	RobotMap.retractPopoutChannel);
	DoubleSolenoid lockingSolenoid = new DoubleSolenoid(RobotMap.lockManipulatorChannel, 
	RobotMap.unlockManipulatorChannel);
	
	
	final static double vacuumCurrentReducedPowerNoLoad = 0;
	final static double vacuumCurrentFullPowerLoad = 9999999;
	VacuumState currentVacuumState = VacuumState.kOff;
	
	public void lockManipulator() {
		lockingSolenoid.set(Value.kForward);
	}
	
	public void unlockManipulator() {
		lockingSolenoid.set(Value.kReverse);
	}
	
	//Controls the four-bar linkage
	public void actuateFloor(Value value) {
		floorSolenoid.set(value);
	}
	
	public Value getFloorPosition() {
		return floorSolenoid.get();
	}
	
	//Controls the suction valves
	public void openSuctionValve() {
		suctionValveRelay.set(Relay.Value.kForward);
	}
	
	public void closeSuctionValve() {
		suctionValveRelay.set(Relay.Value.kOff);
	}
	
	//Controls the vacuum pump
	public void setVacuum(boolean enabled) {
		currentVacuumState = VacuumState.kFullPower;
	}
	
	public boolean getSuctionValveClosed() {
		return suctionValveRelay.get() == Relay.Value.kOff ? true : false;
	}
	
	public double getVacuumCurrent() {
		return vacuumPumpTalon.getOutputCurrent();
	}
	
	public double getVacuumDutyCycle() {
		return vacuumPumpTalon.get();
	}
	
	public boolean getHatchAquired() {
		return currentVacuumState == VacuumState.kReducedPower;
	}
	
	@Override
	public void periodic() {
		SmartDashboard.putNumber("Vacuum Current", vacuumPumpTalon.getOutputCurrent());
		
		if(currentVacuumState == VacuumState.kFullPower &&
			vacuumPumpTalon.getOutputCurrent() > vacuumCurrentFullPowerLoad) {
			currentVacuumState = VacuumState.kReducedPower;   
		} else if(currentVacuumState == VacuumState.kReducedPower &&
					vacuumPumpTalon.getOutputCurrent() < vacuumCurrentReducedPowerNoLoad) {
			currentVacuumState = VacuumState.kFullPower;
		}
		
		vacuumPumpTalon.set(ControlMode.PercentOutput, currentVacuumState.get());
		Robot.oi.setHatchLED(getHatchAquired());
	}
	
	//Controls the popout piston
	public void actuatePopout(Value value) {
		popoutSolenoid.set(value);
	}
	
	public Value getPopoutPosition() {
		return popoutSolenoid.get();
	}
	
	//Controls the roller
	public void setRollerSpeed(double speed) {
		intakeController.set(ControlMode.PercentOutput,speed);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeJoystick());
	}
}
