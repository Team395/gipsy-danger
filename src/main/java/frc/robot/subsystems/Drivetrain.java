package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.commands.TankDrive;

public class Drivetrain extends Subsystem {
    private final int LEFT_LEADER_TALON = 1;
    private final int LEFT_FOLLOWER_TALON = 2;
    private final int RIGHT_LEADER_TALON = 3;
    private final int RIGHT_FOLLOWER_TALON = 4;

    private final WPI_TalonSRX leftLeader    = Robot.controllerMap.getTalonByID(LEFT_LEADER_TALON);
    private final WPI_TalonSRX leftFollower  = Robot.controllerMap.getTalonByID(LEFT_FOLLOWER_TALON);
    private final WPI_TalonSRX rightLeader   = Robot.controllerMap.getTalonByID(RIGHT_LEADER_TALON);
    private final WPI_TalonSRX rightFollower = Robot.controllerMap.getTalonByID(RIGHT_FOLLOWER_TALON);

    public Drivetrain(){
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        rightLeader.setInverted(true);
        rightFollower.setInverted(true);
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

    
}
