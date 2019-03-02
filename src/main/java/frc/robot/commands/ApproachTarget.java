package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ApproachTarget extends CommandGroup {

  public ApproachTarget() {
    addSequential(new AimAtTarget());
    //TODO: Decide this based on robot state
    addSequential(new DriveToTarget(DriveToTarget.TargetType.kLowTarget));
  }
  
}
