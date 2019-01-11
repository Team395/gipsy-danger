package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.TankDrive;

public class Drivetrain extends Subsystem {
    private final int LEFT_LEADER_TALON = 1;
    private final int LEFT_FOLLOWER_TALON = 2;
    private final int RIGHT_LEADER_TALON = 3;
    private final int RIGHT_FOLLOWER_TALON = 4;

    private final WPI_TalonSRX leftLeader = new WPI_TalonSRX(LEFT_LEADER_TALON);
    private final WPI_TalonSRX leftFollower = new WPI_TalonSRX(LEFT_FOLLOWER_TALON);
    private final WPI_TalonSRX rightLeader = new WPI_TalonSRX(RIGHT_LEADER_TALON);
    private final WPI_TalonSRX rightFollower = new WPI_TalonSRX(RIGHT_FOLLOWER_TALON);

    public Drivetrain(){
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        rightLeader.setInverted(true);
        rightFollower.setInverted(false);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        leftLeader.set(leftSpeed);
        rightLeader.set(rightSpeed);
    }
}
