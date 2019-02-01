package frc.robot.utils;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.utils.SparkMAX;

public class SpeedControllerMap {
    private Map<Integer, WPI_TalonSRX> talonMap = new HashMap<>();
    private Map<Integer, SparkMAX> sparkMap = new HashMap<>();

    public SpeedControllerMap() {
        sparkMap.put(1, new SparkMAX(1, MotorType.kBrushless));
        sparkMap.put(2, new SparkMAX(2, MotorType.kBrushless));
        sparkMap.put(3, new SparkMAX(3, MotorType.kBrushless));
        sparkMap.put(4, new SparkMAX(4, MotorType.kBrushless));

    }

    public WPI_TalonSRX getTalonByID(int id) {
        return talonMap.get(id);
    }

    public SparkMAX getSparkByID(int id) {
        return sparkMap.get(id);
    }
};