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
import com.ctre.phoenix.motorcontrol.can.VictorSPXConfiguration;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX elevatorLeader = Robot.speedControllerMap.getTalonByID(RobotMap.elevatorLeaderTalonID);
  WPI_VictorSPX elevatorFollower = Robot.speedControllerMap.getVictorByID(RobotMap.elevatorFollowerVictorID);
  
  //Gear ratio is 21.32:1

  //Encoder moves 4096 ticks/rotation after reduction.
  //The elevator pulley is 24 teeth, 5mm pitch
  //Output is [0,1023] not [0,1]
  //Velocity is calculated as ticks/100 ms
  
  TalonSRXConfiguration leaderConfig = new TalonSRXConfiguration();
  VictorSPXConfiguration defaultConfig = new VictorSPXConfiguration();
  SlotConfiguration slot0 = new SlotConfiguration();
  SlotConfiguration slot1 = new SlotConfiguration();

  /**
   * 1 rot / 4096 ticks *
   * 16 teeth / rot *
   * 0.25 in / tooth = 
   * 0.0009765625 inches / tick
   * 1024 ticks / inch
   */

  final double inchesPerTick = 0.0009765625;
  final double ticksPerInch = 1024;
  
  final double allowableErrorInches = 0.25;
  final double climbingFeedforward = -0.18; 

  public Elevator() {
    slot0.kP = 0.5; //TODO
    slot0.kI = 0; //TODO
    slot0.kD = 0; //TODO
    slot0.kF = 0.22;

    slot0.integralZone = 0; 
    slot0.allowableClosedloopError = 0;

    leaderConfig.motionCruiseVelocity = 8000;
    leaderConfig.motionAcceleration = 3500; 
    
    leaderConfig.continuousCurrentLimit = 30;
    leaderConfig.peakCurrentLimit = 30;
    leaderConfig.peakCurrentDuration = 0;  
    
    leaderConfig.closedloopRamp = 0.5;
    leaderConfig.openloopRamp = 0.5;
    
    leaderConfig.reverseSoftLimitEnable = true;
    leaderConfig.reverseSoftLimitThreshold = 100;
    leaderConfig.forwardSoftLimitEnable = true;
    leaderConfig.forwardSoftLimitThreshold = (int) (11 * ticksPerInch); 
    
    leaderConfig.slot0 = slot0;

    //elevatorFollower.configAllSettings(defaultConfig);
    
    elevatorFollower.follow(elevatorLeader); 
    
    elevatorLeader.configAllSettings(leaderConfig);

    elevatorLeader.setSelectedSensorPosition(0);
    elevatorLeader.setSelectedSensorPosition(0, 0, 20);
  }

  public double getEndEffectorHeight() {
    return (elevatorLeader.getSelectedSensorPosition() * inchesPerTick);
  }

  public void setEndEffectorHeight(double inches) {
    int setpointTicks = (int) (inches * ticksPerInch);
    elevatorLeader.set(ControlMode.MotionMagic, setpointTicks, DemandType.ArbitraryFeedForward, 0.05);
  }

  public boolean onTarget() {
    return Math.abs(elevatorLeader.getClosedLoopError() * inchesPerTick) < 
                    allowableErrorInches;
  }
  

  public PIDOutput levelElevator() {
    return new PIDOutput(){
    
      @Override
      public void pidWrite(double output) {
        elevatorLeader.set(ControlMode.PercentOutput, output, DemandType.ArbitraryFeedForward, climbingFeedforward);
      }
    };
  }

  public void test(double speed) {
    elevatorLeader.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("ElevatorHeight", getEndEffectorHeight());
    SmartDashboard.putNumber("ElevatorSetpoint", elevatorLeader.getClosedLoopError() * inchesPerTick);
  }

  @Override
  public void initDefaultCommand() {
  }

  public void operatorControl(double speed) {
    elevatorLeader.set(ControlMode.PercentOutput, speed, DemandType.ArbitraryFeedForward, 0.05);
  }

}
