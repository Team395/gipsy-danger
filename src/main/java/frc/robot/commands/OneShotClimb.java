/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.manipulator.ManipulatorClimbMode;

public class OneShotClimb extends CommandGroup {

  public OneShotClimb() {
    setInterruptible(false);
    addSequential(new ManipulatorClimbMode());
    addSequential(new LevelRobot());
    addParallel(new CommandGroup() {
      {
        addSequential(new DriveDownClimber());
        addSequential(new DriveForwardClimber());
      }
    });
    
    addSequential(new RetractToSafeDistance());
  }
}