package frc.robot.utils.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Limelight {
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
    vision(0),
    driver(1);

    public final int id;

    private CamMode(int id){
      this.id = id;
    }
  }
  
  private static NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  public static boolean switchPipeline(Pipeline pipeline) {
    return limelightTable.getEntry("pipeline").setNumber(pipeline.id);
  }

  public static boolean setCamMode(CamMode camMode){
      return limelightTable.getEntry("camMode").setNumber(camMode.id);
  }

  public static Contour getBestContour() {
    //Check if a valid contour is found 
    if(limelightTable.getEntry("tv").getNumber(0).equals(1.0)) {
      return new Contour(limelightTable.getEntry("tx").getDouble(0),
                         limelightTable.getEntry("ty").getDouble(0),
                         limelightTable.getEntry("ta").getDouble(0),
                         limelightTable.getEntry("ts").getDouble(0),
                         limelightTable.getEntry("tl").getDouble(0));
    } else {
      return null;
    }
  }

  public static Corners getContourCorners() {
    return new Corners(limelightTable.getEntry("tcornx").getDoubleArray((double[]) null),
                       limelightTable.getEntry("tcorny").getDoubleArray((double[]) null));
  }
}
