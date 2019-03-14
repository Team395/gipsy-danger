/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.manipulator.IntakeHatch;
import frc.robot.commands.manipulator.PrepIntakeHatch;
import frc.robot.enums.TargetType;

public class AutoIntakeHatch extends CommandGroup {
  public AutoIntakeHatch() {
    addSequential(new ElevatorPreset(ElevatorPreset.Height.kHatchLoading));
    addParallel(new PrepIntakeHatch());
    addSequential(new ApproachTarget(TargetType.kLowTarget));
    addSequential(new IntakeHatch());
  }
}
