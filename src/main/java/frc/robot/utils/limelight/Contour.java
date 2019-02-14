package frc.robot.utils.limelight;

public class Contour {
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