package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControlBoard extends GenericHID {
	
	private DriverStation m_ds = DriverStation.getInstance();

	private enum Axis {
		kFineAdjustUp(7),
		kFineAdjustDown(8);

		private int channel;
		
		private Axis(int channel) {
			this.channel = channel;
		}

		public int getChannel() {
			return channel;
		}
	}

	public enum Button {
		kElevatorHigh(13),
		kElevatorMedium(2),
		kElevatorLow(3),
		kElevatorShip(4),
		kElevatorIntake(5),
		kAutoIntake(8),
		kAutoScore(9),
		kDisableVacuum(6),
		kEnableVacuum(7),
		kRetractIntake(10),
		kRetractFourBar(11),
		kDeployFourBar(12),
		kIntakeOut(16),
		kIntakeIn(15),
		kLeftTarget(17),
		kClimber(18),
		kHatchMode(19),
		kCargoMode(20);
		//dead pins 1, 13, 14
		private int channel;
		
		public int getChannel() {
			return channel;
		}
		
		private Button(int channel) {
			this.channel = channel;
		}
		
	}
	
	public ControlBoard(int port) {
		super(port);
	}
	
	public double getX(Hand hand) {
		throw new UnsupportedOperationException();
	}
	
	public double getY(Hand hand) {
		throw new UnsupportedOperationException();
	}
	
	public boolean getElevatorHigh(){
		return getRawButton(Button.kElevatorHigh.getChannel());
	}
	
	public boolean getElevatorHighPressed(){
		return getRawButtonPressed(Button.kElevatorHigh.getChannel());
	}
	
	public boolean getElevatorHighReleased(){
		return getRawButtonReleased(Button.kElevatorHigh.getChannel());
	}
	
	public boolean getElevatorMedium(){
		return getRawButton(Button.kElevatorMedium.getChannel());
	}
	
	public boolean getElevatorMediumPressed(){
		return getRawButtonPressed(Button.kElevatorMedium.getChannel());
	}
	
	public boolean getElevatorMediumReleased(){
		return getRawButtonReleased(Button.kElevatorMedium.getChannel());
	}

	public boolean getElevatorLow(){
		return getRawButton(Button.kElevatorLow.getChannel());
	}
	
	public boolean getElevatorLowPressed(){
		return getRawButtonPressed(Button.kElevatorLow.getChannel());
	}
	
	public boolean getElevatorLowReleased(){
		return getRawButtonReleased(Button.kElevatorLow.getChannel());
	}

	public boolean getElevatorShip(){
		return getRawButton(Button.kElevatorShip.getChannel());
	}
	
	public boolean getElevatorShipPressed(){
		return getRawButtonPressed(Button.kElevatorShip.getChannel());
	}
	
	public boolean getElevatorShipReleased(){
		return getRawButtonReleased(Button.kElevatorShip.getChannel());
	}

	public boolean getElevatorIntake(){
		return getRawButton(Button.kElevatorIntake.getChannel());
	}
	
	public boolean getElevatorIntakePressed(){
		return getRawButtonPressed(Button.kElevatorIntake.getChannel());
	}
	
	public boolean getElevatorIntakeReleased(){
		return getRawButtonReleased(Button.kElevatorIntake.getChannel());
	}

	public boolean getAutoIntake(){
		return getRawButton(Button.kAutoIntake.getChannel());
	}
	
	public boolean getAutoIntakePressed(){
		return getRawButtonPressed(Button.kAutoIntake.getChannel());
	}
	
	public boolean getAutoIntakeReleased(){
		return getRawButtonReleased(Button.kAutoIntake.getChannel());
	}

	public boolean getAutoScore(){
		return getRawButton(Button.kAutoScore.getChannel());
	}
	
	public boolean getAutoScorePressed(){
		return getRawButtonPressed(Button.kAutoScore.getChannel());
	}
	
	public boolean getAutoScoreReleased(){
		return getRawButtonReleased(Button.kAutoScore.getChannel());
	}

	public boolean getDisableVacuum(){
		return getRawButton(Button.kDisableVacuum.getChannel());
	}
	
	public boolean getDisableVacuumPressed(){
		return getRawButtonPressed(Button.kDisableVacuum.getChannel());
	}
	
	public boolean getDisableVacuumReleased(){
		return getRawButtonReleased(Button.kDisableVacuum.getChannel());
	}

	public boolean getEnableVacuum(){
		return getRawButton(Button.kEnableVacuum.getChannel());
	}
	
	public boolean getEnableVacuumPressed(){
		return getRawButtonPressed(Button.kEnableVacuum.getChannel());
	}
	
	public boolean getEnableVacuumReleased(){
		return getRawButtonReleased(Button.kEnableVacuum.getChannel());
	}

	public boolean getRetractIntake(){
		return getRawButton(Button.kRetractIntake.getChannel());
	}
	
	public boolean getRetractIntakePressed(){
		return getRawButtonPressed(Button.kRetractIntake.getChannel());
	}
	
	public boolean getRetractIntakeReleased(){
		return getRawButtonReleased(Button.kRetractIntake.getChannel());
	}

	public boolean getRetractFourBar(){
		return getRawButton(Button.kRetractFourBar.getChannel());
	}
	
	public boolean getRetractFourBarPressed(){
		return getRawButtonPressed(Button.kRetractFourBar.getChannel());
	}
	
	public boolean getRetractFourBarReleased(){
		return getRawButtonReleased(Button.kRetractFourBar.getChannel());
	}

	public boolean getDeployFourBar(){
		return getRawButton(Button.kDeployFourBar.getChannel());
	}
	
	public boolean getDeployFourBarPressed(){
		return getRawButtonPressed(Button.kDeployFourBar.getChannel());
	}
	
	public boolean getDeployFourBarReleased(){
		return getRawButtonReleased(Button.kDeployFourBar.getChannel());
	}

	public double getFineAdjustUp(){
		return getRawAxis(Axis.kFineAdjustUp.getChannel());
	}

	public double getFineAdjustDown(){
		return getRawAxis(Axis.kFineAdjustDown.getChannel());
	}
	
	public boolean getIntakeOut(){
		return getRawButton(Button.kIntakeOut.getChannel());
	}
	
	public boolean getIntakeOutPressed(){
		return getRawButtonPressed(Button.kIntakeOut.getChannel());
	}
	
	public boolean getIntakeOutReleased(){
		return getRawButtonReleased(Button.kIntakeOut.getChannel());
	}

	public boolean getIntakeIn(){
		return getRawButton(Button.kIntakeIn.getChannel());
	}
	
	public boolean getIntakeInPressed(){
		return getRawButtonPressed(Button.kIntakeIn.getChannel());
	}
	
	public boolean getIntakeInReleased(){
		return getRawButtonReleased(Button.kIntakeIn.getChannel());
	}

	public boolean getLeftTarget(){
		return getRawButton(Button.kLeftTarget.getChannel());
	}
	
	public boolean getLeftTargetPressed(){
		return getRawButtonPressed(Button.kLeftTarget.getChannel());
	}
	
	public boolean getLeftTargetReleased(){
		return getRawButtonReleased(Button.kLeftTarget.getChannel());
	}

	public boolean getClimber(){
		return getRawButton(Button.kClimber.getChannel());
	}
	
	public boolean getClimberPressed(){
		return getRawButtonPressed(Button.kClimber.getChannel());
	}
	
	public boolean getClimberReleased(){
		return getRawButtonReleased(Button.kClimber.getChannel());
	}

	public boolean getHatchMode(){
		return getRawButton(Button.kHatchMode.getChannel());
	}
	
	public boolean getHatchModePressed(){
		return getRawButtonPressed(Button.kHatchMode.getChannel());
	}
	
	public boolean getHatchModeReleased(){
		return getRawButtonReleased(Button.kHatchMode.getChannel());
	}

	public boolean getCargoMode(){
		return getRawButton(Button.kCargoMode.getChannel());
	}
	
	public boolean getCargoModePressed(){
		return getRawButtonPressed(Button.kCargoMode.getChannel());
	}
	
	public boolean getCargoModeReleased(){
		return getRawButtonReleased(Button.kCargoMode.getChannel());
	}

	public void setHatchAquired(boolean lit) {
		setOutput(1, lit);
	}

	public void setAutoscoreMode(boolean lit) {
		setOutput(2, lit);
	}
}