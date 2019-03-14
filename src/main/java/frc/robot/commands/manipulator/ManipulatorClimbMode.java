/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
 * Places the intake in a mode where it can climb.
 */
public class ManipulatorClimbMode extends TimedCommand {

  static double actuationTime = 1.5;
  public ManipulatorClimbMode() {
    super(actuationTime);
    requires(Robot.manipulator);
    setInterruptible(false);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.manipulator.actuateFloor(Value.kForward);
    Robot.manipulator.actuatePopout(Value.kReverse);
  }

  @Override
  protected void execute() {

  }


  @Override
  protected void end() {
    Robot.manipulator.lockManipulator();
  }

  @Override
  protected void interrupted() {

  }
}
