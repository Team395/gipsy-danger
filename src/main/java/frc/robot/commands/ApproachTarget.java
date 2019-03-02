package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ApproachTarget extends CommandGroup {

  public ApproachTarget() {
    addSequential(new AimAtTarget());
    addSequential(new DriveToTarget());
  }
  
}
