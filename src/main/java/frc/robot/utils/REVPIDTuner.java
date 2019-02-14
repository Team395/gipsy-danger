package frc.robot.utils;

import com.revrobotics.CANPIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class REVPIDTuner implements PIDTuner {

    String name;
    CANPIDController controller;
    Preferences preferences = Preferences.getInstance();
    public REVPIDTuner(String name, CANPIDController controller) {
        this.name = name;
        this.controller = controller;
        initializePreferences();
    }

    @Override
    public void initializePreferences() {
        for(int i = 0; i < 4; i++) {
            preferences.putDouble(name + "_P" + i,  preferences.getDouble(name + "_P" + i, 0));
            preferences.putDouble(name + "_I" + i,  preferences.getDouble(name + "_I" + i, 0));
            preferences.putDouble(name + "_D" + i,  preferences.getDouble(name + "_D" + i, 0));
            preferences.putDouble(name + "_FF" + i, preferences.getDouble(name + "_FF" + i, 0));
        }
    }

    @Override 
    public void updateGains() {
        for(int i = 0; i < 4; i++) {
            if(controller.getP(i) != preferences.getDouble(name + "_P" + i, 0)) 
                controller.setP(preferences.getDouble(name + "_P" + i, 0), i);
            
            if(controller.getI(i) != preferences.getDouble(name + "_I" + i, 0)) 
                controller.setI(preferences.getDouble(name + "_I" + i, 0), i);

            if(controller.getD(i) != preferences.getDouble(name + "_D" + i, 0)) 
                controller.setD(preferences.getDouble(name + "_D" + i, 0), i);

            if(controller.getFF(i) != preferences.getDouble(name + "_FF" + i, 0)) 
                controller.setFF(preferences.getDouble(name + "_FF" + i, 0), i);
        }
    }
}
