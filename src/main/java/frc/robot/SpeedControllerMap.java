package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import frc.robot.utils.SparkMAX;

public class SpeedControllerMap {
    private Map<Integer, WPI_TalonSRX> talonMap = new HashMap<>();
    private Map<Integer, WPI_VictorSPX> victorMap = new HashMap<>();
    private Map<Integer, SparkMAX> sparkMap = new HashMap<>();

    public SpeedControllerMap() {
        talonMap.put(1, new WPI_TalonSRX(1));
        talonMap.put(2, new WPI_TalonSRX(2));
        talonMap.put(3, new WPI_TalonSRX(3));
        talonMap.put(4, new WPI_TalonSRX(4));

        talonMap.put(5, new WPI_TalonSRX(5));
        // talonMap.put(6, new WPI_TalonSRX(6));
        // talonMap.put(7, new WPI_TalonSRX(7));
        // talonMap.put(8, new WPI_TalonSRX(8));
        // talonMap.put(9, new WPI_TalonSRX(9));
        // talonMap.put(10, new WPI_TalonSRX(10));
        // talonMap.put(11, new WPI_TalonSRX(11));
        
        victorMap.put(0, new WPI_VictorSPX(12));

        // sparkMap.put(1, new SparkMAX(1, MotorType.kBrushless));
        // sparkMap.put(2, new SparkMAX(2, MotorType.kBrushless));
        // sparkMap.put(3, new SparkMAX(3, MotorType.kBrushless));
        // sparkMap.put(4, new SparkMAX(4, MotorType.kBrushless));
    }

    public WPI_TalonSRX getTalonByID(int id) {
        return talonMap.get(id);
    }

    public WPI_VictorSPX getVictorByID(int id) {
        return victorMap.get(id);
    }
    
    public SparkMAX getSparkByID(int id) {
        return sparkMap.get(id);
    }
};