package frc.robot.commands;

import edu.wpi.first.wpilibj.command.ConditionalCommand;
import frc.robot.Robot;

public class ConditionalAutoIntake extends ConditionalCommand{
  public ConditionalAutoIntake() {
    super(new AutoIntakeCargo(), new AutoIntakeHatch());
  }

  @Override
  public boolean condition() {
    return Robot.oi.getHatchMode();
  }
}
