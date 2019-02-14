/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.IntakeTest;

public class GroundIntake extends Subsystem {

  WPI_TalonSRX intakeController = new WPI_TalonSRX(RobotMap.groundIntakeTalon);
  DoubleSolenoid deploySolenoid = new DoubleSolenoid(RobotMap.deployIntakeChannel, 
                                                     RobotMap.retractIntakeChannel);

  public void setRollerSpeed(double speed) {
    if(deploySolenoid.get() == Value.kForward) {
      intakeController.set(ControlMode.PercentOutput,speed);
    } else {
      intakeController.set(0);
    }
  }

  public void actuateIntake(Value value) {
    deploySolenoid.set(value);
    
  }

  public Value getIntakePosition() {
    return deploySolenoid.get();
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new IntakeTest());
  }
}
