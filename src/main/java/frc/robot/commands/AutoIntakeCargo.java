/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.manipulator.IntakeCargo;
import frc.robot.commands.manipulator.PrepIntakeCargo;
import frc.robot.enums.TargetType;
import frc.robot.Robot;

public class AutoIntakeCargo extends CommandGroup {
  public AutoIntakeCargo() {
    addSequential(new ElevatorPreset(ElevatorPreset.Height.kHatchLoading));
    addParallel(new PrepIntakeCargo());
    addSequential(new ApproachTarget(TargetType.kLowTarget));
    addSequential(new IntakeCargo());
  }

  @Override
	protected boolean isFinished() {
		return Robot.oi.getCancelAuton() || super.isFinished();
	}
}
