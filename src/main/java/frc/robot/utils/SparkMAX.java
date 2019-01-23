package frc.robot.utils;

import com.revrobotics.*;
import java.util.*;

public class SparkMAX {

    public SparkMAX(int deviceID) {

    }

    public void set(double output) {

    }

    public void set(double output, ControlType controlType) {

    } 

    public double get() {
        return 0;
    }

    public ControlType getControlType() {
        return null;
    }

    public void setPIDSetpoint(double setpoint, int slot, ControlType controlType) {

    }

    public double getPIDSetpoint() {
        return 0;
    }

    public double getPIDError() {
        return 0;
    }

    public void setPIDSlot(double p, 
                           double i, 
                           double d, 
                           double ff, 
                           double iZone, 
                           double minOutput, 
                           double maxOutput, 
                           int slot) {

    }    

    public void setPIDSlot(List<Double> params, int slot) {

    }

    public List<Double> getPIDSlot(int slot) {
        return null;
    }

    public PIDTuner getTuner() {
        return null;
    }  

    public void disable() {

    }

    public double getPosition() {
        return 0;
    }

    public double getVelocity() {
        return 0;
    }

    public void zeroPosition() {

    }
}