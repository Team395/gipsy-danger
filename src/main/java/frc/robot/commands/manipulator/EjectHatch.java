package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;


/**
 * Ejects the hatch at the end of a scoring sequence.
 */
public class EjectHatch extends InstantCommand {
  
  public EjectHatch() {
    requires(Robot.hatchManipulator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after timeout
  @Override
  protected void end() {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

}
