package frc.robot.utils;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import java.util.*;

public class SparkMAX {
    CANSparkMax spark;
    CANPIDController pidController;
    CANEncoder encoder = null;
    ControlType controlType = ControlType.kVoltage;
    double setpoint = 0;

    public SparkMAX(int deviceID, MotorType motorType) {
        //If no value for encoder is provided, assume there is an encoder only if it's brushless
        this(deviceID, motorType, motorType == MotorType.kBrushless);
    }

    public SparkMAX(int deviceID, MotorType motorType, boolean encoderConnected) {
        //Construct the spark
        spark = new CANSparkMax(deviceID, motorType);
        
        //We need the controller for voltage control no matter what
        pidController = spark.getPIDController();

        //Only construct an encoder if it's plugged in
        if(encoderConnected) {
            encoder = spark.getEncoder();
        }
    }

    public void set(double output) {
        //Basically equivalent to duty cycle control, should be a bit more reliable
        pidController.setReference(output * 12, ControlType.kVoltage);
        //TODO: Test that this isn't affected by PID parameters
        controlType = ControlType.kVoltage;
    }

    public double get() {
        return spark.getAppliedOutput();
    }

    public ControlType getControlType() {
        return controlType;
    }

    public void setPIDSetpoint(double setpoint, int slot, ControlType controlType) {
        //Make sure this is actually PID
        assert controlType == ControlType.kPosition || controlType == ControlType.kVelocity :
            "Invalid ControlType: Not PID";
        
        //Bookkeeping. Grr that we have to do this
        this.controlType = controlType;
        this.setpoint = setpoint;

        //Actually set CANPIDController
        pidController.setReference(setpoint, controlType, slot);
    }

    public double getPIDSetpoint() {
        assert controlType == ControlType.kPosition || controlType == ControlType.kVelocity :
        "Invalid ControlType: Not PID";

        return setpoint;
    }

    public double getPIDError() {
        assert controlType == ControlType.kPosition || controlType == ControlType.kVelocity :
        "Invalid ControlType: Not PID";

        if(controlType == ControlType.kPosition) {
            return setpoint - getPosition();
        } else {
            return setpoint - getVelocity();
        }
    }

    public void setPIDSlot(double p, 
                           double i, 
                           double d, 
                           double ff, 
                           double iZone, 
                           double minOutput, 
                           double maxOutput, 
                           int slot) {
        pidController.setP(p, slot);
        pidController.setI(i, slot);
        pidController.setD(d, slot);
        pidController.setFF(ff, slot);
        pidController.setIZone(iZone, slot);
        pidController.setOutputRange(minOutput, maxOutput, slot);
    }    

    //Here if we want to get, modify, and reset using getPIDSlot()
    public void setPIDSlot(List<Double> params, int slot) {
        assert params.size() == 7 : "Wrong number of parameters";

        setPIDSlot(params.get(0),
                   params.get(1),
                   params.get(2),
                   params.get(3),
                   params.get(4),
                   params.get(5),
                   params.get(6),
                   slot);
    }

    public List<Double> getPIDSlot(int slot) {
        return Arrays.asList(pidController.getP(slot),
                             pidController.getI(slot),
                             pidController.getD(slot),
                             pidController.getFF(slot),
                             pidController.getIZone(slot),
                             pidController.getOutputMin(slot),
                             pidController.getOutputMax(slot));
    }

    public PIDTuner getTuner() {
        return null;
    }  

    public void disable() {
        setpoint = 0;
        set(0);
    }

    public double getPosition() {
        assert encoder != null : "No encoder connected";
        return encoder.getPosition();
    }

    public double getVelocity() {
        assert encoder != null : "No encoder connected";
        //TODO: Smooth it out
        return encoder.getVelocity();
    }

    public void zeroPosition() {

    }
}