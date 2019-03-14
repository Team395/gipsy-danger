/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.AutoIntakeHatch;
import frc.robot.commands.AutoScore;
import frc.robot.commands.DriveFeet;
import frc.robot.commands.TurnToDegree;
import frc.robot.enums.ScoringPosition;
import frc.robot.enums.Side;
import frc.robot.Robot;

public class ScoreSingleShipFromCenter extends CommandGroup {
  public ScoreSingleShipFromCenter(Side scoringSide) {
    double turnInversion = (scoringSide == Side.kLeft ? 1 : -1);
    //Drive off the step
    addSequential(new DriveFeet(5));
    //Turn towards the cargo bay on the loadingStationSide
    addSequential(new TurnToDegree(turnInversion * 6.556));
    //Score
    addSequential(new AutoScore(ScoringPosition.kHatchShip));
    //Back up from scoring position
    addSequential(new DriveFeet(-1));
    //Turn to loading station
    addSequential(new TurnToDegree(turnInversion * 134.357));
    //Drive to loading station
    addSequential(new DriveFeet(14.5));
    //Turn to loading station
    addSequential(new TurnToDegree(180));
    //Intake the hatch
    addSequential(new AutoIntakeHatch());
    //Drive back from loading station
    addSequential(new DriveFeet(-6.5));
    //Turn towards field
    addSequential(new TurnToDegree(turnInversion * 0));
  }

  @Override
  protected boolean isFinished() {
    return Robot.oi.getCancelAuton() || super.isFinished();
  }
}
