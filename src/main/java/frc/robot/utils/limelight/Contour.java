package frc.robot.utils.limelight;

public class Contour {
    //Horizontal offset from -27 to 27 degrees
    public final double xOffset;
    //Vertical offset from -20.5 to 20.5 degrees
    public final double yOffset;
    //Area as a percent of total image
    public final double percentArea;
    //Skew of target from -90 to 0 degrees
    public final double skewAngle;
    //Latency of the pipeline in ms
    public final double pipelineLatency;

    public Contour(double xOffset, double yOffset, double percentArea, double skewAngle, double pipelineLatency){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.percentArea = percentArea;
        this.skewAngle = skewAngle;
        this.pipelineLatency = pipelineLatency;
    }
}