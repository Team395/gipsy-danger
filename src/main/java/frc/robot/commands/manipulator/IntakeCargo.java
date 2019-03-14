package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

/**
 * Intakes the cargo from the loading station.
 */

public class IntakeCargo extends TimedCommand {

  static final double intakeCargoTimeout = 2;
  public IntakeCargo() {
    super(intakeCargoTimeout);
    requires(Robot.manipulator);
    setInterruptible(false);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.manipulator.setRollerSpeed(-1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.manipulator.actuatePopout(Value.kReverse);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
