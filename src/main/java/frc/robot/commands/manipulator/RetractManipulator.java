// package frc.robot.commands.manipulator;

// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj.command.InstantCommand;
// import edu.wpi.first.wpilibj.command.Scheduler;
// import frc.robot.Robot;

// /**
// * Retracts the manipulator while on defense.
// */
// public class RetractManipulator extends InstantCommand {
// 	public RetractManipulator() {
// 		super(Robot.manipulator);
// 	}
	
// 	// Called once when the command executes
// 	@Override
// 	protected void initialize() {
// 		Robot.manipulator.actuateFloor(Value.kReverse);
// 		Robot.manipulator.actuatePopout(Value.kReverse);
// 		Scheduler.getInstance().add(new EjectCargo());
// 	}
	
// }
