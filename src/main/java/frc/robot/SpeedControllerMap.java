package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SpeedControllerMap {
    private Map<Integer, WPI_TalonSRX> talonMap = new HashMap<>();

    public SpeedControllerMap() {
        talonMap.put(1, new WPI_TalonSRX(1));
        talonMap.put(2, new WPI_TalonSRX(2));
        talonMap.put(3, new WPI_TalonSRX(3));
        talonMap.put(4, new WPI_TalonSRX(4));
        talonMap.put(5, new WPI_TalonSRX(5));
    }

    public WPI_TalonSRX getTalonByID(int id) {
        return talonMap.get(id);
    }
};