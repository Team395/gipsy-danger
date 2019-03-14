package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;


/**
 * Ejects the hatch at the end of a scoring sequence.
 */
public class EjectHatch extends TimedCommand {

  public static final double ejectHatchTime = 1;
  
  public EjectHatch() {
    super(1);
    requires(Robot.manipulator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.manipulator.actuatePopout(Value.kForward);
    Robot.manipulator.setVacuum(false);
    Robot.manipulator.openSuctionValve();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after timeout
  @Override
  protected void end() {
    Robot.manipulator.actuateFloor(Value.kReverse);
    Robot.manipulator.actuatePopout(Value.kReverse);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

}
