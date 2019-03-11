/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems.manipulator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class SuctionCups extends Subsystem {
  Relay suctionValveRelay = new Relay(RobotMap.suctionValveRelayChannel);
  WPI_TalonSRX vacuumPumpTalon = Robot.speedControllerMap.getTalonByID(RobotMap.vacuumPumpTalonID);

  public void openSuctionValve() {
    suctionValveRelay.set(Value.kOn);
  }

  public void closeSuctionValve() {
    suctionValveRelay.set(Value.kOff);
  }

  public void setVacuum(double dutyCycle) {
    vacuumPumpTalon.set(dutyCycle);
  }

  public boolean getSuctionValveClosed() {
    return suctionValveRelay.get() == Value.kOff ? true : false;
  }
  
  public double getVacuumCurrent() {
    return vacuumPumpTalon.getOutputCurrent();
  }

  public double getVacuumDutyCycle() {
    return vacuumPumpTalon.get();
  }

  @Override
  public void initDefaultCommand() {

  }
}
