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
    public static final int driveShiftLowChannelID = 2;
    public static final int driveShiftHighChannelID = 3;

    //Intake
    public static final int intakeRollerTalonID = 11;
    public static final int vacuumPumpTalonID = -1;
    public static final int suctionValveRelayChannel = -1;
    public static final int deployPopoutChannel = -1;
    public static final int retractPopoutChannel = -1;
    public static final int deployFloorChannel = -1;
    public static final int retractFloorChannel = -1;
    public static final int lockManipulatorChannel = -1;
    public static final int unlockManipulatorChannel = -1;
    
    //Climber
    public static final int climberLeadScrewTalonID = 7;
    public static final int climberWheelPodTalonID = 6;
}
 