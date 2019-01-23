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