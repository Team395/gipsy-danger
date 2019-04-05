package frc.robot.triggers;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class XboxTriggerTrigger extends Trigger {
	
	final static double onThreshold = 0.8;
	
	GenericHID.Hand hand;
	XboxController controller;

	public XboxTriggerTrigger(XboxController controller, GenericHID.Hand hand) {
		this.controller = controller;
		this.hand = hand;
	}
	
	@Override
	public boolean get() {
		return controller.getTriggerAxis(hand) > onThreshold;
	}
}
