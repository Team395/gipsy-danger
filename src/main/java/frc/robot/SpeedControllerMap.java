package frc.robot;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SpeedControllerMap {
    private Map<Integer, WPI_TalonSRX> talonMap = new HashMap<>();

    public SpeedControllerMap() {

    }

    public WPI_TalonSRX getTalonByID(int id) {
        return talonMap.get(id);
    }
};