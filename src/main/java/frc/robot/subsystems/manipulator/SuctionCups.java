/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.manipulator;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class SuctionCups extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Solenoid suctionCupSolenoid = new Solenoid(RobotMap.suctionCupChannel);

  public void actuateSuction(boolean on) {
    suctionCupSolenoid.set(on);
  }

  public boolean getSuctionState() {
    return suctionCupSolenoid.get();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }
}
