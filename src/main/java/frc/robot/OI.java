package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.ConditionalCommand;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.commands.AimAtOffset;
import frc.robot.commands.ApproachTarget;
import frc.robot.commands.AutoIntakeHatch;
import frc.robot.commands.AutoScoreHatch;
import frc.robot.commands.DriveFeet;
import frc.robot.enums.TargetType;
import frc.robot.triggers.ElevatorTrigger;

public class OI {
    Joystick leftJoystick = new Joystick(0);
    Joystick rightJoystick = new Joystick(1);
    XboxController xboxController = new XboxController(2);

	static final double joystickDeadzone = 0.1;
    
    public ElevatorTrigger elevatorTrigger;//= new ElevatorTrigger();
    

    // Button elevatorMedium  = new JoystickButton(xboxController, 4);
	// Button elevatorLow     = new JoystickButton(xboxController, 2);
    // Button elevatorShip    = new JoystickButton(xboxController, 1);
    // Button elevatorIntake  = new JoystickButton(xboxController, 9); 
    // Button toggleGamePiece = new JoystickButton(xboxController, 8);
    // Button retractIntake   = new JoystickButton(xboxController, 7);
    
    Button driveOffStep = new JoystickButton(leftJoystick, 10);

    Button deployHatchManipulator = new JoystickButton(xboxController, 6);
    Button retractHatchManipulator = new JoystickButton(xboxController, 5);
    Button autoScoreHatch = new JoystickButton(xboxController, 3);
    Button approachTarget = new JoystickButton(xboxController, 4);
    Button autoIntakeHatch = new JoystickButton(xboxController, 2);
    Button turnToOffset = new JoystickButton(xboxController, 1);

    public void setUpTriggers() {
        deployHatchManipulator.whenPressed(
            new InstantCommand(
                Robot.hatchManipulator,
                () -> Robot.hatchManipulator.setHatchSolenoid(Value.kForward)
            )
        );

        retractHatchManipulator.whenPressed(
            new InstantCommand(
                Robot.hatchManipulator,
                () -> Robot.hatchManipulator.setHatchSolenoid(Value.kReverse)
            )
        );

        approachTarget.whenPressed(new ApproachTarget(TargetType.kLowTarget));
        autoIntakeHatch.whenPressed(new AutoIntakeHatch());
        autoScoreHatch.whenPressed(new AutoScoreHatch());
        turnToOffset.whenPressed(new AimAtOffset(TargetType.kLowTarget));

        // elevatorHigh.whenPressed(new ElevatorPreset(PresetHeight.kHigh));
		// elevatorMedium.whenPressed(new ElevatorPreset(PresetHeight.kMedium));
		// elevatorLow.whenPressed(new ElevatorPreset(PresetHeight.kLow));
		// elevatorShip.whenPressed(new ElevatorPreset(PresetHeight.kShip));
		// elevatorIntake.whenPressed(new ElevatorPreset(PresetHeight.kZero));
        // elevatorTrigger.whenActive(new ElevatorJoystick()); 

        /**
         * TODO: Replace
         *  autoIntake.whenPressed(new ConditionalAutoIntake());
         *	autoScore.whenPressed(new AutoScoreChooser());
         */

         //TODO: Remove
        // autoIntake.whenPressed(
        //     new ConditionalCommand(
        //         new PrepIntakeCargo(),
        //         new PrepIntakeHatch()) {
        //             @Override
        //             public boolean condition(){
        //                 return Robot.oi.getCargoMode();
        //             }
        //     }
        // );
        
        // //TODO: Remove
        // autoScore.whenPressed(
        //     new ConditionalCommand(
        //         new EjectCargo(),
        //         new EjectHatch()) {
        //             @Override
        //             public boolean condition(){
        //                 return Robot.oi.getCargoMode();
        //             }
        //     }
        // );

        
        // // enableClimber.whenPressed(new OneShotClimb());
        
        // toggleGamePiece.whenPressed(
        //     new InstantCommand(
        //         Robot.manipulator,
        //         () -> {
        //             if(Robot.manipulator.getPopoutPosition() == Value.kReverse) {
        //                 Robot.manipulator.actuatePopout(Value.kForward);
        //             } else if(Robot.manipulator.getPopoutPosition() == Value.kForward) {
        //                 Robot.manipulator.actuatePopout(Value.kReverse);
        //             } else {
        //                 Robot.manipulator.actuatePopout(Value.kReverse);
        //             }
        //             // if(Robot.manipulator.getIntakeMode() == IntakeMode.kCargo) {
        //             //     Robot.manipulator.setIntakeMode(IntakeMode.kHatchPanel);
        //             //     Robot.manipulator.actuatePopout(Value.kReverse);
        //             // } if(Robot.manipulator.getIntakeMode() == IntakeMode.kHatchPanel) {
        //             //     Robot.manipulator.setIntakeMode(IntakeMode.kCargo);
        //             //     Robot.manipulator.actuatePopout(Value.kForward);
        //             // }
        //         }
        //     )
        // );
    
        // hatchMode.whenPressed(
        //     new InstantCommand(
        //         Robot.manipulator, 
        //         () -> Robot.manipulator.actuatePopout(Value.kReverse)
        //     ) 
        // );

        // cargoMode.whenPressed(
        //     new InstantCommand(
        //         Robot.manipulator, 
        //         () -> Robot.manipulator.actuatePopout(Value.kForward)
        //     ) 
        // );

        //retractIntake.whenPressed(new RetractManipulator());
    
        driveOffStep.whenPressed(
            new ConditionalCommand( 
                new DriveFeet(-5, false)) {
                    @Override
                    public boolean condition() {
                        return Timer.getMatchTime() <= 15;
                    }
                }
        );
	}
	
	private double getJoyY(Joystick stick) {
		if(Math.abs(stick.getY()) < joystickDeadzone) {
			return 0;
		}
		
		return -stick.getY();     
	}
	
	public double getLeftY() {
		return getJoyY(leftJoystick);
	}
	
	public double getRightY() {
		return getJoyY(rightJoystick);
	}
	
	public double getIntakeThrottle() {
        return -xboxController.getY(Hand.kRight);
    }
    
    public double getElevatorThrottle() {
        return -xboxController.getY(Hand.kLeft);
    }
    
    public double getClimberThrottle() {
        return 0;
    }
    
    public double getWheelThrottle() {
        return 0;
    }
    
    public boolean getCancelAuton() {
        return rightJoystick.getTop() && leftJoystick.getTop();
    }

    public boolean getShiftHigh() {
        return rightJoystick.getTrigger();
    }

    public boolean getHalfSpeed() {
        return leftJoystick.getTrigger();
    }
}