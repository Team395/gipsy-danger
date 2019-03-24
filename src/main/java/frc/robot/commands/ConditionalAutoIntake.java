// package frc.robot.commands;

// import edu.wpi.first.wpilibj.command.ConditionalCommand;
// import frc.robot.Robot;
// import frc.robot.enums.IntakeMode;

// public class ConditionalAutoIntake extends ConditionalCommand{
//   public ConditionalAutoIntake() {
//     super(new AutoIntakeCargo(), new AutoIntakeHatch());
//   }

//   @Override
//   public boolean condition() {
//     return Robot.manipulator.getIntakeMode() == IntakeMode.kHatchPanel;
//   }
// }
