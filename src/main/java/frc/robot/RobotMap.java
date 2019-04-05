package frc.robot;

public class RobotMap {

    //Elevator
    public static int elevatorLeaderTalonID = 8; 
    public static int elevatorFollowerVictorID = 12;

    //Drive
    public static final int driveLeftLeaderSparkID = 1;
    public static final int driveLeftFollowerSparkID = 2;
    public static final int driveRightLeaderSparkID = 3;
    public static final int driveRightFollowerSparkID = 4;
    public static final int driveShiftLowChannelID = 0;  
    public static final int driveShiftHighChannelID = 1; 

    //Roller Manipulator
    public static final int intakeRollerTopTalonID = 10;
    public static final int intakeRollerBottomTalonId = 11;

    //Hatch Manipulator
    public static final int scoreSolenoidForwardChannelID = 6;
    public static final int scoreSolenoidReverseChannelID = 7;
    public static final int deploySolenoidForwardChannelID = 2;
    public static final int deploySolenoidReverseChannelID = 3;

    //Climber
    public static final int climberLeadScrewTalonID = 7;  
    public static final int climberWheelPodTalonID = 6;
    public static final int rightLockServoPWM = -1;
    public static final int leftLockServoPWM = -1;  

    //Pigeon
    public static final int pigeonIMUTalonID = 10;
}
 