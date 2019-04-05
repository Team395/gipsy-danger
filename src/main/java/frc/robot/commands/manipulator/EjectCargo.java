package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj.command.TimedCommand;
import frc.robot.Robot;

/**
* Ejects the cargo at the end of a scoring sequence.
*/
public class EjectCargo extends TimedCommand {
    
    private static final double cargoEjectTime = 1;
    
    public EjectCargo() {
        super(cargoEjectTime);
        requires(Robot.cargoManipulator);
    }
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        Robot.cargoManipulator.setRollerSpeed(1);
    }
    
    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    }
    
    // Called once after timeout
    @Override
    protected void end() {
        Robot.cargoManipulator.setRollerSpeed(0);
    }
    
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        end();
    }
}
