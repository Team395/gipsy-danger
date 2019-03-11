package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;

public class ControlBoard extends GenericHID {
	
	private enum Button {
		kElevatorHigh(1),
		kElevatorMedium(2),
		kElevatorLow(3),
		kElevatorIntake(4),
		kElevatorGround(5),
		kAutoIntake(6),
		kAutoScore(7),
		kDisableVacuum(8),
		kEnableVacuum(9),
		kRetractIntake(10),
		kRetractFourBar(11),
		kDeployFourBar(12),
		kFineAdjustUp(13),
		kFineAdjustDown(14),
		kIntakeOut(15),
		kIntakeIn(16),
		kLeftTarget(17),
		kClimber(18),
		kHatchMode(19),
		kCargoMode(20);
		
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

	public boolean getElevatorIntake(){
		return getRawButton(Button.kElevatorIntake.getChannel());
	}
	
	public boolean getElevatorIntakePressed(){
		return getRawButtonPressed(Button.kElevatorIntake.getChannel());
	}
	
	public boolean getElevatorIntakeReleased(){
		return getRawButtonReleased(Button.kElevatorIntake.getChannel());
	}

	public boolean getElevatorGround(){
		return getRawButton(Button.kElevatorGround.getChannel());
	}
	
	public boolean getElevatorGroundPressed(){
		return getRawButtonPressed(Button.kElevatorGround.getChannel());
	}
	
	public boolean getElevatorGroundReleased(){
		return getRawButtonReleased(Button.kElevatorGround.getChannel());
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

	public boolean getFineAdjustUp(){
		return getRawButton(Button.kFineAdjustUp.getChannel());
	}
	
	public boolean getFineAdjustUpPressed(){
		return getRawButtonPressed(Button.kFineAdjustUp.getChannel());
	}
	
	public boolean getFineAdjustUpReleased(){
		return getRawButtonReleased(Button.kFineAdjustUp.getChannel());
	}

	public boolean getFineAdjustDown(){
		return getRawButton(Button.kFineAdjustDown.getChannel());
	}
	
	public boolean getFineAdjustDownPressed(){
		return getRawButtonPressed(Button.kFineAdjustDown.getChannel());
	}
	
	public boolean getFineAdjustDownReleased(){
		return getRawButtonReleased(Button.kFineAdjustDown.getChannel());
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
	
	@Override
	public double getRawAxis(int channel) {
		throw new UnsupportedOperationException();
	}
}