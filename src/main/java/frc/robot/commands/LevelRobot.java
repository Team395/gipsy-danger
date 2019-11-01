package frc.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Waits for all paralleled commands to end
 */
public class LevelRobot extends Command {

  final static double p = 1.0/15.0;
  final static double d = 1.0/20.0;

  //The forward/backward tilt axis is roll rather than pitch
  PIDController pidController = new PIDController(p, 0, d, Robot.gyro.getRollSource(), Robot.elevator.levelElevator());

  public LevelRobot() {
    requires(Robot.elevator);
    setInterruptible(false);
  }

  @Override
  protected void initialize() {
    pidController.enable();
    pidController.setSetpoint(Robot.gyro.getRoll());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pidController.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  @Override 
  protected boolean isFinished() {
    return false;
  }

}
