package frc.robot.utils.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
	static Contour lastContourPulled;
	public static double successfulDriveArea = 0.12;

	public static enum Pipeline{
		kLeftTarget(0),
		kRightTarget(1),
		kDriverControl(2);
		
		public final int id;
		
		private Pipeline(int id){
			this.id = id;
		}
		
	}
	
	public static enum CamMode{
		kVision(0),
		kDriver(1);
		
		public final int id;
		
		private CamMode(int id){
			this.id = id;
		}
	}
	
	private static NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
	
	public static boolean switchPipeline(Pipeline pipeline) {
		return limelightTable.getEntry("pipeline").setNumber(pipeline.id);
	}
	
	public static boolean switchPipeline(int pipeline) {
		return limelightTable.getEntry("pipeline").setNumber(pipeline);
	}
	
	public static boolean setCamMode(CamMode camMode){
		return limelightTable.getEntry("camMode").setNumber(camMode.id);
	}
	
	public static Contour getBestContour() {
		//Check if a valid contour is found 
		if(limelightTable.getEntry("tv").getNumber(0).equals(1.0)) {
			lastContourPulled = new Contour(limelightTable.getEntry("tx").getDouble(0),
			limelightTable.getEntry("ty").getDouble(0),
			limelightTable.getEntry("ta").getDouble(0),
			limelightTable.getEntry("ts").getDouble(0),
			limelightTable.getEntry("tl").getDouble(0));
			return lastContourPulled;
		} else {
			lastContourPulled = null;
		}
		return lastContourPulled;
	}
	
	public static Contour getLastContourPulled() {
		return lastContourPulled;
	}
	public static Corners getContourCorners() {
		return new Corners(limelightTable.getEntry("tcornx").getDoubleArray((double[]) null),
		limelightTable.getEntry("tcorny").getDoubleArray((double[]) null));
	}

	public static void clearLastSeenContour() {
		lastContourPulled = null;
	}
}
