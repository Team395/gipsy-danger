package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import frc.robot.Robot;

/**
 * Prepares the intake to take in a hatch.
 */
public class PrepIntakeHatch extends InstantCommand {

  public PrepIntakeHatch() {
    super();
    requires(Robot.manipulator);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {

  }

}
