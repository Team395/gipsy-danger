package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.TimedCommand;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Robot;

/**
* Intakes the cargo from the loading station.
*/

public class IntakeCargo extends TimedCommand {
    
    static final double intakeCargoTimeout = 1;
    
    public IntakeCargo() {
        super(intakeCargoTimeout);
        requires(Robot.cargoManipulator);
    }
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.cargoManipulator.setRollerSpeed(-1);
    }
    
    // Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.cargoManipulator.setRollerSpeed(0);
    }
}
