package frc.robot;

public class RobotMap {
    //Roller Manipulator
    //TODO: might be flipped
    public static final int intakeRollerTopTalonID = 9;
    public static final int intakeRollerBottomTalonId = 10;

    //Hatch Manipulator
    public static final int scoreSolenoidForwardChannelID = 7;
    public static final int scoreSolenoidReverseChannelID = 3;
    public static final int deploySolenoidForwardChannelID = 2;
    public static final int deploySolenoidReverseChannelID = 6;

    //Climber
    public static final int climberLeadScrewTalonID = 7;  
    public static final int climberWheelPodTalonID = 6;
    public static final int rightLockServoPWM = 1; //TODO: fix
    public static final int leftLockServoPWM = 2; //TODO: fix

    //Elevator
    public static int elevatorLeaderTalonID = 8; 
    public static int elevatorFollowerVictorID = 12;

    //Drive
    public static final int driveLeftLeaderSparkID = 1;
    public static final int driveLeftFollowerSparkID = 2;
    public static final int driveRightLeaderSparkID = 3;   public static final int driveRightFollowerSparkID = 4;
    public static final int driveShiftLowChannelID = 1;
    public static final int driveShiftHighChannelID = 0;

    //Pigeon
    public static final int pigeonIMUID = 0;

}
 