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

  public static enum CamMode{
    vision,
    driver;
  }

  public static class Contour{
    //Horizontal offset from -27 to 27 degrees
    public final double tx;
    //Vertical offset from -20.5 to 20.5 degrees
    public final double ty;
    //Area as a percent of total image
    public final double ta;
    //Skew of target from -90 to 0 degrees
    public final double ts;
    //Latency of the pipeline in ms
    public final double tl;

    public Contour(double tx, double ty, double ta, double ts, double tl){
      this.tx = tx;
      this.ty = ty;
      this.ta = ta;
      this.ts = ts;
      this.tl = tl;
    }
  }
  
  private static NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  public static boolean switchPipeline(Pipeline pipeline) {
    return limelightTable.getEntry("pipeline").setNumber(pipeline.id);
  }

  public static boolean setCamMode(CamMode camMode){
    if(camMode == CamMode.vision) {
      return limelightTable.getEntry("camMode").setNumber(0);
    } else {
      return limelightTable.getEntry("camMode").setNumber(1);
    }
  }

  public static Contour getBestContour() {
    if(limelightTable.getEntry("tv").getNumber(0).equals(1)) {
      return new Contour(limelightTable.getEntry("tx").getDouble(0),
                        limelightTable.getEntry("ty").getDouble(0),
                        limelightTable.getEntry("ta").getDouble(0),
                        limelightTable.getEntry("ts").getDouble(0),
                        limelightTable.getEntry("tl").getDouble(0));
    } else {
      return null;
    }
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
