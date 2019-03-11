package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.enums.TargetType;

public class ApproachTarget extends CommandGroup {

  public ApproachTarget(TargetType targetType) {
    addSequential(new AimAtTarget());
    addSequential(new DriveToTarget(targetType));
  }
  
}
