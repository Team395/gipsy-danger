package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainEncoders;
import frc.robot.subsystems.DrivetrainGyro;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.RollerIntake;

/**
* The VM is configured to automatically run this class, and to call the
* functions corresponding to each mode, as described in the TimedRobot
* documentation. If you change the name of this class or the package after
* creating this project, you must also update the build.gradle file in the
* project.
*/
public class Robot extends TimedRobot {
  public static OI oi;
  public static SpeedControllerMap speedControllerMap = new SpeedControllerMap();
  public static Elevator elevator = new Elevator();
  public static RollerIntake rollerIntake = new RollerIntake();
  public static Drivetrain drivetrain = new Drivetrain();
  public static DrivetrainEncoders encoders = new DrivetrainEncoders();
  public static DrivetrainGyro gyro = new DrivetrainGyro();
  public static Climber climber = new Climber();
  
  /**
  * This function is run when the robot is first started up and should be
  * used for any initialization code.
  */
  @Override
  public void robotInit() {
    oi = new OI();
    oi.setUpTriggers();
  }
  
  /**s
  * This function is called every robot packet, no matter the mode. Use
  * this for items like diagnostics that you want ran during disabled,
  * autonomous, teleoperated and test.
  *
  * This runs after the mode specific periodic functions, but before
  * LiveWindow and SmartDashboard integrated updating.
  */
  @Override
  public void robotPeriodic() {
  }
  
  /**
  * This function is called once each time the robot enters Disabled mode.
  * You can use it to reset any subsystem information you want to clear when
  * the robot is disabled.
  */
  @Override
  public void disabledInit() {
  }
  
  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }
  
  @Override
  public void autonomousInit() {
    
  }
  
  /**
  * This function is called periodically during autonomous.
  */
  @Override
  public void autonomousPeriodic() {
    if(oi.getCancelAuton()) {
      Scheduler.getInstance().removeAll();
    }
    Scheduler.getInstance().run();
  }
  
  @Override
  public void teleopInit() {
    
  }
  
  /**
  * This function is called periodically during operator control.
  */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }
	
	/**
	* This function is called periodically during test mode.
	*/
	@Override
	public void testPeriodic() {
	}
}
