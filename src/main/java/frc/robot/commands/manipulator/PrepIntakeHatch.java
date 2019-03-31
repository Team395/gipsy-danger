package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.Robot;
import frc.robot.subsystems.HatchManipulator;

/**
 * Prepares the intake to take in a hatch.
 */
public class PrepIntakeHatch extends InstantCommand {

  public PrepIntakeHatch() {
    super();
    requires(Robot.hatchManipulator);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.hatchManipulator.setHatchSolenoid(Value.kForward);
  }

}
