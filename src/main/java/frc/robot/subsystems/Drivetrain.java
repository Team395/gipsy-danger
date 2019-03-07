package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.util.WPILibVersion;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;
import frc.robot.utils.LinearOutput;
import frc.robot.utils.SparkMAX;

public class Drivetrain extends Subsystem {

    public enum Gear {
        kHigh,
        kLow;
    }

	WPI_TalonSRX leftLeader    = Robot.speedControllerMap.getTalonByID(RobotMap.driveLeftLeaderSparkID);
	WPI_TalonSRX leftFollower  = Robot.speedControllerMap.getTalonByID(RobotMap.driveLeftFollowerSparkID);
	WPI_TalonSRX rightLeader   = Robot.speedControllerMap.getTalonByID(RobotMap.driveRightLeaderSparkID);
	WPI_TalonSRX rightFollower = Robot.speedControllerMap.getTalonByID(RobotMap.driveRightFollowerSparkID);

    public Drivetrain(){
        leftLeader.setInverted(false);
        leftFollower.setInverted(false);
        rightLeader.setInverted(true);
        rightFollower.setInverted(true);

        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftLeader.set(leftSpeed);
        rightLeader.set(rightSpeed);
    }

    public PIDOutput getTurnOutput() {
        return new PIDOutput(){
        
            @Override
            public void pidWrite(double output) {
                tankDrive(-output, output);
            }
        };
    }

    public LinearOutput getLinearOutput(){
        return new LinearOutput(this);
    }

    public void shift(Gear gear) {
    }
}
