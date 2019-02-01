package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

  //Controllers
  public static final int LEFT_JOYSTICK = 0;
  public static final int RIGHT_JOYSTICK = 1;
  public static final int XBOX_CONTROLLER = 2;

  //Drivetrain
  public static final int LEFT_LEADER_SPARK = 1;
  public static final int LEFT_FOLLOWER_SPARK = 3;
  public static final int RIGHT_LEADER_SPARK = 2;
  public static final int RIGHT_FOLLOWER_SPARK = 4;

}
