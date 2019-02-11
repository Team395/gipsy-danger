/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.SlotConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX elevatorLeader = Robot.speedControllerMap.getTalonByID(RobotMap.elevatorLeaderTalon);
  WPI_TalonSRX elevatorFollower = Robot.speedControllerMap.getTalonByID(RobotMap.elevatorFollowerTalon);
  
  //Gear ratio is 21.32:1

  //Encoder moves 4096 ticks/rotation after reduction.
  //The elevator pulley is 24 teeth, 5mm pitch
  //Output is [0,1023] not [0,1]
  //Velocity is calculated as ticks/100 ms
  
  TalonSRXConfiguration leaderConfig = new TalonSRXConfiguration();
  TalonSRXConfiguration defaultConfig = new TalonSRXConfiguration();
  SlotConfiguration slot0 = new SlotConfiguration();

  /**
   * 1 rot / 4096 ticks *
   * 24 teeth / rot *
   * 5 mm / tooth *
   * 1 in / 25.4 mm =
   * 0.00115342 inches / tick
   * 866.986 ticks / inch
   */

  final double inchesPerTick = 0.00115342;
  final double ticksPerInch = 866.986;
  final double cascadeCorrection = 2;
  final double heightOffset = 0;
  
  final double allowableErrorInches = 0.25; 

  public Elevator() {
    
    slot0.kP = 0.5; //TODO
    slot0.kI = 0; //TODO
    slot0.kD = 0; //TODO
    slot0.kF = 0.27;//0.2046; //5000 units/100 ms

    slot0.integralZone = 0; //TODO
    slot0.allowableClosedloopError = 0;

    leaderConfig.motionCruiseVelocity = 5000; //TODO
    leaderConfig.motionAcceleration = 9000; //TODO
    
    leaderConfig.continuousCurrentLimit = 30;
    leaderConfig.peakCurrentLimit = 30;
    leaderConfig.peakCurrentDuration = 500;  
    
    leaderConfig.closedloopRamp = 0.15;
    leaderConfig.openloopRamp = 0.15;
    
    leaderConfig.reverseSoftLimitEnable = true;
    leaderConfig.reverseSoftLimitThreshold = 100;
    leaderConfig.forwardSoftLimitEnable = true;
    leaderConfig.forwardSoftLimitThreshold = 36000;//TODO
    
    leaderConfig.slot0 = slot0;

    elevatorFollower.follow(elevatorLeader); 
    
    elevatorLeader.configAllSettings(leaderConfig);
    elevatorFollower.configAllSettings(defaultConfig);
  }

  public double getEndEffectorHeight() {
    return (elevatorLeader.getSelectedSensorPosition() * inchesPerTick * cascadeCorrection) + 
           heightOffset;
  }

  public void setEndEffectorHeight(double inches) {
    int setpointTicks = (int) ((inches - heightOffset) * ticksPerInch / 
                        (cascadeCorrection));
    elevatorLeader.set(ControlMode.MotionMagic, setpointTicks, DemandType.ArbitraryFeedForward, 0.05);
  }

  public boolean onTarget() {
    return Math.abs(elevatorLeader.getClosedLoopError() * inchesPerTick) < 
                    allowableErrorInches;
  }
 
  public void test(double speed) {
    elevatorLeader.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
