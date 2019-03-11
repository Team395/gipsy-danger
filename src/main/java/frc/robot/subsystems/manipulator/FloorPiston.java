/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.manipulator;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class FloorPiston extends Subsystem {
  DoubleSolenoid floorSolenoid = new DoubleSolenoid(RobotMap.deployFloorChannel,
                                                    RobotMap.retractFloorChannel);
  
  public void actuateFloor(Value value) {
      floorSolenoid.set(value);
  }

  public Value getfloorPosition() {
      return floorSolenoid.get();
  }

  @Override
  public void initDefaultCommand() {
  }
}
