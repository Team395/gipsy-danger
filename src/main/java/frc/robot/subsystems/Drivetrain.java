package frc.robot.subsystems;


import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
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

    SparkMAX leftLeader    = Robot.speedControllerMap.getSparkByID(RobotMap.leftLeaderSpark);
    SparkMAX leftFollower  = Robot.speedControllerMap.getSparkByID(RobotMap.leftFollowerSpark);
    SparkMAX rightLeader   = Robot.speedControllerMap.getSparkByID(RobotMap.rightLeaderSpark);
    SparkMAX rightFollower = Robot.speedControllerMap.getSparkByID(RobotMap.rightFollowerSpark);


    DoubleSolenoid shifter = new DoubleSolenoid(RobotMap.shiftLowChannel
        , RobotMap.shiftHighChannel);

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