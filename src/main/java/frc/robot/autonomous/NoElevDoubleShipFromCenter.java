/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveFeet;
import frc.robot.commands.NoElevAutoIntakeHatch;
import frc.robot.commands.NoElevAutoScore;
import frc.robot.commands.TurnToDegree;
import frc.robot.enums.Side;

public class NoElevDoubleShipFromCenter extends CommandGroup {
  public NoElevDoubleShipFromCenter(Side loadingStationSide) {
      double turnInversion = (loadingStationSide == Side.kLeft ? 1 : -1);
      //Drive off the step
      addSequential(new DriveFeet(5));
      //Turn towards the cargo bay on the loadingStationSide
      addSequential(new TurnToDegree(turnInversion * 6.556));
      //Score
      addSequential(new NoElevAutoScore());
      //Back up from scoring position
      addSequential(new DriveFeet(-1));
      //Turn to loading station
      addSequential(new TurnToDegree(turnInversion * 134.357));
      //Drive to loading station
      addSequential(new DriveFeet(14.5));
      //Turn to loading station
      addSequential(new TurnToDegree(180));
      //Intake the hatch
      addSequential(new NoElevAutoIntakeHatch());
      //Drive back from loading station
      addSequential(new DriveFeet(-6.5));
      //Turn towards cargo bay
      addSequential(new TurnToDegree(turnInversion * -115.236));
      //Drive to cargo bay
      addSequential(new DriveFeet(13));
      //Turn to cargo bay
      addSequential(new TurnToDegree(turnInversion * -5.035));
      //Score
      addSequential(new NoElevAutoScore());
      //Back up from scoring position
      addSequential(new DriveFeet(-1));
      //Turn to loading station
      addSequential(new TurnToDegree(turnInversion * 129.771));
      //Drive to loading station
      addSequential(new DriveFeet(15.847));
      //Turn to loading station
      addSequential(new TurnToDegree(turnInversion * 180));
    }
  
    // @Override
    // protected boolean isFinished() {
    //   // return Robot.oi.getCancelAuton() || super.isFinished();
    // }
}
