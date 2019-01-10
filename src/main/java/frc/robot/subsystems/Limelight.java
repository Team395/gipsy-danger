/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public static enum Pipeline{
    leftTarget(0),
    rightTarget(1);

    public final int id;

    private Pipeline(int id){
      this.id = id;
    }

  }

  public static NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  public static boolean switchPipeline(Pipeline pipeline) {
    return limelightTable.getEntry("pipeline").setNumber(pipeline.id);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
