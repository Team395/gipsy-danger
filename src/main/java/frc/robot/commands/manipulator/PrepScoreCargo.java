// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.commands.manipulator;

// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
// import edu.wpi.first.wpilibj.command.InstantCommand;
// import frc.robot.Robot;

// /**
//  * Prepares the robot to score a cargo.
//  */
// public class PrepScoreCargo extends InstantCommand {

//   public PrepScoreCargo() {
//     super();
//     requires(Robot.manipulator);
//   }

//   // Called once when the command executes
//   @Override
//   protected void initialize() {
//     Robot.manipulator.actuateFloor(Value.kForward);
//     Robot.manipulator.actuatePopout(Value.kForward);
//   }

// }
