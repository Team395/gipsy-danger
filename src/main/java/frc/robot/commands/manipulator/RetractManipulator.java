/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;

/**
 * Retracts the manipulator while on defense.
 */
public class RetractManipulator extends InstantCommand {
  public RetractManipulator() {
    super(Robot.manipulator);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    Robot.manipulator.actuateFloor(Value.kReverse);
    Robot.manipulator.actuatePopout(Value.kReverse);
    Robot.manipulator.setRollerSpeed(0);
  }

}
