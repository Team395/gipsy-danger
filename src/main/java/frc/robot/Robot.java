package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.ScoreDoubleRocketFromSide;
import frc.robot.autonomous.ScoreDoubleShipFromCenter;
import frc.robot.autonomous.ScoreDoubleShipFromSide;
import frc.robot.autonomous.ScoreSingleBackRocketFromSide;
import frc.robot.autonomous.ScoreSingleFromSide;
import frc.robot.autonomous.ScoreSingleFrontRocketFromSide;
import frc.robot.autonomous.ScoreSingleShipFromCenter;
import frc.robot.enums.AutoMode;
import frc.robot.enums.Side;
import frc.robot.subsystems.CargoManipulator;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.DrivetrainEncoders;
import frc.robot.subsystems.DrivetrainGyro;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.utils.limelight.Limelight;
import frc.robot.utils.limelight.Limelight.CamMode;
import frc.robot.utils.limelight.Limelight.Pipeline;

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
	public static Elevator elevator;// = new Elevator();
	public static CargoManipulator manipulator;// = new Manipulator();
	public static HatchManipulator hatchManipulator = new HatchManipulator();
	public static Drivetrain drivetrain = new Drivetrain();
	public static DrivetrainEncoders encoders = new DrivetrainEncoders();
	public static DrivetrainGyro gyro = new DrivetrainGyro();
	public static Climber climber;// = new Climber();
	
	static SendableChooser<Side> sideChooser = new SendableChooser<>();
	static SendableChooser<AutoMode> autoChooser = new SendableChooser<>();

	/**
	* This function is run when the robot is first started up and should be
	* used for any initialization code.
	*/
	@Override
	public void robotInit() {
		oi = new OI();
		oi.setUpTriggers();

		sideChooser.setDefaultOption("Start left or prefer left loading station.", Side.kLeft);
		//sideChooser.addOption("Start right or prefer right loading station.", Side.kRight);
		SmartDashboard.putData(sideChooser);
		
		// autoChooser.addOption("Score two on center from center", AutoMode.kDoubleCenterScoreFromCenter);
		// autoChooser.addOption("Score one on center from center", AutoMode.kSingleCenterScoreFromCenter);
		// autoChooser.addOption("Score two on center from side", AutoMode.kDoubleCenterScoreFromSide);
		// autoChooser.addOption("Score one on center from side", AutoMode.kSingleCenterScoreFromSide);
		// autoChooser.addOption("Score two on front rocket from side ", AutoMode.kDoubleFrontRocketScore);
		// autoChooser.addOption("Score one on front rocket from side", AutoMode.kSingleFrontRocketScore);
		// autoChooser.addOption("Score one on back rocket from side", AutoMode.kSingleBackRocketScore);
		autoChooser.setDefaultOption("Joystick control", AutoMode.kJoystickControl);
		SmartDashboard.putData(autoChooser);
		Limelight.setCamMode(CamMode.kVision);
		Limelight.switchPipeline(Pipeline.kDriverControl);
	}
	
	/**
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
		Side side = sideChooser.getSelected();
		Command command;

		switch(autoChooser.getSelected()) {
			case kDoubleCenterScoreFromCenter:
				command = new ScoreDoubleShipFromCenter(side);
				break;
			case kSingleCenterScoreFromCenter:
				command = new ScoreSingleShipFromCenter(side);
				break;
			case kDoubleCenterScoreFromSide:
				command = new ScoreDoubleShipFromSide(side);
				break;			
			case kSingleCenterScoreFromSide:
				command = new ScoreSingleFromSide(side);
				break;
			case kDoubleFrontRocketScore:
				command = new ScoreDoubleRocketFromSide(side);
				break;
			case kSingleFrontRocketScore:
				command = new ScoreSingleFrontRocketFromSide(side);
			case kSingleBackRocketScore:
				command = new ScoreSingleBackRocketFromSide(side);
			case kJoystickControl:
			default:
				command = null;
		}

		if(command != null){
			Scheduler.getInstance().add(command);
		}

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
	
	ArrayList<Solenoid> solenoids = new ArrayList<>();

	@Override
	public void testInit() {
		// super.testInit();
		
		// for(int i = 0; i < 8; i++) {
		// 	solenoids.add(new Solenoid(i));
		// }
		
		// SmartDashboard.putNumber("SolenoidToFire", 8);
	}
	/**
	* This function is called periodically during test mode.
	*/
	@Override
	public void testPeriodic() {
		
	}
}

