/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.TestPIDCommand;
import frc.robot.utils.PIDTuner;
import frc.robot.utils.REVPIDTuner;

/**
 * Add your docs here.
 */
public class TestPIDSubsystem extends Subsystem {
  CANSparkMax testMotor = new CANSparkMax(1, MotorType.kBrushless);
  CANPIDController controller = testMotor.getPIDController();
  CANEncoder encoder = testMotor.getEncoder();
  double setpoint = 0;
  double error = 0;

  public TestPIDSubsystem() {
    super("TestNEO");
    controller.setOutputRange(-1, 1, 0);
    controller.setOutputRange(-1, 1, 1);
  }

  public PIDTuner getTuner() {
    return new REVPIDTuner(super.getName(), controller);
  }
  
  public void setPosition(double rotations) {
    controller.setReference(rotations, ControlType.kPosition, 0);
    setpoint = rotations;
    error = rotations - getPosition();
  }

  public void setSpeed(double rpm) {
    controller.setReference(rpm, ControlType.kVelocity, 1);
    setpoint = rpm;
    error = rpm - getVelocity();
  }


  public void zero() {
    controller.setReference(0, ControlType.kVoltage);
    error = 0;
    setpoint = 0;
  }

  public double getPosition() {
    return encoder.getPosition();
  }

  public double getVelocity() {
    return encoder.getVelocity();
  }
  
  public void printTelemetry() {
    SmartDashboard.putNumber("Current Position", getPosition());
    SmartDashboard.putNumber("Current Velocity", getVelocity());
    SmartDashboard.putNumber("Output", testMotor.getAppliedOutput());
    SmartDashboard.putNumber("Error", error);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TestPIDCommand());
  }
}


