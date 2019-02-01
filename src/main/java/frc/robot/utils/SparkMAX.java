package frc.robot.utils;

import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Timer;

import java.util.*;

public class SparkMAX {
    //Integral Members of the Controller
    CANSparkMax spark;
    CANPIDController pidController;
    CANEncoder encoder = null;

    //Bookkeeping variables for PID
    ControlType controlType = ControlType.kVoltage;
    double setpoint = 0;
    double zeroPosition = 0;
    
    //The expiration time of samples for all sampling based methods
    final static double expirationTime = 0.1;

    //Velocity Smoothing
    final static int velocitySampleSize = 5;
    double[] velocitySamples = new double[velocitySampleSize];
    double lastVelocitySampleTime = 0;
    int sampleIndex = 0;

    //Instance of PIDTuner to return
    PIDTuner tuner;
    
    //onTarget bookkeeping
    Double acceptableError;
    Double onTargetTime;
    double lastTargetSampleTime = Timer.getFPGATimestamp();
    double firstTargetSampleTime = Timer.getFPGATimestamp();

    /*
     *  Constructs a new SparkMAX, assuming an encoder only if the motor type 
     *  is brushless.
     */
    public SparkMAX(int deviceID, MotorType motorType) {
        this(deviceID, motorType, motorType == MotorType.kBrushless);
    }

    /*
     * Overload of previous constructor to support a brushed motor with an 
     * encoder.
     */
    public SparkMAX(int deviceID, MotorType motorType, boolean encoderConnected) {
        //Construct the spark
        spark = new CANSparkMax(deviceID, motorType);
        
        //We need the controller for voltage control no matter what
        pidController = spark.getPIDController();

        //Only construct an encoder if it's plugged in
        if(encoderConnected) {
            encoder = spark.getEncoder();
        }

        tuner = new REVPIDTuner("SPARK" + spark.getDeviceId(), pidController);
    }

    /*
     *  Implements a set method similar to the WPILIB interface that truncates 
     *  input to [-1,1]. Controls by scaling closed loop voltage control from 
     *  0 to 12 rather than 0 to VCC.
     */
    public void set(double output) {
        output = Math.min(output, 1);
        output = Math.max(output, -1);

        pidController.setReference(output * 12, ControlType.kVoltage);
        //TODO: Test that this isn't affected by PID parameters
        controlType = ControlType.kVoltage;
    }

    /*
     *  Implements a get method similar to the WPILIB interface, to get the
     *  current applied output from -1 to 1. Is not necessarily equivalent to
     *  the set() value.
     */

    public double get() {
        return spark.getAppliedOutput();
    }

    /*
     *  Returns the last used controlType
     */

    public ControlType getControlType() {
        return controlType;
    }

    /*
     *  Sets the setpoint for the internal PID controller and enables it.
     */

    public void setPIDSetpoint(double setpoint, int slot, ControlType controlType) {
        //Make sure this is actually PID
        if(controlType != ControlType.kPosition && controlType != ControlType.kVelocity)
            throw new IllegalStateException("Invalid ControlType: Not PID");
        
        this.controlType = controlType;
        this.setpoint = setpoint;

        pidController.setReference(setpoint, controlType, slot);
    }

    /*
     *  Returns the last setpoint given to the PIDController.
     */ 

    private double getPIDSetpoint() {
        if(controlType != ControlType.kPosition && controlType != ControlType.kVelocity)
            throw new IllegalStateException("Invalid ControlType: Not PID");

        return setpoint;
    }

    /*
     *  Returns the current distance from the setpoint.
     */

    private double getPIDError() {
        if(controlType != ControlType.kPosition && controlType != ControlType.kVelocity)
            throw new IllegalStateException("Invalid ControlType: Not PID");

        if(controlType == ControlType.kPosition) {
            return setpoint - getPosition();
        } else {
            return setpoint - getVelocity();
        }
    }

    /*
     *  Sets the amount of acceptable error in native units for the controller
     *  to be on target.
     */

