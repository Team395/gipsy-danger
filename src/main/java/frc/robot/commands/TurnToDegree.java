package frc.robot.commands;

import frc.robot.Robot;

/**
 * A conveniance command which allows us to turn the robot to a certain field angle based on the gyro and TurnDegrees
 */
public class TurnToDegree extends TurnDegrees {
    public TurnToDegree(double angle) {
        super(
              (angle - Robot.gyro.getYaw()) % 360 > 180 ?
              (angle - Robot.gyro.getYaw()) % 360 - 360 :
              (angle - Robot.gyro.getYaw()) % 360
             );
    }
}