package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.commands.TankDrive;
import frc.robot.utils.SparkMAX;

public class Drivetrain extends Subsystem {

    public enum Gear {
        kHigh,
        kLow;
    }

    SparkMAX leftFollower  = Robot.controllerMap.getSparkByID(RobotMap.LEFT_FOLLOWER_SPARK);
    SparkMAX rightLeader   = Robot.controllerMap.getSparkByID(RobotMap.RIGHT_LEADER_SPARK);
    SparkMAX leftLeader    = Robot.controllerMap.getSparkByID(RobotMap.LEFT_LEADER_SPARK);
    SparkMAX rightFollower = Robot.controllerMap.getSparkByID(RobotMap.RIGHT_FOLLOWER_SPARK);

    DoubleSolenoid shifter = new DoubleSolenoid(0,1);

    public Drivetrain(){
        leftLeader.setInverted(true);
        leftFollower.setInverted(true);
        rightLeader.setInverted(false);
        rightFollower.setInverted(false);

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

    //TODO: Look at this for cleanliness
    public PIDOutput getTurnOutput() {
        return new PIDOutput(){
        
            @Override
            public void pidWrite(double output) {
                tankDrive(-output, output);
            }
        };
    }

    public LinearOutput getLinearOutput(){
        return new LinearOutput();
    }

    //TODO: Look at this for cleanliness
    public class LinearOutput implements PIDOutput {
        private double headingCorrection = 0;

        public void setHeadingCorrection(double headingCorrection) {
            this.headingCorrection = headingCorrection;
        }

        @Override
        public void pidWrite(double output) {
            tankDrive(output - headingCorrection, output + headingCorrection);
        }
    }

    public void shift(Gear gear) {
        if(gear == Gear.kHigh)
            shifter.set(Value.kForward);
        else if(gear == Gear.kLow)
            shifter.set(Value.kReverse);
        Robot.encoders.setGearing(gear);
    }

    public Gear getShifterState() {
        return shifter.get() == Value.kForward ? Gear.kHigh : Gear.kLow;
    }
}