    public void setAcceptableError(double acceptableError) {
        if(acceptableError < 0) {
            throw new IllegalArgumentException("acceptableError cannot be negative");
        }
        this.acceptableError = acceptableError;
    }

    /*
     *  Sets the amount of time error needs to be acceptable before controller
     *  is on target.
     */

    public void setOnTargetTime(double onTargetTime) {
        if(onTargetTime < 0) {
            throw new IllegalArgumentException("onTargetTime cannot be negative");
        }
        this.onTargetTime = onTargetTime;
    }

    /*
     *  Returns true if the controller has been within the bounds of the
     *  acceptable error for onTargetTime AND if this method has been called
     *  at least once every 0.1 seconds. Intended to be checked continuously.
     */

    public boolean onTarget() {
        if(onTargetTime == null) {
            throw new IllegalStateException("onTargetTime not set");
        } else if(acceptableError == null) {
            throw new IllegalStateException("acceptableError not set");
        }

        if(!withinAcceptableError() || Timer.getFPGATimestamp() - lastTargetSampleTime > expirationTime ) {
            firstTargetSampleTime = Timer.getFPGATimestamp();
        }

        lastTargetSampleTime = Timer.getFPGATimestamp();

        return lastTargetSampleTime - firstTargetSampleTime >= onTargetTime;
    }

    /*
     *  Returns true if the controller is within the bounds of the acceptable
     *  error as set above.
     */

    public boolean withinAcceptableError() {
        if(acceptableError == null) {
            throw new IllegalStateException("acceptableError not set");
        } 

        return Math.abs(getPIDError()) <= acceptableError;
    }

    /*
     *  Sets all PID parameters for a slot one by one. To be used infrequently.
     */

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

    /*
     *  Overload of previous method to take a List<Double> instead.
     */

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

    /*
     *  Returns the parameters in a given slot.
     */

    public List<Double> getPIDSlot(int slot) {
        return Arrays.asList(pidController.getP(slot),
                             pidController.getI(slot),
                             pidController.getD(slot),
                             pidController.getFF(slot),
                             pidController.getIZone(slot),
                             pidController.getOutputMin(slot),
                             pidController.getOutputMax(slot));
    }

    /*
     *  Returns an object which can be used to tune the CANPIDController from
     *  the Preferences utility.
     */

    public PIDTuner getTuner(String name) {
        return tuner;
    }  

    /*
     *  Turns off the controller and resets the setpoint to 0.
     */

    public void disable() {
        setpoint = 0;
        set(0);
    }

    /*
     *  Returns the current zeroed encoder position.
     */

    public double getPosition() {
        assert encoder != null : "No encoder connected";
        return encoder.getPosition() - zeroPosition;
    }

    /*
     *  Returns the current smoothed velocity.
     */

    public double getVelocity() {
        assert encoder != null : "No encoder connected";

        //Refresh sample array if old
        if(Timer.getFPGATimestamp() - lastVelocitySampleTime > 0.1)
            velocitySamples = new double[velocitySampleSize];

        //Store values and increment index 
        velocitySamples[sampleIndex++] = encoder.getVelocity();
        lastVelocitySampleTime = Timer.getFPGATimestamp();
        
        //Make sure index doesn't overrun end
        if(sampleIndex == velocitySampleSize)
            sampleIndex = 0;

        //Sum up and divide by number of nonzero samples
        double sum = 0;
        int validSamples = 0;
        
        //Add up samples and count nonzero
        for(double sample : velocitySamples) {
            sum += sample;
            if(sample != 0.0)
                validSamples++;
        }
        
        //Protect against 0/0
        if(validSamples == 0)
            validSamples++;

        //Return average
        return sum/validSamples;
    }

    /*
     *  Zeroes the position via a member variable.
     */

    public void zeroPosition() {
        if(encoder == null)
            throw new NoEncoderException();

        this.zeroPosition = encoder.getPosition();
    }

    public void follow(SparkMAX leader) {
        spark.follow(leader.spark);
    }

    public void setInverted(boolean isInverted) {
        spark.setInverted(isInverted);
    }

}